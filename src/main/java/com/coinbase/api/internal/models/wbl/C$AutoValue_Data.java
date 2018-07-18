package com.coinbase.api.internal.models.wbl;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Data extends Data {
    private final List<PendingHold> pendingHolds;

    C$AutoValue_Data(List<PendingHold> pendingHolds) {
        this.pendingHolds = pendingHolds;
    }

    @SerializedName("pending_holds")
    public List<PendingHold> getPendingHolds() {
        return this.pendingHolds;
    }

    public String toString() {
        return "Data{pendingHolds=" + this.pendingHolds + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Data)) {
            return false;
        }
        Data that = (Data) o;
        if (this.pendingHolds != null) {
            return this.pendingHolds.equals(that.getPendingHolds());
        }
        if (that.getPendingHolds() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.pendingHolds == null ? 0 : this.pendingHolds.hashCode());
    }
}
