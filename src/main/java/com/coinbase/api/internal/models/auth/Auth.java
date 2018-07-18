package com.coinbase.api.internal.models.auth;

import com.coinbase.api.internal.models.auth.AutoValue_Auth.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Auth {
    @SerializedName("code")
    public abstract String getCode();

    @SerializedName("success")
    public abstract Boolean getSuccess();

    public static TypeAdapter<Auth> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
