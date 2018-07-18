package com.coinbase.v2.models.transfers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Secure3dVerification {
    @SerializedName("payload")
    @Expose
    private List<Payload> payload = new ArrayList();
    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Payload> getPayload() {
        return this.payload;
    }

    public void setPayload(List<Payload> payload) {
        this.payload = payload;
    }
}
