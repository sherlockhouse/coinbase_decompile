package com.coinbase.api.internal.models.priceCharts;

import com.coinbase.api.internal.models.priceCharts.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Data {

    public static abstract class Builder {
        public abstract Data build();

        public abstract Builder setCurrency(String str);

        public abstract Builder setPrices(List<Price> list);
    }

    @SerializedName("currency")
    public abstract String getCurrency();

    @SerializedName("prices")
    public abstract List<Price> getPrices();

    public static Builder builder() {
        return new Builder().setPrices(new ArrayList());
    }

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultPrices(new ArrayList());
    }
}
