package com.coinbase.api.internal.models.wbl;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_AccountBalance extends AccountBalance {
    private final String accountId;
    private final Amount availableBalance;
    private final Amount availableNativeBalance;

    C$AutoValue_AccountBalance(String accountId, Amount availableBalance, Amount availableNativeBalance) {
        this.accountId = accountId;
        this.availableBalance = availableBalance;
        this.availableNativeBalance = availableNativeBalance;
    }

    @SerializedName("account_id")
    public String getAccountId() {
        return this.accountId;
    }

    @SerializedName("available_balance")
    public Amount getAvailableBalance() {
        return this.availableBalance;
    }

    @SerializedName("available_native_balance")
    public Amount getAvailableNativeBalance() {
        return this.availableNativeBalance;
    }

    public String toString() {
        return "AccountBalance{accountId=" + this.accountId + ", availableBalance=" + this.availableBalance + ", availableNativeBalance=" + this.availableNativeBalance + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AccountBalance)) {
            return false;
        }
        AccountBalance that = (AccountBalance) o;
        if (this.accountId != null) {
            if (this.accountId.equals(that.getAccountId())) {
            }
            return false;
        }
        if (this.availableBalance != null) {
            if (this.availableBalance.equals(that.getAvailableBalance())) {
            }
            return false;
        }
        if (this.availableNativeBalance == null) {
            if (that.getAvailableNativeBalance() == null) {
                return true;
            }
        } else if (this.availableNativeBalance.equals(that.getAvailableNativeBalance())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.accountId == null ? 0 : this.accountId.hashCode())) * 1000003) ^ (this.availableBalance == null ? 0 : this.availableBalance.hashCode())) * 1000003;
        if (this.availableNativeBalance != null) {
            i = this.availableNativeBalance.hashCode();
        }
        return h ^ i;
    }
}
