package com.coinbase.android.signin.state;

import rx.functions.Action1;

final /* synthetic */ class UpfrontKycIdentityAcceptableDocumentsPresenter$$Lambda$2 implements Action1 {
    private final UpfrontKycIdentityAcceptableDocumentsPresenter arg$1;

    private UpfrontKycIdentityAcceptableDocumentsPresenter$$Lambda$2(UpfrontKycIdentityAcceptableDocumentsPresenter upfrontKycIdentityAcceptableDocumentsPresenter) {
        this.arg$1 = upfrontKycIdentityAcceptableDocumentsPresenter;
    }

    public static Action1 lambdaFactory$(UpfrontKycIdentityAcceptableDocumentsPresenter upfrontKycIdentityAcceptableDocumentsPresenter) {
        return new UpfrontKycIdentityAcceptableDocumentsPresenter$$Lambda$2(upfrontKycIdentityAcceptableDocumentsPresenter);
    }

    public void call(Object obj) {
        UpfrontKycIdentityAcceptableDocumentsPresenter.lambda$getIdentitySupportedDocument$1(this.arg$1, (Throwable) obj);
    }
}
