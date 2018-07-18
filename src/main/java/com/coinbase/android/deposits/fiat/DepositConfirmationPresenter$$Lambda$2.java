package com.coinbase.android.deposits.fiat;

import rx.functions.Action1;

final /* synthetic */ class DepositConfirmationPresenter$$Lambda$2 implements Action1 {
    private final DepositConfirmationPresenter arg$1;

    private DepositConfirmationPresenter$$Lambda$2(DepositConfirmationPresenter depositConfirmationPresenter) {
        this.arg$1 = depositConfirmationPresenter;
    }

    public static Action1 lambdaFactory$(DepositConfirmationPresenter depositConfirmationPresenter) {
        return new DepositConfirmationPresenter$$Lambda$2(depositConfirmationPresenter);
    }

    public void call(Object obj) {
        DepositConfirmationPresenter.lambda$commitDeposit$1(this.arg$1, (Throwable) obj);
    }
}
