package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.models.idology.AutoValue_Job.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Job {

    public static abstract class Builder {
        public abstract Job build();

        public abstract Builder setEmployer(String str);

        public abstract Builder setTitle(String str);

        public abstract Builder setTitleDesc(String str);
    }

    @SerializedName("employer")
    public abstract String getEmployer();

    @SerializedName("title")
    public abstract String getTitle();

    @SerializedName("title_desc")
    public abstract String getTitleDesc();

    public static TypeAdapter<Job> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new Builder();
    }
}
