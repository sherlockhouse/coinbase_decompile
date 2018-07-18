package com.coinbase.api.internal.models.availablePaymentMethods;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_BuyingPower extends BuyingPower {
    private final String text;
    private final String type;

    static final class Builder extends com.coinbase.api.internal.models.availablePaymentMethods.BuyingPower.Builder {
        private String text;
        private String type;

        Builder() {
        }

        Builder(BuyingPower source) {
            this.type = source.getType();
            this.text = source.getText();
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.BuyingPower.Builder setType(String type) {
            this.type = type;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.BuyingPower.Builder setText(String text) {
            this.text = text;
            return this;
        }

        public BuyingPower build() {
            return new AutoValue_BuyingPower(this.type, this.text);
        }
    }

    C$AutoValue_BuyingPower(String type, String text) {
        this.type = type;
        this.text = text;
    }

    @SerializedName("type")
    public String getType() {
        return this.type;
    }

    @SerializedName("text")
    public String getText() {
        return this.text;
    }

    public String toString() {
        return "BuyingPower{type=" + this.type + ", text=" + this.text + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BuyingPower)) {
            return false;
        }
        BuyingPower that = (BuyingPower) o;
        if (this.type != null) {
            if (this.type.equals(that.getType())) {
            }
            return false;
        }
        if (this.text == null) {
            if (that.getText() == null) {
                return true;
            }
        } else if (this.text.equals(that.getText())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.type == null ? 0 : this.type.hashCode())) * 1000003;
        if (this.text != null) {
            i = this.text.hashCode();
        }
        return h ^ i;
    }
}
