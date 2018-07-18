package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class WithdrawFiatPresenter$$Lambda$27 implements Action1 {
    private final WithdrawFiatPresenter arg$1;

    private WithdrawFiatPresenter$$Lambda$27(WithdrawFiatPresenter withdrawFiatPresenter) {
        this.arg$1 = withdrawFiatPresenter;
    }

    public static Action1 lambdaFactory$(WithdrawFiatPresenter withdrawFiatPresenter) {
        return new WithdrawFiatPresenter$$Lambda$27(withdrawFiatPresenter);
    }

    public void call(Object obj) {
        WithdrawFiatPresenter.lambda$refreshPaymentMethods$26(this.arg$1, (Pair) obj);
    }
}
