package com.coinbase.android.transactions;

import android.util.Pair;
import rx.functions.Func1;

final /* synthetic */ class TransactionListPresenter$$Lambda$7 implements Func1 {
    private final TransactionListPresenter arg$1;

    private TransactionListPresenter$$Lambda$7(TransactionListPresenter transactionListPresenter) {
        this.arg$1 = transactionListPresenter;
    }

    public static Func1 lambdaFactory$(TransactionListPresenter transactionListPresenter) {
        return new TransactionListPresenter$$Lambda$7(transactionListPresenter);
    }

    public Object call(Object obj) {
        return TransactionListPresenter.lambda$fetchNextTransactions$6(this.arg$1, (Pair) obj);
    }
}
