package com.coinbase.api;

final /* synthetic */ class LoginManager$$Lambda$5 implements Runnable {
    private final LoginManager arg$1;

    private LoginManager$$Lambda$5(LoginManager loginManager) {
        this.arg$1 = loginManager;
    }

    public static Runnable lambdaFactory$(LoginManager loginManager) {
        return new LoginManager$$Lambda$5(loginManager);
    }

    public void run() {
        LoginManager.lambda$null$2(this.arg$1);
    }
}
