package com.coinbase.api.internal.models.availablePaymentMethods;

import com.coinbase.api.internal.models.availablePaymentMethods.AutoValue_Currency.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Currency {

    public static abstract class Builder {
        public abstract Currency build();

        public abstract Builder setCode(String str);

        public abstract Builder setColor(String str);

        public abstract Builder setExponent(Integer num);

        public abstract Builder setName(String str);

        public abstract Builder setType(String str);
    }

    @SerializedName("code")
    public abstract String getCode();

    @SerializedName("color")
    public abstract String getColor();

    @SerializedName("exponent")
    public abstract Integer getExponent();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("type")
    public abstract String getType();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Currency> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
