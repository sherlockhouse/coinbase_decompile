package com.coinbase.android.signin.state;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class UpfrontKycIdentityAcceptableDocumentsPresenter$$Lambda$1 implements Action1 {
    private final UpfrontKycIdentityAcceptableDocumentsPresenter arg$1;

    private UpfrontKycIdentityAcceptableDocumentsPresenter$$Lambda$1(UpfrontKycIdentityAcceptableDocumentsPresenter upfrontKycIdentityAcceptableDocumentsPresenter) {
        this.arg$1 = upfrontKycIdentityAcceptableDocumentsPresenter;
    }

    public static Action1 lambdaFactory$(UpfrontKycIdentityAcceptableDocumentsPresenter upfrontKycIdentityAcceptableDocumentsPresenter) {
        return new UpfrontKycIdentityAcceptableDocumentsPresenter$$Lambda$1(upfrontKycIdentityAcceptableDocumentsPresenter);
    }

    public void call(Object obj) {
        UpfrontKycIdentityAcceptableDocumentsPresenter.lambda$getIdentitySupportedDocument$0(this.arg$1, (Pair) obj);
    }
}
