package com.coinbase.api.internal.models.billingAddress;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_BillingAddress extends BillingAddress {
    private final Data data;

    C$AutoValue_BillingAddress(Data data) {
        this.data = data;
    }

    @SerializedName("data")
    public Data getData() {
        return this.data;
    }

    public String toString() {
        return "BillingAddress{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BillingAddress)) {
            return false;
        }
        BillingAddress that = (BillingAddress) o;
        if (this.data != null) {
            return this.data.equals(that.getData());
        }
        if (that.getData() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.data == null ? 0 : this.data.hashCode());
    }
}
