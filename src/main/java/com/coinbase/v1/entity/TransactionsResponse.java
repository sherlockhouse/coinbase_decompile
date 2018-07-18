package com.coinbase.v1.entity;

import com.coinbase.v1.deserializer.TransactionsLifter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;
import org.joda.money.Money;

public class TransactionsResponse extends Response {
    private static final long serialVersionUID = 1738758156595044771L;
    private Money _balance;
    private User _currentUser;
    private Money _nativeBalance;
    private List<Transaction> _transactions;

    public List<Transaction> getTransactions() {
        return this._transactions;
    }

    @JsonDeserialize(converter = TransactionsLifter.class)
    public void setTransactions(List<Transaction> transactions) {
        this._transactions = transactions;
    }

    public User getCurrentUser() {
        return this._currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this._currentUser = currentUser;
    }

    public Money getBalance() {
        return this._balance;
    }

    public void setBalance(Money balance) {
        this._balance = balance;
    }

    public Money getNativeBalance() {
        return this._nativeBalance;
    }

    public void setNativeBalance(Money nativeBalance) {
        this._nativeBalance = nativeBalance;
    }
}
