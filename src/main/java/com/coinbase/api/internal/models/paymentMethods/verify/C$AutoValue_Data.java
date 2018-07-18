package com.coinbase.api.internal.models.paymentMethods.verify;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Data extends Data {
    private final Boolean allowBuy;
    private final Boolean allowSell;
    private final String createdAt;
    private final String currency;
    private final String id;
    private final String name;
    private final Boolean primaryBuy;
    private final Boolean primarySell;
    private final String type;
    private final String updatedAt;
    private final Boolean verified;

    static final class Builder extends com.coinbase.api.internal.models.paymentMethods.verify.Data.Builder {
        private Boolean allowBuy;
        private Boolean allowSell;
        private String createdAt;
        private String currency;
        private String id;
        private String name;
        private Boolean primaryBuy;
        private Boolean primarySell;
        private String type;
        private String updatedAt;
        private Boolean verified;

        Builder() {
        }

        Builder(Data source) {
            this.id = source.getId();
            this.type = source.getType();
            this.name = source.getName();
            this.currency = source.getCurrency();
            this.primaryBuy = source.getPrimaryBuy();
            this.primarySell = source.getPrimarySell();
            this.allowBuy = source.getAllowBuy();
            this.allowSell = source.getAllowSell();
            this.createdAt = source.getCreatedAt();
            this.updatedAt = source.getUpdatedAt();
            this.verified = source.getVerified();
        }

        public com.coinbase.api.internal.models.paymentMethods.verify.Data.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.verify.Data.Builder setType(String type) {
            this.type = type;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.verify.Data.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.verify.Data.Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.verify.Data.Builder setPrimaryBuy(Boolean primaryBuy) {
            this.primaryBuy = primaryBuy;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.verify.Data.Builder setPrimarySell(Boolean primarySell) {
            this.primarySell = primarySell;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.verify.Data.Builder setAllowBuy(Boolean allowBuy) {
            this.allowBuy = allowBuy;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.verify.Data.Builder setAllowSell(Boolean allowSell) {
            this.allowSell = allowSell;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.verify.Data.Builder setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.verify.Data.Builder setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public com.coinbase.api.internal.models.paymentMethods.verify.Data.Builder setVerified(Boolean verified) {
            this.verified = verified;
            return this;
        }

        public Data build() {
            return new AutoValue_Data(this.id, this.type, this.name, this.currency, this.primaryBuy, this.primarySell, this.allowBuy, this.allowSell, this.createdAt, this.updatedAt, this.verified);
        }
    }

    C$AutoValue_Data(String id, String type, String name, String currency, Boolean primaryBuy, Boolean primarySell, Boolean allowBuy, Boolean allowSell, String createdAt, String updatedAt, Boolean verified) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.currency = currency;
        this.primaryBuy = primaryBuy;
        this.primarySell = primarySell;
        this.allowBuy = allowBuy;
        this.allowSell = allowSell;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.verified = verified;
    }

    @SerializedName("id")
    public String getId() {
        return this.id;
    }

    @SerializedName("type")
    public String getType() {
        return this.type;
    }

    @SerializedName("name")
    public String getName() {
        return this.name;
    }

    @SerializedName("currency")
    public String getCurrency() {
        return this.currency;
    }

    @SerializedName("primary_buy")
    public Boolean getPrimaryBuy() {
        return this.primaryBuy;
    }

    @SerializedName("primary_sell")
    public Boolean getPrimarySell() {
        return this.primarySell;
    }

    @SerializedName("allow_buy")
    public Boolean getAllowBuy() {
        return this.allowBuy;
    }

    @SerializedName("allow_sell")
    public Boolean getAllowSell() {
        return this.allowSell;
    }

    @SerializedName("created_at")
    public String getCreatedAt() {
        return this.createdAt;
    }

    @SerializedName("updated_at")
    public String getUpdatedAt() {
        return this.updatedAt;
    }

    @SerializedName("verified")
    public Boolean getVerified() {
        return this.verified;
    }

    public String toString() {
        return "Data{id=" + this.id + ", type=" + this.type + ", name=" + this.name + ", currency=" + this.currency + ", primaryBuy=" + this.primaryBuy + ", primarySell=" + this.primarySell + ", allowBuy=" + this.allowBuy + ", allowSell=" + this.allowSell + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", verified=" + this.verified + "}";
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
        if (this.id != null) {
            if (this.id.equals(that.getId())) {
            }
            return false;
        }
        if (this.type != null) {
            if (this.type.equals(that.getType())) {
            }
            return false;
        }
        if (this.name != null) {
            if (this.name.equals(that.getName())) {
            }
            return false;
        }
        if (this.currency != null) {
            if (this.currency.equals(that.getCurrency())) {
            }
            return false;
        }
        if (this.primaryBuy != null) {
            if (this.primaryBuy.equals(that.getPrimaryBuy())) {
            }
            return false;
        }
        if (this.primarySell != null) {
            if (this.primarySell.equals(that.getPrimarySell())) {
            }
            return false;
        }
        if (this.allowBuy != null) {
            if (this.allowBuy.equals(that.getAllowBuy())) {
            }
            return false;
        }
        if (this.allowSell != null) {
            if (this.allowSell.equals(that.getAllowSell())) {
            }
            return false;
        }
        if (this.createdAt != null) {
            if (this.createdAt.equals(that.getCreatedAt())) {
            }
            return false;
        }
        if (this.updatedAt != null) {
            if (this.updatedAt.equals(that.getUpdatedAt())) {
            }
            return false;
        }
        if (this.verified == null) {
            if (that.getVerified() == null) {
                return true;
            }
        } else if (this.verified.equals(that.getVerified())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((((((1 * 1000003) ^ (this.id == null ? 0 : this.id.hashCode())) * 1000003) ^ (this.type == null ? 0 : this.type.hashCode())) * 1000003) ^ (this.name == null ? 0 : this.name.hashCode())) * 1000003) ^ (this.currency == null ? 0 : this.currency.hashCode())) * 1000003) ^ (this.primaryBuy == null ? 0 : this.primaryBuy.hashCode())) * 1000003) ^ (this.primarySell == null ? 0 : this.primarySell.hashCode())) * 1000003) ^ (this.allowBuy == null ? 0 : this.allowBuy.hashCode())) * 1000003) ^ (this.allowSell == null ? 0 : this.allowSell.hashCode())) * 1000003) ^ (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 1000003) ^ (this.updatedAt == null ? 0 : this.updatedAt.hashCode())) * 1000003;
        if (this.verified != null) {
            i = this.verified.hashCode();
        }
        return h ^ i;
    }
}
