package com.coinbase.api.internal.models.phoneNumber;

import com.coinbase.api.internal.models.phoneNumber.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Data {

    public static abstract class Builder {
        public abstract Data build();

        public abstract Builder setCreatedAt(String str);

        public abstract Builder setId(String str);

        public abstract Builder setNumber(String str);

        public abstract Builder setPrimary(Boolean bool);

        public abstract Builder setResource(String str);

        public abstract Builder setResourcePath(String str);

        public abstract Builder setUpdatedAt(String str);

        public abstract Builder setVerified(Boolean bool);
    }

    @SerializedName("created_at")
    public abstract String getCreatedAt();

    @SerializedName("id")
    public abstract String getId();

    @SerializedName("number")
    public abstract String getNumber();

    @SerializedName("primary")
    public abstract Boolean getPrimary();

    @SerializedName("resource")
    public abstract String getResource();

    @SerializedName("resource_path")
    public abstract String getResourcePath();

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
