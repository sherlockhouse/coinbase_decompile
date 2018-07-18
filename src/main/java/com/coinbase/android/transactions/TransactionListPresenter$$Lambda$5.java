package com.coinbase.android.transactions;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class TransactionListPresenter$$Lambda$5 implements Action1 {
    private final TransactionListPresenter arg$1;

    private TransactionListPresenter$$Lambda$5(TransactionListPresenter transactionListPresenter) {
        this.arg$1 = transactionListPresenter;
    }

    public static Action1 lambdaFactory$(TransactionListPresenter transactionListPresenter) {
        return new TransactionListPresenter$$Lambda$5(transactionListPresenter);
    }

    public void call(Object obj) {
        TransactionListPresenter.lambda$fetchTransactions$4(this.arg$1, (Pair) obj);
    }
}
