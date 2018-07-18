package com.coinbase.api.internal.models.policyRestrictions;

import com.coinbase.api.internal.models.policyRestrictions.AutoValue_Restrictions.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Restrictions {
    @SerializedName("data")
    public abstract Data getData();

    public static TypeAdapter<Restrictions> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
