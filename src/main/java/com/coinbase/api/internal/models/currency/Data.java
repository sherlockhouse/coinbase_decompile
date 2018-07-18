package com.coinbase.api.internal.models.currency;

import com.coinbase.api.internal.models.currency.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Data {

    public static abstract class Builder {
        public abstract Data build();

        public abstract Builder setAddressRegex(String str);

        public abstract Builder setCanBuy(boolean z);

        public abstract Builder setCanSell(boolean z);

        public abstract Builder setCode(String str);

        public abstract Builder setColor(String str);

        public abstract Builder setExponent(int i);

        public abstract Builder setImage(Image image);

        public abstract Builder setName(String str);

        public abstract Builder setPriceAlertsEnabled(boolean z);

        public abstract Builder setUriScheme(String str);
    }

    @SerializedName("address_regex")
    public abstract String getAddressRegex();

    @SerializedName("can_buy")
    public abstract boolean getCanBuy();

    @SerializedName("can_sell")
    public abstract boolean getCanSell();

    @SerializedName("code")
    public abstract String getCode();

    @SerializedName("color")
    public abstract String getColor();

    @SerializedName("exponent")
    public abstract int getExponent();

    @SerializedName("image")
    public abstract Image getImage();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("price_alerts_enabled")
    public abstract boolean getPriceAlertsEnabled();

    @SerializedName("uri_scheme")
    public abstract String getUriScheme();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
