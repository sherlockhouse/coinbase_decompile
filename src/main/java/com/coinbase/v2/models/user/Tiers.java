package com.coinbase.v2.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tiers {
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("completed_description")
    @Expose
    private String completedDescription;
    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("upgrade_button_text")
    @Expose
    private String upgradeButtonText;

    public String getUpgradeButtonText() {
        return this.upgradeButtonText;
    }

    public void setUpgradeButtonText(String upgradeButtonText) {
        this.upgradeButtonText = upgradeButtonText;
    }

    public String getHeader() {
        return this.header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCompletedDescription() {
        return this.completedDescription;
    }

    public void setCompletedDescription(String completedDescription) {
        this.completedDescription = completedDescription;
    }
}
