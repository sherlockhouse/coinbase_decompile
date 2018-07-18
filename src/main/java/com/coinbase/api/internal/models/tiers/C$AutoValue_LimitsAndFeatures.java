package com.coinbase.api.internal.models.tiers;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_LimitsAndFeatures extends LimitsAndFeatures {
    private final List<BuySellLimit> buyDepositLimits;
    private final LifetimeLimit lifetimeLimit;
    private final LevelFeature receives;
    private final LevelFeature sends;
    private final String title;

    C$AutoValue_LimitsAndFeatures(String title, LifetimeLimit lifetimeLimit, List<BuySellLimit> buyDepositLimits, LevelFeature sends, LevelFeature receives) {
        this.title = title;
        this.lifetimeLimit = lifetimeLimit;
        this.buyDepositLimits = buyDepositLimits;
        this.sends = sends;
        this.receives = receives;
    }

    @SerializedName("title")
    public String getTitle() {
        return this.title;
    }

    @SerializedName("lifetime_limit")
    public LifetimeLimit getLifetimeLimit() {
        return this.lifetimeLimit;
    }

    @SerializedName("buy_deposit_limits")
    public List<BuySellLimit> getBuyDepositLimits() {
        return this.buyDepositLimits;
    }

    @SerializedName("sends")
    public LevelFeature getSends() {
        return this.sends;
    }

    @SerializedName("receives")
    public LevelFeature getReceives() {
        return this.receives;
    }

    public String toString() {
        return "LimitsAndFeatures{title=" + this.title + ", lifetimeLimit=" + this.lifetimeLimit + ", buyDepositLimits=" + this.buyDepositLimits + ", sends=" + this.sends + ", receives=" + this.receives + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LimitsAndFeatures)) {
            return false;
        }
        LimitsAndFeatures that = (LimitsAndFeatures) o;
        if (this.title != null) {
            if (this.title.equals(that.getTitle())) {
            }
            return false;
        }
        if (this.lifetimeLimit != null) {
            if (this.lifetimeLimit.equals(that.getLifetimeLimit())) {
            }
            return false;
        }
        if (this.buyDepositLimits != null) {
            if (this.buyDepositLimits.equals(that.getBuyDepositLimits())) {
            }
            return false;
        }
        if (this.sends != null) {
            if (this.sends.equals(that.getSends())) {
            }
            return false;
        }
        if (this.receives == null) {
            if (that.getReceives() == null) {
                return true;
            }
        } else if (this.receives.equals(that.getReceives())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((1 * 1000003) ^ (this.title == null ? 0 : this.title.hashCode())) * 1000003) ^ (this.lifetimeLimit == null ? 0 : this.lifetimeLimit.hashCode())) * 1000003) ^ (this.buyDepositLimits == null ? 0 : this.buyDepositLimits.hashCode())) * 1000003) ^ (this.sends == null ? 0 : this.sends.hashCode())) * 1000003;
        if (this.receives != null) {
            i = this.receives.hashCode();
        }
        return h ^ i;
    }
}
