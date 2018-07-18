package com.coinbase.api.internal.models.paymentMethods.verify;

import com.coinbase.api.internal.models.paymentMethods.verify.AutoValue_Verify.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Verify {

    public static abstract class Builder {
        public abstract Verify build();

        public abstract Builder setData(Data data);
    }

    @SerializedName("data")
    public abstract Data getData();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Verify> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
