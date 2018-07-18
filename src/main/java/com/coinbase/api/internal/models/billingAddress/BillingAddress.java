package com.coinbase.api.internal.models.billingAddress;

import com.coinbase.api.internal.models.billingAddress.AutoValue_BillingAddress.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class BillingAddress {
    @SerializedName("data")
    public abstract Data getData();

    public static TypeAdapter<BillingAddress> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
