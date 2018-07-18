package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.models.idology.AutoValue_Data.GsonTypeAdapter;
import com.coinbase.v2.models.user.DateOfBirth;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Data {

    public static abstract class Builder {
        public abstract Data build();

        public abstract Builder setAddress(Address address);

        public abstract Builder setCreatedAt(String str);

        public abstract Builder setCurrentJob(Job job);

        public abstract Builder setDateOfBirth(DateOfBirth dateOfBirth);

        public abstract Builder setFallbackToRestriction(String str);

        public abstract Builder setFirstName(String str);

        public abstract Builder setFormerJob(Job job);

        public abstract Builder setId(String str);

        public abstract Builder setInformationalOnly(boolean z);

        public abstract Builder setLastName(String str);

        public abstract Builder setMethod(String str);

        public abstract Builder setMobilePhoneNumber(String str);

        public abstract Builder setPrimarySourceOfFunds(String str);

        public abstract Builder setQuestions(List<Question> list);

        public abstract Builder setSsnLast4(String str);

        public abstract Builder setStatus(Status status);

        public abstract Builder setUpdatedAt(String str);

        public abstract Builder setUsesCoinbaseFor(List<String> list);
    }

    public enum Status {
        SUCCESS,
        FAILED,
        HAS_QUESTIONS,
        UNKNOWN;

        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    @SerializedName("address")
    public abstract Address getAddress();

    @SerializedName("created_at")
    public abstract String getCreatedAt();

    @SerializedName("current_job")
    public abstract Job getCurrentJob();

    @SerializedName("date_of_birth")
    public abstract DateOfBirth getDateOfBirth();

    @SerializedName("fallback_to_restriction")
    public abstract String getFallbackToRestriction();

    @SerializedName("first_name")
    public abstract String getFirstName();

    @SerializedName("former_job")
    public abstract Job getFormerJob();

    @SerializedName("id")
    public abstract String getId();

    @SerializedName("informational_only")
    public abstract boolean getInformationalOnly();

    @SerializedName("last_name")
    public abstract String getLastName();

    @SerializedName("method")
    public abstract String getMethod();

    @SerializedName("mobile_phone_number")
    public abstract String getMobilePhoneNumber();

    @SerializedName("primary_source_of_funds")
    public abstract String getPrimarySourceOfFunds();

    @SerializedName("questions")
    public abstract List<Question> getQuestions();

    @SerializedName("ssn_last4")
    public abstract String getSsnLast4();

    @SerializedName("status")
    public abstract Status getStatus();

    @SerializedName("updated_at")
    public abstract String getUpdatedAt();

    @SerializedName("uses_coinbase_for")
    public abstract List<String> getUsesCoinbaseFor();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new Builder().setQuestions(new ArrayList()).setUsesCoinbaseFor(new ArrayList()).setStatus(Status.UNKNOWN).setInformationalOnly(false);
    }

    public static Builder builder(Data original) {
        return new Builder().setId(original.getId()).setCreatedAt(original.getCreatedAt()).setUpdatedAt(original.getUpdatedAt()).setStatus(original.getStatus()).setMethod(original.getMethod()).setInformationalOnly(original.getInformationalOnly()).setFirstName(original.getFirstName()).setLastName(original.getLastName()).setAddress(original.getAddress()).setDateOfBirth(original.getDateOfBirth()).setMobilePhoneNumber(original.getMobilePhoneNumber()).setUsesCoinbaseFor(original.getUsesCoinbaseFor()).setPrimarySourceOfFunds(original.getPrimarySourceOfFunds()).setCurrentJob(original.getCurrentJob()).setFormerJob(original.getFormerJob()).setSsnLast4(original.getSsnLast4()).setQuestions(original.getQuestions()).setFallbackToRestriction(original.getFallbackToRestriction());
    }
}
