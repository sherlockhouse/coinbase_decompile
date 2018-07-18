package com.coinbase.android.event;

import com.coinbase.v2.models.transactions.Data;
import com.coinbase.v2.models.transactions.Transaction;

public class TransferMadeEvent {
    public Data data;
    public boolean isConsumed = false;
    public Transaction transaction;

    public TransferMadeEvent(Data data, Transaction transaction) {
        this.data = data;
        this.transaction = transaction;
    }
}
