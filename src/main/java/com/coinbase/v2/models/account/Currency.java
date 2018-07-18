package com.coinbase.v2.models.account;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Currency {
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("exponent")
    @Expose
    private Integer exponent;
    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("uri_scheme")
    @Expose
    private String uriScheme;

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

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getExponent() {
        return this.exponent;
    }

    public void setExponent(Integer exponent) {
        this.exponent = exponent;
    }

    public String getUriScheme() {
        return this.uriScheme;
    }

    public void setUriScheme(String uriScheme) {
        this.uriScheme = uriScheme;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
