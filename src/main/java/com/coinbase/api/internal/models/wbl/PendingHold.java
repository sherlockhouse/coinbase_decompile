package com.coinbase.api.internal.models.wbl;

import com.coinbase.api.internal.models.wbl.AutoValue_PendingHold.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class PendingHold {

    public static abstract class Builder {
        public abstract PendingHold build();

        public abstract Builder setAmount(Amount amount);

        public abstract Builder setAvailableIn(String str);

        public abstract Builder setDate(String str);
    }

    @SerializedName("amount")
    public abstract Amount getAmount();

    @SerializedName("available_in")
    public abstract String getAvailableIn();

    @SerializedName("date")
    public abstract String getDate();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<PendingHold> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
