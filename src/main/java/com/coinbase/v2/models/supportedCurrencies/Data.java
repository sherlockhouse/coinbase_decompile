package com.coinbase.v2.models.supportedCurrencies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("min_size")
    @Expose
    private String minSize;
    @SerializedName("name")
    @Expose
    private String name;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinSize() {
        return this.minSize;
    }

    public void setMinSize(String minSize) {
        this.minSize = minSize;
    }
}
