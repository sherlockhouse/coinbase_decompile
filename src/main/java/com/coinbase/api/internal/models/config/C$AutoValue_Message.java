package com.coinbase.api.internal.models.config;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Message extends Message {
    private final String action;
    private final String description;
    private final String image;
    private final String link;
    private final String minVersion;
    private final String title;
    private final String version;
    private final VersionRange versionRange;

    static final class Builder extends com.coinbase.api.internal.models.config.Message.Builder {
        private String action;
        private String description;
        private String image;
        private String link;
        private String minVersion;
        private String title;
        private String version;
        private VersionRange versionRange;

        Builder() {
        }

        Builder(Message source) {
            this.image = source.getImage();
            this.versionRange = source.getVersionRange();
            this.title = source.getTitle();
            this.description = source.getDescription();
            this.action = source.getAction();
            this.version = source.getVersion();
            this.minVersion = source.getMinVersion();
            this.link = source.getLink();
        }

        public com.coinbase.api.internal.models.config.Message.Builder setImage(String image) {
            this.image = image;
            return this;
        }

        public com.coinbase.api.internal.models.config.Message.Builder setVersionRange(VersionRange versionRange) {
            this.versionRange = versionRange;
            return this;
        }

        public com.coinbase.api.internal.models.config.Message.Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public com.coinbase.api.internal.models.config.Message.Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public com.coinbase.api.internal.models.config.Message.Builder setAction(String action) {
            this.action = action;
            return this;
        }

        public com.coinbase.api.internal.models.config.Message.Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public com.coinbase.api.internal.models.config.Message.Builder setMinVersion(String minVersion) {
            this.minVersion = minVersion;
            return this;
        }

        public com.coinbase.api.internal.models.config.Message.Builder setLink(String link) {
            this.link = link;
            return this;
        }

        public Message build() {
            return new AutoValue_Message(this.image, this.versionRange, this.title, this.description, this.action, this.version, this.minVersion, this.link);
        }
    }

    C$AutoValue_Message(String image, VersionRange versionRange, String title, String description, String action, String version, String minVersion, String link) {
        this.image = image;
        this.versionRange = versionRange;
        this.title = title;
        this.description = description;
        this.action = action;
        this.version = version;
        this.minVersion = minVersion;
        this.link = link;
    }

    @SerializedName("image")
    public String getImage() {
        return this.image;
    }

    @SerializedName("version_range")
    public VersionRange getVersionRange() {
        return this.versionRange;
    }

    @SerializedName("title")
    public String getTitle() {
        return this.title;
    }

    @SerializedName("description")
    public String getDescription() {
        return this.description;
    }

    @SerializedName("action")
    public String getAction() {
        return this.action;
    }

    @SerializedName("version")
    public String getVersion() {
        return this.version;
    }

    @SerializedName("min_version")
    public String getMinVersion() {
        return this.minVersion;
    }

    @SerializedName("link")
    public String getLink() {
        return this.link;
    }

    public String toString() {
        return "Message{image=" + this.image + ", versionRange=" + this.versionRange + ", title=" + this.title + ", description=" + this.description + ", action=" + this.action + ", version=" + this.version + ", minVersion=" + this.minVersion + ", link=" + this.link + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Message)) {
            return false;
        }
        Message that = (Message) o;
        if (this.image != null) {
            if (this.image.equals(that.getImage())) {
            }
            return false;
        }
        if (this.versionRange != null) {
            if (this.versionRange.equals(that.getVersionRange())) {
            }
            return false;
        }
        if (this.title != null) {
            if (this.title.equals(that.getTitle())) {
            }
            return false;
        }
        if (this.description != null) {
            if (this.description.equals(that.getDescription())) {
            }
            return false;
        }
        if (this.action != null) {
            if (this.action.equals(that.getAction())) {
            }
            return false;
        }
        if (this.version != null) {
            if (this.version.equals(that.getVersion())) {
            }
            return false;
        }
        if (this.minVersion != null) {
            if (this.minVersion.equals(that.getMinVersion())) {
            }
            return false;
        }
        if (this.link == null) {
            if (that.getLink() == null) {
                return true;
            }
        } else if (this.link.equals(that.getLink())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((1 * 1000003) ^ (this.image == null ? 0 : this.image.hashCode())) * 1000003) ^ (this.versionRange == null ? 0 : this.versionRange.hashCode())) * 1000003) ^ (this.title == null ? 0 : this.title.hashCode())) * 1000003) ^ (this.description == null ? 0 : this.description.hashCode())) * 1000003) ^ (this.action == null ? 0 : this.action.hashCode())) * 1000003) ^ (this.version == null ? 0 : this.version.hashCode())) * 1000003) ^ (this.minVersion == null ? 0 : this.minVersion.hashCode())) * 1000003;
        if (this.link != null) {
            i = this.link.hashCode();
        }
        return h ^ i;
    }
}
