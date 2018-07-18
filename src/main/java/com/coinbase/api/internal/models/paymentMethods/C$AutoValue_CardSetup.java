package com.coinbase.api.internal.models.paymentMethods;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_CardSetup extends CardSetup {
    private final Data data;

    static final class Builder extends com.coinbase.api.internal.models.paymentMethods.CardSetup.Builder {
        private Data data;

        Builder() {
        }

        Builder(CardSetup source) {
            this.data = source.getData();
        }

        public com.coinbase.api.internal.models.paymentMethods.CardSetup.Builder setData(Data data) {
            this.data = data;
            return this;
        }

        public CardSetup build() {
            return new AutoValue_CardSetup(this.data);
        }
    }

    C$AutoValue_CardSetup(Data data) {
        this.data = data;
    }

    @SerializedName("data")
    public Data getData() {
        return this.data;
    }

    public String toString() {
        return "CardSetup{data=" + this.data + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof CardSetup)) {
            return false;
        }
        CardSetup that = (CardSetup) o;
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
