package com.coinbase.android.deposits.fiat;

import rx.functions.Action1;

final /* synthetic */ class WithdrawFiatPresenter$$Lambda$2 implements Action1 {
    private final WithdrawFiatPresenter arg$1;

    private WithdrawFiatPresenter$$Lambda$2(WithdrawFiatPresenter withdrawFiatPresenter) {
        this.arg$1 = withdrawFiatPresenter;
    }

    public static Action1 lambdaFactory$(WithdrawFiatPresenter withdrawFiatPresenter) {
        return new WithdrawFiatPresenter$$Lambda$2(withdrawFiatPresenter);
    }

    public void call(Object obj) {
        WithdrawFiatPresenter.lambda$updateQuickstart$1(this.arg$1, (Throwable) obj);
    }
}
