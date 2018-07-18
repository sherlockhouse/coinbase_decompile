package com.coinbase.v2.models.paymentMethods;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NextRequirement {
    @SerializedName("amount_remaining")
    @Expose
    private Amount amountRemaining;
    @SerializedName("completion_time")
    @Expose
    private String completionTime;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("volume")
    @Expose
    private Amount volume;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Amount getVolume() {
        return this.volume;
    }

    public void setVolume(Amount volume) {
        this.volume = volume;
    }

    public String getCompletionTime() {
        return this.completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }

    public Amount getAmountRemaining() {
        return this.amountRemaining;
    }

    public void setAmountRemaining(Amount amountRemaining) {
        this.amountRemaining = amountRemaining;
    }
}
