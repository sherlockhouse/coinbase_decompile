package com.coinbase.api.internal.models.idology;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Country extends Country {
    private final String code;
    private final String name;

    static final class Builder extends com.coinbase.api.internal.models.idology.Country.Builder {
        private String code;
        private String name;

        Builder() {
        }

        Builder(Country source) {
            this.code = source.getCode();
            this.name = source.getName();
        }

        public com.coinbase.api.internal.models.idology.Country.Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Country.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Country build() {
            return new AutoValue_Country(this.code, this.name);
        }
    }

    C$AutoValue_Country(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @SerializedName("code")
    public String getCode() {
        return this.code;
    }

    @SerializedName("name")
    public String getName() {
        return this.name;
    }

    public String toString() {
        return "Country{code=" + this.code + ", name=" + this.name + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Country)) {
            return false;
        }
        Country that = (Country) o;
        if (this.code != null) {
            if (this.code.equals(that.getCode())) {
            }
            return false;
        }
        if (this.name == null) {
            if (that.getName() == null) {
                return true;
            }
        } else if (this.name.equals(that.getName())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.code == null ? 0 : this.code.hashCode())) * 1000003;
        if (this.name != null) {
            i = this.name.hashCode();
        }
        return h ^ i;
    }
}
