package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.tiers.AutoValue_Tiers.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Tiers {
    @SerializedName("data")
    public abstract Data getData();

    public static TypeAdapter<Tiers> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
