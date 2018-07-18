package com.coinbase.v2.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
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
