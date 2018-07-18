package com.coinbase.api.internal.models.institutions;

import com.coinbase.api.internal.models.institutions.AutoValue_Credentials.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Credentials {
    @SerializedName("password")
    public abstract String getPassword();

    @SerializedName("pin")
    public abstract String getPin();

    @SerializedName("username")
    public abstract String getUsername();

    public static TypeAdapter<Credentials> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
