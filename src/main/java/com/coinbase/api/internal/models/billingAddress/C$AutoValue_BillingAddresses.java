package com.coinbase.api.internal.models.billingAddress;

import com.coinbase.api.internal.models.Pagination;
import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_BillingAddresses extends BillingAddresses {
    private final List<Data> data;
    private final Pagination pagination;

    C$AutoValue_BillingAddresses(Pagination pagination, List<Data> data) {
        this.pagination = pagination;
        this.data = data;
    }

    @SerializedName("pagination")
    public Pagination getPagination() {
        return this.pagination;
    }

    @SerializedName("data")
    public List<Data> getData() {
        return this.data;
    }

    public String toString() {
        return "BillingAddresses{pagination=" + this.pagination + ", data=" + this.data + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BillingAddresses)) {
            return false;
        }
        BillingAddresses that = (BillingAddresses) o;
        if (this.pagination != null) {
            if (this.pagination.equals(that.getPagination())) {
            }
            return false;
        }
        if (this.data == null) {
            if (that.getData() == null) {
                return true;
            }
        } else if (this.data.equals(that.getData())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.pagination == null ? 0 : this.pagination.hashCode())) * 1000003;
        if (this.data != null) {
            i = this.data.hashCode();
        }
        return h ^ i;
    }
}
