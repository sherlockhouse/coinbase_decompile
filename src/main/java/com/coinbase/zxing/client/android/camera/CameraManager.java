package com.coinbase.zxing.client.android.camera;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.coinbase.zxing.client.android.camera.open.OpenCameraInterface;
import com.coinbase.zxing.client.android.camera.open.OpenCameraManager;
import com.google.zxing.PlanarYUVLuminanceSource;
import java.io.IOException;

public final class CameraManager {
    private static final int MAX_FRAME_HEIGHT = 600;
    private static final int MAX_FRAME_WIDTH = 800;
    private static final int MIN_FRAME_HEIGHT = 0;
    private static final int MIN_FRAME_WIDTH = 0;
    private static final String TAG = CameraManager.class.getSimpleName();
    private AutoFocusManager autoFocusManager;
    private Camera camera;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private Rect framingRect;
    private Rect framingRectInPreview;
    private Point framingViewSize;
    private boolean initialized;
    private final PreviewCallback previewCallback = new PreviewCallback(this.configManager);
    private boolean previewing;
    private int requestedFramingRectHeight;
    private int requestedFramingRectWidth;

    public CameraManager(Context context) {
        this.context = context;
        this.configManager = new CameraConfigurationManager(context);
    }

    public static void setCameraDisplayOrientation(Activity activity, int cameraId, Camera camera) {
        int result;
        CameraInfo info = new CameraInfo();
        Camera.getCameraInfo(cameraId, info);
        int degrees = 0;
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case 0:
                degrees = 0;
                break;
            case 1:
                degrees = 90;
                break;
            case 2:
                degrees = 180;
                break;
            case 3:
                degrees = 270;
                break;
        }
        if (info.facing == 1) {
            result = (360 - ((info.orientation + degrees) % 360)) % 360;
        } else {
            result = ((info.orientation - degrees) + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    public synchronized void openDriver(Activity activity, SurfaceHolder holder) throws IOException {
        Camera theCamera = this.camera;
        if (theCamera == null) {
            theCamera = ((OpenCameraInterface) new OpenCameraManager().build()).open();
            setCameraDisplayOrientation(activity, 0, theCamera);
            if (theCamera == null) {
                throw new IOException();
            }
            this.camera = theCamera;
        }
        theCamera.setPreviewDisplay(holder);
        if (!this.initialized) {
            this.initialized = true;
            this.configManager.initFromCameraParameters(theCamera);
            if (this.requestedFramingRectWidth > 0 && this.requestedFramingRectHeight > 0) {
                setManualFramingRect(this.requestedFramingRectWidth, this.requestedFramingRectHeight);
                this.requestedFramingRectWidth = 0;
                this.requestedFramingRectHeight = 0;
            }
        }
        Parameters parameters = theCamera.getParameters();
        String parametersFlattened = parameters == null ? null : parameters.flatten();
        try {
            this.configManager.setDesiredCameraParameters(theCamera, false);
        } catch (RuntimeException e) {
            Log.w(TAG, "Camera rejected parameters. Setting only minimal safe-mode parameters");
            Log.i(TAG, "Resetting to saved camera params: " + parametersFlattened);
            if (parametersFlattened != null) {
                parameters = theCamera.getParameters();
                parameters.unflatten(parametersFlattened);
                try {
                    theCamera.setParameters(parameters);
                    this.configManager.setDesiredCameraParameters(theCamera, true);
                } catch (RuntimeException e2) {
                    Log.w(TAG, "Camera rejected even safe-mode parameters! No configuration");
                }
            }
        }
    }

    public synchronized boolean isOpen() {
        return this.camera != null;
    }

    public synchronized void closeDriver() {
        if (this.camera != null) {
            this.camera.release();
            this.camera = null;
            this.framingRect = null;
            this.framingRectInPreview = null;
        }
    }

    public synchronized void startPreview() {
        Camera theCamera = this.camera;
        if (!(theCamera == null || this.previewing)) {
            theCamera.startPreview();
            this.previewing = true;
            this.autoFocusManager = new AutoFocusManager(this.context, this.camera);
        }
    }

    public synchronized void stopPreview() {
        if (this.autoFocusManager != null) {
            this.autoFocusManager.stop();
            this.autoFocusManager = null;
        }
        if (this.camera != null && this.previewing) {
            this.camera.stopPreview();
            this.previewCallback.setHandler(null, 0);
            this.previewing = false;
        }
    }

    public synchronized void setTorch(boolean newSetting) {
        if (this.camera != null) {
            if (this.autoFocusManager != null) {
                this.autoFocusManager.stop();
            }
            this.configManager.setTorch(this.camera, newSetting);
            if (this.autoFocusManager != null) {
                this.autoFocusManager.start();
            }
        }
    }

    public synchronized void requestPreviewFrame(Handler handler, int message) {
        Camera theCamera = this.camera;
        if (theCamera != null && this.previewing) {
            this.previewCallback.setHandler(handler, message);
            theCamera.setOneShotPreviewCallback(this.previewCallback);
        }
    }

    public synchronized Rect getFramingRect() {
        Rect rect = null;
        synchronized (this) {
            if (this.framingRect == null) {
                if (this.camera != null) {
                    Point screenResolution = this.configManager.getScreenResolution();
                    if (screenResolution != null) {
                        int width;
                        if (this.context.getResources().getConfiguration().orientation != 1) {
                            width = (screenResolution.x * 3) / 4;
                        } else {
                            width = (screenResolution.x * 9) / 10;
                        }
                        if (width < 0) {
                            width = 0;
                        } else if (width > MAX_FRAME_WIDTH) {
                            width = MAX_FRAME_WIDTH;
                        }
                        int height = width;
                        if (height < 0) {
                            height = 0;
                        } else if (height > MAX_FRAME_HEIGHT) {
                            height = MAX_FRAME_HEIGHT;
                        }
                        if (height < width) {
                            width = height;
                        }
                        int leftOffset = (screenResolution.x - width) / 2;
                        int topOffset = (screenResolution.y - height) / 2;
                        this.framingRect = new Rect(leftOffset, topOffset, leftOffset + width, topOffset + height);
                        Log.d(TAG, "Calculated framing rect: " + this.framingRect);
                    }
                }
            }
            rect = this.framingRect;
        }
        return rect;
    }

    public synchronized Rect getFramingRectInPreview() {
        Rect rect = null;
        synchronized (this) {
            if (this.framingRectInPreview == null) {
                Rect framingRect = getFramingRect();
                if (framingRect != null) {
                    Rect rect2 = new Rect(framingRect);
                    Point cameraResolution = this.configManager.getCameraResolution();
                    Point screenResolution = this.configManager.getScreenResolution();
                    if (!(cameraResolution == null || screenResolution == null)) {
                        if (this.context.getResources().getConfiguration().orientation != 1) {
                            rect2.left = (rect2.left * cameraResolution.x) / screenResolution.x;
                            rect2.right = (rect2.right * cameraResolution.x) / screenResolution.x;
                            rect2.top = (rect2.top * cameraResolution.y) / screenResolution.y;
                            rect2.bottom = (rect2.bottom * cameraResolution.y) / screenResolution.y;
                        } else if (this.framingViewSize != null) {
                            rect2.left = (rect2.left * cameraResolution.y) / this.framingViewSize.x;
                            rect2.right = (rect2.right * cameraResolution.y) / this.framingViewSize.x;
                            rect2.top = (rect2.top * cameraResolution.x) / this.framingViewSize.y;
                            rect2.bottom = (rect2.bottom * cameraResolution.x) / this.framingViewSize.y;
                        }
                        this.framingRectInPreview = rect2;
                    }
                }
            }
            rect = this.framingRectInPreview;
        }
        return rect;
    }

    public void setFramingViewSize(Point framingViewSize) {
        this.framingViewSize = framingViewSize;
    }

    public synchronized void setManualFramingRect(int width, int height) {
        if (this.initialized) {
            Point screenResolution = this.configManager.getScreenResolution();
            if (width > screenResolution.x) {
                width = screenResolution.x;
            }
            if (height > screenResolution.y) {
                height = screenResolution.y;
            }
            int leftOffset = (screenResolution.x - width) / 2;
            int topOffset = (screenResolution.y - height) / 2;
            this.framingRect = new Rect(leftOffset, topOffset, leftOffset + width, topOffset + height);
            Log.d(TAG, "Calculated manual framing rect: " + this.framingRect);
            this.framingRectInPreview = null;
        } else {
            this.requestedFramingRectWidth = width;
            this.requestedFramingRectHeight = height;
        }
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] data, int width, int height) {
        Rect rect = getFramingRectInPreview();
        if (rect == null) {
            return null;
        }
        int dataWidth = width;
        if (rect.left + rect.width() > dataWidth) {
            dataWidth = rect.left + rect.width();
        }
        int dataHeight = height;
        if (rect.top + rect.height() > dataHeight) {
            dataHeight = rect.top + rect.height();
        }
        return new PlanarYUVLuminanceSource(data, dataWidth, dataHeight, rect.left, rect.top, rect.width(), rect.height(), false);
    }
}
