package com.coinbase.api.internal.models.wbl;

import com.coinbase.api.internal.models.wbl.AutoValue_PendingHolds.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class PendingHolds {
    @SerializedName("data")
    public abstract Data getData();

    public static TypeAdapter<PendingHolds> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
