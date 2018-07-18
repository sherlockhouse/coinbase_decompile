package com.coinbase.api.internal.models.institutions;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Data extends Data {
    private final Credentials credentials;
    private final Boolean hasMfa;
    private final Image image;
    private final List<Object> mfa;
    private final String name;
    private final String type;

    static final class Builder extends com.coinbase.api.internal.models.institutions.Data.Builder {
        private Credentials credentials;
        private Boolean hasMfa;
        private Image image;
        private List<Object> mfa;
        private String name;
        private String type;

        Builder() {
        }

        Builder(Data source) {
            this.credentials = source.getCredentials();
            this.hasMfa = source.getHasMfa();
            this.mfa = source.getMfa();
            this.name = source.getName();
            this.type = source.getType();
            this.image = source.getImage();
        }

        public com.coinbase.api.internal.models.institutions.Data.Builder setCredentials(Credentials credentials) {
            this.credentials = credentials;
            return this;
        }

        public com.coinbase.api.internal.models.institutions.Data.Builder setHasMfa(Boolean hasMfa) {
            this.hasMfa = hasMfa;
            return this;
        }

        public com.coinbase.api.internal.models.institutions.Data.Builder setMfa(List<Object> mfa) {
            this.mfa = mfa;
            return this;
        }

        public com.coinbase.api.internal.models.institutions.Data.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public com.coinbase.api.internal.models.institutions.Data.Builder setType(String type) {
            this.type = type;
            return this;
        }

        public com.coinbase.api.internal.models.institutions.Data.Builder setImage(Image image) {
            this.image = image;
            return this;
        }

        public Data build() {
            return new AutoValue_Data(this.credentials, this.hasMfa, this.mfa, this.name, this.type, this.image);
        }
    }

    C$AutoValue_Data(Credentials credentials, Boolean hasMfa, List<Object> mfa, String name, String type, Image image) {
        this.credentials = credentials;
        this.hasMfa = hasMfa;
        this.mfa = mfa;
        this.name = name;
        this.type = type;
        this.image = image;
    }

    @SerializedName("credentials")
    public Credentials getCredentials() {
        return this.credentials;
    }

    @SerializedName("has_mfa")
    public Boolean getHasMfa() {
        return this.hasMfa;
    }

    @SerializedName("mfa")
    public List<Object> getMfa() {
        return this.mfa;
    }

    @SerializedName("name")
    public String getName() {
        return this.name;
    }

    @SerializedName("type")
    public String getType() {
        return this.type;
    }

    @SerializedName("image")
    public Image getImage() {
        return this.image;
    }

    public String toString() {
        return "Data{credentials=" + this.credentials + ", hasMfa=" + this.hasMfa + ", mfa=" + this.mfa + ", name=" + this.name + ", type=" + this.type + ", image=" + this.image + "}";
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
        if (this.credentials != null) {
            if (this.credentials.equals(that.getCredentials())) {
            }
            return false;
        }
        if (this.hasMfa != null) {
            if (this.hasMfa.equals(that.getHasMfa())) {
            }
            return false;
        }
        if (this.mfa != null) {
            if (this.mfa.equals(that.getMfa())) {
            }
            return false;
        }
        if (this.name != null) {
            if (this.name.equals(that.getName())) {
            }
            return false;
        }
        if (this.type != null) {
            if (this.type.equals(that.getType())) {
            }
            return false;
        }
        if (this.image == null) {
            if (that.getImage() == null) {
                return true;
            }
        } else if (this.image.equals(that.getImage())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((1 * 1000003) ^ (this.credentials == null ? 0 : this.credentials.hashCode())) * 1000003) ^ (this.hasMfa == null ? 0 : this.hasMfa.hashCode())) * 1000003) ^ (this.mfa == null ? 0 : this.mfa.hashCode())) * 1000003) ^ (this.name == null ? 0 : this.name.hashCode())) * 1000003) ^ (this.type == null ? 0 : this.type.hashCode())) * 1000003;
        if (this.image != null) {
            i = this.image.hashCode();
        }
        return h ^ i;
    }
}
