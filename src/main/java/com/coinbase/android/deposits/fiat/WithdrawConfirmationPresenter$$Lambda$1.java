package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class WithdrawConfirmationPresenter$$Lambda$1 implements Action1 {
    private final WithdrawConfirmationPresenter arg$1;

    private WithdrawConfirmationPresenter$$Lambda$1(WithdrawConfirmationPresenter withdrawConfirmationPresenter) {
        this.arg$1 = withdrawConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(WithdrawConfirmationPresenter withdrawConfirmationPresenter) {
        return new WithdrawConfirmationPresenter$$Lambda$1(withdrawConfirmationPresenter);
    }

    public void call(Object obj) {
        WithdrawConfirmationPresenter.lambda$commitWithdraw$0(this.arg$1, (Pair) obj);
    }
}
