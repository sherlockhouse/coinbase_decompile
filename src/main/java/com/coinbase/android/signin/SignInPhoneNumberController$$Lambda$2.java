package com.coinbase.android.signin;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class SignInPhoneNumberController$$Lambda$2 implements OnEditorActionListener {
    private final SignInPhoneNumberController arg$1;

    private SignInPhoneNumberController$$Lambda$2(SignInPhoneNumberController signInPhoneNumberController) {
        this.arg$1 = signInPhoneNumberController;
    }

    public static OnEditorActionListener lambdaFactory$(SignInPhoneNumberController signInPhoneNumberController) {
        return new SignInPhoneNumberController$$Lambda$2(signInPhoneNumberController);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return SignInPhoneNumberController.lambda$onCreateView$1(this.arg$1, textView, i, keyEvent);
    }
}
