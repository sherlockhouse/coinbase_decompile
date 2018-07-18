package com.coinbase.api.internal.models.tiers;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Warning extends Warning {
    private final String id;
    private final String message;
    private final String url;

    C$AutoValue_Warning(String id, String message, String url) {
        this.id = id;
        this.message = message;
        this.url = url;
    }

    @SerializedName("id")
    public String getId() {
        return this.id;
    }

    @SerializedName("message")
    public String getMessage() {
        return this.message;
    }

    @SerializedName("url")
    public String getUrl() {
        return this.url;
    }

    public String toString() {
        return "Warning{id=" + this.id + ", message=" + this.message + ", url=" + this.url + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Warning)) {
            return false;
        }
        Warning that = (Warning) o;
        if (this.id != null) {
            if (this.id.equals(that.getId())) {
            }
            return false;
        }
        if (this.message != null) {
            if (this.message.equals(that.getMessage())) {
            }
            return false;
        }
        if (this.url == null) {
            if (that.getUrl() == null) {
                return true;
            }
        } else if (this.url.equals(that.getUrl())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.id == null ? 0 : this.id.hashCode())) * 1000003) ^ (this.message == null ? 0 : this.message.hashCode())) * 1000003;
        if (this.url != null) {
            i = this.url.hashCode();
        }
        return h ^ i;
    }
}
