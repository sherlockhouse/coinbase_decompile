package com.coinbase.android.deposits.fiat;

import rx.functions.Action1;

final /* synthetic */ class DepositFiatPresenter$$Lambda$18 implements Action1 {
    private final DepositFiatPresenter arg$1;

    private DepositFiatPresenter$$Lambda$18(DepositFiatPresenter depositFiatPresenter) {
        this.arg$1 = depositFiatPresenter;
    }

    public static Action1 lambdaFactory$(DepositFiatPresenter depositFiatPresenter) {
        return new DepositFiatPresenter$$Lambda$18(depositFiatPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Error subscribing to LinkedAccountConnector for getPaymentMethodSelectedSubject()");
    }
}
