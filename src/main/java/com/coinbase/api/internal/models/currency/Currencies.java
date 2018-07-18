package com.coinbase.api.internal.models.currency;

import com.coinbase.api.internal.models.currency.AutoValue_Currencies.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Currencies {

    public static abstract class Builder {
        public abstract Currencies build();

        public abstract Builder setData(List<Data> list);
    }

    @SerializedName("data")
    public abstract List<Data> getData();

    public static TypeAdapter<Currencies> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultData(new ArrayList());
    }

    public static Builder builder() {
        return new Builder();
    }
}
