package com.coinbase.v2.models.transfers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserWarning {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("message")
    @Expose
    private String message;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
