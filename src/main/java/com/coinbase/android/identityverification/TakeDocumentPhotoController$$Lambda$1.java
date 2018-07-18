package com.coinbase.android.identityverification;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class TakeDocumentPhotoController$$Lambda$1 implements OnClickListener {
    private final TakeDocumentPhotoController arg$1;

    private TakeDocumentPhotoController$$Lambda$1(TakeDocumentPhotoController takeDocumentPhotoController) {
        this.arg$1 = takeDocumentPhotoController;
    }

    public static OnClickListener lambdaFactory$(TakeDocumentPhotoController takeDocumentPhotoController) {
        return new TakeDocumentPhotoController$$Lambda$1(takeDocumentPhotoController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onToggleClicked();
    }
}
