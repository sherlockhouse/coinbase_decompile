package com.coinbase.api.internal.models.config;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Config extends Config {
    private final Data data;

    static final class Builder extends com.coinbase.api.internal.models.config.Config.Builder {
        private Data data;

        Builder() {
        }

        Builder(Config source) {
            this.data = source.getData();
        }

        public com.coinbase.api.internal.models.config.Config.Builder setData(Data data) {
            this.data = data;
            return this;
        }

        public Config build() {
            return new AutoValue_Config(this.data);
        }
    }

    C$AutoValue_Config(Data data) {
        this.data = data;
    }

    @SerializedName("data")
    public Data getData() {
        return this.data;
    }

    public String toString() {
        return "Config{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Config)) {
            return false;
        }
        Config that = (Config) o;
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
