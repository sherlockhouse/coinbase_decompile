package com.coinbase.android.notifications.priceAlerts;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_LocalPriceAlerts extends LocalPriceAlerts {
    private final List<LocalPriceAlert> priceAlerts;

    static final class Builder extends com.coinbase.android.notifications.priceAlerts.LocalPriceAlerts.Builder {
        private List<LocalPriceAlert> priceAlerts;

        Builder() {
        }

        Builder(LocalPriceAlerts source) {
            this.priceAlerts = source.getPriceAlerts();
        }

        public com.coinbase.android.notifications.priceAlerts.LocalPriceAlerts.Builder setPriceAlerts(List<LocalPriceAlert> priceAlerts) {
            this.priceAlerts = priceAlerts;
            return this;
        }

        public LocalPriceAlerts build() {
            return new AutoValue_LocalPriceAlerts(this.priceAlerts);
        }
    }

    C$AutoValue_LocalPriceAlerts(List<LocalPriceAlert> priceAlerts) {
        this.priceAlerts = priceAlerts;
    }

    @SerializedName("price_alerts")
    public List<LocalPriceAlert> getPriceAlerts() {
        return this.priceAlerts;
    }

    public String toString() {
        return "LocalPriceAlerts{priceAlerts=" + this.priceAlerts + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LocalPriceAlerts)) {
            return false;
        }
        LocalPriceAlerts that = (LocalPriceAlerts) o;
        if (this.priceAlerts != null) {
            return this.priceAlerts.equals(that.getPriceAlerts());
        }
        if (that.getPriceAlerts() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.priceAlerts == null ? 0 : this.priceAlerts.hashCode());
    }
}
