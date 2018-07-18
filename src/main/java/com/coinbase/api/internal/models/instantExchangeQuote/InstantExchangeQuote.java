package com.coinbase.api.internal.models.instantExchangeQuote;

import com.coinbase.api.internal.models.instantExchangeQuote.AutoValue_InstantExchangeQuote.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class InstantExchangeQuote {

    public static abstract class Builder {
        public abstract InstantExchangeQuote build();

        public abstract Builder setData(Data data);
    }

    @SerializedName("data")
    public abstract Data getData();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<InstantExchangeQuote> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
