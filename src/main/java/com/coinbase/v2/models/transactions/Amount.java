package com.coinbase.v2.models.transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Amount {
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("currency")
    @Expose
    private String currency;

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
