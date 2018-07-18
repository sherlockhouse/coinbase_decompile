package com.coinbase.v1.entity;

import java.io.Serializable;

public class TransferNode implements Serializable {
    private static final long serialVersionUID = 640048507035606528L;
    private Transfer _transfer;

    public Transfer getTransfer() {
        return this._transfer;
    }

    public void setTransfer(Transfer transfer) {
        this._transfer = transfer;
    }
}
