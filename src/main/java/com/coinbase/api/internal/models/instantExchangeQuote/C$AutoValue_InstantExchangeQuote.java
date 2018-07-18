package com.coinbase.api.internal.models.instantExchangeQuote;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_InstantExchangeQuote extends InstantExchangeQuote {
    private final Data data;

    static final class Builder extends com.coinbase.api.internal.models.instantExchangeQuote.InstantExchangeQuote.Builder {
        private Data data;

        Builder() {
        }

        Builder(InstantExchangeQuote source) {
            this.data = source.getData();
        }

        public com.coinbase.api.internal.models.instantExchangeQuote.InstantExchangeQuote.Builder setData(Data data) {
            this.data = data;
            return this;
        }

        public InstantExchangeQuote build() {
            return new AutoValue_InstantExchangeQuote(this.data);
        }
    }

    C$AutoValue_InstantExchangeQuote(Data data) {
        this.data = data;
    }

    @SerializedName("data")
    public Data getData() {
        return this.data;
    }

    public String toString() {
        return "InstantExchangeQuote{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof InstantExchangeQuote)) {
            return false;
        }
        InstantExchangeQuote that = (InstantExchangeQuote) o;
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
