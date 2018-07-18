package com.coinbase.android.deposits.fiat;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class DepositConfirmationPresenter$$Lambda$1 implements Action1 {
    private final DepositConfirmationPresenter arg$1;

    private DepositConfirmationPresenter$$Lambda$1(DepositConfirmationPresenter depositConfirmationPresenter) {
        this.arg$1 = depositConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(DepositConfirmationPresenter depositConfirmationPresenter) {
        return new DepositConfirmationPresenter$$Lambda$1(depositConfirmationPresenter);
    }

    public void call(Object obj) {
        DepositConfirmationPresenter.lambda$commitDeposit$0(this.arg$1, (Pair) obj);
    }
}
