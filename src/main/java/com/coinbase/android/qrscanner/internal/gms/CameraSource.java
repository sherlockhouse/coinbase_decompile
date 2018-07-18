package com.coinbase.android.qrscanner.internal.gms;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import java.io.IOException;
import java.lang.Thread.State;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CameraSource {
    private static final float ASPECT_RATIO_TOLERANCE = 0.01f;
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_BACK = 0;
    @SuppressLint({"InlinedApi"})
    public static final int CAMERA_FACING_FRONT = 1;
    private static final int DUMMY_TEXTURE_NAME = 100;
    private static final String TAG = "OpenCameraSource";
    private Map<byte[], ByteBuffer> mBytesToByteBuffer;
    private Camera mCamera;
    private final Object mCameraLock;
    private Context mContext;
    private SurfaceTexture mDummySurfaceTexture;
    private SurfaceView mDummySurfaceView;
    private int mFacing;
    private String mFlashMode;
    private String mFocusMode;
    private FrameProcessingRunnable mFrameProcessor;
    private Size mPreviewSize;
    private Thread mProcessingThread;
    private float mRequestedFps;
    private int mRequestedPreviewHeight;
    private int mRequestedPreviewWidth;
    private int mRotation;

    public interface AutoFocusCallback {
        void onAutoFocus(boolean z);
    }

    public interface AutoFocusMoveCallback {
        void onAutoFocusMoving(boolean z);
    }

    public static class Builder {
        private CameraSource mCameraSource = new CameraSource();
        private final Detector<?> mDetector;

        public Builder(Context context, Detector<?> detector) {
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            } else if (detector == null) {
                throw new IllegalArgumentException("No detector supplied.");
            } else {
                this.mDetector = detector;
                this.mCameraSource.mContext = context;
            }
        }

        public Builder setRequestedFps(float fps) {
            if (fps <= 0.0f) {
                throw new IllegalArgumentException("Invalid fps: " + fps);
            }
            this.mCameraSource.mRequestedFps = fps;
            return this;
        }

        public Builder setFocusMode(String mode) {
            this.mCameraSource.mFocusMode = mode;
            return this;
        }

        public Builder setFlashMode(String mode) {
            this.mCameraSource.mFlashMode = mode;
            return this;
        }

        public Builder setRequestedPreviewSize(int width, int height) {
            if (width <= 0 || width > 1000000 || height <= 0 || height > 1000000) {
                throw new IllegalArgumentException("Invalid preview size: " + width + "x" + height);
            }
            this.mCameraSource.mRequestedPreviewWidth = width;
            this.mCameraSource.mRequestedPreviewHeight = height;
            return this;
        }

        public Builder setFacing(int facing) {
            if (facing == 0 || facing == 1) {
                this.mCameraSource.mFacing = facing;
                return this;
            }
            throw new IllegalArgumentException("Invalid camera: " + facing);
        }

        public CameraSource build() {
            CameraSource cameraSource = this.mCameraSource;
            CameraSource cameraSource2 = this.mCameraSource;
            cameraSource2.getClass();
            cameraSource.mFrameProcessor = new FrameProcessingRunnable(this.mDetector);
            return this.mCameraSource;
        }
    }

    private class CameraAutoFocusCallback implements android.hardware.Camera.AutoFocusCallback {
        private AutoFocusCallback mDelegate;

        private CameraAutoFocusCallback() {
        }

        public void onAutoFocus(boolean success, Camera camera) {
            if (this.mDelegate != null) {
                this.mDelegate.onAutoFocus(success);
            }
        }
    }

    private class CameraAutoFocusMoveCallback implements android.hardware.Camera.AutoFocusMoveCallback {
        private AutoFocusMoveCallback mDelegate;

        private CameraAutoFocusMoveCallback() {
        }

        public void onAutoFocusMoving(boolean start, Camera camera) {
            if (this.mDelegate != null) {
                this.mDelegate.onAutoFocusMoving(start);
            }
        }
    }

    private class CameraPreviewCallback implements PreviewCallback {
        private CameraPreviewCallback() {
        }

        public void onPreviewFrame(byte[] data, Camera camera) {
            CameraSource.this.mFrameProcessor.setNextFrame(data, camera);
        }
    }

    private class FrameProcessingRunnable implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = (!CameraSource.class.desiredAssertionStatus());
        private boolean mActive = true;
        private Detector<?> mDetector;
        private final Object mLock = new Object();
        private ByteBuffer mPendingFrameData;
        private int mPendingFrameId = 0;
        private long mPendingTimeMillis;
        private long mStartTimeMillis = SystemClock.elapsedRealtime();

        FrameProcessingRunnable(Detector<?> detector) {
            this.mDetector = detector;
        }

        @SuppressLint({"Assert"})
        void release() {
            if ($assertionsDisabled || CameraSource.this.mProcessingThread.getState() == State.TERMINATED) {
                this.mDetector.release();
                this.mDetector = null;
                return;
            }
            throw new AssertionError();
        }

        void setActive(boolean active) {
            synchronized (this.mLock) {
                this.mActive = active;
                this.mLock.notifyAll();
            }
        }

        void setNextFrame(byte[] data, Camera camera) {
            synchronized (this.mLock) {
                if (this.mPendingFrameData != null) {
                    camera.addCallbackBuffer(this.mPendingFrameData.array());
                    this.mPendingFrameData = null;
                }
                if (CameraSource.this.mBytesToByteBuffer.containsKey(data)) {
                    this.mPendingTimeMillis = SystemClock.elapsedRealtime() - this.mStartTimeMillis;
                    this.mPendingFrameId++;
                    this.mPendingFrameData = (ByteBuffer) CameraSource.this.mBytesToByteBuffer.get(data);
                    this.mLock.notifyAll();
                    return;
                }
                Log.d(CameraSource.TAG, "Skipping frame.  Could not find ByteBuffer associated with the image data from the camera.");
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            while (true) {
                synchronized (this.mLock) {
                    while (this.mActive && this.mPendingFrameData == null) {
                        try {
                            this.mLock.wait();
                        } catch (InterruptedException e) {
                            Log.d(CameraSource.TAG, "Frame processing loop terminated.", e);
                            return;
                        }
                    }
                    if (this.mActive) {
                        Frame outputFrame = new com.google.android.gms.vision.Frame.Builder().setImageData(this.mPendingFrameData, CameraSource.this.mPreviewSize.getWidth(), CameraSource.this.mPreviewSize.getHeight(), 17).setId(this.mPendingFrameId).setTimestampMillis(this.mPendingTimeMillis).setRotation(CameraSource.this.mRotation).build();
                        ByteBuffer data = this.mPendingFrameData;
                        this.mPendingFrameData = null;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    private class PictureDoneCallback implements android.hardware.Camera.PictureCallback {
        private PictureCallback mDelegate;

        private PictureDoneCallback() {
        }

        public void onPictureTaken(byte[] data, Camera camera) {
            if (this.mDelegate != null) {
                this.mDelegate.onPictureTaken(data);
            }
            synchronized (CameraSource.this.mCameraLock) {
                if (CameraSource.this.mCamera != null) {
                    CameraSource.this.mCamera.startPreview();
                }
            }
        }
    }

    private class PictureStartCallback implements android.hardware.Camera.ShutterCallback {
        private ShutterCallback mDelegate;

        private PictureStartCallback() {
        }

        public void onShutter() {
            if (this.mDelegate != null) {
                this.mDelegate.onShutter();
            }
        }
    }

    public interface ShutterCallback {
        void onShutter();
    }

    private static class SizePair {
        private Size mPicture;
        private Size mPreview;

        public SizePair(Camera.Size previewSize, Camera.Size pictureSize) {
            this.mPreview = new Size(previewSize.width, previewSize.height);
            if (pictureSize != null) {
                this.mPicture = new Size(pictureSize.width, pictureSize.height);
            }
        }

        public Size previewSize() {
            return this.mPreview;
        }

        public Size pictureSize() {
            return this.mPicture;
        }
    }

    public void release() {
        synchronized (this.mCameraLock) {
            stop();
            this.mFrameProcessor.release();
        }
    }

    public CameraSource start() throws IOException {
        synchronized (this.mCameraLock) {
            if (this.mCamera != null) {
            } else {
                this.mCamera = createCamera();
                this.mDummySurfaceTexture = new SurfaceTexture(100);
                this.mCamera.setPreviewTexture(this.mDummySurfaceTexture);
                this.mCamera.startPreview();
                this.mProcessingThread = new Thread(this.mFrameProcessor);
                this.mFrameProcessor.setActive(true);
                this.mProcessingThread.start();
            }
        }
        return this;
    }

    public CameraSource start(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this.mCameraLock) {
            if (this.mCamera != null) {
            } else {
                this.mCamera = createCamera();
                this.mCamera.setPreviewDisplay(surfaceHolder);
                this.mCamera.startPreview();
                this.mProcessingThread = new Thread(this.mFrameProcessor);
                this.mFrameProcessor.setActive(true);
                this.mProcessingThread.start();
            }
        }
        return this;
    }

    public void stop() {
        synchronized (this.mCameraLock) {
            this.mFrameProcessor.setActive(false);
            if (this.mProcessingThread != null) {
                try {
                    this.mProcessingThread.join();
                } catch (InterruptedException e) {
                    Log.d(TAG, "Frame processing thread interrupted on release.");
                }
                this.mProcessingThread = null;
            }
            this.mBytesToByteBuffer.clear();
            if (this.mCamera != null) {
                this.mCamera.stopPreview();
                this.mCamera.setPreviewCallbackWithBuffer(null);
                try {
                    this.mCamera.setPreviewTexture(null);
                } catch (Exception e2) {
                    Log.e(TAG, "Failed to clear camera preview: " + e2);
                }
                this.mCamera.release();
                this.mCamera = null;
            }
        }
    }

    public Size getPreviewSize() {
        return this.mPreviewSize;
    }

    public int getCameraFacing() {
        return this.mFacing;
    }

    public int doZoom(float scale) {
        int i;
        synchronized (this.mCameraLock) {
            if (this.mCamera == null) {
                i = 0;
            } else {
                i = 0;
                Parameters parameters = this.mCamera.getParameters();
                if (parameters.isZoomSupported()) {
                    float newZoom;
                    int maxZoom = parameters.getMaxZoom();
                    i = parameters.getZoom() + 1;
                    if (scale > 1.0f) {
                        newZoom = ((float) i) + (((float) (maxZoom / 10)) * scale);
                    } else {
                        newZoom = ((float) i) * scale;
                    }
                    i = Math.round(newZoom) - 1;
                    if (i < 0) {
                        i = 0;
                    } else if (i > maxZoom) {
                        i = maxZoom;
                    }
                    parameters.setZoom(i);
                    this.mCamera.setParameters(parameters);
                } else {
                    Log.w(TAG, "Zoom is not supported on this device");
                }
            }
        }
        return i;
    }

    public void takePicture(ShutterCallback shutter, PictureCallback jpeg) {
        synchronized (this.mCameraLock) {
            if (this.mCamera != null) {
                PictureStartCallback startCallback = new PictureStartCallback();
                startCallback.mDelegate = shutter;
                PictureDoneCallback doneCallback = new PictureDoneCallback();
                doneCallback.mDelegate = jpeg;
                this.mCamera.takePicture(startCallback, null, null, doneCallback);
            }
        }
    }

    public String getFocusMode() {
        return this.mFocusMode;
    }

    public boolean setFocusMode(String mode) {
        boolean z;
        synchronized (this.mCameraLock) {
            if (!(this.mCamera == null || mode == null)) {
                Parameters parameters = this.mCamera.getParameters();
                if (parameters.getSupportedFocusModes().contains(mode)) {
                    parameters.setFocusMode(mode);
                    this.mCamera.setParameters(parameters);
                    this.mFocusMode = mode;
                    z = true;
                }
            }
            z = false;
        }
        return z;
    }

    public String getFlashMode() {
        return this.mFlashMode;
    }

    public boolean setFlashMode(String mode) {
        boolean z = false;
        synchronized (this.mCameraLock) {
            if (!(this.mCamera == null || mode == null)) {
                Parameters parameters = this.mCamera.getParameters();
                if (parameters.getSupportedFlashModes() == null) {
                } else if (parameters.getSupportedFlashModes().contains(mode)) {
                    parameters.setFlashMode(mode);
                    this.mCamera.setParameters(parameters);
                    this.mFlashMode = mode;
                    z = true;
                }
            }
        }
        return z;
    }

    public boolean isFlashModeSupported(String mode) {
        boolean z = false;
        synchronized (this.mCameraLock) {
            if (this.mCamera == null && mode == null) {
            } else {
                Parameters parameters = this.mCamera.getParameters();
                if (parameters.getSupportedFlashModes() == null) {
                } else {
                    z = parameters.getSupportedFlashModes().contains(mode);
                }
            }
        }
        return z;
    }

    public void autoFocus(AutoFocusCallback cb) {
        synchronized (this.mCameraLock) {
            if (this.mCamera != null) {
                CameraAutoFocusCallback autoFocusCallback = null;
                if (cb != null) {
                    autoFocusCallback = new CameraAutoFocusCallback();
                    autoFocusCallback.mDelegate = cb;
                }
                this.mCamera.autoFocus(autoFocusCallback);
            }
        }
    }

    public void cancelAutoFocus() {
        synchronized (this.mCameraLock) {
            if (this.mCamera != null) {
                this.mCamera.cancelAutoFocus();
            }
        }
    }

    public boolean setAutoFocusMoveCallback(AutoFocusMoveCallback cb) {
        synchronized (this.mCameraLock) {
            if (this.mCamera != null) {
                CameraAutoFocusMoveCallback autoFocusMoveCallback = null;
                if (cb != null) {
                    autoFocusMoveCallback = new CameraAutoFocusMoveCallback();
                    autoFocusMoveCallback.mDelegate = cb;
                }
                this.mCamera.setAutoFocusMoveCallback(autoFocusMoveCallback);
            }
        }
        return true;
    }

    private CameraSource() {
        this.mCameraLock = new Object();
        this.mFacing = 0;
        this.mRequestedFps = 30.0f;
        this.mRequestedPreviewWidth = 1024;
        this.mRequestedPreviewHeight = 768;
        this.mFocusMode = null;
        this.mFlashMode = null;
        this.mBytesToByteBuffer = new HashMap();
    }

    @SuppressLint({"InlinedApi"})
    private Camera createCamera() {
        int requestedCameraId = getIdForRequestedCamera(this.mFacing);
        if (requestedCameraId == -1) {
            throw new RuntimeException("Could not find requested camera.");
        }
        Camera camera = Camera.open(requestedCameraId);
        SizePair sizePair = selectSizePair(camera, this.mRequestedPreviewWidth, this.mRequestedPreviewHeight);
        if (sizePair == null) {
            throw new RuntimeException("Could not find suitable preview size.");
        }
        Size pictureSize = sizePair.pictureSize();
        this.mPreviewSize = sizePair.previewSize();
        int[] previewFpsRange = selectPreviewFpsRange(camera, this.mRequestedFps);
        if (previewFpsRange == null) {
            throw new RuntimeException("Could not find suitable preview frames per second range.");
        }
        Parameters parameters = camera.getParameters();
        if (pictureSize != null) {
            parameters.setPictureSize(pictureSize.getWidth(), pictureSize.getHeight());
        }
        parameters.setPreviewSize(this.mPreviewSize.getWidth(), this.mPreviewSize.getHeight());
        parameters.setPreviewFpsRange(previewFpsRange[0], previewFpsRange[1]);
        parameters.setPreviewFormat(17);
        setRotation(camera, parameters, requestedCameraId);
        if (this.mFocusMode != null) {
            if (parameters.getSupportedFocusModes().contains(this.mFocusMode)) {
                parameters.setFocusMode(this.mFocusMode);
            } else {
                Log.i(TAG, "Camera focus mode: " + this.mFocusMode + " is not supported on this device.");
            }
        }
        this.mFocusMode = parameters.getFocusMode();
        if (!(this.mFlashMode == null || parameters.getSupportedFlashModes() == null)) {
            if (parameters.getSupportedFlashModes().contains(this.mFlashMode)) {
                parameters.setFlashMode(this.mFlashMode);
            } else {
                Log.i(TAG, "Camera flash mode: " + this.mFlashMode + " is not supported on this device.");
            }
        }
        this.mFlashMode = parameters.getFlashMode();
        camera.setParameters(parameters);
        camera.setPreviewCallbackWithBuffer(new CameraPreviewCallback());
        camera.addCallbackBuffer(createPreviewBuffer(this.mPreviewSize));
        camera.addCallbackBuffer(createPreviewBuffer(this.mPreviewSize));
        camera.addCallbackBuffer(createPreviewBuffer(this.mPreviewSize));
        camera.addCallbackBuffer(createPreviewBuffer(this.mPreviewSize));
        return camera;
    }

    private static int getIdForRequestedCamera(int facing) {
        CameraInfo cameraInfo = new CameraInfo();
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == facing) {
                return i;
            }
        }
        return -1;
    }

    private static SizePair selectSizePair(Camera camera, int desiredWidth, int desiredHeight) {
        SizePair selectedPair = null;
        int minDiff = Integer.MAX_VALUE;
        for (SizePair sizePair : generateValidPreviewSizeList(camera)) {
            Size size = sizePair.previewSize();
            int diff = Math.abs(size.getWidth() - desiredWidth) + Math.abs(size.getHeight() - desiredHeight);
            if (diff < minDiff) {
                selectedPair = sizePair;
                minDiff = diff;
            }
        }
        return selectedPair;
    }

    private static List<SizePair> generateValidPreviewSizeList(Camera camera) {
        Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        List<SizePair> validPreviewSizes = new ArrayList();
        for (Camera.Size previewSize : supportedPreviewSizes) {
            float previewAspectRatio = ((float) previewSize.width) / ((float) previewSize.height);
            for (Camera.Size pictureSize : supportedPictureSizes) {
                if (Math.abs(previewAspectRatio - (((float) pictureSize.width) / ((float) pictureSize.height))) < ASPECT_RATIO_TOLERANCE) {
                    validPreviewSizes.add(new SizePair(previewSize, pictureSize));
                    break;
                }
            }
        }
        if (validPreviewSizes.size() == 0) {
            Log.w(TAG, "No preview sizes have a corresponding same-aspect-ratio picture size");
            for (Camera.Size previewSize2 : supportedPreviewSizes) {
                validPreviewSizes.add(new SizePair(previewSize2, null));
            }
        }
        return validPreviewSizes;
    }

    private int[] selectPreviewFpsRange(Camera camera, float desiredPreviewFps) {
        int desiredPreviewFpsScaled = (int) (1000.0f * desiredPreviewFps);
        int[] selectedFpsRange = null;
        int minDiff = Integer.MAX_VALUE;
        for (int[] range : camera.getParameters().getSupportedPreviewFpsRange()) {
            int diff = Math.abs(desiredPreviewFpsScaled - range[0]) + Math.abs(desiredPreviewFpsScaled - range[1]);
            if (diff < minDiff) {
                selectedFpsRange = range;
                minDiff = diff;
            }
        }
        return selectedFpsRange;
    }

    private void setRotation(Camera camera, Parameters parameters, int cameraId) {
        int angle;
        int displayAngle;
        int degrees = 0;
        int rotation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
        switch (rotation) {
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
            default:
                Log.e(TAG, "Bad rotation value: " + rotation);
                break;
        }
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(cameraId, cameraInfo);
        if (cameraInfo.facing == 1) {
            angle = (cameraInfo.orientation + degrees) % 360;
            displayAngle = (360 - angle) % 360;
        } else {
            angle = ((cameraInfo.orientation - degrees) + 360) % 360;
            displayAngle = angle;
        }
        this.mRotation = angle / 90;
        camera.setDisplayOrientation(displayAngle);
        parameters.setRotation(angle);
    }

    private byte[] createPreviewBuffer(Size previewSize) {
        byte[] byteArray = new byte[(((int) Math.ceil(((double) ((long) ((previewSize.getHeight() * previewSize.getWidth()) * ImageFormat.getBitsPerPixel(17)))) / 8.0d)) + 1)];
        ByteBuffer buffer = ByteBuffer.wrap(byteArray);
        if (buffer.hasArray() && buffer.array() == byteArray) {
            this.mBytesToByteBuffer.put(byteArray, buffer);
            return byteArray;
        }
        throw new IllegalStateException("Failed to create valid buffer for camera source.");
    }
}
