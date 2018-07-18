package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.models.idology.Data.Status;
import com.coinbase.v2.models.user.DateOfBirth;
import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Data extends Data {
    private final Address address;
    private final String createdAt;
    private final Job currentJob;
    private final DateOfBirth dateOfBirth;
    private final String fallbackToRestriction;
    private final String firstName;
    private final Job formerJob;
    private final String id;
    private final boolean informationalOnly;
    private final String lastName;
    private final String method;
    private final String mobilePhoneNumber;
    private final String primarySourceOfFunds;
    private final List<Question> questions;
    private final String ssnLast4;
    private final Status status;
    private final String updatedAt;
    private final List<String> usesCoinbaseFor;

    static final class Builder extends com.coinbase.api.internal.models.idology.Data.Builder {
        private Address address;
        private String createdAt;
        private Job currentJob;
        private DateOfBirth dateOfBirth;
        private String fallbackToRestriction;
        private String firstName;
        private Job formerJob;
        private String id;
        private Boolean informationalOnly;
        private String lastName;
        private String method;
        private String mobilePhoneNumber;
        private String primarySourceOfFunds;
        private List<Question> questions;
        private String ssnLast4;
        private Status status;
        private String updatedAt;
        private List<String> usesCoinbaseFor;

        Builder() {
        }

        Builder(Data source) {
            this.id = source.getId();
            this.createdAt = source.getCreatedAt();
            this.updatedAt = source.getUpdatedAt();
            this.status = source.getStatus();
            this.method = source.getMethod();
            this.informationalOnly = Boolean.valueOf(source.getInformationalOnly());
            this.firstName = source.getFirstName();
            this.lastName = source.getLastName();
            this.address = source.getAddress();
            this.dateOfBirth = source.getDateOfBirth();
            this.mobilePhoneNumber = source.getMobilePhoneNumber();
            this.usesCoinbaseFor = source.getUsesCoinbaseFor();
            this.primarySourceOfFunds = source.getPrimarySourceOfFunds();
            this.currentJob = source.getCurrentJob();
            this.formerJob = source.getFormerJob();
            this.ssnLast4 = source.getSsnLast4();
            this.questions = source.getQuestions();
            this.fallbackToRestriction = source.getFallbackToRestriction();
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setStatus(Status status) {
            this.status = status;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setMethod(String method) {
            this.method = method;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setInformationalOnly(boolean informationalOnly) {
            this.informationalOnly = Boolean.valueOf(informationalOnly);
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setDateOfBirth(DateOfBirth dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setMobilePhoneNumber(String mobilePhoneNumber) {
            this.mobilePhoneNumber = mobilePhoneNumber;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setUsesCoinbaseFor(List<String> usesCoinbaseFor) {
            this.usesCoinbaseFor = usesCoinbaseFor;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setPrimarySourceOfFunds(String primarySourceOfFunds) {
            this.primarySourceOfFunds = primarySourceOfFunds;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setCurrentJob(Job currentJob) {
            this.currentJob = currentJob;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setFormerJob(Job formerJob) {
            this.formerJob = formerJob;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setSsnLast4(String ssnLast4) {
            this.ssnLast4 = ssnLast4;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setQuestions(List<Question> questions) {
            this.questions = questions;
            return this;
        }

        public com.coinbase.api.internal.models.idology.Data.Builder setFallbackToRestriction(String fallbackToRestriction) {
            this.fallbackToRestriction = fallbackToRestriction;
            return this;
        }

        public Data build() {
            String missing = "";
            if (this.informationalOnly == null) {
                missing = missing + " informationalOnly";
            }
            if (missing.isEmpty()) {
                return new AutoValue_Data(this.id, this.createdAt, this.updatedAt, this.status, this.method, this.informationalOnly.booleanValue(), this.firstName, this.lastName, this.address, this.dateOfBirth, this.mobilePhoneNumber, this.usesCoinbaseFor, this.primarySourceOfFunds, this.currentJob, this.formerJob, this.ssnLast4, this.questions, this.fallbackToRestriction);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_Data(String id, String createdAt, String updatedAt, Status status, String method, boolean informationalOnly, String firstName, String lastName, Address address, DateOfBirth dateOfBirth, String mobilePhoneNumber, List<String> usesCoinbaseFor, String primarySourceOfFunds, Job currentJob, Job formerJob, String ssnLast4, List<Question> questions, String fallbackToRestriction) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.status = status;
        this.method = method;
        this.informationalOnly = informationalOnly;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.usesCoinbaseFor = usesCoinbaseFor;
        this.primarySourceOfFunds = primarySourceOfFunds;
        this.currentJob = currentJob;
        this.formerJob = formerJob;
        this.ssnLast4 = ssnLast4;
        this.questions = questions;
        this.fallbackToRestriction = fallbackToRestriction;
    }

    @SerializedName("id")
    public String getId() {
        return this.id;
    }

    @SerializedName("created_at")
    public String getCreatedAt() {
        return this.createdAt;
    }

    @SerializedName("updated_at")
    public String getUpdatedAt() {
        return this.updatedAt;
    }

    @SerializedName("status")
    public Status getStatus() {
        return this.status;
    }

    @SerializedName("method")
    public String getMethod() {
        return this.method;
    }

    @SerializedName("informational_only")
    public boolean getInformationalOnly() {
        return this.informationalOnly;
    }

    @SerializedName("first_name")
    public String getFirstName() {
        return this.firstName;
    }

    @SerializedName("last_name")
    public String getLastName() {
        return this.lastName;
    }

    @SerializedName("address")
    public Address getAddress() {
        return this.address;
    }

    @SerializedName("date_of_birth")
    public DateOfBirth getDateOfBirth() {
        return this.dateOfBirth;
    }

    @SerializedName("mobile_phone_number")
    public String getMobilePhoneNumber() {
        return this.mobilePhoneNumber;
    }

    @SerializedName("uses_coinbase_for")
    public List<String> getUsesCoinbaseFor() {
        return this.usesCoinbaseFor;
    }

    @SerializedName("primary_source_of_funds")
    public String getPrimarySourceOfFunds() {
        return this.primarySourceOfFunds;
    }

    @SerializedName("current_job")
    public Job getCurrentJob() {
        return this.currentJob;
    }

    @SerializedName("former_job")
    public Job getFormerJob() {
        return this.formerJob;
    }

    @SerializedName("ssn_last4")
    public String getSsnLast4() {
        return this.ssnLast4;
    }

    @SerializedName("questions")
    public List<Question> getQuestions() {
        return this.questions;
    }

    @SerializedName("fallback_to_restriction")
    public String getFallbackToRestriction() {
        return this.fallbackToRestriction;
    }

    public String toString() {
        return "Data{id=" + this.id + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ", status=" + this.status + ", method=" + this.method + ", informationalOnly=" + this.informationalOnly + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", address=" + this.address + ", dateOfBirth=" + this.dateOfBirth + ", mobilePhoneNumber=" + this.mobilePhoneNumber + ", usesCoinbaseFor=" + this.usesCoinbaseFor + ", primarySourceOfFunds=" + this.primarySourceOfFunds + ", currentJob=" + this.currentJob + ", formerJob=" + this.formerJob + ", ssnLast4=" + this.ssnLast4 + ", questions=" + this.questions + ", fallbackToRestriction=" + this.fallbackToRestriction + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Data)) {
            return false;
        }
        Data that = (Data) o;
        if (this.id != null) {
            if (this.id.equals(that.getId())) {
            }
            return false;
        }
        if (this.createdAt != null) {
            if (this.createdAt.equals(that.getCreatedAt())) {
            }
            return false;
        }
        if (this.updatedAt != null) {
            if (this.updatedAt.equals(that.getUpdatedAt())) {
            }
            return false;
        }
        if (this.status != null) {
            if (this.status.equals(that.getStatus())) {
            }
            return false;
        }
        if (this.method != null) {
            if (this.method.equals(that.getMethod())) {
            }
            return false;
        }
        if (this.informationalOnly == that.getInformationalOnly()) {
            if (this.firstName != null) {
                if (this.firstName.equals(that.getFirstName())) {
                }
            }
            if (this.lastName != null) {
                if (this.lastName.equals(that.getLastName())) {
                }
            }
            if (this.address != null) {
                if (this.address.equals(that.getAddress())) {
                }
            }
            if (this.dateOfBirth != null) {
                if (this.dateOfBirth.equals(that.getDateOfBirth())) {
                }
            }
            if (this.mobilePhoneNumber != null) {
                if (this.mobilePhoneNumber.equals(that.getMobilePhoneNumber())) {
                }
            }
            if (this.usesCoinbaseFor != null) {
                if (this.usesCoinbaseFor.equals(that.getUsesCoinbaseFor())) {
                }
            }
            if (this.primarySourceOfFunds != null) {
                if (this.primarySourceOfFunds.equals(that.getPrimarySourceOfFunds())) {
                }
            }
            if (this.currentJob != null) {
                if (this.currentJob.equals(that.getCurrentJob())) {
                }
            }
            if (this.formerJob != null) {
                if (this.formerJob.equals(that.getFormerJob())) {
                }
            }
            if (this.ssnLast4 != null) {
                if (this.ssnLast4.equals(that.getSsnLast4())) {
                }
            }
            if (this.questions != null) {
                if (this.questions.equals(that.getQuestions())) {
                }
            }
            if (this.fallbackToRestriction == null) {
                if (that.getFallbackToRestriction() == null) {
                    return true;
                }
            } else if (this.fallbackToRestriction.equals(that.getFallbackToRestriction())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((((((((((((((((((((1 * 1000003) ^ (this.id == null ? 0 : this.id.hashCode())) * 1000003) ^ (this.createdAt == null ? 0 : this.createdAt.hashCode())) * 1000003) ^ (this.updatedAt == null ? 0 : this.updatedAt.hashCode())) * 1000003) ^ (this.status == null ? 0 : this.status.hashCode())) * 1000003) ^ (this.method == null ? 0 : this.method.hashCode())) * 1000003) ^ (this.informationalOnly ? 1231 : 1237)) * 1000003) ^ (this.firstName == null ? 0 : this.firstName.hashCode())) * 1000003) ^ (this.lastName == null ? 0 : this.lastName.hashCode())) * 1000003) ^ (this.address == null ? 0 : this.address.hashCode())) * 1000003) ^ (this.dateOfBirth == null ? 0 : this.dateOfBirth.hashCode())) * 1000003) ^ (this.mobilePhoneNumber == null ? 0 : this.mobilePhoneNumber.hashCode())) * 1000003) ^ (this.usesCoinbaseFor == null ? 0 : this.usesCoinbaseFor.hashCode())) * 1000003) ^ (this.primarySourceOfFunds == null ? 0 : this.primarySourceOfFunds.hashCode())) * 1000003) ^ (this.currentJob == null ? 0 : this.currentJob.hashCode())) * 1000003) ^ (this.formerJob == null ? 0 : this.formerJob.hashCode())) * 1000003) ^ (this.ssnLast4 == null ? 0 : this.ssnLast4.hashCode())) * 1000003) ^ (this.questions == null ? 0 : this.questions.hashCode())) * 1000003;
        if (this.fallbackToRestriction != null) {
            i = this.fallbackToRestriction.hashCode();
        }
        return h ^ i;
    }
}
