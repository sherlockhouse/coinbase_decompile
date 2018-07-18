package com.coinbase.api.internal.models.tiers;

import android.text.TextUtils;
import com.coinbase.api.internal.models.tiers.AutoValue_Requirement.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class Requirement {

    public enum Status {
        INCOMPLETE("incomplete"),
        COMPLETE("complete"),
        PENDING("pending");
        
        private final String mStatus;

        private Status(String status) {
            this.mStatus = status;
        }

        public static Status fromString(String statusStr) {
            for (Status status : values()) {
                if (TextUtils.equals(status.mStatus, statusStr)) {
                    return status;
                }
            }
            return INCOMPLETE;
        }
    }

    @SerializedName("description")
    public abstract String getDescription();

    @SerializedName("identifier")
    public abstract String getIdentifier();

    @SerializedName("status")
    public abstract String getStatus();

    public static TypeAdapter<Requirement> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
