package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class SignUpController$$Lambda$4 implements Action1 {
    private final SignUpController arg$1;

    private SignUpController$$Lambda$4(SignUpController signUpController) {
        this.arg$1 = signUpController;
    }

    public static Action1 lambdaFactory$(SignUpController signUpController) {
        return new SignUpController$$Lambda$4(signUpController);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Couldn't get email verified event", (Throwable) obj);
    }
}
