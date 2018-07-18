package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class LoginController$$Lambda$6 implements Action1 {
    private final LoginController arg$1;

    private LoginController$$Lambda$6(LoginController loginController) {
        this.arg$1 = loginController;
    }

    public static Action1 lambdaFactory$(LoginController loginController) {
        return new LoginController$$Lambda$6(loginController);
    }

    public void call(Object obj) {
        this.arg$1.onDeviceVerifyFailed((Exception) obj);
    }
}
