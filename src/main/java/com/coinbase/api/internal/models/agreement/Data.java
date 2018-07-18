package com.coinbase.api.internal.models.agreement;

import com.coinbase.api.internal.models.agreement.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Data {
    @SerializedName("html")
    public abstract String getHtml();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
