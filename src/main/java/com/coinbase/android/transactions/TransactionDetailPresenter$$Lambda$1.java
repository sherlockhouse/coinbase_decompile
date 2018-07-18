package com.coinbase.android.transactions;

import android.util.Pair;
import rx.functions.Action1;

final /* synthetic */ class TransactionDetailPresenter$$Lambda$1 implements Action1 {
    private final TransactionDetailPresenter arg$1;

    private TransactionDetailPresenter$$Lambda$1(TransactionDetailPresenter transactionDetailPresenter) {
        this.arg$1 = transactionDetailPresenter;
    }

    public static Action1 lambdaFactory$(TransactionDetailPresenter transactionDetailPresenter) {
        return new TransactionDetailPresenter$$Lambda$1(transactionDetailPresenter);
    }

    public void call(Object obj) {
        TransactionDetailPresenter.lambda$cancelTransaction$0(this.arg$1, (Pair) obj);
    }
}
