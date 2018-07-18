package com.coinbase.api.internal.models.idology;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Address extends Address {
    private final String city;
    private final Country country;
    private final String line1;
    private final String line2;
    private final String postalCode;
    private final String state;

    static final class Builder extends com.coinbase.api.internal.models.idology.Address.Builder {
        private String city;
        private Country country;
        private String line1;
        private String line2;
        private String postalCode;
        private String state;

        Builder() {
        }

        Builder(Address source) {
            this.line1 = source.getLine1();
            this.line2 = source.getLine2();
            this.city = source.getCity();
            this.state = source.getState();
            this.postalCode = source.getPostalCode();
            this.country = source.getCountry();
        }

        public com.coinbase.api.internal.models.idology.Address.Builder setLine1(String line1) {
            this.line1 = line1;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Address.Builder setLine2(String line2) {
            this.line2 = line2;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Address.Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Address.Builder setState(String state) {
            this.state = state;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Address.Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Address.Builder setCountry(Country country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new AutoValue_Address(this.line1, this.line2, this.city, this.state, this.postalCode, this.country);
        }
    }

    C$AutoValue_Address(String line1, String line2, String city, String state, String postalCode, Country country) {
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    @SerializedName("line1")
    public String getLine1() {
        return this.line1;
    }

    @SerializedName("line2")
    public String getLine2() {
        return this.line2;
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

    public String toString() {
        return "Address{line1=" + this.line1 + ", line2=" + this.line2 + ", city=" + this.city + ", state=" + this.state + ", postalCode=" + this.postalCode + ", country=" + this.country + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Address)) {
            return false;
        }
        Address that = (Address) o;
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
        if (this.country == null) {
            if (that.getCountry() == null) {
                return true;
            }
        } else if (this.country.equals(that.getCountry())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((1 * 1000003) ^ (this.line1 == null ? 0 : this.line1.hashCode())) * 1000003) ^ (this.line2 == null ? 0 : this.line2.hashCode())) * 1000003) ^ (this.city == null ? 0 : this.city.hashCode())) * 1000003) ^ (this.state == null ? 0 : this.state.hashCode())) * 1000003) ^ (this.postalCode == null ? 0 : this.postalCode.hashCode())) * 1000003;
        if (this.country != null) {
            i = this.country.hashCode();
        }
        return h ^ i;
    }
}
