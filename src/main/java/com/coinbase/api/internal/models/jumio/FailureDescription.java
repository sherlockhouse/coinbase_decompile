package com.coinbase.api.internal.models.jumio;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FailureDescription {
    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("hint")
    @Expose
    private String hint;

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHint() {
        return this.hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
