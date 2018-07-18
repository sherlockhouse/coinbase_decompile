package com.coinbase.android.signin;

import android.app.ProgressDialog;
import rx.functions.Action1;

final /* synthetic */ class SignUpController$$Lambda$13 implements Action1 {
    private final SignUpController arg$1;
    private final ProgressDialog arg$2;

    private SignUpController$$Lambda$13(SignUpController signUpController, ProgressDialog progressDialog) {
        this.arg$1 = signUpController;
        this.arg$2 = progressDialog;
    }

    public static Action1 lambdaFactory$(SignUpController signUpController, ProgressDialog progressDialog) {
        return new SignUpController$$Lambda$13(signUpController, progressDialog);
    }

    public void call(Object obj) {
        SignUpController.lambda$null$8(this.arg$1, this.arg$2, (Throwable) obj);
    }
}
