package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class SignInPhoneNumberPresenter$$Lambda$3 implements Action1 {
    private final SignInPhoneNumberPresenter arg$1;

    private SignInPhoneNumberPresenter$$Lambda$3(SignInPhoneNumberPresenter signInPhoneNumberPresenter) {
        this.arg$1 = signInPhoneNumberPresenter;
    }

    public static Action1 lambdaFactory$(SignInPhoneNumberPresenter signInPhoneNumberPresenter) {
        return new SignInPhoneNumberPresenter$$Lambda$3(signInPhoneNumberPresenter);
    }

    public void call(Object obj) {
        SignInPhoneNumberPresenter.lambda$addPhoneNumber$2(this.arg$1, (Throwable) obj);
    }
}
