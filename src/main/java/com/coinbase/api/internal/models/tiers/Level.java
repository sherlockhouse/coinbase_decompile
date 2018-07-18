package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.tiers.AutoValue_Level.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Level {
    @SerializedName("description")
    public abstract String getDescription();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("requirements")
    public abstract List<Requirement> getRequirements();

    @SerializedName("status")
    public abstract Status getStatus();

    public static TypeAdapter<Level> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultRequirements(new ArrayList());
    }
}
