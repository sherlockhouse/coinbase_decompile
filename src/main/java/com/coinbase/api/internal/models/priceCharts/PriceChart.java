package com.coinbase.api.internal.models.priceCharts;

import com.coinbase.api.internal.models.priceCharts.AutoValue_PriceChart.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class PriceChart {

    public static abstract class Builder {
        public abstract PriceChart build();

        public abstract Builder setData(Data data);
    }

    @SerializedName("data")
    public abstract Data getData();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<PriceChart> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
