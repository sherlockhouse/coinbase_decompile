package com.coinbase.api.internal.models.config;

import com.coinbase.api.internal.models.config.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Data {

    public static abstract class Builder {
        public abstract Data build();

        public abstract Builder setAndroid(Android android);

        public abstract Builder setIos(Ios ios);
    }

    @SerializedName("android")
    public abstract Android getAndroid();

    @SerializedName("ios")
    public abstract Ios getIos();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
