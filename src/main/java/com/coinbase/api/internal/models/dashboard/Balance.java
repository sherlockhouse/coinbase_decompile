package com.coinbase.api.internal.models.dashboard;

import com.coinbase.api.internal.models.currency.Data;
import com.coinbase.api.internal.models.dashboard.AutoValue_Balance.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Balance {

    public static abstract class Builder {
        public abstract Balance build();

        public abstract Builder setAmount(Amount amount);

        public abstract Builder setCurrency(Data data);
    }

    @SerializedName("amount")
    public abstract Amount getAmount();

    @SerializedName("currency")
    public abstract Data getCurrency();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Balance> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
