package com.coinbase.v1.entity;

public class TransferResponse extends Response {
    private static final long serialVersionUID = 5487491088482969188L;
    private Transfer _transfer;

    public Transfer getTransfer() {
        return this._transfer;
    }

    public void setTransfer(Transfer transfer) {
        this._transfer = transfer;
    }
}
