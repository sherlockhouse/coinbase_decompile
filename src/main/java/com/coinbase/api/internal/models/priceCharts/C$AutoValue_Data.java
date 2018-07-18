package com.coinbase.api.internal.models.priceCharts;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Data extends Data {
    private final String currency;
    private final List<Price> prices;

    static final class Builder extends com.coinbase.api.internal.models.priceCharts.Data.Builder {
        private String currency;
        private List<Price> prices;

        Builder() {
        }

        Builder(Data source) {
            this.currency = source.getCurrency();
            this.prices = source.getPrices();
        }

        public com.coinbase.api.internal.models.priceCharts.Data.Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public com.coinbase.api.internal.models.priceCharts.Data.Builder setPrices(List<Price> prices) {
            this.prices = prices;
            return this;
        }

        public Data build() {
            return new AutoValue_Data(this.currency, this.prices);
        }
    }

    C$AutoValue_Data(String currency, List<Price> prices) {
        this.currency = currency;
        this.prices = prices;
    }

    @SerializedName("currency")
    public String getCurrency() {
        return this.currency;
    }

    @SerializedName("prices")
    public List<Price> getPrices() {
        return this.prices;
    }

    public String toString() {
        return "Data{currency=" + this.currency + ", prices=" + this.prices + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Data)) {
            return false;
        }
        Data that = (Data) o;
        if (this.currency != null) {
            if (this.currency.equals(that.getCurrency())) {
            }
            return false;
        }
        if (this.prices == null) {
            if (that.getPrices() == null) {
                return true;
            }
        } else if (this.prices.equals(that.getPrices())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.currency == null ? 0 : this.currency.hashCode())) * 1000003;
        if (this.prices != null) {
            i = this.prices.hashCode();
        }
        return h ^ i;
    }
}
