package com.coinbase.v2.models.transfers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("amount")
    @Expose
    private Amount amount;
    @SerializedName("committed")
    @Expose
    private Boolean committed;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("fee")
    @Expose
    private Amount fee;
    @SerializedName("fee_explanation_url")
    @Expose
    private String feeExplanationUrl;
    @SerializedName("hold_days")
    @Expose
    private Integer holdDays;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("instant")
    @Expose
    private Boolean instant;
    @SerializedName("is_first_buy")
    @Expose
    private Boolean isFirstBuy;
    @SerializedName("payment_method")
    @Expose
    private PaymentMethod paymentMethod;
    @SerializedName("payment_method_fee")
    @Expose
    private Amount paymentMethodFee;
    @SerializedName("payout_at")
    @Expose
    private String payoutAt;
    @SerializedName("requires_completion_step")
    @Expose
    private Boolean requiresCompletionStep;
    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("resource_path")
    @Expose
    private String resourcePath;
    @SerializedName("secure3d_verification")
    @Expose
    private Secure3dVerification secure3dVerification;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("subtotal")
    @Expose
    private Amount subtotal;
    @SerializedName("total")
    @Expose
    private Amount total;
    @SerializedName("transaction")
    @Expose
    private Transaction transaction;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Amount getAmount() {
        return this.amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Amount getTotal() {
        return this.total;
    }

    public void setTotal(Amount total) {
        this.total = total;
    }

    public Amount getSubtotal() {
        return this.subtotal;
    }

    public void setSubtotal(Amount subtotal) {
        this.subtotal = subtotal;
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

    public String getResource() {
        return this.resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getResourcePath() {
        return this.resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    public Boolean getCommitted() {
        return this.committed;
    }

    public void setCommitted(Boolean committed) {
        this.committed = committed;
    }

    public Boolean getInstant() {
        return this.instant;
    }

    public void setInstant(Boolean instant) {
        this.instant = instant;
    }

    public Boolean getIsFirstBuy() {
        return this.isFirstBuy;
    }

    public void setIsFirstBuy(Boolean isFirstBuy) {
        this.isFirstBuy = isFirstBuy;
    }

    public Amount getFee() {
        return this.fee;
    }

    public void setFee(Amount fee) {
        this.fee = fee;
    }

    public String getPayoutAt() {
        return this.payoutAt;
    }

    public void setPayoutAt(String payoutAt) {
        this.payoutAt = payoutAt;
    }

    public Boolean getRequiresCompletionStep() {
        return this.requiresCompletionStep;
    }

    public void setRequiresCompletionStep(Boolean requiresCompletionStep) {
        this.requiresCompletionStep = requiresCompletionStep;
    }

    public Secure3dVerification getSecure3dVerification() {
        return this.secure3dVerification;
    }

    public void setSecure3dVerification(Secure3dVerification secure3dVerification) {
        this.secure3dVerification = secure3dVerification;
    }

    public String getFeeExplanationUrl() {
        return this.feeExplanationUrl;
    }

    public void setFeeExplanationUrl(String feeExplanationUrl) {
        this.feeExplanationUrl = feeExplanationUrl;
    }

    public Amount getPaymentMethodFee() {
        return this.paymentMethodFee;
    }

    public void setPaymentMethodFee(Amount paymentMethodFee) {
        this.paymentMethodFee = paymentMethodFee;
    }

    public Integer getHoldDays() {
        return this.holdDays;
    }

    public void setHoldDays(Integer holdDays) {
        this.holdDays = holdDays;
    }
}
