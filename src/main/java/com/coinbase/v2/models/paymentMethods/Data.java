package com.coinbase.v2.models.paymentMethods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Data {
    @SerializedName("allow_buy")
    @Expose
    private Boolean allowBuy;
    @SerializedName("allow_deposit")
    @Expose
    private Boolean allowDeposit;
    @SerializedName("allow_sell")
    @Expose
    private Boolean allowSell;
    @SerializedName("allow_withdraw")
    @Expose
    private Boolean allowWithdraw;
    private CDVStatus cdvStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("fiat_account")
    @Expose
    private FiatAccount fiatAccount;
    @SerializedName("icon_url")
    @Expose
    private String iconUrl;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("limits")
    @Expose
    private Limits limits;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("picker_data")
    @Expose
    private PickerData pickerData;
    @SerializedName("primary_buy")
    @Expose
    private Boolean primaryBuy;
    @SerializedName("primary_sell")
    @Expose
    private Boolean primarySell;
    @SerializedName("recurring_options")
    @Expose
    private List<Object> recurringOptions = new ArrayList();
    private Type type;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("verification_method")
    @Expose
    private VerificationMethod verificationMethod;
    @SerializedName("verified")
    @Expose
    private Boolean verified;

    public enum CDVStatus {
        READY,
        IN_PROGRESS,
        UNKNOWN;

        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public enum Type {
        ACH_BANK_ACCOUNT,
        CREDIT_CARD,
        DEBIT_CARD,
        SECURE_3DS,
        WORLDPAY_CARD,
        SEPA_BANK_ACCOUNT,
        FIAT_ACCOUNT,
        IDEAL_BANK,
        XFERS,
        BANK_WIRE,
        PAYPAL_ACCOUNT,
        UNKNOWN;

        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public enum VerificationMethod {
        CDV,
        ACH_SETUP_SESSION,
        UNKNOWN;

        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getIconUrl() {
        return this.iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getPrimaryBuy() {
        return this.primaryBuy;
    }

    public void setPrimaryBuy(Boolean primaryBuy) {
        this.primaryBuy = primaryBuy;
    }

    public Boolean getPrimarySell() {
        return this.primarySell;
    }

    public void setPrimarySell(Boolean primarySell) {
        this.primarySell = primarySell;
    }

    public Boolean getAllowBuy() {
        return this.allowBuy;
    }

    public void setAllowBuy(Boolean allowBuy) {
        this.allowBuy = allowBuy;
    }

    public Boolean getAllowSell() {
        return this.allowSell;
    }

    public void setAllowSell(Boolean allowSell) {
        this.allowSell = allowSell;
    }

    public Boolean getAllowDeposit() {
        return this.allowDeposit;
    }

    public void setAllowDeposit(Boolean allowDeposit) {
        this.allowDeposit = allowDeposit;
    }

    public Boolean getAllowWithdraw() {
        return this.allowWithdraw;
    }

    public void setAllowWithdraw(Boolean allowWithdraw) {
        this.allowWithdraw = allowWithdraw;
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

    public FiatAccount getFiatAccount() {
        return this.fiatAccount;
    }

    public void setFiatAccount(FiatAccount fiatAccount) {
        this.fiatAccount = fiatAccount;
    }

    public Limits getLimits() {
        return this.limits;
    }

    public void setLimits(Limits limits) {
        this.limits = limits;
    }

    public PickerData getPickerData() {
        return this.pickerData;
    }

    public void setPickerData(PickerData pickerData) {
        this.pickerData = pickerData;
    }

    public Boolean getVerified() {
        return this.verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public List<Object> getRecurringOptions() {
        return this.recurringOptions;
    }

    public void setRecurringOptions(List<Object> recurringOptions) {
        this.recurringOptions = recurringOptions;
    }

    public VerificationMethod getVerificationMethod() {
        return this.verificationMethod;
    }

    public void setVerificationMethod(VerificationMethod verificationMethod) {
        this.verificationMethod = verificationMethod;
    }

    public CDVStatus getCdvStatus() {
        return this.cdvStatus;
    }

    public void setCdvStatus(CDVStatus cdvStatus) {
        this.cdvStatus = cdvStatus;
    }
}
