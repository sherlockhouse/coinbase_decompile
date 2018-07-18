package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.currency.Image;
import com.coinbase.api.internal.models.tiers.AutoValue_Currency.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Currency {
    @SerializedName("code")
    public abstract String getCode();

    @SerializedName("color")
    public abstract String getColor();

    @SerializedName("exponent")
    public abstract Integer getExponent();

    @SerializedName("image")
    public abstract Image getImage();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("price_alerts_enabled")
    public abstract Boolean getPriceAlertsEnabled();

    @SerializedName("type")
    public abstract String getType();

    public static TypeAdapter<Currency> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
