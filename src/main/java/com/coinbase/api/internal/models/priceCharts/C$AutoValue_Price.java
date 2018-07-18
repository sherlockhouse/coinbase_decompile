package com.coinbase.api.internal.models.priceCharts;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Price extends Price {
    private final String price;
    private final String time;

    static final class Builder extends com.coinbase.api.internal.models.priceCharts.Price.Builder {
        private String price;
        private String time;

        Builder() {
        }

        Builder(Price source) {
            this.price = source.getPrice();
            this.time = source.getTime();
        }

        public com.coinbase.api.internal.models.priceCharts.Price.Builder setPrice(String price) {
            this.price = price;
            return this;
        }

        public com.coinbase.api.internal.models.priceCharts.Price.Builder setTime(String time) {
            this.time = time;
            return this;
        }

        public Price build() {
            return new AutoValue_Price(this.price, this.time);
        }
    }

    C$AutoValue_Price(String price, String time) {
        this.price = price;
        this.time = time;
    }

    @SerializedName("price")
    public String getPrice() {
        return this.price;
    }

    @SerializedName("time")
    public String getTime() {
        return this.time;
    }

    public String toString() {
        return "Price{price=" + this.price + ", time=" + this.time + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Price)) {
            return false;
        }
        Price that = (Price) o;
        if (this.price != null) {
            if (this.price.equals(that.getPrice())) {
            }
            return false;
        }
        if (this.time == null) {
            if (that.getTime() == null) {
                return true;
            }
        } else if (this.time.equals(that.getTime())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.price == null ? 0 : this.price.hashCode())) * 1000003;
        if (this.time != null) {
            i = this.time.hashCode();
        }
        return h ^ i;
    }
}
