package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.tiers.AutoValue_BuySellLimit.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class BuySellLimit {
    @SerializedName("amount")
    public abstract String getAmount();

    @SerializedName("currency")
    public abstract Currency getCurrency();

    @SerializedName("enabled")
    public abstract Boolean getEnabled();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("per_unit")
    public abstract String getPerUnit();

    @SerializedName("period_in_days")
    public abstract Integer getPeriodInDays();

    @SerializedName("status_text")
    public abstract String getStatusText();

    @SerializedName("type")
    public abstract String getType();

    public static TypeAdapter<BuySellLimit> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
