package com.coinbase.api.internal.models.currency;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Currencies extends Currencies {
    private final List<Data> data;

    static final class Builder extends com.coinbase.api.internal.models.currency.Currencies.Builder {
        private List<Data> data;

        Builder() {
        }

        Builder(Currencies source) {
            this.data = source.getData();
        }

        public com.coinbase.api.internal.models.currency.Currencies.Builder setData(List<Data> data) {
            this.data = data;
            return this;
        }

        public Currencies build() {
            return new AutoValue_Currencies(this.data);
        }
    }

    C$AutoValue_Currencies(List<Data> data) {
        this.data = data;
    }

    @SerializedName("data")
    public List<Data> getData() {
        return this.data;
    }

    public String toString() {
        return "Currencies{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Currencies)) {
            return false;
        }
        Currencies that = (Currencies) o;
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
