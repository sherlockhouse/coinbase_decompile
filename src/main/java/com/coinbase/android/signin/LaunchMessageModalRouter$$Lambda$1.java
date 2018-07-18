package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class LaunchMessageModalRouter$$Lambda$1 implements Func1 {
    private final LaunchMessageModalRouter arg$1;

    private LaunchMessageModalRouter$$Lambda$1(LaunchMessageModalRouter launchMessageModalRouter) {
        this.arg$1 = launchMessageModalRouter;
    }

    public static Func1 lambdaFactory$(LaunchMessageModalRouter launchMessageModalRouter) {
        return new LaunchMessageModalRouter$$Lambda$1(launchMessageModalRouter);
    }

    public Object call(Object obj) {
        return LaunchMessageModalRouter.lambda$route$1(this.arg$1, (Pair) obj);
    }
}
