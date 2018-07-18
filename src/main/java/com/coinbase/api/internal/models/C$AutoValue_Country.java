package com.coinbase.api.internal.models;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Country extends Country {
    private final String code;
    private final String image;
    private final String name;

    C$AutoValue_Country(String code, String name, String image) {
        this.code = code;
        this.name = name;
        this.image = image;
    }

    @SerializedName("code")
    public String getCode() {
        return this.code;
    }

    @SerializedName("name")
    public String getName() {
        return this.name;
    }

    @SerializedName("image")
    public String getImage() {
        return this.image;
    }

    public String toString() {
        return "Country{code=" + this.code + ", name=" + this.name + ", image=" + this.image + "}";
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
        if (this.name != null) {
            if (this.name.equals(that.getName())) {
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
        int h = ((((1 * 1000003) ^ (this.code == null ? 0 : this.code.hashCode())) * 1000003) ^ (this.name == null ? 0 : this.name.hashCode())) * 1000003;
        if (this.image != null) {
            i = this.image.hashCode();
        }
        return h ^ i;
    }
}
