package com.coinbase.android.notifications.priceAlerts;

import com.coinbase.android.notifications.priceAlerts.AutoValue_LocalPriceAlert.GsonTypeAdapter;
import com.coinbase.api.internal.models.currency.Data;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import org.joda.money.CurrencyUnit;

public abstract class LocalPriceAlert {

    public static abstract class Builder {
        public abstract LocalPriceAlert build();

        public abstract Builder setAmount(String str);

        public abstract Builder setCreatedOn(long j);

        public abstract Builder setCurrency(Data data);

        public abstract Builder setCurrencyUnit(CurrencyUnit currencyUnit);

        public abstract Builder setDisplayText(String str);

        public abstract Builder setEnabled(boolean z);

        public abstract Builder setId(String str);

        public abstract Builder setIsAbove(boolean z);

        public abstract Builder setNotificationMessage(String str);

        public abstract Builder setNotificationTitle(String str);

        public abstract Builder setTriggeredOn(long j);
    }

    @SerializedName("amount")
    public abstract String getAmount();

    @SerializedName("created_on")
    public abstract long getCreatedOn();

    @SerializedName("currency")
    public abstract Data getCurrency();

    @SerializedName("currency_unit")
    public abstract CurrencyUnit getCurrencyUnit();

    @SerializedName("display_text")
    public abstract String getDisplayText();

    @SerializedName("enabled")
    public abstract boolean getEnabled();

    @SerializedName("id")
    public abstract String getId();

    @SerializedName("is_above")
    public abstract boolean getIsAbove();

    @SerializedName("notification_message")
    public abstract String getNotificationMessage();

    @SerializedName("notification_title")
    public abstract String getNotificationTitle();

    @SerializedName("triggered_on")
    public abstract long getTriggeredOn();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<LocalPriceAlert> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
