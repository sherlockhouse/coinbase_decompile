package com.coinbase.v1.entity;

import java.util.List;

public class AccountsResponse extends Response {
    private static final long serialVersionUID = -4054933671856737119L;
    private List<Account> _accounts;

    public List<Account> getAccounts() {
        return this._accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this._accounts = accounts;
    }
}
