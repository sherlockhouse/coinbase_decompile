package com.coinbase.api.internal.models.paymentMethods;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Data extends Data {
    private final List<Mapping> mapping;
    private final String oneTimeToken;
    private final String processUrl;

    static final class Builder extends com.coinbase.api.internal.models.paymentMethods.Data.Builder {
        private List<Mapping> mapping;
        private String oneTimeToken;
        private String processUrl;

        Builder() {
        }

        Builder(Data source) {
            this.oneTimeToken = source.getOneTimeToken();
            this.processUrl = source.getProcessUrl();
            this.mapping = source.getMapping();
        }

        public com.coinbase.api.internal.models.paymentMethods.Data.Builder setOneTimeToken(String oneTimeToken) {
            this.oneTimeToken = oneTimeToken;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.Data.Builder setProcessUrl(String processUrl) {
            this.processUrl = processUrl;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.Data.Builder setMapping(List<Mapping> mapping) {
            this.mapping = mapping;
            return this;
        }

        public Data build() {
            return new AutoValue_Data(this.oneTimeToken, this.processUrl, this.mapping);
        }
    }

    C$AutoValue_Data(String oneTimeToken, String processUrl, List<Mapping> mapping) {
        this.oneTimeToken = oneTimeToken;
        this.processUrl = processUrl;
        this.mapping = mapping;
    }

    @SerializedName("one_time_token")
    public String getOneTimeToken() {
        return this.oneTimeToken;
    }

    @SerializedName("process_url")
    public String getProcessUrl() {
        return this.processUrl;
    }

    @SerializedName("mapping")
    public List<Mapping> getMapping() {
        return this.mapping;
    }

    public String toString() {
        return "Data{oneTimeToken=" + this.oneTimeToken + ", processUrl=" + this.processUrl + ", mapping=" + this.mapping + "}";
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
        if (this.oneTimeToken != null) {
            if (this.oneTimeToken.equals(that.getOneTimeToken())) {
            }
            return false;
        }
        if (this.processUrl != null) {
            if (this.processUrl.equals(that.getProcessUrl())) {
            }
            return false;
        }
        if (this.mapping == null) {
            if (that.getMapping() == null) {
                return true;
            }
        } else if (this.mapping.equals(that.getMapping())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.oneTimeToken == null ? 0 : this.oneTimeToken.hashCode())) * 1000003) ^ (this.processUrl == null ? 0 : this.processUrl.hashCode())) * 1000003;
        if (this.mapping != null) {
            i = this.mapping.hashCode();
        }
        return h ^ i;
    }
}
