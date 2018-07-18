package com.coinbase.api.internal.models.config;

import com.coinbase.api.internal.models.config.AutoValue_Message.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Message {

    public static abstract class Builder {
        public abstract Message build();

        public abstract Builder setAction(String str);

        public abstract Builder setDescription(String str);

        public abstract Builder setImage(String str);

        public abstract Builder setLink(String str);

        public abstract Builder setMinVersion(String str);

        public abstract Builder setTitle(String str);

        public abstract Builder setVersion(String str);

        public abstract Builder setVersionRange(VersionRange versionRange);
    }

    @SerializedName("action")
    public abstract String getAction();

    @SerializedName("description")
    public abstract String getDescription();

    @SerializedName("image")
    public abstract String getImage();

    @SerializedName("link")
    public abstract String getLink();

    @SerializedName("min_version")
    public abstract String getMinVersion();

    @SerializedName("title")
    public abstract String getTitle();

    @SerializedName("version")
    public abstract String getVersion();

    @SerializedName("version_range")
    public abstract VersionRange getVersionRange();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Message> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
