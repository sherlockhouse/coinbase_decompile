package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.models.idology.AutoValue_Pagination.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Pagination {

    public static abstract class Builder {
        public abstract Pagination build();

        public abstract Builder setEndingBefore(Object obj);

        public abstract Builder setLimit(Integer num);

        public abstract Builder setNextUri(Object obj);

        public abstract Builder setOrder(String str);

        public abstract Builder setPreviousUri(Object obj);

        public abstract Builder setStartingAfter(Object obj);
    }

    @SerializedName("ending_before")
    public abstract Object getEndingBefore();

    @SerializedName("limit")
    public abstract Integer getLimit();

    @SerializedName("next_uri")
    public abstract Object getNextUri();

    @SerializedName("order")
    public abstract String getOrder();

    @SerializedName("previous_uri")
    public abstract Object getPreviousUri();

    @SerializedName("starting_after")
    public abstract Object getStartingAfter();

    public static TypeAdapter<Pagination> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new Builder();
    }
}
