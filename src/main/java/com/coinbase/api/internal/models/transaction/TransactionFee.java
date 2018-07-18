package com.coinbase.api.internal.models.transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionFee {
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
