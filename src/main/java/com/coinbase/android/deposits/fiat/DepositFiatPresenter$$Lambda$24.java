package com.coinbase.android.deposits.fiat;

import rx.functions.Action1;

final /* synthetic */ class DepositFiatPresenter$$Lambda$24 implements Action1 {
    private final DepositFiatPresenter arg$1;

    private DepositFiatPresenter$$Lambda$24(DepositFiatPresenter depositFiatPresenter) {
        this.arg$1 = depositFiatPresenter;
    }

    public static Action1 lambdaFactory$(DepositFiatPresenter depositFiatPresenter) {
        return new DepositFiatPresenter$$Lambda$24(depositFiatPresenter);
    }

    public void call(Object obj) {
        DepositFiatPresenter.lambda$previewDeposit$23(this.arg$1, (Throwable) obj);
    }
}
