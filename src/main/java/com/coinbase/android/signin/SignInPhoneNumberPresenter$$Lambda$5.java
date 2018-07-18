package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class SignInPhoneNumberPresenter$$Lambda$5 implements Action1 {
    private final SignInPhoneNumberPresenter arg$1;

    private SignInPhoneNumberPresenter$$Lambda$5(SignInPhoneNumberPresenter signInPhoneNumberPresenter) {
        this.arg$1 = signInPhoneNumberPresenter;
    }

    public static Action1 lambdaFactory$(SignInPhoneNumberPresenter signInPhoneNumberPresenter) {
        return new SignInPhoneNumberPresenter$$Lambda$5(signInPhoneNumberPresenter);
    }

    public void call(Object obj) {
        SignInPhoneNumberPresenter.lambda$getPhoneNumbersAndRouteToVerificationScreenIfNecessary$4(this.arg$1, (Throwable) obj);
    }
}
