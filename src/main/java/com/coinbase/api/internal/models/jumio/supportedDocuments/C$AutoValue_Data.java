package com.coinbase.api.internal.models.jumio.supportedDocuments;

import com.coinbase.api.internal.models.Country;
import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Data extends Data {
    private final Country country;
    private final List<SupportedIdType> supportedIdTypes;

    C$AutoValue_Data(Country country, List<SupportedIdType> supportedIdTypes) {
        this.country = country;
        this.supportedIdTypes = supportedIdTypes;
    }

    @SerializedName("country")
    public Country getCountry() {
        return this.country;
    }

    @SerializedName("supported_id_types")
    public List<SupportedIdType> getSupportedIdTypes() {
        return this.supportedIdTypes;
    }

    public String toString() {
        return "Data{country=" + this.country + ", supportedIdTypes=" + this.supportedIdTypes + "}";
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
        if (this.country != null) {
            if (this.country.equals(that.getCountry())) {
            }
            return false;
        }
        if (this.supportedIdTypes == null) {
            if (that.getSupportedIdTypes() == null) {
                return true;
            }
        } else if (this.supportedIdTypes.equals(that.getSupportedIdTypes())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.country == null ? 0 : this.country.hashCode())) * 1000003;
        if (this.supportedIdTypes != null) {
            i = this.supportedIdTypes.hashCode();
        }
        return h ^ i;
    }
}
