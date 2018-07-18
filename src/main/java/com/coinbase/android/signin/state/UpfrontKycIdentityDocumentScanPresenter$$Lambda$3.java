package com.coinbase.android.signin.state;

import rx.functions.Action1;

final /* synthetic */ class UpfrontKycIdentityDocumentScanPresenter$$Lambda$3 implements Action1 {
    private final UpfrontKycIdentityDocumentScanPresenter arg$1;

    private UpfrontKycIdentityDocumentScanPresenter$$Lambda$3(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter) {
        this.arg$1 = upfrontKycIdentityDocumentScanPresenter;
    }

    public static Action1 lambdaFactory$(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter) {
        return new UpfrontKycIdentityDocumentScanPresenter$$Lambda$3(upfrontKycIdentityDocumentScanPresenter);
    }

    public void call(Object obj) {
        UpfrontKycIdentityDocumentScanPresenter.lambda$detectFace$6(this.arg$1, (Boolean) obj);
    }
}
