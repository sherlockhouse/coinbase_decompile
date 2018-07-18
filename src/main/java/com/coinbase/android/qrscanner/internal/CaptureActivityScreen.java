package com.coinbase.android.qrscanner.internal;

import android.content.Intent;

public interface CaptureActivityScreen {
    void displayCameraBugMessageAndContinue();

    void displayFrameworkBugMessageAndExit();

    void finish();

    void hideTorch();

    void setResult(int i, Intent intent);

    void setTorchOff();

    void setTorchOn();
}
