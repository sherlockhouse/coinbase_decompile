package com.coinbase.api.internal.models.policyRestrictions;

import com.coinbase.api.internal.models.policyRestrictions.AutoValue_PolicyRestrictions.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class PolicyRestrictions {
    @SerializedName("description")
    public abstract String getDescription();

    @SerializedName("error")
    public abstract String getError();

    @SerializedName("id")
    public abstract String getId();

    @SerializedName("message")
    public abstract String getMessage();

    @SerializedName("pending")
    public abstract Boolean getPending();

    @SerializedName("required")
    public abstract Boolean getRequired();

    @SerializedName("url")
    public abstract Url getUrl();

    @SerializedName("user_type")
    public abstract String getUserType();

    public static TypeAdapter<PolicyRestrictions> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
