package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.models.idology.AutoValue_Idology.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Idology {

    public static abstract class Builder {
        public abstract Idology build();

        public abstract Builder setData(Data data);
    }

    @SerializedName("data")
    public abstract Data getData();

    public static TypeAdapter<Idology> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new Builder();
    }
}
