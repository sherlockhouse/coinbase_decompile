package com.coinbase.android.transactions;

import com.coinbase.android.ControllerScope;

public class TransactionListPresenterModule {
    private final TransactionListScreen mScreen;

    public TransactionListPresenterModule(TransactionListScreen screen) {
        this.mScreen = screen;
    }

    @ControllerScope
    public TransactionListScreen providesTransactionListScreen() {
        return this.mScreen;
    }
}
