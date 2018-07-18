package com.coinbase.android.signin.state;

import android.graphics.Bitmap;
import rx.Observable.OnSubscribe;
import rx.Subscriber;

final /* synthetic */ class UpfrontKycIdentityDocumentScanPresenter$$Lambda$4 implements OnSubscribe {
    private final UpfrontKycIdentityDocumentScanPresenter arg$1;
    private final Bitmap arg$2;
    private final Bitmap arg$3;
    private final Bitmap arg$4;

    private UpfrontKycIdentityDocumentScanPresenter$$Lambda$4(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3) {
        this.arg$1 = upfrontKycIdentityDocumentScanPresenter;
        this.arg$2 = bitmap;
        this.arg$3 = bitmap2;
        this.arg$4 = bitmap3;
    }

    public static OnSubscribe lambdaFactory$(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3) {
        return new UpfrontKycIdentityDocumentScanPresenter$$Lambda$4(upfrontKycIdentityDocumentScanPresenter, bitmap, bitmap2, bitmap3);
    }

    public void call(Object obj) {
        UpfrontKycIdentityDocumentScanPresenter.lambda$createJumioProfile$7(this.arg$1, this.arg$2, this.arg$3, this.arg$4, (Subscriber) obj);
    }
}
