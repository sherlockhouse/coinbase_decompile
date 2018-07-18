package com.coinbase.android.signin;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SignInPhoneNumberController$$Lambda$1 implements OnClickListener {
    private final SignInPhoneNumberController arg$1;

    private SignInPhoneNumberController$$Lambda$1(SignInPhoneNumberController signInPhoneNumberController) {
        this.arg$1 = signInPhoneNumberController;
    }

    public static OnClickListener lambdaFactory$(SignInPhoneNumberController signInPhoneNumberController) {
        return new SignInPhoneNumberController$$Lambda$1(signInPhoneNumberController);
    }

    public void onClick(View view) {
        this.arg$1.mPresenter.onUserConfirm(this.arg$1.mBinding.etVerifyPhoneInput.getText().toString(), ((CountryCode) this.arg$1.mBinding.spinnerVerifyPhoneCountry.getSelectedItem()).getAlpha2());
    }
}
