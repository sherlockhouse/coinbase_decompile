package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.tiers.AutoValue_Status.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Status {
    @SerializedName("complete")
    public abstract Boolean getComplete();

    @SerializedName("description")
    public abstract String getDescription();

    public static TypeAdapter<Status> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
