package com.coinbase.api.internal.models.policyRestrictions;

import com.coinbase.api.internal.models.policyRestrictions.AutoValue_Url.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Url {
    @SerializedName("link")
    public abstract String getLink();

    @SerializedName("text")
    public abstract String getText();

    public static TypeAdapter<Url> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
