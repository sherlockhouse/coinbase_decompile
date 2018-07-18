package com.coinbase.android.identityverification;

import android.graphics.Bitmap;
import android.os.Bundle;

public interface JumioDocumentScanScreen {
    void finish();

    void hideButtons();

    void hideProgressDialog();

    boolean isShown();

    void routeAddCard();

    void routeRetakeDocumentPhoto();

    void routeTakeDocumentPhoto(Bundle bundle);

    void showContinueConfirmPicture(Bitmap bitmap);

    void showErrorMessage(String str);

    void showException(Throwable th);

    void showJumioUploadingProgress();

    void showPleaseWaitProgress();

    void showTakeBack();

    void showTakeFront();

    void showTakeSelfie();

    void updateScanText(int i, int i2);
}
