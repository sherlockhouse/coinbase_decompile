package com.coinbase.api.internal.models.auth;

import com.coinbase.api.internal.models.auth.AutoValue_Error.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Error {

    public static abstract class Builder {
        public abstract Error build();

        public abstract Builder setError(String str);

        public abstract Builder setSuccess(Boolean bool);

        public abstract Builder set_2faAuthentication(String str);
    }

    @SerializedName("error")
    public abstract String getError();

    @SerializedName("success")
    public abstract Boolean getSuccess();

    @SerializedName("2fa_authentication")
    public abstract String get_2faAuthentication();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<Error> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
