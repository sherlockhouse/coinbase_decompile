package com.coinbase.api.internal.models.paymentMethods.verify;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Verify extends Verify {
    private final Data data;

    static final class Builder extends com.coinbase.api.internal.models.paymentMethods.verify.Verify.Builder {
        private Data data;

        Builder() {
        }

        Builder(Verify source) {
            this.data = source.getData();
        }

        public com.coinbase.api.internal.models.paymentMethods.verify.Verify.Builder setData(Data data) {
            this.data = data;
            return this;
        }

        public Verify build() {
            String missing = "";
            if (this.data == null) {
                missing = missing + " data";
            }
            if (missing.isEmpty()) {
                return new AutoValue_Verify(this.data);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_Verify(Data data) {
        if (data == null) {
            throw new NullPointerException("Null data");
        }
        this.data = data;
    }

    @SerializedName("data")
    public Data getData() {
        return this.data;
    }

    public String toString() {
        return "Verify{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Verify)) {
            return false;
        }
        return this.data.equals(((Verify) o).getData());
    }

    public int hashCode() {
        return (1 * 1000003) ^ this.data.hashCode();
    }
}
