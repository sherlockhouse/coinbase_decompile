package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class AuthRouter$$Lambda$3 implements Action1 {
    private final AuthRouter arg$1;

    private AuthRouter$$Lambda$3(AuthRouter authRouter) {
        this.arg$1 = authRouter;
    }

    public static Action1 lambdaFactory$(AuthRouter authRouter) {
        return new AuthRouter$$Lambda$3(authRouter);
    }

    public void call(Object obj) {
        AuthRouter.lambda$routeToNextObservable$2(this.arg$1, (Throwable) obj);
    }
}
