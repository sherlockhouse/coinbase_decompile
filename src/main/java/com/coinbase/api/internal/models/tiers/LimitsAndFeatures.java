package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.tiers.AutoValue_LimitsAndFeatures.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class LimitsAndFeatures {
    @SerializedName("buy_deposit_limits")
    public abstract List<BuySellLimit> getBuyDepositLimits();

    @SerializedName("lifetime_limit")
    public abstract LifetimeLimit getLifetimeLimit();

    @SerializedName("receives")
    public abstract LevelFeature getReceives();

    @SerializedName("sends")
    public abstract LevelFeature getSends();

    @SerializedName("title")
    public abstract String getTitle();

    public static TypeAdapter<LimitsAndFeatures> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultBuyDepositLimits(new ArrayList());
    }
}
