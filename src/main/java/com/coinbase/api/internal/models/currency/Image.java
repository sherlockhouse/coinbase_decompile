package com.coinbase.api.internal.models.currency;

import com.coinbase.api.internal.models.currency.AutoValue_Image.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Image {

    public static abstract class Builder {
        public abstract Image build();

        public abstract Builder setUrl(String str);
    }

    @SerializedName("url")
    public abstract String getUrl();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Image> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
