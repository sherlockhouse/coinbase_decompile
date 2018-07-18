package com.coinbase.android.notifications.priceAlerts;

import com.coinbase.android.notifications.priceAlerts.AutoValue_LocalPriceAlerts.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class LocalPriceAlerts {

    public static abstract class Builder {
        public abstract LocalPriceAlerts build();

        public abstract Builder setPriceAlerts(List<LocalPriceAlert> list);
    }

    @SerializedName("price_alerts")
    public abstract List<LocalPriceAlert> getPriceAlerts();

    public static TypeAdapter<LocalPriceAlerts> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultPriceAlerts(new ArrayList());
    }

    public static Builder builder() {
        return new Builder();
    }
}
