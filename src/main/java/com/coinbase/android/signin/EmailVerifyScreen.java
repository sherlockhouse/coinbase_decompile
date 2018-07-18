package com.coinbase.android.signin;

import android.text.Spanned;

public interface EmailVerifyScreen {
    void hideEmailView();

    void hideProgress();

    void setMessage(Spanned spanned);

    void showEmailView();

    void showProgress();
}
