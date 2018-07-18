package com.coinbase.api.internal.models.config;

import com.coinbase.api.internal.models.config.AutoValue_Config.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Config {

    public static abstract class Builder {
        public abstract Config build();

        public abstract Builder setData(Data data);
    }

    @SerializedName("data")
    public abstract Data getData();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Config> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
