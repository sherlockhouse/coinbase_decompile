package com.coinbase.android.signin.state;

import android.hardware.Camera;

public interface UpfrontKycTakeDocumentPhotoScreen {
    void configureCamera(Camera camera, boolean z);

    void hideFaceMatchGuide();

    void notifyHintsChanged();

    void popBackstack();

    void setGuidelinesRatio(float f, float f2);

    void setJumioTitle(int i);

    void showFaceMatchGuide();

    void showFlashlightOff();

    void showFlashlightOn();

    void tryPreviewDisplay(Camera camera) throws Exception;
}
