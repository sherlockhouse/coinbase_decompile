package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.tiers.AutoValue_AccountDetails.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class AccountDetails {
    @SerializedName("limits_and_features")
    public abstract LimitsAndFeatures getLimitsAndFeatures();

    @SerializedName("title")
    public abstract String getTitle();

    @SerializedName("upgrade_button_text")
    public abstract String getUpgradeButtonText();

    public static TypeAdapter<AccountDetails> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
