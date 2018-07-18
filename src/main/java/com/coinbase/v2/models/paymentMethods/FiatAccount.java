package com.coinbase.v2.models.paymentMethods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FiatAccount {
    @SerializedName("id")
    @Expose
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
