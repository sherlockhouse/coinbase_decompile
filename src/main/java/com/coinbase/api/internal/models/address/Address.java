package com.coinbase.api.internal.models.address;

import com.coinbase.api.internal.models.address.AutoValue_Address.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Address {
    @SerializedName("data")
    public abstract Data getData();

    public static TypeAdapter<Address> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
