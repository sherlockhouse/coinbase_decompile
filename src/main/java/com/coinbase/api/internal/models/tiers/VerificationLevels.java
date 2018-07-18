package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.tiers.AutoValue_VerificationLevels.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class VerificationLevels {
    @SerializedName("levels")
    public abstract List<Level> getLevels();

    public static TypeAdapter<VerificationLevels> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultLevels(new ArrayList());
    }
}
