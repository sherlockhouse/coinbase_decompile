package com.coinbase.android.signin;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SignUpController$$Lambda$5 implements OnClickListener {
    private final SignUpController arg$1;

    private SignUpController$$Lambda$5(SignUpController signUpController) {
        this.arg$1 = signUpController;
    }

    public static OnClickListener lambdaFactory$(SignUpController signUpController) {
        return new SignUpController$$Lambda$5(signUpController);
    }

    public void onClick(View view) {
        this.arg$1.onSignUpPressed();
    }
}
