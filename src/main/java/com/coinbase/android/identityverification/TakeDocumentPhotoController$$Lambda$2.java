package com.coinbase.android.identityverification;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;

final /* synthetic */ class TakeDocumentPhotoController$$Lambda$2 implements PictureCallback {
    private final TakeDocumentPhotoController arg$1;

    private TakeDocumentPhotoController$$Lambda$2(TakeDocumentPhotoController takeDocumentPhotoController) {
        this.arg$1 = takeDocumentPhotoController;
    }

    public static PictureCallback lambdaFactory$(TakeDocumentPhotoController takeDocumentPhotoController) {
        return new TakeDocumentPhotoController$$Lambda$2(takeDocumentPhotoController);
    }

    public void onPictureTaken(byte[] bArr, Camera camera) {
        this.arg$1.mPresenter.onPictureTaken(bArr, camera, this.arg$1.mSurfaceHolder.getSurface());
    }
}
