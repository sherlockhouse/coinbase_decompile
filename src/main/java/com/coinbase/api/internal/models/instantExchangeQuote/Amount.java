package com.coinbase.api.internal.models.instantExchangeQuote;

import com.coinbase.api.internal.models.instantExchangeQuote.AutoValue_Amount.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Amount {

    public static abstract class Builder {
        public abstract Amount build();

        public abstract Builder setAmount(String str);

        public abstract Builder setCurrency(String str);
    }

    @SerializedName("amount")
    public abstract String getAmount();

    @SerializedName("currency")
    public abstract String getCurrency();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Amount> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
