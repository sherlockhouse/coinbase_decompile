package com.coinbase.android.signin;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

final /* synthetic */ class SignUpController$$Lambda$6 implements OnCheckedChangeListener {
    private final SignUpController arg$1;

    private SignUpController$$Lambda$6(SignUpController signUpController) {
        this.arg$1 = signUpController;
    }

    public static OnCheckedChangeListener lambdaFactory$(SignUpController signUpController) {
        return new SignUpController$$Lambda$6(signUpController);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        SignUpController.lambda$onCreateView$5(this.arg$1, compoundButton, z);
    }
}
