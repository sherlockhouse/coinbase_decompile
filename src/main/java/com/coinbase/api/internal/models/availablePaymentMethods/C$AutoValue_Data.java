package com.coinbase.api.internal.models.availablePaymentMethods;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Data extends Data {
    private final List<AvailablePaymentMethod> availablePaymentMethods;
    private final String baseFees;

    C$AutoValue_Data(List<AvailablePaymentMethod> availablePaymentMethods, String baseFees) {
        this.availablePaymentMethods = availablePaymentMethods;
        this.baseFees = baseFees;
    }

    @SerializedName("available_payment_methods")
    public List<AvailablePaymentMethod> getAvailablePaymentMethods() {
        return this.availablePaymentMethods;
    }

    @SerializedName("base_fees")
    public String getBaseFees() {
        return this.baseFees;
    }

    public String toString() {
        return "Data{availablePaymentMethods=" + this.availablePaymentMethods + ", baseFees=" + this.baseFees + "}";
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
        if (this.availablePaymentMethods != null) {
            if (this.availablePaymentMethods.equals(that.getAvailablePaymentMethods())) {
            }
            return false;
        }
        if (this.baseFees == null) {
            if (that.getBaseFees() == null) {
                return true;
            }
        } else if (this.baseFees.equals(that.getBaseFees())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.availablePaymentMethods == null ? 0 : this.availablePaymentMethods.hashCode())) * 1000003;
        if (this.baseFees != null) {
            i = this.baseFees.hashCode();
        }
        return h ^ i;
    }
}
