package com.coinbase.api.internal.models.address;

import com.coinbase.api.internal.models.address.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Data {
    @SerializedName("city")
    public abstract String getCity();

    @SerializedName("country")
    public abstract Country getCountry();

    @SerializedName("id")
    public abstract String getId();

    @SerializedName("line1")
    public abstract String getLine1();

    @SerializedName("line2")
    public abstract Object getLine2();

    @SerializedName("line3")
    public abstract Object getLine3();

    @SerializedName("postal_code")
    public abstract String getPostalCode();

    @SerializedName("resource")
    public abstract String getResource();

    @SerializedName("resource_path")
    public abstract String getResourcePath();

    @SerializedName("state")
    public abstract String getState();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
