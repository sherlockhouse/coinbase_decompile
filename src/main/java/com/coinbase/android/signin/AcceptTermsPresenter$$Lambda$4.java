package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class AcceptTermsPresenter$$Lambda$4 implements Action1 {
    private final AcceptTermsPresenter arg$1;

    private AcceptTermsPresenter$$Lambda$4(AcceptTermsPresenter acceptTermsPresenter) {
        this.arg$1 = acceptTermsPresenter;
    }

    public static Action1 lambdaFactory$(AcceptTermsPresenter acceptTermsPresenter) {
        return new AcceptTermsPresenter$$Lambda$4(acceptTermsPresenter);
    }

    public void call(Object obj) {
        AcceptTermsPresenter.lambda$setTNCDescription$3(this.arg$1, (Throwable) obj);
    }
}
