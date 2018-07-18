package com.coinbase.android.signin;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;

final /* synthetic */ class SignUpController$$Lambda$7 implements OnKeyListener {
    private final SignUpController arg$1;

    private SignUpController$$Lambda$7(SignUpController signUpController) {
        this.arg$1 = signUpController;
    }

    public static OnKeyListener lambdaFactory$(SignUpController signUpController) {
        return new SignUpController$$Lambda$7(signUpController);
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return SignUpController.lambda$onCreateView$6(this.arg$1, view, i, keyEvent);
    }
}
