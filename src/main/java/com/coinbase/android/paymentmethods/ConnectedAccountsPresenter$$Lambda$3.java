package com.coinbase.android.paymentmethods;

import rx.functions.Action1;

final /* synthetic */ class ConnectedAccountsPresenter$$Lambda$3 implements Action1 {
    private final ConnectedAccountsPresenter arg$1;

    private ConnectedAccountsPresenter$$Lambda$3(ConnectedAccountsPresenter connectedAccountsPresenter) {
        this.arg$1 = connectedAccountsPresenter;
    }

    public static Action1 lambdaFactory$(ConnectedAccountsPresenter connectedAccountsPresenter) {
        return new ConnectedAccountsPresenter$$Lambda$3(connectedAccountsPresenter);
    }

    public void call(Object obj) {
        this.arg$1.mLogger.error("Error in onPaymentMethodsUpdated", (Throwable) obj);
    }
}
