package com.coinbase.api.internal.models.config;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_VersionRange extends VersionRange {
    private final String max;
    private final String min;

    C$AutoValue_VersionRange(String min, String max) {
        this.min = min;
        this.max = max;
    }

    @SerializedName("min")
    public String getMin() {
        return this.min;
    }

    @SerializedName("max")
    public String getMax() {
        return this.max;
    }

    public String toString() {
        return "VersionRange{min=" + this.min + ", max=" + this.max + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VersionRange)) {
            return false;
        }
        VersionRange that = (VersionRange) o;
        if (this.min != null) {
            if (this.min.equals(that.getMin())) {
            }
            return false;
        }
        if (this.max == null) {
            if (that.getMax() == null) {
                return true;
            }
        } else if (this.max.equals(that.getMax())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.min == null ? 0 : this.min.hashCode())) * 1000003;
        if (this.max != null) {
            i = this.max.hashCode();
        }
        return h ^ i;
    }
}
