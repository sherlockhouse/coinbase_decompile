package com.coinbase.v2.models.price;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price {
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
