package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.models.idology.AutoValue_Country.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Country {

    public static abstract class Builder {
        public abstract Country build();

        public abstract Builder setCode(String str);

        public abstract Builder setName(String str);
    }

    @SerializedName("code")
    public abstract String getCode();

    @SerializedName("name")
    public abstract String getName();

    public static TypeAdapter<Country> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new Builder();
    }
}
