package com.coinbase.api.internal.models.address;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Data extends Data {
    private final String city;
    private final Country country;
    private final String id;
    private final String line1;
    private final Object line2;
    private final Object line3;
    private final String postalCode;
    private final String resource;
    private final String resourcePath;
    private final String state;

    C$AutoValue_Data(String id, String line1, Object line2, Object line3, String city, String state, String postalCode, Country country, String resource, String resourcePath) {
        this.id = id;
        this.line1 = line1;
        this.line2 = line2;
        this.line3 = line3;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.resource = resource;
        this.resourcePath = resourcePath;
    }

    @SerializedName("id")
    public String getId() {
        return this.id;
    }

    @SerializedName("line1")
    public String getLine1() {
        return this.line1;
    }

    @SerializedName("line2")
    public Object getLine2() {
        return this.line2;
    }

    @SerializedName("line3")
    public Object getLine3() {
        return this.line3;
    }

    @SerializedName("city")
    public String getCity() {
        return this.city;
    }

    @SerializedName("state")
    public String getState() {
        return this.state;
    }

    @SerializedName("postal_code")
    public String getPostalCode() {
        return this.postalCode;
    }

    @SerializedName("country")
    public Country getCountry() {
        return this.country;
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
        return "Data{id=" + this.id + ", line1=" + this.line1 + ", line2=" + this.line2 + ", line3=" + this.line3 + ", city=" + this.city + ", state=" + this.state + ", postalCode=" + this.postalCode + ", country=" + this.country + ", resource=" + this.resource + ", resourcePath=" + this.resourcePath + "}";
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
        if (this.line1 != null) {
            if (this.line1.equals(that.getLine1())) {
            }
            return false;
        }
        if (this.line2 != null) {
            if (this.line2.equals(that.getLine2())) {
            }
            return false;
        }
        if (this.line3 != null) {
            if (this.line3.equals(that.getLine3())) {
            }
            return false;
        }
        if (this.city != null) {
            if (this.city.equals(that.getCity())) {
            }
            return false;
        }
        if (this.state != null) {
            if (this.state.equals(that.getState())) {
            }
            return false;
        }
        if (this.postalCode != null) {
            if (this.postalCode.equals(that.getPostalCode())) {
            }
            return false;
        }
        if (this.country != null) {
            if (this.country.equals(that.getCountry())) {
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
        int h = ((((((((((((((((((1 * 1000003) ^ (this.id == null ? 0 : this.id.hashCode())) * 1000003) ^ (this.line1 == null ? 0 : this.line1.hashCode())) * 1000003) ^ (this.line2 == null ? 0 : this.line2.hashCode())) * 1000003) ^ (this.line3 == null ? 0 : this.line3.hashCode())) * 1000003) ^ (this.city == null ? 0 : this.city.hashCode())) * 1000003) ^ (this.state == null ? 0 : this.state.hashCode())) * 1000003) ^ (this.postalCode == null ? 0 : this.postalCode.hashCode())) * 1000003) ^ (this.country == null ? 0 : this.country.hashCode())) * 1000003) ^ (this.resource == null ? 0 : this.resource.hashCode())) * 1000003;
        if (this.resourcePath != null) {
            i = this.resourcePath.hashCode();
        }
        return h ^ i;
    }
}
