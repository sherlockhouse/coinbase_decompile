package com.coinbase.android.qrscanner.internal.gms;

import com.coinbase.android.qrscanner.internal.BarcodeCallback;
import com.coinbase.android.qrscanner.ui.gms.BarcodeGraphic;
import com.coinbase.android.qrscanner.ui.gms.GraphicOverlay;
import com.google.android.gms.vision.Detector.Detections;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

class BarcodeGraphicTracker extends Tracker<Barcode> {
    private final BarcodeCallback mCallback;
    private final BarcodeGraphic mGraphic;
    private final GraphicOverlay<BarcodeGraphic> mOverlay;

    BarcodeGraphicTracker(GraphicOverlay<BarcodeGraphic> overlay, BarcodeGraphic graphic, BarcodeCallback callback) {
        this.mOverlay = overlay;
        this.mGraphic = graphic;
        this.mCallback = callback;
    }

    public void onNewItem(int id, Barcode item) {
        this.mGraphic.setId(id);
        this.mCallback.onNewBarcodeFound(item);
    }

    public void onUpdate(Detections<Barcode> detections, Barcode item) {
        this.mOverlay.add(this.mGraphic);
        this.mGraphic.updateItem(item);
    }

    public void onMissing(Detections<Barcode> detections) {
        this.mOverlay.remove(this.mGraphic);
    }

    public void onDone() {
        this.mOverlay.remove(this.mGraphic);
    }
}
