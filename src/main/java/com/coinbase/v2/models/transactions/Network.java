package com.coinbase.v2.models.transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Network {
    @SerializedName("confirmations")
    @Expose
    private Integer confirmations;
    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getConfirmations() {
        return this.confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }
}
