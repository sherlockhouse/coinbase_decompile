package com.coinbase.api.internal.models.tiers;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_BuySellLimit extends BuySellLimit {
    private final String amount;
    private final Currency currency;
    private final Boolean enabled;
    private final String name;
    private final String perUnit;
    private final Integer periodInDays;
    private final String statusText;
    private final String type;

    C$AutoValue_BuySellLimit(String type, String name, Currency currency, String amount, Integer periodInDays, String perUnit, Boolean enabled, String statusText) {
        this.type = type;
        this.name = name;
        this.currency = currency;
        this.amount = amount;
        this.periodInDays = periodInDays;
        this.perUnit = perUnit;
        this.enabled = enabled;
        this.statusText = statusText;
    }

    @SerializedName("type")
    public String getType() {
        return this.type;
    }

    @SerializedName("name")
    public String getName() {
        return this.name;
    }

    @SerializedName("currency")
    public Currency getCurrency() {
        return this.currency;
    }

    @SerializedName("amount")
    public String getAmount() {
        return this.amount;
    }

    @SerializedName("period_in_days")
    public Integer getPeriodInDays() {
        return this.periodInDays;
    }

    @SerializedName("per_unit")
    public String getPerUnit() {
        return this.perUnit;
    }

    @SerializedName("enabled")
    public Boolean getEnabled() {
        return this.enabled;
    }

    @SerializedName("status_text")
    public String getStatusText() {
        return this.statusText;
    }

    public String toString() {
        return "BuySellLimit{type=" + this.type + ", name=" + this.name + ", currency=" + this.currency + ", amount=" + this.amount + ", periodInDays=" + this.periodInDays + ", perUnit=" + this.perUnit + ", enabled=" + this.enabled + ", statusText=" + this.statusText + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BuySellLimit)) {
            return false;
        }
        BuySellLimit that = (BuySellLimit) o;
        if (this.type != null) {
            if (this.type.equals(that.getType())) {
            }
            return false;
        }
        if (this.name != null) {
            if (this.name.equals(that.getName())) {
            }
            return false;
        }
        if (this.currency != null) {
            if (this.currency.equals(that.getCurrency())) {
            }
            return false;
        }
        if (this.amount != null) {
            if (this.amount.equals(that.getAmount())) {
            }
            return false;
        }
        if (this.periodInDays != null) {
            if (this.periodInDays.equals(that.getPeriodInDays())) {
            }
            return false;
        }
        if (this.perUnit != null) {
            if (this.perUnit.equals(that.getPerUnit())) {
            }
            return false;
        }
        if (this.enabled != null) {
            if (this.enabled.equals(that.getEnabled())) {
            }
            return false;
        }
        if (this.statusText == null) {
            if (that.getStatusText() == null) {
                return true;
            }
        } else if (this.statusText.equals(that.getStatusText())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((1 * 1000003) ^ (this.type == null ? 0 : this.type.hashCode())) * 1000003) ^ (this.name == null ? 0 : this.name.hashCode())) * 1000003) ^ (this.currency == null ? 0 : this.currency.hashCode())) * 1000003) ^ (this.amount == null ? 0 : this.amount.hashCode())) * 1000003) ^ (this.periodInDays == null ? 0 : this.periodInDays.hashCode())) * 1000003) ^ (this.perUnit == null ? 0 : this.perUnit.hashCode())) * 1000003) ^ (this.enabled == null ? 0 : this.enabled.hashCode())) * 1000003;
        if (this.statusText != null) {
            i = this.statusText.hashCode();
        }
        return h ^ i;
    }
}
