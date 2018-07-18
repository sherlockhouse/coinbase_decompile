package com.coinbase.android.qrscanner.internal.gms;

import com.coinbase.android.qrscanner.internal.BarcodeCallback;
import com.coinbase.android.qrscanner.ui.gms.BarcodeGraphic;
import com.coinbase.android.qrscanner.ui.gms.GraphicOverlay;
import com.google.android.gms.vision.MultiProcessor.Factory;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

public class BarcodeTrackerFactory implements Factory<Barcode> {
    private BarcodeCallback mCallback;
    private GraphicOverlay<BarcodeGraphic> mGraphicOverlay;

    public BarcodeTrackerFactory(GraphicOverlay<BarcodeGraphic> barcodeGraphicOverlay, BarcodeCallback callback) {
        this.mGraphicOverlay = barcodeGraphicOverlay;
        this.mCallback = callback;
    }

    public Tracker<Barcode> create(Barcode barcode) {
        return new BarcodeGraphicTracker(this.mGraphicOverlay, new BarcodeGraphic(this.mGraphicOverlay), this.mCallback);
    }
}
