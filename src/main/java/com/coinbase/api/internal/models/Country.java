package com.coinbase.api.internal.models;

import com.coinbase.api.internal.models.AutoValue_Country.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Country {
    @SerializedName("code")
    public abstract String getCode();

    @SerializedName("image")
    public abstract String getImage();

    @SerializedName("name")
    public abstract String getName();

    public static TypeAdapter<Country> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
