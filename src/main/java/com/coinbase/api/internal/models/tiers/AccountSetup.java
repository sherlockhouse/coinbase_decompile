package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.tiers.AutoValue_AccountSetup.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class AccountSetup {
    @SerializedName("button_text")
    public abstract String getButtonText();

    @SerializedName("description")
    public abstract String getDescription();

    @SerializedName("title")
    public abstract String getTitle();

    public static TypeAdapter<AccountSetup> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
