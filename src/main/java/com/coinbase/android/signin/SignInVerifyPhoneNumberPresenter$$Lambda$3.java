package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class SignInVerifyPhoneNumberPresenter$$Lambda$3 implements Action1 {
    private final SignInVerifyPhoneNumberPresenter arg$1;

    private SignInVerifyPhoneNumberPresenter$$Lambda$3(SignInVerifyPhoneNumberPresenter signInVerifyPhoneNumberPresenter) {
        this.arg$1 = signInVerifyPhoneNumberPresenter;
    }

    public static Action1 lambdaFactory$(SignInVerifyPhoneNumberPresenter signInVerifyPhoneNumberPresenter) {
        return new SignInVerifyPhoneNumberPresenter$$Lambda$3(signInVerifyPhoneNumberPresenter);
    }

    public void call(Object obj) {
        SignInVerifyPhoneNumberPresenter.lambda$onResendTokenClicked$2(this.arg$1, (Throwable) obj);
    }
}
