package com.coinbase.android.signin;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SignInVerifyPhoneNumberController$$Lambda$1 implements OnClickListener {
    private final SignInVerifyPhoneNumberController arg$1;

    private SignInVerifyPhoneNumberController$$Lambda$1(SignInVerifyPhoneNumberController signInVerifyPhoneNumberController) {
        this.arg$1 = signInVerifyPhoneNumberController;
    }

    public static OnClickListener lambdaFactory$(SignInVerifyPhoneNumberController signInVerifyPhoneNumberController) {
        return new SignInVerifyPhoneNumberController$$Lambda$1(signInVerifyPhoneNumberController);
    }

    public void onClick(View view) {
        this.arg$1.mBackNavigationConnector.get().onNext(null);
    }
}
