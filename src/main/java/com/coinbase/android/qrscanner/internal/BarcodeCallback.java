package com.coinbase.android.qrscanner.internal;

import com.google.android.gms.vision.barcode.Barcode;

public interface BarcodeCallback {
    void onNewBarcodeFound(Barcode barcode);
}
