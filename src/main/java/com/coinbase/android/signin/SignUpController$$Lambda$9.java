package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class SignUpController$$Lambda$9 implements Action1 {
    private final SignUpController arg$1;
    private final String arg$2;

    private SignUpController$$Lambda$9(SignUpController signUpController, String str) {
        this.arg$1 = signUpController;
        this.arg$2 = str;
    }

    public static Action1 lambdaFactory$(SignUpController signUpController, String str) {
        return new SignUpController$$Lambda$9(signUpController, str);
    }

    public void call(Object obj) {
        SignUpController.lambda$startSignup$10(this.arg$1, this.arg$2, (Pair) obj);
    }
}
