package com.coinbase.api.internal.models.achSetupSession;

import com.coinbase.api.internal.models.achSetupSession.AutoValue_AchSetupSession.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class AchSetupSession {
    @SerializedName("data")
    public abstract Data getData();

    public static TypeAdapter<AchSetupSession> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
