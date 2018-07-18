package com.coinbase.android.signin.state;

import rx.functions.Action1;

final /* synthetic */ class UpfrontKycIdentityProcessingPresenter$$Lambda$3 implements Action1 {
    private final UpfrontKycIdentityProcessingPresenter arg$1;

    private UpfrontKycIdentityProcessingPresenter$$Lambda$3(UpfrontKycIdentityProcessingPresenter upfrontKycIdentityProcessingPresenter) {
        this.arg$1 = upfrontKycIdentityProcessingPresenter;
    }

    public static Action1 lambdaFactory$(UpfrontKycIdentityProcessingPresenter upfrontKycIdentityProcessingPresenter) {
        return new UpfrontKycIdentityProcessingPresenter$$Lambda$3(upfrontKycIdentityProcessingPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mSubscription.add(this.arg$1.getJumioProfilesDelayed(UpfrontKycIdentityProcessingPresenter.SERVER_ERROR_TIMEOUT));
    }
}
