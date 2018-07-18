package com.coinbase.api.internal.models.privacy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("html")
    @Expose
    private String html;

    public String getHtml() {
        return this.html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
