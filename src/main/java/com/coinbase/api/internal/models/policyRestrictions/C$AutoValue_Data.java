package com.coinbase.api.internal.models.policyRestrictions;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Data extends Data {
    private final List<PolicyRestrictions> policyRestrictions;

    C$AutoValue_Data(List<PolicyRestrictions> policyRestrictions) {
        this.policyRestrictions = policyRestrictions;
    }

    @SerializedName("policy_restrictions")
    public List<PolicyRestrictions> getPolicyRestrictions() {
        return this.policyRestrictions;
    }

    public String toString() {
        return "Data{policyRestrictions=" + this.policyRestrictions + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Data)) {
            return false;
        }
        Data that = (Data) o;
        if (this.policyRestrictions != null) {
            return this.policyRestrictions.equals(that.getPolicyRestrictions());
        }
        if (that.getPolicyRestrictions() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.policyRestrictions == null ? 0 : this.policyRestrictions.hashCode());
    }
}
