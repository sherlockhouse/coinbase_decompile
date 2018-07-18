package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class AuthRouter$$Lambda$1 implements Action1 {
    private static final AuthRouter$$Lambda$1 instance = new AuthRouter$$Lambda$1();

    private AuthRouter$$Lambda$1() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        AuthRouter.lambda$routeToNext$0((Void) obj);
    }
}
