package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.models.idology.AutoValue_Question.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Question {

    public static abstract class Builder {
        public abstract Question build();

        public abstract Builder setAnswers(List<String> list);

        public abstract Builder setPrompt(String str);

        public abstract Builder setType(String str);
    }

    @SerializedName("answers")
    public abstract List<String> getAnswers();

    @SerializedName("prompt")
    public abstract String getPrompt();

    @SerializedName("type")
    public abstract String getType();

    public static TypeAdapter<Question> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new Builder().setAnswers(new ArrayList());
    }
}
