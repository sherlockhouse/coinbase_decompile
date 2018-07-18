package com.coinbase.api;

import rx.functions.Action1;

final /* synthetic */ class LoginManager$$Lambda$4 implements Action1 {
    private static final LoginManager$$Lambda$4 instance = new LoginManager$$Lambda$4();

    private LoginManager$$Lambda$4() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        LoginManager.lambda$updateAccounts$4((Throwable) obj);
    }
}
