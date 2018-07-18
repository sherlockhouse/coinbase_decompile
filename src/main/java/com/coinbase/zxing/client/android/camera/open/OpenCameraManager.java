package com.coinbase.zxing.client.android.camera.open;

import com.coinbase.zxing.client.android.common.PlatformSupportManager;

public final class OpenCameraManager extends PlatformSupportManager<OpenCameraInterface> {
    public OpenCameraManager() {
        super(OpenCameraInterface.class, new DefaultOpenCameraInterface());
        addImplementationClass(9, "com.coinbase.zxing.client.android.camera.open.GingerbreadOpenCameraInterface");
    }
}
