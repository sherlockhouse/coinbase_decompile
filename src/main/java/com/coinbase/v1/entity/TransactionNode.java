package com.coinbase.v1.entity;

import java.io.Serializable;

public class TransactionNode implements Serializable {
    private static final long serialVersionUID = 7122426900695444540L;
    private Transaction _transaction;

    public Transaction getTransaction() {
        return this._transaction;
    }

    public void setTransaction(Transaction transaction) {
        this._transaction = transaction;
    }
}
