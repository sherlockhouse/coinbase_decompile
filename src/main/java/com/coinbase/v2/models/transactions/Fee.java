package com.coinbase.v2.models.transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fee {
    @SerializedName("amount")
    @Expose
    private Amount amount;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("type")
    @Expose
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Amount getAmount() {
        return this.amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
