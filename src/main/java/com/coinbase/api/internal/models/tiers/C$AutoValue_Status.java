package com.coinbase.api.internal.models.tiers;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Status extends Status {
    private final Boolean complete;
    private final String description;

    C$AutoValue_Status(Boolean complete, String description) {
        this.complete = complete;
        this.description = description;
    }

    @SerializedName("complete")
    public Boolean getComplete() {
        return this.complete;
    }

    @SerializedName("description")
    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "Status{complete=" + this.complete + ", description=" + this.description + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Status)) {
            return false;
        }
        Status that = (Status) o;
        if (this.complete != null) {
            if (this.complete.equals(that.getComplete())) {
            }
            return false;
        }
        if (this.description == null) {
            if (that.getDescription() == null) {
                return true;
            }
        } else if (this.description.equals(that.getDescription())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.complete == null ? 0 : this.complete.hashCode())) * 1000003;
        if (this.description != null) {
            i = this.description.hashCode();
        }
        return h ^ i;
    }
}
