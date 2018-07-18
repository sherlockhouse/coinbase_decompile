package com.coinbase.android.signin;

import rx.functions.Func1;

final /* synthetic */ class TwoFactorRouter$$Lambda$1 implements Func1 {
    private final TwoFactorRouter arg$1;
    private final String arg$2;

    private TwoFactorRouter$$Lambda$1(TwoFactorRouter twoFactorRouter, String str) {
        this.arg$1 = twoFactorRouter;
        this.arg$2 = str;
    }

    public static Func1 lambdaFactory$(TwoFactorRouter twoFactorRouter, String str) {
        return new TwoFactorRouter$$Lambda$1(twoFactorRouter, str);
    }

    public Object call(Object obj) {
        return TwoFactorRouter.lambda$routeToNext$0(this.arg$1, this.arg$2, (Boolean) obj);
    }
}
