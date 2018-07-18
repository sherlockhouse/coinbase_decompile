package com.coinbase.api.internal.models.dashboard;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Dashboard extends Dashboard {
    private final Data data;

    static final class Builder extends com.coinbase.api.internal.models.dashboard.Dashboard.Builder {
        private Data data;

        Builder() {
        }

        Builder(Dashboard source) {
            this.data = source.getData();
        }

        public com.coinbase.api.internal.models.dashboard.Dashboard.Builder setData(Data data) {
            this.data = data;
            return this;
        }

        public Dashboard build() {
            return new AutoValue_Dashboard(this.data);
        }
    }

    C$AutoValue_Dashboard(Data data) {
        this.data = data;
    }

    @SerializedName("data")
    public Data getData() {
        return this.data;
    }

    public String toString() {
        return "Dashboard{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Dashboard)) {
            return false;
        }
        Dashboard that = (Dashboard) o;
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
