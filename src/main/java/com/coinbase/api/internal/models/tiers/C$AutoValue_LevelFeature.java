package com.coinbase.api.internal.models.tiers;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_LevelFeature extends LevelFeature {
    private final String description;
    private final Boolean enabled;
    private final String statusText;

    C$AutoValue_LevelFeature(String description, Boolean enabled, String statusText) {
        this.description = description;
        this.enabled = enabled;
        this.statusText = statusText;
    }

    @SerializedName("description")
    public String getDescription() {
        return this.description;
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
        return "LevelFeature{description=" + this.description + ", enabled=" + this.enabled + ", statusText=" + this.statusText + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof LevelFeature)) {
            return false;
        }
        LevelFeature that = (LevelFeature) o;
        if (this.description != null) {
            if (this.description.equals(that.getDescription())) {
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
        int h = ((((1 * 1000003) ^ (this.description == null ? 0 : this.description.hashCode())) * 1000003) ^ (this.enabled == null ? 0 : this.enabled.hashCode())) * 1000003;
        if (this.statusText != null) {
            i = this.statusText.hashCode();
        }
        return h ^ i;
    }
}
