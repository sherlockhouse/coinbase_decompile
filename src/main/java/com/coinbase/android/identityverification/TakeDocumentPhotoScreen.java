package com.coinbase.android.identityverification;

import android.hardware.Camera;

public interface TakeDocumentPhotoScreen {
    void configureCamera(Camera camera, boolean z);

    double getViewfinderCropHeight();

    double getViewfinderCropWidth();

    double getViewfinderCropX();

    double getViewfinderCropY();

    void hideFaceMatchGuide();

    void popBackstack();

    void setJumioTitle(int i);

    void showFaceMatchGuide();

    void showFlashlightOff();

    void showFlashlightOn();

    void tryPreviewDisplay(Camera camera) throws Exception;
}
