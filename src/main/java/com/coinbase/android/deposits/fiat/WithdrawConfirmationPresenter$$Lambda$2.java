package com.coinbase.android.deposits.fiat;

import rx.functions.Action1;

final /* synthetic */ class WithdrawConfirmationPresenter$$Lambda$2 implements Action1 {
    private final WithdrawConfirmationPresenter arg$1;

    private WithdrawConfirmationPresenter$$Lambda$2(WithdrawConfirmationPresenter withdrawConfirmationPresenter) {
        this.arg$1 = withdrawConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(WithdrawConfirmationPresenter withdrawConfirmationPresenter) {
        return new WithdrawConfirmationPresenter$$Lambda$2(withdrawConfirmationPresenter);
    }

    public void call(Object obj) {
        WithdrawConfirmationPresenter.lambda$commitWithdraw$1(this.arg$1, (Throwable) obj);
    }
}
