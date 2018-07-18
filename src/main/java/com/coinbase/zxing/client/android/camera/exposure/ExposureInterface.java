package com.coinbase.zxing.client.android.camera.exposure;

import android.hardware.Camera.Parameters;

public interface ExposureInterface {
    void setExposure(Parameters parameters, boolean z);
}
