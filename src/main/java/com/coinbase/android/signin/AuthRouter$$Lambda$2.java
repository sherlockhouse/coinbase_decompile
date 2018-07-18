package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class AuthRouter$$Lambda$2 implements Action1 {
    private static final AuthRouter$$Lambda$2 instance = new AuthRouter$$Lambda$2();

    private AuthRouter$$Lambda$2() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        AuthRouter.lambda$routeToNext$1((Throwable) obj);
    }
}
