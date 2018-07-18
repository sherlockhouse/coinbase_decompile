package com.coinbase.android.signin;

import com.coinbase.android.signin.EmailVerifiedConnector.Type;
import rx.functions.Action1;

final /* synthetic */ class LoginController$$Lambda$3 implements Action1 {
    private final LoginController arg$1;

    private LoginController$$Lambda$3(LoginController loginController) {
        this.arg$1 = loginController;
    }

    public static Action1 lambdaFactory$(LoginController loginController) {
        return new LoginController$$Lambda$3(loginController);
    }

    public void call(Object obj) {
        LoginController.lambda$onCreateView$2(this.arg$1, (Type) obj);
    }
}
