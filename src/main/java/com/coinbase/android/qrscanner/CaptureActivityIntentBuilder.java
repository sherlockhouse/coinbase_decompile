package com.coinbase.android.qrscanner;

import android.app.Activity;
import android.content.Intent;
import com.coinbase.android.qrscanner.ui.CaptureActivity;
import com.coinbase.zxing.client.android.Intents.Scan;

public class CaptureActivityIntentBuilder {
    private final Intent mIntent;

    public CaptureActivityIntentBuilder(Activity activity) {
        this.mIntent = new Intent(activity, CaptureActivity.class);
    }

    public CaptureActivityIntentBuilder makeAction() {
        this.mIntent.setAction(Scan.ACTION);
        return this;
    }

    public CaptureActivityIntentBuilder withQrCodeMode() {
        this.mIntent.putExtra(Scan.MODE, Scan.QR_CODE_MODE);
        return this;
    }

    public CaptureActivityIntentBuilder withMessage(String text) {
        this.mIntent.putExtra(Scan.PROMPT_MESSAGE, text);
        return this;
    }

    public CaptureActivityIntentBuilder withReceiveAddress(String receiveAddress) {
        this.mIntent.putExtra(com.coinbase.zxing.client.android.CaptureActivity.QR_TEXT, receiveAddress);
        return this;
    }

    public CaptureActivityIntentBuilder withExplanationText(String explanationText) {
        this.mIntent.putExtra(com.coinbase.zxing.client.android.CaptureActivity.EXPLAIN_TEXT, explanationText);
        return this;
    }

    public CaptureActivityIntentBuilder withBitmap(byte[] bitmap) {
        this.mIntent.putExtra(com.coinbase.zxing.client.android.CaptureActivity.QR_BITMAP, bitmap);
        return this;
    }

    public Intent build() {
        return this.mIntent;
    }
}
