package com.coinbase.api.internal.models.availablePaymentMethods;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Currency extends Currency {
    private final String code;
    private final String color;
    private final Integer exponent;
    private final String name;
    private final String type;

    static final class Builder extends com.coinbase.api.internal.models.availablePaymentMethods.Currency.Builder {
        private String code;
        private String color;
        private Integer exponent;
        private String name;
        private String type;

        Builder() {
        }

        Builder(Currency source) {
            this.code = source.getCode();
            this.name = source.getName();
            this.color = source.getColor();
            this.exponent = source.getExponent();
            this.type = source.getType();
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.Currency.Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.Currency.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.Currency.Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.Currency.Builder setExponent(Integer exponent) {
            this.exponent = exponent;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.Currency.Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Currency build() {
            return new AutoValue_Currency(this.code, this.name, this.color, this.exponent, this.type);
        }
    }

    C$AutoValue_Currency(String code, String name, String color, Integer exponent, String type) {
        this.code = code;
        this.name = name;
        this.color = color;
        this.exponent = exponent;
        this.type = type;
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

    public String toString() {
        return "Currency{code=" + this.code + ", name=" + this.name + ", color=" + this.color + ", exponent=" + this.exponent + ", type=" + this.type + "}";
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
        if (this.type == null) {
            if (that.getType() == null) {
                return true;
            }
        } else if (this.type.equals(that.getType())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((1 * 1000003) ^ (this.code == null ? 0 : this.code.hashCode())) * 1000003) ^ (this.name == null ? 0 : this.name.hashCode())) * 1000003) ^ (this.color == null ? 0 : this.color.hashCode())) * 1000003) ^ (this.exponent == null ? 0 : this.exponent.hashCode())) * 1000003;
        if (this.type != null) {
            i = this.type.hashCode();
        }
        return h ^ i;
    }
}
