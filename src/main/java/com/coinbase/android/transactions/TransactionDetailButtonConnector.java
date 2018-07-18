package com.coinbase.android.transactions;

import rx.subjects.PublishSubject;

public class TransactionDetailButtonConnector {
    private final PublishSubject<ActionType> mTransactionDetailButtonSubject;

    public TransactionDetailButtonConnector() {
        this(PublishSubject.create());
    }

    public TransactionDetailButtonConnector(PublishSubject<ActionType> transactionDetailButtonSubject) {
        this.mTransactionDetailButtonSubject = transactionDetailButtonSubject;
    }

    public PublishSubject<ActionType> get() {
        return this.mTransactionDetailButtonSubject;
    }
}
