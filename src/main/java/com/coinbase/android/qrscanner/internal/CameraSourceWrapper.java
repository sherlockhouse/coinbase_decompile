package com.coinbase.android.qrscanner.internal;

import android.app.Activity;
import android.view.SurfaceHolder;
import com.coinbase.android.qrscanner.internal.gms.BarcodeTrackerFactory;
import com.coinbase.android.qrscanner.internal.gms.CameraSource;
import com.coinbase.android.qrscanner.ui.gms.GraphicOverlay;
import com.google.android.gms.common.images.Size;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.gms.vision.barcode.BarcodeDetector.Builder;
import java.io.IOException;

class CameraSourceWrapper {
    private CameraSource mCameraSource;
    volatile boolean mInvalidated = false;
    private final CaptureActivityScreen mScreen;
    private boolean mTorchOff = true;

    CameraSourceWrapper(CaptureActivityScreen screen) {
        this.mScreen = screen;
    }

    void create(GraphicOverlay graphicOverlay, BarcodeCallback callback) {
        this.mInvalidated = false;
        BarcodeDetector barcodeDetector = new Builder((Activity) this.mScreen).setBarcodeFormats(256).build();
        barcodeDetector.setProcessor(new MultiProcessor.Builder(new BarcodeTrackerFactory(graphicOverlay, callback)).build());
        this.mCameraSource = new CameraSource.Builder((Activity) this.mScreen, barcodeDetector).setFocusMode("continuous-picture").build();
    }

    void start(SurfaceHolder holder) throws IOException, SecurityException {
        try {
            this.mCameraSource.start(holder);
        } catch (RuntimeException e) {
            this.mInvalidated = true;
            throw new IOException("Exception starting camera", e);
        }
    }

    boolean isTorchAvailable() {
        if (this.mInvalidated) {
            return false;
        }
        return this.mCameraSource.isFlashModeSupported("torch");
    }

    void toggleTorch() {
        if (!this.mInvalidated && this.mCameraSource != null) {
            if (this.mTorchOff) {
                this.mCameraSource.setFlashMode("torch");
                this.mScreen.setTorchOff();
            } else {
                this.mCameraSource.setFlashMode("off");
                this.mScreen.setTorchOn();
            }
            this.mTorchOff = !this.mTorchOff;
        }
    }

    Size getPreviewSize() {
        if (this.mInvalidated) {
            return null;
        }
        return this.mCameraSource.getPreviewSize();
    }

    int getCameraFacing() {
        if (this.mInvalidated) {
            return 0;
        }
        return this.mCameraSource.getCameraFacing();
    }

    void stop() {
        if (!this.mInvalidated) {
            this.mCameraSource.stop();
        }
    }
}
