package com.coinbase.api.internal.models.jumio.supportedDocuments;

import com.coinbase.api.internal.models.jumio.JumioProfiles.Type;
import com.coinbase.api.internal.models.jumio.supportedDocuments.AutoValue_SupportedIdType.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class SupportedIdType {
    @SerializedName("backside_image_required")
    public abstract Boolean getBacksideImageRequired();

    public abstract Type getType();

    public static TypeAdapter<SupportedIdType> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultType(Type.UNKNOWN);
    }
}
