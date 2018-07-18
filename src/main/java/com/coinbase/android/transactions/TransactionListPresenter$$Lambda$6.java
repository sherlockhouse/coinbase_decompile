package com.coinbase.android.transactions;

import rx.functions.Action1;

final /* synthetic */ class TransactionListPresenter$$Lambda$6 implements Action1 {
    private final TransactionListPresenter arg$1;

    private TransactionListPresenter$$Lambda$6(TransactionListPresenter transactionListPresenter) {
        this.arg$1 = transactionListPresenter;
    }

    public static Action1 lambdaFactory$(TransactionListPresenter transactionListPresenter) {
        return new TransactionListPresenter$$Lambda$6(transactionListPresenter);
    }

    public void call(Object obj) {
        this.arg$1.handleErrorResponse((Throwable) obj);
    }
}
