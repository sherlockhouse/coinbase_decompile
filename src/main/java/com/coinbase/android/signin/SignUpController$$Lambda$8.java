package com.coinbase.android.signin;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SignUpController$$Lambda$8 implements OnClickListener {
    private final SignUpController arg$1;

    private SignUpController$$Lambda$8(SignUpController signUpController) {
        this.arg$1 = signUpController;
    }

    public static OnClickListener lambdaFactory$(SignUpController signUpController) {
        return new SignUpController$$Lambda$8(signUpController);
    }

    public void onClick(View view) {
        SignUpController.lambda$onCreateView$9(this.arg$1, view);
    }
}
