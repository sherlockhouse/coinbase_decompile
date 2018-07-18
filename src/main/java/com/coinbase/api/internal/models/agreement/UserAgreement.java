package com.coinbase.api.internal.models.agreement;

import com.coinbase.api.internal.models.agreement.AutoValue_UserAgreement.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class UserAgreement {
    @SerializedName("data")
    public abstract Data getData();

    public static TypeAdapter<UserAgreement> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
