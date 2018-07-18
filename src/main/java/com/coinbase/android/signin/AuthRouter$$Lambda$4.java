package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class AuthRouter$$Lambda$4 implements Func1 {
    private final AuthRouter arg$1;

    private AuthRouter$$Lambda$4(AuthRouter authRouter) {
        this.arg$1 = authRouter;
    }

    public static Func1 lambdaFactory$(AuthRouter authRouter) {
        return new AuthRouter$$Lambda$4(authRouter);
    }

    public Object call(Object obj) {
        return this.arg$1.routeToNext((Pair) obj);
    }
}
