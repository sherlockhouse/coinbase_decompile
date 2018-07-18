package com.coinbase.android.signin.state;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class UpfrontKycIdentityDocumentScanPresenter$$Lambda$6 implements Action1 {
    private final UpfrontKycIdentityDocumentScanPresenter arg$1;

    private UpfrontKycIdentityDocumentScanPresenter$$Lambda$6(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter) {
        this.arg$1 = upfrontKycIdentityDocumentScanPresenter;
    }

    public static Action1 lambdaFactory$(UpfrontKycIdentityDocumentScanPresenter upfrontKycIdentityDocumentScanPresenter) {
        return new UpfrontKycIdentityDocumentScanPresenter$$Lambda$6(upfrontKycIdentityDocumentScanPresenter);
    }

    public void call(Object obj) {
        UpfrontKycIdentityDocumentScanPresenter.lambda$createJumioProfile$9(this.arg$1, (Pair) obj);
    }
}
