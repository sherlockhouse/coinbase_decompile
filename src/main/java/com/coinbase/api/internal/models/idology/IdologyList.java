package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.models.idology.AutoValue_IdologyList.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class IdologyList {

    public static abstract class Builder {
        public abstract IdologyList build();

        public abstract Builder setData(List<Data> list);

        public abstract Builder setPagination(Pagination pagination);
    }

    @SerializedName("data")
    public abstract List<Data> getData();

    @SerializedName("pagination")
    public abstract Pagination getPagination();

    public static TypeAdapter<IdologyList> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultData(new ArrayList());
    }

    public static Builder builder() {
        return new Builder();
    }
}
