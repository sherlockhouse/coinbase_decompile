package com.coinbase.v1.entity;

import java.io.Serializable;

public class Token implements Serializable {
    private static final long serialVersionUID = 5190861502452162126L;
    private String address;
    private String tokenId;

    public String getTokenId() {
        return this.tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
