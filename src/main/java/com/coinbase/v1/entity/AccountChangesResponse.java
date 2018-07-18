package com.coinbase.v1.entity;

import java.util.List;
import org.joda.money.Money;

public class AccountChangesResponse extends Response {
    private List<AccountChange> accountChanges;
    private Money balance;
    private User currentUser;
    private Money nativeBalance;

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public Money getBalance() {
        return this.balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Money getNativeBalance() {
        return this.nativeBalance;
    }

    public void setNativeBalance(Money nativeBalance) {
        this.nativeBalance = nativeBalance;
    }

    public List<AccountChange> getAccountChanges() {
        return this.accountChanges;
    }

    public void setAccountChanges(List<AccountChange> accountChanges) {
        this.accountChanges = accountChanges;
    }
}
