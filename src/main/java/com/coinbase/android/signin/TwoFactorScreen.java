package com.coinbase.android.signin;

import android.view.View.OnClickListener;

public interface TwoFactorScreen {
    String getEnteredText();

    void hideProgress();

    void hideTwoFactorView();

    void setActionClickable(boolean z);

    void setActionOnClickListener(OnClickListener onClickListener);

    void setActionText(String str);

    void showProgress();

    void showTwoFactorView();
}
