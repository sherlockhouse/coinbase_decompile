package com.coinbase.api.internal.models.priceCharts;

import com.coinbase.api.internal.models.priceCharts.AutoValue_Price.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Price {

    public static abstract class Builder {
        public abstract Price build();

        public abstract Builder setPrice(String str);

        public abstract Builder setTime(String str);
    }

    @SerializedName("price")
    public abstract String getPrice();

    @SerializedName("time")
    public abstract String getTime();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Price> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
