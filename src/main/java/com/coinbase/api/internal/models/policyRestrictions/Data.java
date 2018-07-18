package com.coinbase.api.internal.models.policyRestrictions;

import com.coinbase.api.internal.models.policyRestrictions.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Data {
    @SerializedName("policy_restrictions")
    public abstract List<PolicyRestrictions> getPolicyRestrictions();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultPolicyRestrictions(new ArrayList());
    }
}
