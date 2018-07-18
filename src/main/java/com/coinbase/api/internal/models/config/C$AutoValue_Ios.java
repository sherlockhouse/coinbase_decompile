package com.coinbase.api.internal.models.config;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Ios extends Ios {
    private final Message message;
    private final Message version;

    C$AutoValue_Ios(Message version, Message message) {
        this.version = version;
        this.message = message;
    }

    @SerializedName("version")
    public Message getVersion() {
        return this.version;
    }

    @SerializedName("message")
    public Message getMessage() {
        return this.message;
    }

    public String toString() {
        return "Ios{version=" + this.version + ", message=" + this.message + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Ios)) {
            return false;
        }
        Ios that = (Ios) o;
        if (this.version != null) {
            if (this.version.equals(that.getVersion())) {
            }
            return false;
        }
        if (this.message == null) {
            if (that.getMessage() == null) {
                return true;
            }
        } else if (this.message.equals(that.getMessage())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.version == null ? 0 : this.version.hashCode())) * 1000003;
        if (this.message != null) {
            i = this.message.hashCode();
        }
        return h ^ i;
    }
}
