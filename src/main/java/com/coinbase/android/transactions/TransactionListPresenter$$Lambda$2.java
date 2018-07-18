package com.coinbase.android.transactions;

import rx.functions.Func1;

final /* synthetic */ class TransactionListPresenter$$Lambda$2 implements Func1 {
    private static final TransactionListPresenter$$Lambda$2 instance = new TransactionListPresenter$$Lambda$2();

    private TransactionListPresenter$$Lambda$2() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return TransactionListPresenter.lambda$onInit$1((ActionType) obj);
    }
}
