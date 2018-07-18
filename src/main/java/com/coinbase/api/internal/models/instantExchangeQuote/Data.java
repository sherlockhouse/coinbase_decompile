package com.coinbase.api.internal.models.instantExchangeQuote;

import com.coinbase.api.internal.models.instantExchangeQuote.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Data {

    public static abstract class Builder {
        public abstract Data build();

        public abstract Builder setAmount(Amount amount);

        public abstract Builder setBitcoin(Amount amount);

        public abstract Builder setFiat(Amount amount);

        public abstract Builder setId(String str);
    }

    @SerializedName("amount")
    public abstract Amount getAmount();

    @SerializedName("bitcoin")
    public abstract Amount getBitcoin();

    @SerializedName("fiat")
    public abstract Amount getFiat();

    @SerializedName("id")
    public abstract String getId();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
