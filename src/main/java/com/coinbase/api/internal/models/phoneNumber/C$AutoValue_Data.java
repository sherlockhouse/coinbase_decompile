package com.coinbase.api.internal.models.phoneNumber;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Data extends Data {
    private final String createdAt;
    private final String id;
    private final String number;
    private final Boolean primary;
    private final String resource;
    private final String resourcePath;
    private final String updatedAt;
    private final Boolean verified;

    static final class Builder extends com.coinbase.api.internal.models.phoneNumber.Data.Builder {
        private String createdAt;
        private String id;
        private String number;
        private Boolean primary;
        private String resource;
        private String resourcePath;
        private String updatedAt;
        private Boolean verified;

        Builder() {
        }

        Builder(Data source) {
            this.id = source.getId();
            this.number = source.getNumber();
            this.primary = source.getPrimary();
            this.verified = source.getVerified();
            this.createdAt = source.getCreatedAt();
            this.updatedAt = source.getUpdatedAt();
            this.resource = source.getResource();
            this.resourcePath = source.getResourcePath();
        }

        public com.coinbase.api.internal.models.phoneNumber.Data.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public com.coinbase.api.internal.models.phoneNumber.Data.Builder setNumber(String number) {
            this.number = number;
            return this;
        }

        public com.coinbase.api.internal.models.phoneNumber.Data.Builder setPrimary(Boolean primary) {
            this.primary = primary;
            return this;
        }

        public com.coinbase.api.internal.models.phoneNumber.Data.Builder setVerified(Boolean verified) {
            this.verified = verified;
            return this;
        }

        public com.coinbase.api.internal.models.phoneNumber.Data.Builder setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public com.coinbase.api.internal.models.phoneNumber.Data.Builder setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public com.coinbase.api.internal.models.phoneNumber.Data.Builder setResource(String resource) {
            this.resource = resource;
            return this;
        }

        public com.coinbase.api.internal.models.phoneNumber.Data.Builder setResourcePath(String resourcePath) {
            this.resourcePath = resourcePath;
            return this;
        }

        public Data build() {
            return new AutoValue_Data(this.id, this.number, this.primary, this.verified, this.createdAt, this.updatedAt, this.resource, this.resourcePath);
        }
    }

    C$AutoValue_Data(String id, String number, Boolean primary, Boolean verified, String createdAt, String updatedAt, String resource, String resourcePath) {
        this.id = id;
        this.number = number;
        this.primary = primary;
        this.verified = verified;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.resource = resource;
        this.resourcePath = resourcePath;
    }

    @SerializedName("id")
    public String getId() {
        return this.id;
    }

    @SerializedName("number")
    public String getNumber() {
        return this.number;
    }

    @SerializedName("primary")
    public Boolean getPrimary() {
        return this.primary;
    }

    @SerializedName("verified")
    public Boolean getVerified() {
        return this.verified;
    }

    @SerializedName("created_at")
    public String getCreatedAt() {
        return this.createdAt;
    }

    @SerializedName("updated_at")
    public String getUpdatedAt() {
        return this.updatedAt;
    }

    @SerializedName("resource")
    public String getResource() {
        return this.resource;
    }

    @SerializedName("resource_path")
    public String getResourcePath() {
        return this.resourcePath;
    }

    public String toString() {
        return "Data{id=" + this.id + ", number=" + this.number + ", primary=" + this.primary + ", verified=" + this.verified + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", resource=" + this.resource + ", resourcePath=" + this.resourcePath + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Data)) {
            return false;
        }
        Data that = (Data) o;
        if (this.id != null) {
            if (this.id.equals(that.getId())) {
            }
            return false;
        }
        if (this.number != null) {
            if (this.number.equals(that.getNumber())) {
            }
            return false;
        }
        if (this.primary != null) {
            if (this.primary.equals(that.getPrimary())) {
            }
            return false;
        }
        if (this.verified != null) {
            if (this.verified.equals(that.getVerified())) {
            }
            return false;
        }
        if (this.createdAt != null) {
            if (this.createdAt.equals(that.getCreatedAt())) {
            }
            return false;
        }
        if (this.updatedAt != null) {
            if (this.updatedAt.equals(that.getUpdatedAt())) {
            }
            return false;
        }
        if (this.resource != null) {
            if (this.resource.equals(that.getResource())) {
            }
            return false;
        }
        if (this.resourcePath == null) {
            if (that.getResourcePath() == null) {
                return true;
            }
        } else if (this.resourcePath.equals(that.getResourcePath())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((1 * 1000003) ^ (this.id == null ? 0 : this.id.hashCode())) * 1000003) ^ (this.number == null ? 0 : this.number.hashCode())) * 1000003) ^ (this.primary == null ? 0 : this.primary.hashCode())) * 1000003) ^ (this.verified == null ? 0 : this.verified.hashCode())) * 1000003) ^ (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 1000003) ^ (this.updatedAt == null ? 0 : this.updatedAt.hashCode())) * 1000003) ^ (this.resource == null ? 0 : this.resource.hashCode())) * 1000003;
        if (this.resourcePath != null) {
            i = this.resourcePath.hashCode();
        }
        return h ^ i;
    }
}
