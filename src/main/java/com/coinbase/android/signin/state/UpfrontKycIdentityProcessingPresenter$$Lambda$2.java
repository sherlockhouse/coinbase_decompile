package com.coinbase.android.signin.state;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class UpfrontKycIdentityProcessingPresenter$$Lambda$2 implements Action1 {
    private final UpfrontKycIdentityProcessingPresenter arg$1;

    private UpfrontKycIdentityProcessingPresenter$$Lambda$2(UpfrontKycIdentityProcessingPresenter upfrontKycIdentityProcessingPresenter) {
        this.arg$1 = upfrontKycIdentityProcessingPresenter;
    }

    public static Action1 lambdaFactory$(UpfrontKycIdentityProcessingPresenter upfrontKycIdentityProcessingPresenter) {
        return new UpfrontKycIdentityProcessingPresenter$$Lambda$2(upfrontKycIdentityProcessingPresenter);
    }

    public void call(Object obj) {
        UpfrontKycIdentityProcessingPresenter.lambda$getJumioProfilesDelayed$1(this.arg$1, (Pair) obj);
    }
}
