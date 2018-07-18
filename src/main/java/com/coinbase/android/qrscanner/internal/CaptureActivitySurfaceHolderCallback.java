package com.coinbase.android.qrscanner.internal;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.coinbase.android.qrscanner.ui.gms.GraphicOverlay;
import com.google.android.gms.common.images.Size;
import java.io.IOException;

class CaptureActivitySurfaceHolderCallback implements Callback {
    private static final String TAG = CaptureActivitySurfaceHolderCallback.class.getSimpleName();
    private final CameraSourceWrapper mCameraSourceWrapper;
    private final ContextCompatWrapper mContextCompatWrapper;
    private GraphicOverlay mGraphicOverlay;
    private final CaptureActivityScreen mScreen;
    private SurfaceView mSurfaceView;

    static class ContextCompatWrapper {
        ContextCompatWrapper() {
        }

        int checkSelfPermission(Activity activity, String permission) {
            return ContextCompat.checkSelfPermission(activity, "android.permission.CAMERA");
        }
    }

    CaptureActivitySurfaceHolderCallback(CaptureActivityScreen screen, CameraSourceWrapper cameraSourceWrapper) {
        this(screen, new ContextCompatWrapper(), cameraSourceWrapper);
    }

    CaptureActivitySurfaceHolderCallback(CaptureActivityScreen screen, ContextCompatWrapper contextCompatWrapper, CameraSourceWrapper cameraSourceWrapper) {
        this.mScreen = screen;
        this.mContextCompatWrapper = contextCompatWrapper;
        this.mCameraSourceWrapper = cameraSourceWrapper;
    }

    void setGraphicOverlay(GraphicOverlay graphicOverlay) {
        this.mGraphicOverlay = graphicOverlay;
    }

    void setSurfaceView(SurfaceView surfaceView) {
        this.mSurfaceView = surfaceView;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Exception e;
        boolean isPortrait = true;
        Activity activity = this.mScreen;
        if (this.mContextCompatWrapper.checkSelfPermission(activity, "android.permission.CAMERA") == 0) {
            try {
                int i;
                this.mCameraSourceWrapper.start(this.mSurfaceView.getHolder());
                if (!this.mCameraSourceWrapper.isTorchAvailable()) {
                    this.mScreen.hideTorch();
                }
                Size s = this.mCameraSourceWrapper.getPreviewSize();
                int w = s.getWidth();
                int h = s.getHeight();
                if (activity.getResources().getConfiguration().orientation != 1) {
                    isPortrait = false;
                }
                GraphicOverlay graphicOverlay = this.mGraphicOverlay;
                if (isPortrait) {
                    i = h;
                } else {
                    i = w;
                }
                if (!isPortrait) {
                    w = h;
                }
                graphicOverlay.setCameraInfo(i, w, this.mCameraSourceWrapper.getCameraFacing());
            } catch (IOException e2) {
                e = e2;
                Log.w(TAG, e);
                this.mScreen.displayCameraBugMessageAndContinue();
            } catch (SecurityException e3) {
                e = e3;
                Log.w(TAG, e);
                this.mScreen.displayCameraBugMessageAndContinue();
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        this.mCameraSourceWrapper.stop();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }
}
