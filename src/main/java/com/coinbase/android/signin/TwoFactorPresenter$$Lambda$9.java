package com.coinbase.android.signin;

import rx.functions.Action1;

final /* synthetic */ class TwoFactorPresenter$$Lambda$9 implements Action1 {
    private final TwoFactorPresenter arg$1;

    private TwoFactorPresenter$$Lambda$9(TwoFactorPresenter twoFactorPresenter) {
        this.arg$1 = twoFactorPresenter;
    }

    public static Action1 lambdaFactory$(TwoFactorPresenter twoFactorPresenter) {
        return new TwoFactorPresenter$$Lambda$9(twoFactorPresenter);
    }

    public void call(Object obj) {
        TwoFactorPresenter.lambda$login$8(this.arg$1, (Throwable) obj);
    }
}
