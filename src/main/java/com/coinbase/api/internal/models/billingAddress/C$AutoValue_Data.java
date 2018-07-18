package com.coinbase.api.internal.models.billingAddress;

import com.coinbase.api.internal.models.Country;
import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Data extends Data {
    private final String city;
    private final Country country;
    private final String id;
    private final String line1;
    private final String line2;
    private final String line3;
    private final String postalCode;
    private final String resource;
    private final String resourcePath;
    private final String state;

    static final class Builder extends com.coinbase.api.internal.models.billingAddress.Data.Builder {
        private String city;
        private Country country;
        private String id;
        private String line1;
        private String line2;
        private String line3;
        private String postalCode;
        private String resource;
        private String resourcePath;
        private String state;

        Builder() {
        }

        Builder(Data source) {
            this.id = source.getId();
            this.line1 = source.getLine1();
            this.line2 = source.getLine2();
            this.line3 = source.getLine3();
            this.city = source.getCity();
            this.state = source.getState();
            this.postalCode = source.getPostalCode();
            this.country = source.getCountry();
            this.resource = source.getResource();
            this.resourcePath = source.getResourcePath();
        }

        public com.coinbase.api.internal.models.billingAddress.Data.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public com.coinbase.api.internal.models.billingAddress.Data.Builder setLine1(String line1) {
            this.line1 = line1;
            return this;
        }

        public com.coinbase.api.internal.models.billingAddress.Data.Builder setLine2(String line2) {
            this.line2 = line2;
            return this;
        }

        public com.coinbase.api.internal.models.billingAddress.Data.Builder setLine3(String line3) {
            this.line3 = line3;
            return this;
        }

        public com.coinbase.api.internal.models.billingAddress.Data.Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public com.coinbase.api.internal.models.billingAddress.Data.Builder setState(String state) {
            this.state = state;
            return this;
        }

        public com.coinbase.api.internal.models.billingAddress.Data.Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public com.coinbase.api.internal.models.billingAddress.Data.Builder setCountry(Country country) {
            this.country = country;
            return this;
        }

        public com.coinbase.api.internal.models.billingAddress.Data.Builder setResource(String resource) {
            this.resource = resource;
            return this;
        }

        public com.coinbase.api.internal.models.billingAddress.Data.Builder setResourcePath(String resourcePath) {
            this.resourcePath = resourcePath;
            return this;
        }

        public Data build() {
            String missing = "";
            if (this.id == null) {
                missing = missing + " id";
            }
            if (missing.isEmpty()) {
                return new AutoValue_Data(this.id, this.line1, this.line2, this.line3, this.city, this.state, this.postalCode, this.country, this.resource, this.resourcePath);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_Data(String id, String line1, String line2, String line3, String city, String state, String postalCode, Country country, String resource, String resourcePath) {
        if (id == null) {
            throw new NullPointerException("Null id");
        }
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
    public String getLine2() {
        return this.line2;
    }

    @SerializedName("line3")
    public String getLine3() {
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
        if (this.id.equals(that.getId())) {
            if (this.line1 != null) {
                if (this.line1.equals(that.getLine1())) {
                }
            }
            if (this.line2 != null) {
                if (this.line2.equals(that.getLine2())) {
                }
            }
            if (this.line3 != null) {
                if (this.line3.equals(that.getLine3())) {
                }
            }
            if (this.city != null) {
                if (this.city.equals(that.getCity())) {
                }
            }
            if (this.state != null) {
                if (this.state.equals(that.getState())) {
                }
            }
            if (this.postalCode != null) {
                if (this.postalCode.equals(that.getPostalCode())) {
                }
            }
            if (this.country != null) {
                if (this.country.equals(that.getCountry())) {
                }
            }
            if (this.resource != null) {
                if (this.resource.equals(that.getResource())) {
                }
            }
            if (this.resourcePath == null) {
                if (that.getResourcePath() == null) {
                    return true;
                }
            } else if (this.resourcePath.equals(that.getResourcePath())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((((1 * 1000003) ^ this.id.hashCode()) * 1000003) ^ (this.line1 == null ? 0 : this.line1.hashCode())) * 1000003) ^ (this.line2 == null ? 0 : this.line2.hashCode())) * 1000003) ^ (this.line3 == null ? 0 : this.line3.hashCode())) * 1000003) ^ (this.city == null ? 0 : this.city.hashCode())) * 1000003) ^ (this.state == null ? 0 : this.state.hashCode())) * 1000003) ^ (this.postalCode == null ? 0 : this.postalCode.hashCode())) * 1000003) ^ (this.country == null ? 0 : this.country.hashCode())) * 1000003) ^ (this.resource == null ? 0 : this.resource.hashCode())) * 1000003;
        if (this.resourcePath != null) {
            i = this.resourcePath.hashCode();
        }
        return h ^ i;
    }
}
