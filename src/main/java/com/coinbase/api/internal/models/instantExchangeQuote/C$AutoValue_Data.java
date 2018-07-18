package com.coinbase.api.internal.models.instantExchangeQuote;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Data extends Data {
    private final Amount amount;
    private final Amount bitcoin;
    private final Amount fiat;
    private final String id;

    static final class Builder extends com.coinbase.api.internal.models.instantExchangeQuote.Data.Builder {
        private Amount amount;
        private Amount bitcoin;
        private Amount fiat;
        private String id;

        Builder() {
        }

        Builder(Data source) {
            this.id = source.getId();
            this.fiat = source.getFiat();
            this.bitcoin = source.getBitcoin();
            this.amount = source.getAmount();
        }

        public com.coinbase.api.internal.models.instantExchangeQuote.Data.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public com.coinbase.api.internal.models.instantExchangeQuote.Data.Builder setFiat(Amount fiat) {
            this.fiat = fiat;
            return this;
        }

        public com.coinbase.api.internal.models.instantExchangeQuote.Data.Builder setBitcoin(Amount bitcoin) {
            this.bitcoin = bitcoin;
            return this;
        }

        public com.coinbase.api.internal.models.instantExchangeQuote.Data.Builder setAmount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public Data build() {
            return new AutoValue_Data(this.id, this.fiat, this.bitcoin, this.amount);
        }
    }

    C$AutoValue_Data(String id, Amount fiat, Amount bitcoin, Amount amount) {
        this.id = id;
        this.fiat = fiat;
        this.bitcoin = bitcoin;
        this.amount = amount;
    }

    @SerializedName("id")
    public String getId() {
        return this.id;
    }

    @SerializedName("fiat")
    public Amount getFiat() {
        return this.fiat;
    }

    @SerializedName("bitcoin")
    public Amount getBitcoin() {
        return this.bitcoin;
    }

    @SerializedName("amount")
    public Amount getAmount() {
        return this.amount;
    }

    public String toString() {
        return "Data{id=" + this.id + ", fiat=" + this.fiat + ", bitcoin=" + this.bitcoin + ", amount=" + this.amount + "}";
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
        if (this.id != null) {
            if (this.id.equals(that.getId())) {
            }
            return false;
        }
        if (this.fiat != null) {
            if (this.fiat.equals(that.getFiat())) {
            }
            return false;
        }
        if (this.bitcoin != null) {
            if (this.bitcoin.equals(that.getBitcoin())) {
            }
            return false;
        }
        if (this.amount == null) {
            if (that.getAmount() == null) {
                return true;
            }
        } else if (this.amount.equals(that.getAmount())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((1 * 1000003) ^ (this.id == null ? 0 : this.id.hashCode())) * 1000003) ^ (this.fiat == null ? 0 : this.fiat.hashCode())) * 1000003) ^ (this.bitcoin == null ? 0 : this.bitcoin.hashCode())) * 1000003;
        if (this.amount != null) {
            i = this.amount.hashCode();
        }
        return h ^ i;
    }
}
