package com.coinbase.api.internal.models.jumio;

import com.coinbase.api.internal.models.Country;
import com.coinbase.api.internal.models.jumio.JumioProfiles.Status;
import com.coinbase.api.internal.models.jumio.JumioProfiles.Type;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("failure_description")
    @Expose
    private FailureDescription failureDescription;
    @SerializedName("failure_reason")
    @Expose
    private String failureReason;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    private Status status;
    private Type type;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        if (this.type == null) {
            return Type.UNKNOWN;
        }
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFailureReason() {
        return this.failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public FailureDescription getFailureDescription() {
        return this.failureDescription;
    }

    public void setFailureDescription(FailureDescription failureDescription) {
        this.failureDescription = failureDescription;
    }
}
