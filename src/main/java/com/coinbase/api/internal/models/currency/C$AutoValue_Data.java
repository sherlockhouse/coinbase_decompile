package com.coinbase.api.internal.models.currency;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Data extends Data {
    private final String addressRegex;
    private final boolean canBuy;
    private final boolean canSell;
    private final String code;
    private final String color;
    private final int exponent;
    private final Image image;
    private final String name;
    private final boolean priceAlertsEnabled;
    private final String uriScheme;

    static final class Builder extends com.coinbase.api.internal.models.currency.Data.Builder {
        private String addressRegex;
        private Boolean canBuy;
        private Boolean canSell;
        private String code;
        private String color;
        private Integer exponent;
        private Image image;
        private String name;
        private Boolean priceAlertsEnabled;
        private String uriScheme;

        Builder() {
        }

        Builder(Data source) {
            this.code = source.getCode();
            this.name = source.getName();
            this.color = source.getColor();
            this.exponent = Integer.valueOf(source.getExponent());
            this.addressRegex = source.getAddressRegex();
            this.uriScheme = source.getUriScheme();
            this.canBuy = Boolean.valueOf(source.getCanBuy());
            this.canSell = Boolean.valueOf(source.getCanSell());
            this.priceAlertsEnabled = Boolean.valueOf(source.getPriceAlertsEnabled());
            this.image = source.getImage();
        }

        public com.coinbase.api.internal.models.currency.Data.Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public com.coinbase.api.internal.models.currency.Data.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public com.coinbase.api.internal.models.currency.Data.Builder setColor(String color) {
            this.color = color;
            return this;
        }

        public com.coinbase.api.internal.models.currency.Data.Builder setExponent(int exponent) {
            this.exponent = Integer.valueOf(exponent);
            return this;
        }

        public com.coinbase.api.internal.models.currency.Data.Builder setAddressRegex(String addressRegex) {
            this.addressRegex = addressRegex;
            return this;
        }

        public com.coinbase.api.internal.models.currency.Data.Builder setUriScheme(String uriScheme) {
            this.uriScheme = uriScheme;
            return this;
        }

        public com.coinbase.api.internal.models.currency.Data.Builder setCanBuy(boolean canBuy) {
            this.canBuy = Boolean.valueOf(canBuy);
            return this;
        }

        public com.coinbase.api.internal.models.currency.Data.Builder setCanSell(boolean canSell) {
            this.canSell = Boolean.valueOf(canSell);
            return this;
        }

        public com.coinbase.api.internal.models.currency.Data.Builder setPriceAlertsEnabled(boolean priceAlertsEnabled) {
            this.priceAlertsEnabled = Boolean.valueOf(priceAlertsEnabled);
            return this;
        }

        public com.coinbase.api.internal.models.currency.Data.Builder setImage(Image image) {
            this.image = image;
            return this;
        }

        public Data build() {
            String missing = "";
            if (this.code == null) {
                missing = missing + " code";
            }
            if (this.name == null) {
                missing = missing + " name";
            }
            if (this.exponent == null) {
                missing = missing + " exponent";
            }
            if (this.canBuy == null) {
                missing = missing + " canBuy";
            }
            if (this.canSell == null) {
                missing = missing + " canSell";
            }
            if (this.priceAlertsEnabled == null) {
                missing = missing + " priceAlertsEnabled";
            }
            if (missing.isEmpty()) {
                return new AutoValue_Data(this.code, this.name, this.color, this.exponent.intValue(), this.addressRegex, this.uriScheme, this.canBuy.booleanValue(), this.canSell.booleanValue(), this.priceAlertsEnabled.booleanValue(), this.image);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_Data(String code, String name, String color, int exponent, String addressRegex, String uriScheme, boolean canBuy, boolean canSell, boolean priceAlertsEnabled, Image image) {
        if (code == null) {
            throw new NullPointerException("Null code");
        }
        this.code = code;
        if (name == null) {
            throw new NullPointerException("Null name");
        }
        this.name = name;
        this.color = color;
        this.exponent = exponent;
        this.addressRegex = addressRegex;
        this.uriScheme = uriScheme;
        this.canBuy = canBuy;
        this.canSell = canSell;
        this.priceAlertsEnabled = priceAlertsEnabled;
        this.image = image;
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
    public int getExponent() {
        return this.exponent;
    }

    @SerializedName("address_regex")
    public String getAddressRegex() {
        return this.addressRegex;
    }

    @SerializedName("uri_scheme")
    public String getUriScheme() {
        return this.uriScheme;
    }

    @SerializedName("can_buy")
    public boolean getCanBuy() {
        return this.canBuy;
    }

    @SerializedName("can_sell")
    public boolean getCanSell() {
        return this.canSell;
    }

    @SerializedName("price_alerts_enabled")
    public boolean getPriceAlertsEnabled() {
        return this.priceAlertsEnabled;
    }

    @SerializedName("image")
    public Image getImage() {
        return this.image;
    }

    public String toString() {
        return "Data{code=" + this.code + ", name=" + this.name + ", color=" + this.color + ", exponent=" + this.exponent + ", addressRegex=" + this.addressRegex + ", uriScheme=" + this.uriScheme + ", canBuy=" + this.canBuy + ", canSell=" + this.canSell + ", priceAlertsEnabled=" + this.priceAlertsEnabled + ", image=" + this.image + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Data)) {
            return false;
        }
        Data that = (Data) o;
        if (this.code.equals(that.getCode()) && this.name.equals(that.getName())) {
            if (this.color != null) {
                if (this.color.equals(that.getColor())) {
                }
            }
            if (this.exponent == that.getExponent()) {
                if (this.addressRegex != null) {
                    if (this.addressRegex.equals(that.getAddressRegex())) {
                    }
                }
                if (this.uriScheme != null) {
                    if (this.uriScheme.equals(that.getUriScheme())) {
                    }
                }
                if (this.canBuy == that.getCanBuy() && this.canSell == that.getCanSell() && this.priceAlertsEnabled == that.getPriceAlertsEnabled()) {
                    if (this.image == null) {
                        if (that.getImage() == null) {
                            return true;
                        }
                    } else if (this.image.equals(that.getImage())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public int hashCode() {
        int i;
        int i2 = 1231;
        int i3 = 0;
        int h = ((((((((((((1 * 1000003) ^ this.code.hashCode()) * 1000003) ^ this.name.hashCode()) * 1000003) ^ (this.color == null ? 0 : this.color.hashCode())) * 1000003) ^ this.exponent) * 1000003) ^ (this.addressRegex == null ? 0 : this.addressRegex.hashCode())) * 1000003) ^ (this.uriScheme == null ? 0 : this.uriScheme.hashCode())) * 1000003;
        if (this.canBuy) {
            i = 1231;
        } else {
            i = 1237;
        }
        h = (h ^ i) * 1000003;
        if (this.canSell) {
            i = 1231;
        } else {
            i = 1237;
        }
        h = (h ^ i) * 1000003;
        if (!this.priceAlertsEnabled) {
            i2 = 1237;
        }
        h = (h ^ i2) * 1000003;
        if (this.image != null) {
            i3 = this.image.hashCode();
        }
        return h ^ i3;
    }
}
