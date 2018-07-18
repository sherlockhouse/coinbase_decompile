package com.coinbase.v2.models.paymentMethods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Limit {
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("next_requirement")
    @Expose
    private NextRequirement nextRequirement;
    @SerializedName("period_in_days")
    @Expose
    private Float periodInDays;
    @SerializedName("remaining")
    @Expose
    private Amount remaining;
    @SerializedName("total")
    @Expose
    private Amount total;

    public Float getPeriodInDays() {
        return this.periodInDays;
    }

    public void setPeriodInDays(Float periodInDays) {
        this.periodInDays = periodInDays;
    }

    public Amount getTotal() {
        return this.total;
    }

    public void setTotal(Amount total) {
        this.total = total;
    }

    public Amount getRemaining() {
        return this.remaining;
    }

    public void setRemaining(Amount remaining) {
        this.remaining = remaining;
    }

    public NextRequirement getNextRequirement() {
        return this.nextRequirement;
    }

    public void setNextRequirement(NextRequirement nextRequirement) {
        this.nextRequirement = nextRequirement;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
