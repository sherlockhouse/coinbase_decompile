package com.coinbase.api.internal.models.dashboard;

import com.coinbase.v2.models.transactions.Data;
import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Data extends Data {
    private final List<Balance> balance;
    private final String firstBuyStatus;
    private final List<String> flags;
    private final Object promo;
    private final List<Data> recentTransactions;
    private final List<RemainingVerification> remainingVerifications;

    static final class Builder extends com.coinbase.api.internal.models.dashboard.Data.Builder {
        private List<Balance> balance;
        private String firstBuyStatus;
        private List<String> flags;
        private Object promo;
        private List<Data> recentTransactions;
        private List<RemainingVerification> remainingVerifications;

        Builder() {
        }

        Builder(Data source) {
            this.balance = source.getBalance();
            this.flags = source.getFlags();
            this.remainingVerifications = source.getRemainingVerifications();
            this.promo = source.getPromo();
            this.recentTransactions = source.getRecentTransactions();
            this.firstBuyStatus = source.getFirstBuyStatus();
        }

        public com.coinbase.api.internal.models.dashboard.Data.Builder setBalance(List<Balance> balance) {
            this.balance = balance;
            return this;
        }

        public com.coinbase.api.internal.models.dashboard.Data.Builder setFlags(List<String> flags) {
            this.flags = flags;
            return this;
        }

        public com.coinbase.api.internal.models.dashboard.Data.Builder setRemainingVerifications(List<RemainingVerification> remainingVerifications) {
            this.remainingVerifications = remainingVerifications;
            return this;
        }

        public com.coinbase.api.internal.models.dashboard.Data.Builder setPromo(Object promo) {
            this.promo = promo;
            return this;
        }

        public com.coinbase.api.internal.models.dashboard.Data.Builder setRecentTransactions(List<Data> recentTransactions) {
            this.recentTransactions = recentTransactions;
            return this;
        }

        public com.coinbase.api.internal.models.dashboard.Data.Builder setFirstBuyStatus(String firstBuyStatus) {
            this.firstBuyStatus = firstBuyStatus;
            return this;
        }

        public Data build() {
            return new AutoValue_Data(this.balance, this.flags, this.remainingVerifications, this.promo, this.recentTransactions, this.firstBuyStatus);
        }
    }

    C$AutoValue_Data(List<Balance> balance, List<String> flags, List<RemainingVerification> remainingVerifications, Object promo, List<Data> recentTransactions, String firstBuyStatus) {
        this.balance = balance;
        this.flags = flags;
        this.remainingVerifications = remainingVerifications;
        this.promo = promo;
        this.recentTransactions = recentTransactions;
        this.firstBuyStatus = firstBuyStatus;
    }

    @SerializedName("balances")
    public List<Balance> getBalance() {
        return this.balance;
    }

    @SerializedName("flags")
    public List<String> getFlags() {
        return this.flags;
    }

    @SerializedName("remaining_verifications")
    public List<RemainingVerification> getRemainingVerifications() {
        return this.remainingVerifications;
    }

    @SerializedName("promo")
    public Object getPromo() {
        return this.promo;
    }

    @SerializedName("recent_transactions")
    public List<Data> getRecentTransactions() {
        return this.recentTransactions;
    }

    @SerializedName("first_buy_status")
    public String getFirstBuyStatus() {
        return this.firstBuyStatus;
    }

    public String toString() {
        return "Data{balance=" + this.balance + ", flags=" + this.flags + ", remainingVerifications=" + this.remainingVerifications + ", promo=" + this.promo + ", recentTransactions=" + this.recentTransactions + ", firstBuyStatus=" + this.firstBuyStatus + "}";
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
        if (this.balance != null) {
            if (this.balance.equals(that.getBalance())) {
            }
            return false;
        }
        if (this.flags != null) {
            if (this.flags.equals(that.getFlags())) {
            }
            return false;
        }
        if (this.remainingVerifications != null) {
            if (this.remainingVerifications.equals(that.getRemainingVerifications())) {
            }
            return false;
        }
        if (this.promo != null) {
            if (this.promo.equals(that.getPromo())) {
            }
            return false;
        }
        if (this.recentTransactions != null) {
            if (this.recentTransactions.equals(that.getRecentTransactions())) {
            }
            return false;
        }
        if (this.firstBuyStatus == null) {
            if (that.getFirstBuyStatus() == null) {
                return true;
            }
        } else if (this.firstBuyStatus.equals(that.getFirstBuyStatus())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((1 * 1000003) ^ (this.balance == null ? 0 : this.balance.hashCode())) * 1000003) ^ (this.flags == null ? 0 : this.flags.hashCode())) * 1000003) ^ (this.remainingVerifications == null ? 0 : this.remainingVerifications.hashCode())) * 1000003) ^ (this.promo == null ? 0 : this.promo.hashCode())) * 1000003) ^ (this.recentTransactions == null ? 0 : this.recentTransactions.hashCode())) * 1000003;
        if (this.firstBuyStatus != null) {
            i = this.firstBuyStatus.hashCode();
        }
        return h ^ i;
    }
}
