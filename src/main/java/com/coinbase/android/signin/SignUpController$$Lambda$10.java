package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class SignUpController$$Lambda$10 implements Action1 {
    private final SignUpController arg$1;

    private SignUpController$$Lambda$10(SignUpController signUpController) {
        this.arg$1 = signUpController;
    }

    public static Action1 lambdaFactory$(SignUpController signUpController) {
        return new SignUpController$$Lambda$10(signUpController);
    }

    public void call(Object obj) {
        SignUpController.lambda$startSignup$11(this.arg$1, (Throwable) obj);
    }
}
