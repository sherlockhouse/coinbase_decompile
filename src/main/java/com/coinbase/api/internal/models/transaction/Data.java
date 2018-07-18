package com.coinbase.api.internal.models.transaction;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("user_fee")
    @Expose
    private UserFee userFee;

    public UserFee getUserFee() {
        return this.userFee;
    }

    public void setUserFee(UserFee userFee) {
        this.userFee = userFee;
    }
}
