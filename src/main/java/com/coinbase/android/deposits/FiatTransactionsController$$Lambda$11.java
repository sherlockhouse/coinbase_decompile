package com.coinbase.android.deposits;

import rx.functions.Action1;

final /* synthetic */ class FiatTransactionsController$$Lambda$11 implements Action1 {
    private static final FiatTransactionsController$$Lambda$11 instance = new FiatTransactionsController$$Lambda$11();

    private FiatTransactionsController$$Lambda$11() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        FiatTransactionsController.lambda$refreshData$10((Throwable) obj);
    }
}
