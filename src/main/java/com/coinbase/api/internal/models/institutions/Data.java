package com.coinbase.api.internal.models.institutions;

import com.coinbase.api.internal.models.institutions.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Data {

    public static abstract class Builder {
        public abstract Data build();

        public abstract Builder setCredentials(Credentials credentials);

        public abstract Builder setHasMfa(Boolean bool);

        public abstract Builder setImage(Image image);

        public abstract Builder setMfa(List<Object> list);

        public abstract Builder setName(String str);

        public abstract Builder setType(String str);
    }

    @SerializedName("credentials")
    public abstract Credentials getCredentials();

    @SerializedName("has_mfa")
    public abstract Boolean getHasMfa();

    @SerializedName("image")
    public abstract Image getImage();

    @SerializedName("mfa")
    public abstract List<Object> getMfa();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("type")
    public abstract String getType();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new Builder().setMfa(new ArrayList());
    }
}
