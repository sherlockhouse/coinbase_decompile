package com.coinbase.android.paymentmethods;

import java.util.List;
import rx.functions.Action1;

final /* synthetic */ class ConnectedAccountsPresenter$$Lambda$4 implements Action1 {
    private final ConnectedAccountsPresenter arg$1;

    private ConnectedAccountsPresenter$$Lambda$4(ConnectedAccountsPresenter connectedAccountsPresenter) {
        this.arg$1 = connectedAccountsPresenter;
    }

    public static Action1 lambdaFactory$(ConnectedAccountsPresenter connectedAccountsPresenter) {
        return new ConnectedAccountsPresenter$$Lambda$4(connectedAccountsPresenter);
    }

    public void call(Object obj) {
        ConnectedAccountsPresenter.lambda$onShow$3(this.arg$1, (List) obj);
    }
}
