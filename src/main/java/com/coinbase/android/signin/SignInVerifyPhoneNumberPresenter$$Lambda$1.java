package com.coinbase.android.signin;

import rx.functions.FuncN;

final /* synthetic */ class SignInVerifyPhoneNumberPresenter$$Lambda$1 implements FuncN {
    private final SignInVerifyPhoneNumberPresenter arg$1;

    private SignInVerifyPhoneNumberPresenter$$Lambda$1(SignInVerifyPhoneNumberPresenter signInVerifyPhoneNumberPresenter) {
        this.arg$1 = signInVerifyPhoneNumberPresenter;
    }

    public static FuncN lambdaFactory$(SignInVerifyPhoneNumberPresenter signInVerifyPhoneNumberPresenter) {
        return new SignInVerifyPhoneNumberPresenter$$Lambda$1(signInVerifyPhoneNumberPresenter);
    }

    public Object call(Object[] objArr) {
        return this.arg$1.combineResendResults(objArr);
    }
}
