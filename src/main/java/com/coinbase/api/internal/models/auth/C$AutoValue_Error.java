package com.coinbase.api.internal.models.auth;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Error extends Error {
    private final String _2faAuthentication;
    private final String error;
    private final Boolean success;

    static final class Builder extends com.coinbase.api.internal.models.auth.Error.Builder {
        private String _2faAuthentication;
        private String error;
        private Boolean success;

        Builder() {
        }

        Builder(Error source) {
            this.success = source.getSuccess();
            this.error = source.getError();
            this._2faAuthentication = source.get_2faAuthentication();
        }

        public com.coinbase.api.internal.models.auth.Error.Builder setSuccess(Boolean success) {
            this.success = success;
            return this;
        }

        public com.coinbase.api.internal.models.auth.Error.Builder setError(String error) {
            this.error = error;
            return this;
        }

        public com.coinbase.api.internal.models.auth.Error.Builder set_2faAuthentication(String _2faAuthentication) {
            this._2faAuthentication = _2faAuthentication;
            return this;
        }

        public Error build() {
            return new AutoValue_Error(this.success, this.error, this._2faAuthentication);
        }
    }

    C$AutoValue_Error(Boolean success, String error, String _2faAuthentication) {
        this.success = success;
        this.error = error;
        this._2faAuthentication = _2faAuthentication;
    }

    @SerializedName("success")
    public Boolean getSuccess() {
        return this.success;
    }

    @SerializedName("error")
    public String getError() {
        return this.error;
    }

    @SerializedName("2fa_authentication")
    public String get_2faAuthentication() {
        return this._2faAuthentication;
    }

    public String toString() {
        return "Error{success=" + this.success + ", error=" + this.error + ", _2faAuthentication=" + this._2faAuthentication + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Error)) {
            return false;
        }
        Error that = (Error) o;
        if (this.success != null) {
            if (this.success.equals(that.getSuccess())) {
            }
            return false;
        }
        if (this.error != null) {
            if (this.error.equals(that.getError())) {
            }
            return false;
        }
        if (this._2faAuthentication == null) {
            if (that.get_2faAuthentication() == null) {
                return true;
            }
        } else if (this._2faAuthentication.equals(that.get_2faAuthentication())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.success == null ? 0 : this.success.hashCode())) * 1000003) ^ (this.error == null ? 0 : this.error.hashCode())) * 1000003;
        if (this._2faAuthentication != null) {
            i = this._2faAuthentication.hashCode();
        }
        return h ^ i;
    }
}
