package com.coinbase.api.internal.models.tiers;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Requirement extends Requirement {
    private final String description;
    private final String identifier;
    private final String status;

    C$AutoValue_Requirement(String identifier, String description, String status) {
        this.identifier = identifier;
        this.description = description;
        this.status = status;
    }

    @SerializedName("identifier")
    public String getIdentifier() {
        return this.identifier;
    }

    @SerializedName("description")
    public String getDescription() {
        return this.description;
    }

    @SerializedName("status")
    public String getStatus() {
        return this.status;
    }

    public String toString() {
        return "Requirement{identifier=" + this.identifier + ", description=" + this.description + ", status=" + this.status + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Requirement)) {
            return false;
        }
        Requirement that = (Requirement) o;
        if (this.identifier != null) {
            if (this.identifier.equals(that.getIdentifier())) {
            }
            return false;
        }
        if (this.description != null) {
            if (this.description.equals(that.getDescription())) {
            }
            return false;
        }
        if (this.status == null) {
            if (that.getStatus() == null) {
                return true;
            }
        } else if (this.status.equals(that.getStatus())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.identifier == null ? 0 : this.identifier.hashCode())) * 1000003) ^ (this.description == null ? 0 : this.description.hashCode())) * 1000003;
        if (this.status != null) {
            i = this.status.hashCode();
        }
        return h ^ i;
    }
}
