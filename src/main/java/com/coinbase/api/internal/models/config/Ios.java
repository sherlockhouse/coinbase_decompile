package com.coinbase.api.internal.models.config;

import com.coinbase.api.internal.models.config.AutoValue_Ios.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Ios {
    @SerializedName("message")
    public abstract Message getMessage();

    @SerializedName("version")
    public abstract Message getVersion();

    public static TypeAdapter<Ios> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
