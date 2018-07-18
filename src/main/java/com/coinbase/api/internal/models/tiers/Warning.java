package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.tiers.AutoValue_Warning.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Warning {
    @SerializedName("id")
    public abstract String getId();

    @SerializedName("message")
    public abstract String getMessage();

    @SerializedName("url")
    public abstract String getUrl();

    public static TypeAdapter<Warning> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
