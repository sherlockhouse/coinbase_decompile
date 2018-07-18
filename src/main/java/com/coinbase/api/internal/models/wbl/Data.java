package com.coinbase.api.internal.models.wbl;

import com.coinbase.api.internal.models.wbl.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Data {
    @SerializedName("pending_holds")
    public abstract List<PendingHold> getPendingHolds();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultPendingHolds(new ArrayList());
    }
}
