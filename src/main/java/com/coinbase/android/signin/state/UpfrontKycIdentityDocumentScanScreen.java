package com.coinbase.android.signin.state;

import android.graphics.Bitmap;
import android.os.Bundle;

public interface UpfrontKycIdentityDocumentScanScreen {
    void finish();

    void hideButtons();

    void hideProgressDialog();

    boolean isShown();

    void notifyHintsChanged();

    void routeTakeDocumentPhoto(Bundle bundle);

    void setContinueButtonText(int i);

    void showContinueConfirmPicture(Bitmap bitmap);

    void showJumioUploadingProgress();

    void showNoFaceDetected();

    void showPleaseWaitProgress();
}
