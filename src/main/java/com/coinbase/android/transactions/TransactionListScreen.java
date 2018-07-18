package com.coinbase.android.transactions;

import com.coinbase.v2.models.transactions.Data;

public interface TransactionListScreen {
    void fetchingTransactionsComplete();

    void hideDetailLayout();

    boolean isDetailLayoutVisible();

    void notifyDataSetChanged();

    void showDetailLayout(Data data);

    void startFetchingTransactions();
}
