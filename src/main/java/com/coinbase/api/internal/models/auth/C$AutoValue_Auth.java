package com.coinbase.api.internal.models.auth;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Auth extends Auth {
    private final String code;
    private final Boolean success;

    C$AutoValue_Auth(Boolean success, String code) {
        this.success = success;
        this.code = code;
    }

    @SerializedName("success")
    public Boolean getSuccess() {
        return this.success;
    }

    @SerializedName("code")
    public String getCode() {
        return this.code;
    }

    public String toString() {
        return "Auth{success=" + this.success + ", code=" + this.code + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Auth)) {
            return false;
        }
        Auth that = (Auth) o;
        if (this.success != null) {
            if (this.success.equals(that.getSuccess())) {
            }
            return false;
        }
        if (this.code == null) {
            if (that.getCode() == null) {
                return true;
            }
        } else if (this.code.equals(that.getCode())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.success == null ? 0 : this.success.hashCode())) * 1000003;
        if (this.code != null) {
            i = this.code.hashCode();
        }
        return h ^ i;
    }
}
