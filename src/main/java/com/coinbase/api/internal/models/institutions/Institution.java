package com.coinbase.api.internal.models.institutions;

import com.coinbase.api.internal.models.institutions.AutoValue_Institution.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Institution {

    public static abstract class Builder {
        public abstract Institution build();

        public abstract Builder setData(List<Data> list);
    }

    @SerializedName("data")
    public abstract List<Data> getData();

    public static TypeAdapter<Institution> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultData(new ArrayList());
    }

    public static Builder builder() {
        return new Builder().setData(new ArrayList());
    }
}
