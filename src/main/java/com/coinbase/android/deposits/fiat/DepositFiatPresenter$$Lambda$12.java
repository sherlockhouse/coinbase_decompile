package com.coinbase.android.deposits.fiat;

import rx.functions.Action1;

final /* synthetic */ class DepositFiatPresenter$$Lambda$12 implements Action1 {
    private final DepositFiatPresenter arg$1;

    private DepositFiatPresenter$$Lambda$12(DepositFiatPresenter depositFiatPresenter) {
        this.arg$1 = depositFiatPresenter;
    }

    public static Action1 lambdaFactory$(DepositFiatPresenter depositFiatPresenter) {
        return new DepositFiatPresenter$$Lambda$12(depositFiatPresenter);
    }

    public void call(Object obj) {
        DepositFiatPresenter.lambda$hookUpLinkedAccounts$11(this.arg$1, (Void) obj);
    }
}
