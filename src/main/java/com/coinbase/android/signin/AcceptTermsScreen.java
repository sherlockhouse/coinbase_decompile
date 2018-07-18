package com.coinbase.android.signin;

import android.text.Spanned;

public interface AcceptTermsScreen {
    void hideProgressDialog();

    void setTermsDescription(Spanned spanned);

    void showProgressDialog();
}
