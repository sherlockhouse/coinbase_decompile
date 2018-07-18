package com.coinbase.api.internal.models.alerts;

import com.coinbase.api.internal.models.alerts.AutoValue_Alerts.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Alerts {
    @SerializedName("data")
    public abstract List<Data> getData();

    public static TypeAdapter<Alerts> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultData(new ArrayList());
    }
}
