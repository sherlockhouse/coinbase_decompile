package com.coinbase.api.internal.models.jumio.supportedDocuments;

import com.coinbase.api.internal.models.jumio.supportedDocuments.AutoValue_SupportedDocument.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class SupportedDocument {
    @SerializedName("data")
    public abstract Data getData();

    public static TypeAdapter<SupportedDocument> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
