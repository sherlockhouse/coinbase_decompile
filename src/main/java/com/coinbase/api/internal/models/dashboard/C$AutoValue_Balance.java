package com.coinbase.api.internal.models.dashboard;

import com.coinbase.api.internal.models.currency.Data;
import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Balance extends Balance {
    private final Amount amount;
    private final Data currency;

    static final class Builder extends com.coinbase.api.internal.models.dashboard.Balance.Builder {
        private Amount amount;
        private Data currency;

        Builder() {
        }

        Builder(Balance source) {
            this.amount = source.getAmount();
            this.currency = source.getCurrency();
        }

        public com.coinbase.api.internal.models.dashboard.Balance.Builder setAmount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public com.coinbase.api.internal.models.dashboard.Balance.Builder setCurrency(Data currency) {
            this.currency = currency;
            return this;
        }

        public Balance build() {
            return new AutoValue_Balance(this.amount, this.currency);
        }
    }

    C$AutoValue_Balance(Amount amount, Data currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @SerializedName("amount")
    public Amount getAmount() {
        return this.amount;
    }

    @SerializedName("currency")
    public Data getCurrency() {
        return this.currency;
    }

    public String toString() {
        return "Balance{amount=" + this.amount + ", currency=" + this.currency + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Balance)) {
            return false;
        }
        Balance that = (Balance) o;
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
