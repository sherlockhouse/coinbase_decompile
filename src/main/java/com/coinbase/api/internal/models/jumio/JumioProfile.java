package com.coinbase.api.internal.models.jumio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JumioProfile {
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
