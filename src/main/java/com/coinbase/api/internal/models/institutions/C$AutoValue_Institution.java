package com.coinbase.api.internal.models.institutions;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Institution extends Institution {
    private final List<Data> data;

    static final class Builder extends com.coinbase.api.internal.models.institutions.Institution.Builder {
        private List<Data> data;

        Builder() {
        }

        Builder(Institution source) {
            this.data = source.getData();
        }

        public com.coinbase.api.internal.models.institutions.Institution.Builder setData(List<Data> data) {
            this.data = data;
            return this;
        }

        public Institution build() {
            return new AutoValue_Institution(this.data);
        }
    }

    C$AutoValue_Institution(List<Data> data) {
        this.data = data;
    }

    @SerializedName("data")
    public List<Data> getData() {
        return this.data;
    }

    public String toString() {
        return "Institution{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Institution)) {
            return false;
        }
        Institution that = (Institution) o;
        if (this.data != null) {
            return this.data.equals(that.getData());
        }
        if (that.getData() != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (1 * 1000003) ^ (this.data == null ? 0 : this.data.hashCode());
    }
}
