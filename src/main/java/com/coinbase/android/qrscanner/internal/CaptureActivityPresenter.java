package com.coinbase.android.qrscanner.internal;

import android.app.Activity;
import android.os.Looper;
import android.view.SurfaceView;
import com.coinbase.android.qrscanner.ui.gms.GraphicOverlay;
import com.coinbase.zxing.client.android.BeepManager;
import com.coinbase.zxing.client.android.InactivityTimer;
import com.google.android.gms.vision.barcode.Barcode;

public class CaptureActivityPresenter {
    private static final int DELAY_MILLIS_AFTER_FIND = 2000;
    private BarcodeCallback mCallback;
    private final CameraSourceWrapper mCameraSourceWrapper;
    private boolean mFoundBarcode;
    private GraphicOverlay mGraphicOverlay;
    private final InactivityTimerWrapper mInactivityTimerWrapper;
    private final CaptureActivitySetResultHandler mSetResultHandler;
    private final CaptureActivitySurfaceHolderCallback mSurfaceHolderCallback;
    private SurfaceView mSurfaceView;

    static class InactivityTimerWrapper {
        private final Activity mActivity;
        private InactivityTimer mInactivityTimer;

        InactivityTimerWrapper(Activity activity) {
            this.mActivity = activity;
        }

        void create() {
            this.mInactivityTimer = new InactivityTimer(this.mActivity);
        }

        void onResume() {
            this.mInactivityTimer.onResume();
        }

        void onPause() {
            this.mInactivityTimer.onPause();
        }

        void shutdown() {
            this.mInactivityTimer.shutdown();
        }
    }

    public CaptureActivityPresenter(CaptureActivityScreen screen) {
        this(screen, new CameraSourceWrapper(screen));
    }

    CaptureActivityPresenter(CaptureActivityScreen screen, CameraSourceWrapper cameraSourceWrapper) {
        this(new CaptureActivitySetResultHandler(Looper.getMainLooper(), screen, new BeepManager((Activity) screen)), new CaptureActivitySurfaceHolderCallback(screen, cameraSourceWrapper), new InactivityTimerWrapper((Activity) screen), cameraSourceWrapper);
    }

    CaptureActivityPresenter(CaptureActivitySetResultHandler handler, CaptureActivitySurfaceHolderCallback surfaceHolderCallback, InactivityTimerWrapper inactivityTimerWrapper, CameraSourceWrapper cameraSourceWrapper) {
        this.mFoundBarcode = false;
        this.mCallback = new BarcodeCallback() {
            public void onNewBarcodeFound(Barcode item) {
                synchronized (CaptureActivityPresenter.this.mSetResultHandler) {
                    if (CaptureActivityPresenter.this.mFoundBarcode) {
                        return;
                    }
                    CaptureActivityPresenter.this.mFoundBarcode = true;
                    CaptureActivityPresenter.this.mSetResultHandler.sendMessageDelayed(CaptureActivityPresenter.this.mSetResultHandler.obtainMessage(CaptureActivitySetResultHandler.DETECTION_RESULT, item), 2000);
                }
            }
        };
        this.mSetResultHandler = handler;
        this.mSurfaceHolderCallback = surfaceHolderCallback;
        this.mInactivityTimerWrapper = inactivityTimerWrapper;
        this.mCameraSourceWrapper = cameraSourceWrapper;
    }

    public void onCreate() {
        this.mInactivityTimerWrapper.create();
    }

    public void onResume(SurfaceView surfaceView, GraphicOverlay graphicOverlay) {
        this.mInactivityTimerWrapper.onResume();
        this.mSurfaceView = surfaceView;
        this.mGraphicOverlay = graphicOverlay;
        this.mSurfaceHolderCallback.setSurfaceView(this.mSurfaceView);
        this.mSurfaceHolderCallback.setGraphicOverlay(this.mGraphicOverlay);
        this.mSurfaceView.getHolder().addCallback(this.mSurfaceHolderCallback);
        this.mCameraSourceWrapper.create(graphicOverlay, this.mCallback);
    }

    public void onPause() {
        this.mInactivityTimerWrapper.onPause();
    }

    public void onDestroy() {
        this.mInactivityTimerWrapper.shutdown();
        this.mSetResultHandler.onDestroy();
    }

    public void toggleTorch() {
        this.mCameraSourceWrapper.toggleTorch();
    }
}
