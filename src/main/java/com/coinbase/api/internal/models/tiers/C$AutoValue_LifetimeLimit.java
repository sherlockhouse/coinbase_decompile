package com.coinbase.api.internal.models.tiers;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_LifetimeLimit extends LifetimeLimit {
    private final String amount;
    private final Currency currency;
    private final String description;
    private final String name;
    private final String remaining;
    private final Boolean unlimited;
    private final String used;

    C$AutoValue_LifetimeLimit(String name, String description, Currency currency, String amount, String used, String remaining, Boolean unlimited) {
        this.name = name;
        this.description = description;
        this.currency = currency;
        this.amount = amount;
        this.used = used;
        this.remaining = remaining;
        this.unlimited = unlimited;
    }

    @SerializedName("name")
    public String getName() {
        return this.name;
    }

    @SerializedName("description")
    public String getDescription() {
        return this.description;
    }

    @SerializedName("currency")
    public Currency getCurrency() {
        return this.currency;
    }

    @SerializedName("amount")
    public String getAmount() {
        return this.amount;
    }

    @SerializedName("used")
    public String getUsed() {
        return this.used;
    }

    @SerializedName("remaining")
    public String getRemaining() {
        return this.remaining;
    }

    @SerializedName("unlimited")
    public Boolean getUnlimited() {
        return this.unlimited;
    }

    public String toString() {
        return "LifetimeLimit{name=" + this.name + ", description=" + this.description + ", currency=" + this.currency + ", amount=" + this.amount + ", used=" + this.used + ", remaining=" + this.remaining + ", unlimited=" + this.unlimited + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LifetimeLimit)) {
            return false;
        }
        LifetimeLimit that = (LifetimeLimit) o;
        if (this.name != null) {
            if (this.name.equals(that.getName())) {
            }
            return false;
        }
        if (this.description != null) {
            if (this.description.equals(that.getDescription())) {
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
        if (this.used != null) {
            if (this.used.equals(that.getUsed())) {
            }
            return false;
        }
        if (this.remaining != null) {
            if (this.remaining.equals(that.getRemaining())) {
            }
            return false;
        }
        if (this.unlimited == null) {
            if (that.getUnlimited() == null) {
                return true;
            }
        } else if (this.unlimited.equals(that.getUnlimited())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((1 * 1000003) ^ (this.name == null ? 0 : this.name.hashCode())) * 1000003) ^ (this.description == null ? 0 : this.description.hashCode())) * 1000003) ^ (this.currency == null ? 0 : this.currency.hashCode())) * 1000003) ^ (this.amount == null ? 0 : this.amount.hashCode())) * 1000003) ^ (this.used == null ? 0 : this.used.hashCode())) * 1000003) ^ (this.remaining == null ? 0 : this.remaining.hashCode())) * 1000003;
        if (this.unlimited != null) {
            i = this.unlimited.hashCode();
        }
        return h ^ i;
    }
}
