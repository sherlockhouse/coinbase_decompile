package com.coinbase.v2.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnboardingItem {
    @SerializedName("required")
    @Expose
    private Boolean required;
    @SerializedName("step")
    @Expose
    private String step;

    public String getStep() {
        return this.step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public Boolean getRequired() {
        return this.required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }
}
