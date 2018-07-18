package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.tiers.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Data {
    @SerializedName("account_details")
    public abstract AccountDetails getAccountDetails();

    @SerializedName("account_setup")
    public abstract AccountSetup getAccountSetup();

    @SerializedName("title")
    public abstract String getTitle();

    @SerializedName("verification_levels")
    public abstract VerificationLevels getVerificationLevels();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
