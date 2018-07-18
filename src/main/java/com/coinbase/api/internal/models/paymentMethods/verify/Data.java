package com.coinbase.api.internal.models.paymentMethods.verify;

import com.coinbase.api.internal.models.paymentMethods.verify.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Data {

    public static abstract class Builder {
        public abstract Data build();

        public abstract Builder setAllowBuy(Boolean bool);

        public abstract Builder setAllowSell(Boolean bool);

        public abstract Builder setCreatedAt(String str);

        public abstract Builder setCurrency(String str);

        public abstract Builder setId(String str);

        public abstract Builder setName(String str);

        public abstract Builder setPrimaryBuy(Boolean bool);

        public abstract Builder setPrimarySell(Boolean bool);

        public abstract Builder setType(String str);

        public abstract Builder setUpdatedAt(String str);

        public abstract Builder setVerified(Boolean bool);
    }

    @SerializedName("allow_buy")
    public abstract Boolean getAllowBuy();

    @SerializedName("allow_sell")
    public abstract Boolean getAllowSell();

    @SerializedName("created_at")
    public abstract String getCreatedAt();

    @SerializedName("currency")
    public abstract String getCurrency();

    @SerializedName("id")
    public abstract String getId();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("primary_buy")
    public abstract Boolean getPrimaryBuy();

    @SerializedName("primary_sell")
    public abstract Boolean getPrimarySell();

    @SerializedName("type")
    public abstract String getType();

    @SerializedName("updated_at")
    public abstract String getUpdatedAt();

    @SerializedName("verified")
    public abstract Boolean getVerified();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
