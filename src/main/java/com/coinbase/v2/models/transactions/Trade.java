package com.coinbase.v2.models.transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Trade {
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
    @SerializedName("fees")
    @Expose
    private List<Fee> fees = new ArrayList();
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("instant")
    @Expose
    private Boolean instant;
    @SerializedName("payment_method")
    @Expose
    private PaymentMethod paymentMethod;
    @SerializedName("payout_at")
    @Expose
    private String payoutAt;
    @SerializedName("resource")
    @Expose
    private String resource;
    @SerializedName("resource_path")
    @Expose
    private String resourcePath;
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

    public List<Fee> getFees() {
        return this.fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
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

    public Boolean getCommitted() {
        return this.committed;
    }

    public void setCommitted(Boolean committed) {
        this.committed = committed;
    }

    public String getPayoutAt() {
        return this.payoutAt;
    }

    public void setPayoutAt(String payoutAt) {
        this.payoutAt = payoutAt;
    }

    public Boolean getInstant() {
        return this.instant;
    }

    public void setInstant(Boolean instant) {
        this.instant = instant;
    }

    public Amount getFee() {
        return this.fee;
    }

    public void setFee(Amount fee) {
        this.fee = fee;
    }
}
