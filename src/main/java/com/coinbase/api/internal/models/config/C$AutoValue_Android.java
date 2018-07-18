package com.coinbase.api.internal.models.config;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Android extends Android {
    private final Message message;
    private final Message version;

    static final class Builder extends com.coinbase.api.internal.models.config.Android.Builder {
        private Message message;
        private Message version;

        Builder() {
        }

        Builder(Android source) {
            this.version = source.getVersion();
            this.message = source.getMessage();
        }

        public com.coinbase.api.internal.models.config.Android.Builder setVersion(Message version) {
            this.version = version;
            return this;
        }

        public com.coinbase.api.internal.models.config.Android.Builder setMessage(Message message) {
            this.message = message;
            return this;
        }

        public Android build() {
            return new AutoValue_Android(this.version, this.message);
        }
    }

    C$AutoValue_Android(Message version, Message message) {
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
        return "Android{version=" + this.version + ", message=" + this.message + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Android)) {
            return false;
        }
        Android that = (Android) o;
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
