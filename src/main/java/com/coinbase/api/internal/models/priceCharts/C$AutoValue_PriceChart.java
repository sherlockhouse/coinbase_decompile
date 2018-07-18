package com.coinbase.api.internal.models.priceCharts;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_PriceChart extends PriceChart {
    private final Data data;

    static final class Builder extends com.coinbase.api.internal.models.priceCharts.PriceChart.Builder {
        private Data data;

        Builder() {
        }

        Builder(PriceChart source) {
            this.data = source.getData();
        }

        public com.coinbase.api.internal.models.priceCharts.PriceChart.Builder setData(Data data) {
            this.data = data;
            return this;
        }

        public PriceChart build() {
            return new AutoValue_PriceChart(this.data);
        }
    }

    C$AutoValue_PriceChart(Data data) {
        this.data = data;
    }

    @SerializedName("data")
    public Data getData() {
        return this.data;
    }

    public String toString() {
        return "PriceChart{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PriceChart)) {
            return false;
        }
        PriceChart that = (PriceChart) o;
        if (this.data != null) {
            return this.data.equals(that.getData());
        }
        if (that.getData() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.data == null ? 0 : this.data.hashCode());
    }
}
