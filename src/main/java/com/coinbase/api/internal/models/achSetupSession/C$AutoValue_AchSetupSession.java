package com.coinbase.api.internal.models.achSetupSession;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_AchSetupSession extends AchSetupSession {
    private final Data data;

    C$AutoValue_AchSetupSession(Data data) {
        this.data = data;
    }

    @SerializedName("data")
    public Data getData() {
        return this.data;
    }

    public String toString() {
        return "AchSetupSession{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AchSetupSession)) {
            return false;
        }
        AchSetupSession that = (AchSetupSession) o;
        if (this.data != null) {
            return this.data.equals(that.getData());
        }
        if (that.getData() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.data == null ? 0 : this.data.hashCode());
    }
}
