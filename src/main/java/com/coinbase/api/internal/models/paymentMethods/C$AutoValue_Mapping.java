package com.coinbase.api.internal.models.paymentMethods;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Mapping extends Mapping {
    private final String id;
    private final String name;
    private final String value;

    static final class Builder extends com.coinbase.api.internal.models.paymentMethods.Mapping.Builder {
        private String id;
        private String name;
        private String value;

        Builder() {
        }

        Builder(Mapping source) {
            this.id = source.getId();
            this.name = source.getName();
            this.value = source.getValue();
        }

        public com.coinbase.api.internal.models.paymentMethods.Mapping.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.Mapping.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.Mapping.Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Mapping build() {
            return new AutoValue_Mapping(this.id, this.name, this.value);
        }
    }

    C$AutoValue_Mapping(String id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @SerializedName("id")
    public String getId() {
        return this.id;
    }

    @SerializedName("name")
    public String getName() {
        return this.name;
    }

    @SerializedName("value")
    public String getValue() {
        return this.value;
    }

    public String toString() {
        return "Mapping{id=" + this.id + ", name=" + this.name + ", value=" + this.value + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Mapping)) {
            return false;
        }
        Mapping that = (Mapping) o;
        if (this.id != null) {
            if (this.id.equals(that.getId())) {
            }
            return false;
        }
        if (this.name != null) {
            if (this.name.equals(that.getName())) {
            }
            return false;
        }
        if (this.value == null) {
            if (that.getValue() == null) {
                return true;
            }
        } else if (this.value.equals(that.getValue())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.id == null ? 0 : this.id.hashCode())) * 1000003) ^ (this.name == null ? 0 : this.name.hashCode())) * 1000003;
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return h ^ i;
    }
}
