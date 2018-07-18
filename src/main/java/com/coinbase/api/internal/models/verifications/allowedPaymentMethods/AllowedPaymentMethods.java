package com.coinbase.api.internal.models.verifications.allowedPaymentMethods;

import com.coinbase.api.internal.models.verifications.allowedPaymentMethods.AutoValue_AllowedPaymentMethods.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class AllowedPaymentMethods {
    @SerializedName("data")
    public abstract List<Data> getData();

    public static TypeAdapter<AllowedPaymentMethods> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultData(new ArrayList());
    }
}
