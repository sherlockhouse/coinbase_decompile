package com.coinbase.api.internal.models.paymentMethods;

import com.coinbase.api.internal.models.paymentMethods.AutoValue_CardSetup.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class CardSetup {

    public static abstract class Builder {
        public abstract CardSetup build();

        public abstract Builder setData(Data data);
    }

    @SerializedName("data")
    public abstract Data getData();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<CardSetup> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
