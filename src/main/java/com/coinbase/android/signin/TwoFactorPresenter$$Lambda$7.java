package com.coinbase.android.signin;

import rx.functions.Func1;

final /* synthetic */ class TwoFactorPresenter$$Lambda$7 implements Func1 {
    private final TwoFactorPresenter arg$1;

    private TwoFactorPresenter$$Lambda$7(TwoFactorPresenter twoFactorPresenter) {
        this.arg$1 = twoFactorPresenter;
    }

    public static Func1 lambdaFactory$(TwoFactorPresenter twoFactorPresenter) {
        return new TwoFactorPresenter$$Lambda$7(twoFactorPresenter);
    }

    public Object call(Object obj) {
        return this.arg$1.mRouter.routeToNext((LoginAuthResult) obj);
    }
}
