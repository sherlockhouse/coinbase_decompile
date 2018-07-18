package com.coinbase.android.signin;

import rx.functions.Func1;

final /* synthetic */ class LoginController$$Lambda$10 implements Func1 {
    private final LoginController arg$1;

    private LoginController$$Lambda$10(LoginController loginController) {
        this.arg$1 = loginController;
    }

    public static Func1 lambdaFactory$(LoginController loginController) {
        return new LoginController$$Lambda$10(loginController);
    }

    public Object call(Object obj) {
        return LoginController.lambda$login$9(this.arg$1, (LoginAuthResult) obj);
    }
}
