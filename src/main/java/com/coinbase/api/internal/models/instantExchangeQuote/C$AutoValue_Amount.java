package com.coinbase.api.internal.models.instantExchangeQuote;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Amount extends Amount {
    private final String amount;
    private final String currency;

    static final class Builder extends com.coinbase.api.internal.models.instantExchangeQuote.Amount.Builder {
        private String amount;
        private String currency;

        Builder() {
        }

        Builder(Amount source) {
            this.amount = source.getAmount();
            this.currency = source.getCurrency();
        }

        public com.coinbase.api.internal.models.instantExchangeQuote.Amount.Builder setAmount(String amount) {
            this.amount = amount;
            return this;
        }

        public com.coinbase.api.internal.models.instantExchangeQuote.Amount.Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Amount build() {
            return new AutoValue_Amount(this.amount, this.currency);
        }
    }

    C$AutoValue_Amount(String amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @SerializedName("amount")
    public String getAmount() {
        return this.amount;
    }

    @SerializedName("currency")
    public String getCurrency() {
        return this.currency;
    }

    public String toString() {
        return "Amount{amount=" + this.amount + ", currency=" + this.currency + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Amount)) {
            return false;
        }
        Amount that = (Amount) o;
        if (this.amount != null) {
            if (this.amount.equals(that.getAmount())) {
            }
            return false;
        }
        if (this.currency == null) {
            if (that.getCurrency() == null) {
                return true;
            }
        } else if (this.currency.equals(that.getCurrency())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.amount == null ? 0 : this.amount.hashCode())) * 1000003;
        if (this.currency != null) {
            i = this.currency.hashCode();
        }
        return h ^ i;
    }
}
