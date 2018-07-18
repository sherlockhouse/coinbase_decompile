package com.coinbase.api.internal.models.idology;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Idology extends Idology {
    private final Data data;

    static final class Builder extends com.coinbase.api.internal.models.idology.Idology.Builder {
        private Data data;

        Builder() {
        }

        Builder(Idology source) {
            this.data = source.getData();
        }

        public com.coinbase.api.internal.models.idology.Idology.Builder setData(Data data) {
            this.data = data;
            return this;
        }

        public Idology build() {
            return new AutoValue_Idology(this.data);
        }
    }

    C$AutoValue_Idology(Data data) {
        this.data = data;
    }

    @SerializedName("data")
    public Data getData() {
        return this.data;
    }

    public String toString() {
        return "Idology{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Idology)) {
            return false;
        }
        Idology that = (Idology) o;
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
