package com.coinbase.api.internal.models.emailPreferences;

import com.coinbase.api.internal.models.emailPreferences.AutoValue_EmailPreferences.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class EmailPreferences {
    @SerializedName("data")
    @Nullable
    public abstract Map<String, Boolean> getData();

    public static TypeAdapter<EmailPreferences> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
