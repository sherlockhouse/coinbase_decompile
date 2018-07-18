package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.models.idology.AutoValue_Answer.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Answer {

    public static abstract class Builder {
        public abstract Answer build();

        public abstract Builder setAnswer(String str);

        public abstract Builder setType(String str);
    }

    @SerializedName("answer")
    public abstract String getAnswer();

    @SerializedName("type")
    public abstract String getType();

    public static TypeAdapter<Answer> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new Builder();
    }
}
