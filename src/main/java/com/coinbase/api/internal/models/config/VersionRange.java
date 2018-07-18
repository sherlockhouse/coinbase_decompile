package com.coinbase.api.internal.models.config;

import com.coinbase.api.internal.models.config.AutoValue_VersionRange.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class VersionRange {
    @SerializedName("max")
    public abstract String getMax();

    @SerializedName("min")
    public abstract String getMin();

    public static TypeAdapter<VersionRange> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
