package com.coinbase.android.paymentmethods;

import com.coinbase.android.event.ClassConsumableEvent;
import rx.functions.Func1;

final /* synthetic */ class ConnectedAccountsPresenter$$Lambda$1 implements Func1 {
    private static final ConnectedAccountsPresenter$$Lambda$1 instance = new ConnectedAccountsPresenter$$Lambda$1();

    private ConnectedAccountsPresenter$$Lambda$1() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ConnectedAccountsPresenter.lambda$onShow$0((ClassConsumableEvent) obj);
    }
}
