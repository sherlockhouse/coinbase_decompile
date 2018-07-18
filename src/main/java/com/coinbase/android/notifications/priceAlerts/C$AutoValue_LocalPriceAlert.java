package com.coinbase.android.notifications.priceAlerts;

import com.coinbase.api.internal.models.currency.Data;
import com.google.gson.annotations.SerializedName;
import org.joda.money.CurrencyUnit;

abstract class C$AutoValue_LocalPriceAlert extends LocalPriceAlert {
    private final String amount;
    private final long createdOn;
    private final Data currency;
    private final CurrencyUnit currencyUnit;
    private final String displayText;
    private final boolean enabled;
    private final String id;
    private final boolean isAbove;
    private final String notificationMessage;
    private final String notificationTitle;
    private final long triggeredOn;

    static final class Builder extends com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder {
        private String amount;
        private Long createdOn;
        private Data currency;
        private CurrencyUnit currencyUnit;
        private String displayText;
        private Boolean enabled;
        private String id;
        private Boolean isAbove;
        private String notificationMessage;
        private String notificationTitle;
        private Long triggeredOn;

        Builder() {
        }

        Builder(LocalPriceAlert source) {
            this.id = source.getId();
            this.amount = source.getAmount();
            this.isAbove = Boolean.valueOf(source.getIsAbove());
            this.notificationTitle = source.getNotificationTitle();
            this.notificationMessage = source.getNotificationMessage();
            this.displayText = source.getDisplayText();
            this.createdOn = Long.valueOf(source.getCreatedOn());
            this.triggeredOn = Long.valueOf(source.getTriggeredOn());
            this.enabled = Boolean.valueOf(source.getEnabled());
            this.currencyUnit = source.getCurrencyUnit();
            this.currency = source.getCurrency();
        }

        public com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder setAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder setIsAbove(boolean isAbove) {
            this.isAbove = Boolean.valueOf(isAbove);
            return this;
        }

        public com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder setNotificationTitle(String notificationTitle) {
            this.notificationTitle = notificationTitle;
            return this;
        }

        public com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder setNotificationMessage(String notificationMessage) {
            this.notificationMessage = notificationMessage;
            return this;
        }

        public com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder setDisplayText(String displayText) {
            this.displayText = displayText;
            return this;
        }

        public com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder setCreatedOn(long createdOn) {
            this.createdOn = Long.valueOf(createdOn);
            return this;
        }

        public com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder setTriggeredOn(long triggeredOn) {
            this.triggeredOn = Long.valueOf(triggeredOn);
            return this;
        }

        public com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder setEnabled(boolean enabled) {
            this.enabled = Boolean.valueOf(enabled);
            return this;
        }

        public com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder setCurrencyUnit(CurrencyUnit currencyUnit) {
            this.currencyUnit = currencyUnit;
            return this;
        }

        public com.coinbase.android.notifications.priceAlerts.LocalPriceAlert.Builder setCurrency(Data currency) {
            this.currency = currency;
            return this;
        }

        public LocalPriceAlert build() {
            String missing = "";
            if (this.id == null) {
                missing = missing + " id";
            }
            if (this.amount == null) {
                missing = missing + " amount";
            }
            if (this.isAbove == null) {
                missing = missing + " isAbove";
            }
            if (this.notificationTitle == null) {
                missing = missing + " notificationTitle";
            }
            if (this.notificationMessage == null) {
                missing = missing + " notificationMessage";
            }
            if (this.displayText == null) {
                missing = missing + " displayText";
            }
            if (this.createdOn == null) {
                missing = missing + " createdOn";
            }
            if (this.triggeredOn == null) {
                missing = missing + " triggeredOn";
            }
            if (this.enabled == null) {
                missing = missing + " enabled";
            }
            if (this.currencyUnit == null) {
                missing = missing + " currencyUnit";
            }
            if (this.currency == null) {
                missing = missing + " currency";
            }
            if (missing.isEmpty()) {
                return new AutoValue_LocalPriceAlert(this.id, this.amount, this.isAbove.booleanValue(), this.notificationTitle, this.notificationMessage, this.displayText, this.createdOn.longValue(), this.triggeredOn.longValue(), this.enabled.booleanValue(), this.currencyUnit, this.currency);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_LocalPriceAlert(String id, String amount, boolean isAbove, String notificationTitle, String notificationMessage, String displayText, long createdOn, long triggeredOn, boolean enabled, CurrencyUnit currencyUnit, Data currency) {
        if (id == null) {
            throw new NullPointerException("Null id");
        }
        this.id = id;
        if (amount == null) {
            throw new NullPointerException("Null amount");
        }
        this.amount = amount;
        this.isAbove = isAbove;
        if (notificationTitle == null) {
            throw new NullPointerException("Null notificationTitle");
        }
        this.notificationTitle = notificationTitle;
        if (notificationMessage == null) {
            throw new NullPointerException("Null notificationMessage");
        }
        this.notificationMessage = notificationMessage;
        if (displayText == null) {
            throw new NullPointerException("Null displayText");
        }
        this.displayText = displayText;
        this.createdOn = createdOn;
        this.triggeredOn = triggeredOn;
        this.enabled = enabled;
        if (currencyUnit == null) {
            throw new NullPointerException("Null currencyUnit");
        }
        this.currencyUnit = currencyUnit;
        if (currency == null) {
            throw new NullPointerException("Null currency");
        }
        this.currency = currency;
    }

    @SerializedName("id")
    public String getId() {
        return this.id;
    }

    @SerializedName("amount")
    public String getAmount() {
        return this.amount;
    }

    @SerializedName("is_above")
    public boolean getIsAbove() {
        return this.isAbove;
    }

    @SerializedName("notification_title")
    public String getNotificationTitle() {
        return this.notificationTitle;
    }

    @SerializedName("notification_message")
    public String getNotificationMessage() {
        return this.notificationMessage;
    }

    @SerializedName("display_text")
    public String getDisplayText() {
        return this.displayText;
    }

    @SerializedName("created_on")
    public long getCreatedOn() {
        return this.createdOn;
    }

    @SerializedName("triggered_on")
    public long getTriggeredOn() {
        return this.triggeredOn;
    }

    @SerializedName("enabled")
    public boolean getEnabled() {
        return this.enabled;
    }

    @SerializedName("currency_unit")
    public CurrencyUnit getCurrencyUnit() {
        return this.currencyUnit;
    }

    @SerializedName("currency")
    public Data getCurrency() {
        return this.currency;
    }

    public String toString() {
        return "LocalPriceAlert{id=" + this.id + ", amount=" + this.amount + ", isAbove=" + this.isAbove + ", notificationTitle=" + this.notificationTitle + ", notificationMessage=" + this.notificationMessage + ", displayText=" + this.displayText + ", createdOn=" + this.createdOn + ", triggeredOn=" + this.triggeredOn + ", enabled=" + this.enabled + ", currencyUnit=" + this.currencyUnit + ", currency=" + this.currency + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LocalPriceAlert)) {
            return false;
        }
        LocalPriceAlert that = (LocalPriceAlert) o;
        if (this.id.equals(that.getId()) && this.amount.equals(that.getAmount()) && this.isAbove == that.getIsAbove() && this.notificationTitle.equals(that.getNotificationTitle()) && this.notificationMessage.equals(that.getNotificationMessage()) && this.displayText.equals(that.getDisplayText()) && this.createdOn == that.getCreatedOn() && this.triggeredOn == that.getTriggeredOn() && this.enabled == that.getEnabled() && this.currencyUnit.equals(that.getCurrencyUnit()) && this.currency.equals(that.getCurrency())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 1231;
        int h = ((int) (((long) (((int) (((long) (((((((((((((1 * 1000003) ^ this.id.hashCode()) * 1000003) ^ this.amount.hashCode()) * 1000003) ^ (this.isAbove ? 1231 : 1237)) * 1000003) ^ this.notificationTitle.hashCode()) * 1000003) ^ this.notificationMessage.hashCode()) * 1000003) ^ this.displayText.hashCode()) * 1000003)) ^ ((this.createdOn >>> 32) ^ this.createdOn))) * 1000003)) ^ ((this.triggeredOn >>> 32) ^ this.triggeredOn))) * 1000003;
        if (!this.enabled) {
            i = 1237;
        }
        return ((((h ^ i) * 1000003) ^ this.currencyUnit.hashCode()) * 1000003) ^ this.currency.hashCode();
    }
}
