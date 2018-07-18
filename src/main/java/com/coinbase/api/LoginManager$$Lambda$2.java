package com.coinbase.api;

import com.coinbase.api.LoginManager.TokenAndUserResponse;
import com.coinbase.api.LoginManager.TokenResponse;
import rx.functions.Action1;

final /* synthetic */ class LoginManager$$Lambda$2 implements Action1 {
    private final LoginManager arg$1;
    private final TokenAndUserResponse arg$2;

    private LoginManager$$Lambda$2(LoginManager loginManager, TokenAndUserResponse tokenAndUserResponse) {
        this.arg$1 = loginManager;
        this.arg$2 = tokenAndUserResponse;
    }

    public static Action1 lambdaFactory$(LoginManager loginManager, TokenAndUserResponse tokenAndUserResponse) {
        return new LoginManager$$Lambda$2(loginManager, tokenAndUserResponse);
    }

    public void call(Object obj) {
        this.arg$1.refreshAccessToken((TokenResponse) this.arg$2);
    }
}
