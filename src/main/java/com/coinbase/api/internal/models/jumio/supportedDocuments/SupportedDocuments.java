package com.coinbase.api.internal.models.jumio.supportedDocuments;

import com.coinbase.api.internal.models.jumio.supportedDocuments.AutoValue_SupportedDocuments.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class SupportedDocuments {
    @SerializedName("data")
    public abstract List<Data> getData();

    public static TypeAdapter<SupportedDocuments> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultData(new ArrayList());
    }
}
