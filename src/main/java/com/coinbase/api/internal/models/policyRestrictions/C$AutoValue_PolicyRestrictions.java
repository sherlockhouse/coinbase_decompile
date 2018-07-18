package com.coinbase.api.internal.models.policyRestrictions;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_PolicyRestrictions extends PolicyRestrictions {
    private final String description;
    private final String error;
    private final String id;
    private final String message;
    private final Boolean pending;
    private final Boolean required;
    private final Url url;
    private final String userType;

    C$AutoValue_PolicyRestrictions(String id, String error, String userType, Boolean required, Boolean pending, String message, String description, Url url) {
        this.id = id;
        this.error = error;
        this.userType = userType;
        this.required = required;
        this.pending = pending;
        this.message = message;
        this.description = description;
        this.url = url;
    }

    @SerializedName("id")
    public String getId() {
        return this.id;
    }

    @SerializedName("error")
    public String getError() {
        return this.error;
    }

    @SerializedName("user_type")
    public String getUserType() {
        return this.userType;
    }

    @SerializedName("required")
    public Boolean getRequired() {
        return this.required;
    }

    @SerializedName("pending")
    public Boolean getPending() {
        return this.pending;
    }

    @SerializedName("message")
    public String getMessage() {
        return this.message;
    }

    @SerializedName("description")
    public String getDescription() {
        return this.description;
    }

    @SerializedName("url")
    public Url getUrl() {
        return this.url;
    }

    public String toString() {
        return "PolicyRestrictions{id=" + this.id + ", error=" + this.error + ", userType=" + this.userType + ", required=" + this.required + ", pending=" + this.pending + ", message=" + this.message + ", description=" + this.description + ", url=" + this.url + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof PolicyRestrictions)) {
            return false;
        }
        PolicyRestrictions that = (PolicyRestrictions) o;
        if (this.id != null) {
            if (this.id.equals(that.getId())) {
            }
            return false;
        }
        if (this.error != null) {
            if (this.error.equals(that.getError())) {
            }
            return false;
        }
        if (this.userType != null) {
            if (this.userType.equals(that.getUserType())) {
            }
            return false;
        }
        if (this.required != null) {
            if (this.required.equals(that.getRequired())) {
            }
            return false;
        }
        if (this.pending != null) {
            if (this.pending.equals(that.getPending())) {
            }
            return false;
        }
        if (this.message != null) {
            if (this.message.equals(that.getMessage())) {
            }
            return false;
        }
        if (this.description != null) {
            if (this.description.equals(that.getDescription())) {
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
        int h = ((((((((((((((1 * 1000003) ^ (this.id == null ? 0 : this.id.hashCode())) * 1000003) ^ (this.error == null ? 0 : this.error.hashCode())) * 1000003) ^ (this.userType == null ? 0 : this.userType.hashCode())) * 1000003) ^ (this.required == null ? 0 : this.required.hashCode())) * 1000003) ^ (this.pending == null ? 0 : this.pending.hashCode())) * 1000003) ^ (this.message == null ? 0 : this.message.hashCode())) * 1000003) ^ (this.description == null ? 0 : this.description.hashCode())) * 1000003;
        if (this.url != null) {
            i = this.url.hashCode();
        }
        return h ^ i;
    }
}
