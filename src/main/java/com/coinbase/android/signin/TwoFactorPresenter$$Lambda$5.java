package com.coinbase.android.signin;

import rx.functions.Action0;

final /* synthetic */ class TwoFactorPresenter$$Lambda$5 implements Action0 {
    private final TwoFactorPresenter arg$1;

    private TwoFactorPresenter$$Lambda$5(TwoFactorPresenter twoFactorPresenter) {
        this.arg$1 = twoFactorPresenter;
    }

    public static Action0 lambdaFactory$(TwoFactorPresenter twoFactorPresenter) {
        return new TwoFactorPresenter$$Lambda$5(twoFactorPresenter);
    }

    public void call() {
        TwoFactorPresenter.lambda$onBackPressed$4(this.arg$1);
    }
}
