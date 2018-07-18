package com.coinbase.v2.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("is_in_europe")
    @Expose
    private Boolean isInEurope;
    @SerializedName("name")
    @Expose
    private String name;

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsInEurope() {
        return this.isInEurope;
    }

    public void setIsInEurope(Boolean isInEurope) {
        this.isInEurope = isInEurope;
    }
}
