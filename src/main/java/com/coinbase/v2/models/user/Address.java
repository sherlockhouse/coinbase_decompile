package com.coinbase.v2.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("line1")
    @Expose
    private String line1;
    @SerializedName("line2")
    @Expose
    private String line2;
    @SerializedName("line3")
    @Expose
    private Object line3;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("state")
    @Expose
    private String state;

    public String getLine1() {
        return this.line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return this.line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public Object getLine3() {
        return this.line3;
    }

    public void setLine3(Object line3) {
        this.line3 = line3;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
