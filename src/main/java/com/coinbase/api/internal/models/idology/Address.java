package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.models.idology.AutoValue_Address.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Address {

    public static abstract class Builder {
        public abstract Address build();

        public abstract Builder setCity(String str);

        public abstract Builder setCountry(Country country);

        public abstract Builder setLine1(String str);

        public abstract Builder setLine2(String str);

        public abstract Builder setPostalCode(String str);

        public abstract Builder setState(String str);
    }

    @SerializedName("city")
    public abstract String getCity();

    @SerializedName("country")
    public abstract Country getCountry();

    @SerializedName("line1")
    public abstract String getLine1();

    @SerializedName("line2")
    public abstract String getLine2();

    @SerializedName("postal_code")
    public abstract String getPostalCode();

    @SerializedName("state")
    public abstract String getState();

    public static TypeAdapter<Address> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new Builder();
    }
}
