package com.coinbase.api.internal.models.dashboard;

import com.coinbase.api.internal.models.dashboard.AutoValue_RemainingVerification.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class RemainingVerification {

    public static abstract class Builder {
        public abstract RemainingVerification build();

        public abstract Builder setRequired(boolean z);

        public abstract Builder setStatus(String str);

        public abstract Builder setStep(String str);
    }

    @SerializedName("status")
    public abstract String getStatus();

    @SerializedName("step")
    public abstract String getStep();

    @SerializedName("required")
    public abstract boolean isRequired();

    public static Builder builder() {
        return new Builder().setRequired(false);
    }

    public static TypeAdapter<RemainingVerification> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
