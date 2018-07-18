package com.coinbase.android.wbl;

import com.coinbase.api.internal.models.wbl.PendingHold;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;
import org.joda.money.Money;

abstract class C$AutoValue_AvailableBalance extends AvailableBalance {
    private final Map<String, Money> accountBalances;
    private final Map<String, Money> accountCryptoBalances;
    private final List<PendingHold> pendingHolds;
    private final Money totalAvailableBalance;
    private final Money totalHoldBalance;
    private final Money totalPortfolioBalance;

    static final class Builder extends com.coinbase.android.wbl.AvailableBalance.Builder {
        private Map<String, Money> accountBalances;
        private Map<String, Money> accountCryptoBalances;
        private List<PendingHold> pendingHolds;
        private Money totalAvailableBalance;
        private Money totalHoldBalance;
        private Money totalPortfolioBalance;

        Builder() {
        }

        Builder(AvailableBalance source) {
            this.totalPortfolioBalance = source.getTotalPortfolioBalance();
            this.totalHoldBalance = source.getTotalHoldBalance();
            this.totalAvailableBalance = source.getTotalAvailableBalance();
            this.pendingHolds = source.getPendingHolds();
            this.accountBalances = source.getAccountBalances();
            this.accountCryptoBalances = source.getAccountCryptoBalances();
        }

        public com.coinbase.android.wbl.AvailableBalance.Builder setTotalPortfolioBalance(Money totalPortfolioBalance) {
            this.totalPortfolioBalance = totalPortfolioBalance;
            return this;
        }

        public com.coinbase.android.wbl.AvailableBalance.Builder setTotalHoldBalance(Money totalHoldBalance) {
            this.totalHoldBalance = totalHoldBalance;
            return this;
        }

        public com.coinbase.android.wbl.AvailableBalance.Builder setTotalAvailableBalance(Money totalAvailableBalance) {
            this.totalAvailableBalance = totalAvailableBalance;
            return this;
        }

        public com.coinbase.android.wbl.AvailableBalance.Builder setPendingHolds(List<PendingHold> pendingHolds) {
            this.pendingHolds = pendingHolds;
            return this;
        }

        public com.coinbase.android.wbl.AvailableBalance.Builder setAccountBalances(Map<String, Money> accountBalances) {
            this.accountBalances = accountBalances;
            return this;
        }

        public com.coinbase.android.wbl.AvailableBalance.Builder setAccountCryptoBalances(Map<String, Money> accountCryptoBalances) {
            this.accountCryptoBalances = accountCryptoBalances;
            return this;
        }

        public AvailableBalance build() {
            return new AutoValue_AvailableBalance(this.totalPortfolioBalance, this.totalHoldBalance, this.totalAvailableBalance, this.pendingHolds, this.accountBalances, this.accountCryptoBalances);
        }
    }

    C$AutoValue_AvailableBalance(Money totalPortfolioBalance, Money totalHoldBalance, Money totalAvailableBalance, List<PendingHold> pendingHolds, Map<String, Money> accountBalances, Map<String, Money> accountCryptoBalances) {
        this.totalPortfolioBalance = totalPortfolioBalance;
        this.totalHoldBalance = totalHoldBalance;
        this.totalAvailableBalance = totalAvailableBalance;
        this.pendingHolds = pendingHolds;
        this.accountBalances = accountBalances;
        this.accountCryptoBalances = accountCryptoBalances;
    }

    @SerializedName("total_portfolio_balance")
    public Money getTotalPortfolioBalance() {
        return this.totalPortfolioBalance;
    }

    @SerializedName("total_hold_balance")
    public Money getTotalHoldBalance() {
        return this.totalHoldBalance;
    }

    @SerializedName("total_available_balance")
    public Money getTotalAvailableBalance() {
        return this.totalAvailableBalance;
    }

    @SerializedName("pending_holds")
    public List<PendingHold> getPendingHolds() {
        return this.pendingHolds;
    }

    @SerializedName("account_balances")
    public Map<String, Money> getAccountBalances() {
        return this.accountBalances;
    }

    @SerializedName("account_crypto_balances")
    public Map<String, Money> getAccountCryptoBalances() {
        return this.accountCryptoBalances;
    }

    public String toString() {
        return "AvailableBalance{totalPortfolioBalance=" + this.totalPortfolioBalance + ", totalHoldBalance=" + this.totalHoldBalance + ", totalAvailableBalance=" + this.totalAvailableBalance + ", pendingHolds=" + this.pendingHolds + ", accountBalances=" + this.accountBalances + ", accountCryptoBalances=" + this.accountCryptoBalances + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AvailableBalance)) {
            return false;
        }
        AvailableBalance that = (AvailableBalance) o;
        if (this.totalPortfolioBalance != null) {
            if (this.totalPortfolioBalance.equals(that.getTotalPortfolioBalance())) {
            }
            return false;
        }
        if (this.totalHoldBalance != null) {
            if (this.totalHoldBalance.equals(that.getTotalHoldBalance())) {
            }
            return false;
        }
        if (this.totalAvailableBalance != null) {
            if (this.totalAvailableBalance.equals(that.getTotalAvailableBalance())) {
            }
            return false;
        }
        if (this.pendingHolds != null) {
            if (this.pendingHolds.equals(that.getPendingHolds())) {
            }
            return false;
        }
        if (this.accountBalances != null) {
            if (this.accountBalances.equals(that.getAccountBalances())) {
            }
            return false;
        }
        if (this.accountCryptoBalances == null) {
            if (that.getAccountCryptoBalances() == null) {
                return true;
            }
        } else if (this.accountCryptoBalances.equals(that.getAccountCryptoBalances())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((1 * 1000003) ^ (this.totalPortfolioBalance == null ? 0 : this.totalPortfolioBalance.hashCode())) * 1000003) ^ (this.totalHoldBalance == null ? 0 : this.totalHoldBalance.hashCode())) * 1000003) ^ (this.totalAvailableBalance == null ? 0 : this.totalAvailableBalance.hashCode())) * 1000003) ^ (this.pendingHolds == null ? 0 : this.pendingHolds.hashCode())) * 1000003) ^ (this.accountBalances == null ? 0 : this.accountBalances.hashCode())) * 1000003;
        if (this.accountCryptoBalances != null) {
            i = this.accountCryptoBalances.hashCode();
        }
        return h ^ i;
    }
}
