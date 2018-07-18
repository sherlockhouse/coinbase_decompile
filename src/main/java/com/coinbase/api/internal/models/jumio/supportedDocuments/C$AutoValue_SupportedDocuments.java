package com.coinbase.api.internal.models.jumio.supportedDocuments;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_SupportedDocuments extends SupportedDocuments {
    private final List<Data> data;

    C$AutoValue_SupportedDocuments(List<Data> data) {
        this.data = data;
    }

    @SerializedName("data")
    public List<Data> getData() {
        return this.data;
    }

    public String toString() {
        return "SupportedDocuments{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SupportedDocuments)) {
            return false;
        }
        SupportedDocuments that = (SupportedDocuments) o;
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
