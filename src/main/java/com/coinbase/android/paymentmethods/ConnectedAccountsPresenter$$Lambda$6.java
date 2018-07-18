package com.coinbase.android.paymentmethods;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class ConnectedAccountsPresenter$$Lambda$6 implements Action1 {
    private final ConnectedAccountsPresenter arg$1;

    private ConnectedAccountsPresenter$$Lambda$6(ConnectedAccountsPresenter connectedAccountsPresenter) {
        this.arg$1 = connectedAccountsPresenter;
    }

    public static Action1 lambdaFactory$(ConnectedAccountsPresenter connectedAccountsPresenter) {
        return new ConnectedAccountsPresenter$$Lambda$6(connectedAccountsPresenter);
    }

    public void call(Object obj) {
        ConnectedAccountsPresenter.lambda$fetchPaymentMethods$5(this.arg$1, (Pair) obj);
    }
}
