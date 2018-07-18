package com.coinbase.android.signin;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class LoginController$$Lambda$7 implements OnClickListener {
    private final LoginController arg$1;

    private LoginController$$Lambda$7(LoginController loginController) {
        this.arg$1 = loginController;
    }

    public static OnClickListener lambdaFactory$(LoginController loginController) {
        return new LoginController$$Lambda$7(loginController);
    }

    public void onClick(View view) {
        this.arg$1.goToWebsite("https://www.coinbase.com/password_resets/new");
    }
}
