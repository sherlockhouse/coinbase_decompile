package com.coinbase.api.internal.models.billingAddress;

import com.coinbase.api.internal.models.Country;
import com.coinbase.api.internal.models.billingAddress.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Data {

    public static abstract class Builder {
        public abstract Data build();

        public abstract Builder setCity(String str);

        public abstract Builder setCountry(Country country);

        public abstract Builder setId(String str);

        public abstract Builder setLine1(String str);

        public abstract Builder setLine2(String str);

        public abstract Builder setLine3(String str);

        public abstract Builder setPostalCode(String str);

        public abstract Builder setResource(String str);

        public abstract Builder setResourcePath(String str);

        public abstract Builder setState(String str);
    }

    @SerializedName("city")
    public abstract String getCity();

    @SerializedName("country")
    public abstract Country getCountry();

    @SerializedName("id")
    public abstract String getId();

    @SerializedName("line1")
    public abstract String getLine1();

    @SerializedName("line2")
    public abstract String getLine2();

    @SerializedName("line3")
    public abstract String getLine3();

    @SerializedName("postal_code")
    public abstract String getPostalCode();

    @SerializedName("resource")
    public abstract String getResource();

    @SerializedName("resource_path")
    public abstract String getResourcePath();

    @SerializedName("state")
    public abstract String getState();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
