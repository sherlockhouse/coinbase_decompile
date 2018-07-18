package com.coinbase.v1.entity;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 3130834102229546418L;
    private Account _account;
    private String _accountId;
    private Address _address;
    private Application _application;
    private Button _button;
    private String _clientId;
    private Boolean _commit;
    private String _currency;
    private String _paymentMethodId;
    private Double _qty;
    private Report _report;
    private String _scopes;
    private String _tokenId;
    private Transaction _transaction;
    private User _user;

    public Report getReport() {
        return this._report;
    }

    public void setReport(Report report) {
        this._report = report;
    }

    public Address getAddress() {
        return this._address;
    }

    public void setAddress(Address address) {
        this._address = address;
    }

    public String getClientId() {
        return this._clientId;
    }

    public void setClientId(String clientId) {
        this._clientId = clientId;
    }

    public String getScopes() {
        return this._scopes;
    }

    public void setScopes(String scopes) {
        this._scopes = scopes;
    }

    public User getUser() {
        return this._user;
    }

    public void setUser(User user) {
        this._user = user;
    }

    public String getPaymentMethodId() {
        return this._paymentMethodId;
    }

    public void setPaymentMethodId(String paymentMethodId) {
        this._paymentMethodId = paymentMethodId;
    }

    public Double getQty() {
        return this._qty;
    }

    public void setQty(Double qty) {
        this._qty = qty;
    }

    public String getAccountId() {
        return this._accountId;
    }

    public void setAccountId(String accountId) {
        this._accountId = accountId;
    }

    public Button getButton() {
        return this._button;
    }

    public void setButton(Button button) {
        this._button = button;
    }

    public Account getAccount() {
        return this._account;
    }

    public void setAccount(Account account) {
        this._account = account;
    }

    public Transaction getTransaction() {
        return this._transaction;
    }

    public void setTransaction(Transaction transaction) {
        this._transaction = transaction;
    }

    public String getTokenId() {
        return this._tokenId;
    }

    public void setTokenId(String tokenId) {
        this._tokenId = tokenId;
    }

    public Application getApplication() {
        return this._application;
    }

    public void setApplication(Application application) {
        this._application = application;
    }

    public String getCurrency() {
        return this._currency;
    }

    public void setCurrency(String currency) {
        this._currency = currency;
    }

    public Boolean getCommit() {
        return this._commit;
    }

    public void setCommit(Boolean commit) {
        this._commit = commit;
    }
}
