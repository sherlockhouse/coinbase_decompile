package com.coinbase.api.internal.models.availablePaymentMethods;

import com.coinbase.api.internal.models.availablePaymentMethods.AutoValue_BuyingPower.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class BuyingPower {

    public static abstract class Builder {
        public abstract BuyingPower build();

        public abstract Builder setText(String str);

        public abstract Builder setType(String str);
    }

    @SerializedName("text")
    public abstract String getText();

    @SerializedName("type")
    public abstract String getType();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<BuyingPower> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
