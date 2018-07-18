package com.coinbase.android.signin;

import rx.functions.Func1;

final /* synthetic */ class TwoFactorPresenter$$Lambda$6 implements Func1 {
    private final TwoFactorPresenter arg$1;

    private TwoFactorPresenter$$Lambda$6(TwoFactorPresenter twoFactorPresenter) {
        this.arg$1 = twoFactorPresenter;
    }

    public static Func1 lambdaFactory$(TwoFactorPresenter twoFactorPresenter) {
        return new TwoFactorPresenter$$Lambda$6(twoFactorPresenter);
    }

    public Object call(Object obj) {
        return TwoFactorPresenter.lambda$login$5(this.arg$1, (LoginAuthResult) obj);
    }
}
