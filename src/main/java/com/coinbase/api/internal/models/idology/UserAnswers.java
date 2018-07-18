package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.models.idology.AutoValue_UserAnswers.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class UserAnswers {

    public static abstract class Builder {
        public abstract UserAnswers build();

        public abstract Builder setAnswers(List<Answer> list);
    }

    @SerializedName("answers")
    public abstract List<Answer> getAnswers();

    public static TypeAdapter<UserAnswers> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new Builder().setAnswers(new ArrayList());
    }
}
