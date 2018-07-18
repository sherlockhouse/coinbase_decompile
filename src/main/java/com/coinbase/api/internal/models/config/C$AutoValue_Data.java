package com.coinbase.api.internal.models.config;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Data extends Data {
    private final Android android;
    private final Ios ios;

    static final class Builder extends com.coinbase.api.internal.models.config.Data.Builder {
        private Android android;
        private Ios ios;

        Builder() {
        }

        Builder(Data source) {
            this.android = source.getAndroid();
            this.ios = source.getIos();
        }

        public com.coinbase.api.internal.models.config.Data.Builder setAndroid(Android android) {
            this.android = android;
            return this;
        }

        public com.coinbase.api.internal.models.config.Data.Builder setIos(Ios ios) {
            this.ios = ios;
            return this;
        }

        public Data build() {
            return new AutoValue_Data(this.android, this.ios);
        }
    }

    C$AutoValue_Data(Android android, Ios ios) {
        this.android = android;
        this.ios = ios;
    }

    @SerializedName("android")
    public Android getAndroid() {
        return this.android;
    }

    @SerializedName("ios")
    public Ios getIos() {
        return this.ios;
    }

    public String toString() {
        return "Data{android=" + this.android + ", ios=" + this.ios + "}";
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
        if (this.android != null) {
            if (this.android.equals(that.getAndroid())) {
            }
            return false;
        }
        if (this.ios == null) {
            if (that.getIos() == null) {
                return true;
            }
        } else if (this.ios.equals(that.getIos())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.android == null ? 0 : this.android.hashCode())) * 1000003;
        if (this.ios != null) {
            i = this.ios.hashCode();
        }
        return h ^ i;
    }
}
