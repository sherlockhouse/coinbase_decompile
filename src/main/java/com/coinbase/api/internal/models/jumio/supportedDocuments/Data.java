package com.coinbase.api.internal.models.jumio.supportedDocuments;

import com.coinbase.api.internal.models.Country;
import com.coinbase.api.internal.models.jumio.supportedDocuments.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Data {
    @SerializedName("country")
    public abstract Country getCountry();

    @SerializedName("supported_id_types")
    public abstract List<SupportedIdType> getSupportedIdTypes();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultSupportedIdTypes(new ArrayList());
    }
}
