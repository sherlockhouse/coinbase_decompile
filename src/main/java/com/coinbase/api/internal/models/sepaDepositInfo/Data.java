package com.coinbase.api.internal.models.sepaDepositInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("highlight")
    @Expose
    private Boolean highlight;
    @SerializedName("key")
    @Expose
    private String key;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("value")
    @Expose
    private String value;

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHighlight() {
        return this.highlight;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }
}
