package com.coinbase.api.internal.models.privacy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserPrivacyPolicy {
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
