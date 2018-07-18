package com.coinbase.api.internal.models.availablePaymentMethods;

import com.coinbase.api.internal.models.availablePaymentMethods.AutoValue_AvailablePaymentMethods.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class AvailablePaymentMethods {
    @SerializedName("data")
    public abstract Data getData();

    public static TypeAdapter<AvailablePaymentMethods> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
