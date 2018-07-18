package com.coinbase.api.internal.models.config;

import com.coinbase.api.internal.models.config.AutoValue_Android.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Android {

    public static abstract class Builder {
        public abstract Android build();

        public abstract Builder setMessage(Message message);

        public abstract Builder setVersion(Message message);
    }

    @SerializedName("message")
    public abstract Message getMessage();

    @SerializedName("version")
    public abstract Message getVersion();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Android> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
