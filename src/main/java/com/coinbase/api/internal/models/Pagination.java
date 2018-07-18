package com.coinbase.api.internal.models;

import com.coinbase.api.internal.models.AutoValue_Pagination.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Pagination {
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
}
