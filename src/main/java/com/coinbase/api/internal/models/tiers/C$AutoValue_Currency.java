package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.currency.Image;
import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Currency extends Currency {
    private final String code;
    private final String color;
    private final Integer exponent;
    private final Image image;
    private final String name;
    private final Boolean priceAlertsEnabled;
    private final String type;

    C$AutoValue_Currency(String code, String name, String color, Integer exponent, String type, Image image, Boolean priceAlertsEnabled) {
        this.code = code;
        this.name = name;
        this.color = color;
        this.exponent = exponent;
        this.type = type;
        this.image = image;
        this.priceAlertsEnabled = priceAlertsEnabled;
    }

    @SerializedName("code")
    public String getCode() {
        return this.code;
    }

    @SerializedName("name")
    public String getName() {
        return this.name;
    }

    @SerializedName("color")
    public String getColor() {
        return this.color;
    }

    @SerializedName("exponent")
    public Integer getExponent() {
        return this.exponent;
    }

    @SerializedName("type")
    public String getType() {
        return this.type;
    }

    @SerializedName("image")
    public Image getImage() {
        return this.image;
    }

    @SerializedName("price_alerts_enabled")
    public Boolean getPriceAlertsEnabled() {
        return this.priceAlertsEnabled;
    }

    public String toString() {
        return "Currency{code=" + this.code + ", name=" + this.name + ", color=" + this.color + ", exponent=" + this.exponent + ", type=" + this.type + ", image=" + this.image + ", priceAlertsEnabled=" + this.priceAlertsEnabled + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Currency)) {
            return false;
        }
        Currency that = (Currency) o;
        if (this.code != null) {
            if (this.code.equals(that.getCode())) {
            }
            return false;
        }
        if (this.name != null) {
            if (this.name.equals(that.getName())) {
            }
            return false;
        }
        if (this.color != null) {
            if (this.color.equals(that.getColor())) {
            }
            return false;
        }
        if (this.exponent != null) {
            if (this.exponent.equals(that.getExponent())) {
            }
            return false;
        }
        if (this.type != null) {
            if (this.type.equals(that.getType())) {
            }
            return false;
        }
        if (this.image != null) {
            if (this.image.equals(that.getImage())) {
            }
            return false;
        }
        if (this.priceAlertsEnabled == null) {
            if (that.getPriceAlertsEnabled() == null) {
                return true;
            }
        } else if (this.priceAlertsEnabled.equals(that.getPriceAlertsEnabled())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((1 * 1000003) ^ (this.code == null ? 0 : this.code.hashCode())) * 1000003) ^ (this.name == null ? 0 : this.name.hashCode())) * 1000003) ^ (this.color == null ? 0 : this.color.hashCode())) * 1000003) ^ (this.exponent == null ? 0 : this.exponent.hashCode())) * 1000003) ^ (this.type == null ? 0 : this.type.hashCode())) * 1000003) ^ (this.image == null ? 0 : this.image.hashCode())) * 1000003;
        if (this.priceAlertsEnabled != null) {
            i = this.priceAlertsEnabled.hashCode();
        }
        return h ^ i;
    }
}
