package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.tiers.AutoValue_LevelFeature.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class LevelFeature {
    @SerializedName("description")
    public abstract String getDescription();

    @SerializedName("enabled")
    public abstract Boolean getEnabled();

    @SerializedName("status_text")
    public abstract String getStatusText();

    public static TypeAdapter<LevelFeature> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
