package com.coinbase.api.internal.models.tiers;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_VerificationLevels extends VerificationLevels {
    private final List<Level> levels;

    C$AutoValue_VerificationLevels(List<Level> levels) {
        this.levels = levels;
    }

    @SerializedName("levels")
    public List<Level> getLevels() {
        return this.levels;
    }

    public String toString() {
        return "VerificationLevels{levels=" + this.levels + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof VerificationLevels)) {
            return false;
        }
        VerificationLevels that = (VerificationLevels) o;
        if (this.levels != null) {
            return this.levels.equals(that.getLevels());
        }
        if (that.getLevels() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.levels == null ? 0 : this.levels.hashCode());
    }
}
