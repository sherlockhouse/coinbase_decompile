package com.coinbase.v2.models.paymentMethods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PickerData {
    @SerializedName("account_name")
    @Expose
    private String accountName;
    @SerializedName("account_number")
    @Expose
    private String accountNumber;
    @SerializedName("account_type")
    @Expose
    private String accountType;
    @SerializedName("card_last4")
    @Expose
    private String cardLast4;
    @SerializedName("card_network")
    @Expose
    private String cardNetwork;
    @SerializedName("card_type")
    @Expose
    private String cardType;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("institution_code")
    @Expose
    private String institutionCode;
    @SerializedName("institution_name")
    @Expose
    private String institutionName;
    @SerializedName("swift")
    @Expose
    private String swift;
    @SerializedName("symbol")
    @Expose
    private String symbol;

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCardLast4() {
        return this.cardLast4;
    }

    public void setCardLast4(String cardLast4) {
        this.cardLast4 = cardLast4;
    }

    public String getCardNetwork() {
        return this.cardNetwork;
    }

    public void setCardNetwork(String cardNetwork) {
        this.cardNetwork = cardNetwork;
    }

    public String getCardType() {
        return this.cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getInstitutionName() {
        return this.institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getInstitutionCode() {
        return this.institutionCode;
    }

    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSwift() {
        return this.swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }
}
