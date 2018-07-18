package com.coinbase.android.signin;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class TwoFactorPresenter$$Lambda$8 implements Action1 {
    private final TwoFactorPresenter arg$1;

    private TwoFactorPresenter$$Lambda$8(TwoFactorPresenter twoFactorPresenter) {
        this.arg$1 = twoFactorPresenter;
    }

    public static Action1 lambdaFactory$(TwoFactorPresenter twoFactorPresenter) {
        return new TwoFactorPresenter$$Lambda$8(twoFactorPresenter);
    }

    public void call(Object obj) {
        TwoFactorPresenter.lambda$login$7(this.arg$1, (Pair) obj);
    }
}
