package com.coinbase.android.wbl;

import com.coinbase.android.wbl.AutoValue_WithdrawalBasedLimitsApiError.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class WithdrawalBasedLimitsApiError {

    public static abstract class Builder {
        public abstract WithdrawalBasedLimitsApiError build();

        public abstract Builder setDismissText(String str);

        public abstract Builder setId(String str);

        public abstract Builder setLearnMoreLocation(String str);

        public abstract Builder setLearnMoreText(String str);

        public abstract Builder setMessage(String str);

        public abstract Builder setTitle(String str);
    }

    @SerializedName("dismiss_text")
    public abstract String getDismissText();

    @SerializedName("id")
    public abstract String getId();

    @SerializedName("learn_more_location")
    public abstract String getLearnMoreLocation();

    @SerializedName("learn_more_text")
    public abstract String getLearnMoreText();

    @SerializedName("message")
    public abstract String getMessage();

    @SerializedName("title")
    public abstract String getTitle();

    public static Builder builder() {
        return new Builder();
    }

    public static TypeAdapter<WithdrawalBasedLimitsApiError> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
