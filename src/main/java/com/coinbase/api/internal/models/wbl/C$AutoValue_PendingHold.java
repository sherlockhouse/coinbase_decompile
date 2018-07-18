package com.coinbase.api.internal.models.wbl;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_PendingHold extends PendingHold {
    private final Amount amount;
    private final String availableIn;
    private final String date;

    static final class Builder extends com.coinbase.api.internal.models.wbl.PendingHold.Builder {
        private Amount amount;
        private String availableIn;
        private String date;

        Builder() {
        }

        Builder(PendingHold source) {
            this.date = source.getDate();
            this.amount = source.getAmount();
            this.availableIn = source.getAvailableIn();
        }

        public com.coinbase.api.internal.models.wbl.PendingHold.Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public com.coinbase.api.internal.models.wbl.PendingHold.Builder setAmount(Amount amount) {
            this.amount = amount;
            return this;
        }

        public com.coinbase.api.internal.models.wbl.PendingHold.Builder setAvailableIn(String availableIn) {
            this.availableIn = availableIn;
            return this;
        }

        public PendingHold build() {
            return new AutoValue_PendingHold(this.date, this.amount, this.availableIn);
        }
    }

    C$AutoValue_PendingHold(String date, Amount amount, String availableIn) {
        this.date = date;
        this.amount = amount;
        this.availableIn = availableIn;
    }

    @SerializedName("date")
    public String getDate() {
        return this.date;
    }

    @SerializedName("amount")
    public Amount getAmount() {
        return this.amount;
    }

    @SerializedName("available_in")
    public String getAvailableIn() {
        return this.availableIn;
    }

    public String toString() {
        return "PendingHold{date=" + this.date + ", amount=" + this.amount + ", availableIn=" + this.availableIn + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PendingHold)) {
            return false;
        }
        PendingHold that = (PendingHold) o;
        if (this.date != null) {
            if (this.date.equals(that.getDate())) {
            }
            return false;
        }
        if (this.amount != null) {
            if (this.amount.equals(that.getAmount())) {
            }
            return false;
        }
        if (this.availableIn == null) {
            if (that.getAvailableIn() == null) {
                return true;
            }
        } else if (this.availableIn.equals(that.getAvailableIn())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.date == null ? 0 : this.date.hashCode())) * 1000003) ^ (this.amount == null ? 0 : this.amount.hashCode())) * 1000003;
        if (this.availableIn != null) {
            i = this.availableIn.hashCode();
        }
        return h ^ i;
    }
}
