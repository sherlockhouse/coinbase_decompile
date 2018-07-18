package com.coinbase.api;

import android.util.Pair;
import com.coinbase.api.LoginManager.TokenAndUserResponse;
import rx.functions.Action1;

final /* synthetic */ class LoginManager$$Lambda$1 implements Action1 {
    private final LoginManager arg$1;
    private final TokenAndUserResponse arg$2;

    private LoginManager$$Lambda$1(LoginManager loginManager, TokenAndUserResponse tokenAndUserResponse) {
        this.arg$1 = loginManager;
        this.arg$2 = tokenAndUserResponse;
    }

    public static Action1 lambdaFactory$(LoginManager loginManager, TokenAndUserResponse tokenAndUserResponse) {
        return new LoginManager$$Lambda$1(loginManager, tokenAndUserResponse);
    }

    public void call(Object obj) {
        LoginManager.lambda$checkTokenValidity$0(this.arg$1, this.arg$2, (Pair) obj);
    }
}
