package com.coinbase.android.signin;

public interface SignInVerifyPhoneNumberScreen {
    String getInput();

    void hideProgress();

    void hideProgressDialog();

    void hideVerifyPhoneNumberView();

    void setVerificationText(CharSequence charSequence);

    void showProgress();

    void showProgressDialog();

    void showVerifyPhoneNumberView();
}
