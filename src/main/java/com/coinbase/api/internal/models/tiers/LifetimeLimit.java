package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.tiers.AutoValue_LifetimeLimit.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class LifetimeLimit {
    @SerializedName("amount")
    public abstract String getAmount();

    @SerializedName("currency")
    public abstract Currency getCurrency();

    @SerializedName("description")
    public abstract String getDescription();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("remaining")
    public abstract String getRemaining();

    @SerializedName("unlimited")
    public abstract Boolean getUnlimited();

    @SerializedName("used")
    public abstract String getUsed();

    public static TypeAdapter<LifetimeLimit> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
