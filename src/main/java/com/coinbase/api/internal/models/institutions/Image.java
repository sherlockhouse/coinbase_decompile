package com.coinbase.api.internal.models.institutions;

import com.coinbase.api.internal.models.institutions.AutoValue_Image.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Image {
    @SerializedName("png")
    public abstract String getPng();

    @SerializedName("svg")
    public abstract String getSvg();

    public static TypeAdapter<Image> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
