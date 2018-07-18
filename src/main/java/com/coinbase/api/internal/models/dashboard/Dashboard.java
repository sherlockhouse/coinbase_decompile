package com.coinbase.api.internal.models.dashboard;

import com.coinbase.api.internal.models.dashboard.AutoValue_Dashboard.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Dashboard {

    public static abstract class Builder {
        public abstract Dashboard build();

        public abstract Builder setData(Data data);
    }

    @SerializedName("data")
    public abstract Data getData();

    public static TypeAdapter<Dashboard> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new Builder();
    }
}
