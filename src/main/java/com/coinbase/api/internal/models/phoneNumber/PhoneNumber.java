package com.coinbase.api.internal.models.phoneNumber;

import com.coinbase.api.internal.models.phoneNumber.AutoValue_PhoneNumber.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class PhoneNumber {
    @SerializedName("data")
    public abstract Data getData();

    public static TypeAdapter<PhoneNumber> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
