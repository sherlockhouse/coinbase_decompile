package com.coinbase.api.internal.models.tiers;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Level extends Level {
    private final String description;
    private final String name;
    private final List<Requirement> requirements;
    private final Status status;

    C$AutoValue_Level(String name, String description, List<Requirement> requirements, Status status) {
        this.name = name;
        this.description = description;
        this.requirements = requirements;
        this.status = status;
    }

    @SerializedName("name")
    public String getName() {
        return this.name;
    }

    @SerializedName("description")
    public String getDescription() {
        return this.description;
    }

    @SerializedName("requirements")
    public List<Requirement> getRequirements() {
        return this.requirements;
    }

    @SerializedName("status")
    public Status getStatus() {
        return this.status;
    }

    public String toString() {
        return "Level{name=" + this.name + ", description=" + this.description + ", requirements=" + this.requirements + ", status=" + this.status + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Level)) {
            return false;
        }
        Level that = (Level) o;
        if (this.name != null) {
            if (this.name.equals(that.getName())) {
            }
            return false;
        }
        if (this.description != null) {
            if (this.description.equals(that.getDescription())) {
            }
            return false;
        }
        if (this.requirements != null) {
            if (this.requirements.equals(that.getRequirements())) {
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
        int h = ((((((1 * 1000003) ^ (this.name == null ? 0 : this.name.hashCode())) * 1000003) ^ (this.description == null ? 0 : this.description.hashCode())) * 1000003) ^ (this.requirements == null ? 0 : this.requirements.hashCode())) * 1000003;
        if (this.status != null) {
            i = this.status.hashCode();
        }
        return h ^ i;
    }
}
