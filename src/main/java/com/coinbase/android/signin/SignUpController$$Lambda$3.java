package com.coinbase.android.signin;

import com.coinbase.android.signin.EmailVerifiedConnector.Type;
import rx.functions.Action1;

final /* synthetic */ class SignUpController$$Lambda$3 implements Action1 {
    private final SignUpController arg$1;

    private SignUpController$$Lambda$3(SignUpController signUpController) {
        this.arg$1 = signUpController;
    }

    public static Action1 lambdaFactory$(SignUpController signUpController) {
        return new SignUpController$$Lambda$3(signUpController);
    }

    public void call(Object obj) {
        this.arg$1.onEmailVerifiedEvent((Type) obj);
    }
}
