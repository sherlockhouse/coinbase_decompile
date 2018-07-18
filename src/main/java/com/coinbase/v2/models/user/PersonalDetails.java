package com.coinbase.v2.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonalDetails {
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("date_of_birth")
    @Expose
    private DateOfBirth dateOfBirth;
    @SerializedName("legal_name")
    @Expose
    private LegalName legalName;

    public DateOfBirth getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(DateOfBirth dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LegalName getLegalName() {
        return this.legalName;
    }

    public void setLegalName(LegalName legalName) {
        this.legalName = legalName;
    }
}
