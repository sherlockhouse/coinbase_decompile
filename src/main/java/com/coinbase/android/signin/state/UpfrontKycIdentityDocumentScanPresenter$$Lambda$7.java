package com.coinbase.android.signin.state;

import rx.functions.Action1;

final /* synthetic */ class UpfrontKycIdentityDocumentScanPresenter$$Lambda$7 implements Action1 {
    private final UpfrontKycIdentityDocumentScanPresenter arg$1;

    private UpfrontKycIdentityDocumentScanPresenter$$Lambda$7(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter) {
        this.arg$1 = upfrontKycIdentityDocumentScanPresenter;
    }

    public static Action1 lambdaFactory$(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter) {
        return new UpfrontKycIdentityDocumentScanPresenter$$Lambda$7(upfrontKycIdentityDocumentScanPresenter);
    }

    public void call(Object obj) {
        UpfrontKycIdentityDocumentScanPresenter.lambda$createJumioProfile$10(this.arg$1, (Throwable) obj);
    }
}
