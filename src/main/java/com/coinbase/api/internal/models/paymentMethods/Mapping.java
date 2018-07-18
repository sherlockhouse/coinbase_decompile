package com.coinbase.api.internal.models.paymentMethods;

import com.coinbase.api.internal.models.paymentMethods.AutoValue_Mapping.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Mapping {

    public static abstract class Builder {
        public abstract Mapping build();

        public abstract Builder setId(String str);

        public abstract Builder setName(String str);

        public abstract Builder setValue(String str);
    }

    @SerializedName("id")
    public abstract String getId();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("value")
    public abstract String getValue();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Mapping> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
