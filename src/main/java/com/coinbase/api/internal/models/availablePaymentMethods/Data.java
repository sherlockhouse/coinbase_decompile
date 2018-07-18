package com.coinbase.api.internal.models.availablePaymentMethods;

import com.coinbase.api.internal.models.availablePaymentMethods.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public abstract class Data {
    @SerializedName("available_payment_methods")
    public abstract List<AvailablePaymentMethod> getAvailablePaymentMethods();

    @SerializedName("base_fees")
    public abstract String getBaseFees();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
