package com.coinbase.android.transactions;

import rx.functions.Action1;

final /* synthetic */ class TransactionDetailPresenter$$Lambda$2 implements Action1 {
    private final TransactionDetailPresenter arg$1;

    private TransactionDetailPresenter$$Lambda$2(TransactionDetailPresenter transactionDetailPresenter) {
        this.arg$1 = transactionDetailPresenter;
    }

    public static Action1 lambdaFactory$(TransactionDetailPresenter transactionDetailPresenter) {
        return new TransactionDetailPresenter$$Lambda$2(transactionDetailPresenter);
    }

    public void call(Object obj) {
        TransactionDetailPresenter.lambda$cancelTransaction$1(this.arg$1, (Throwable) obj);
    }
}
