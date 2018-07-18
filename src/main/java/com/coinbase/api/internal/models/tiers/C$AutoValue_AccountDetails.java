package com.coinbase.api.internal.models.tiers;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_AccountDetails extends AccountDetails {
    private final LimitsAndFeatures limitsAndFeatures;
    private final String title;
    private final String upgradeButtonText;

    C$AutoValue_AccountDetails(String title, LimitsAndFeatures limitsAndFeatures, String upgradeButtonText) {
        this.title = title;
        this.limitsAndFeatures = limitsAndFeatures;
        this.upgradeButtonText = upgradeButtonText;
    }

    @SerializedName("title")
    public String getTitle() {
        return this.title;
    }

    @SerializedName("limits_and_features")
    public LimitsAndFeatures getLimitsAndFeatures() {
        return this.limitsAndFeatures;
    }

    @SerializedName("upgrade_button_text")
    public String getUpgradeButtonText() {
        return this.upgradeButtonText;
    }

    public String toString() {
        return "AccountDetails{title=" + this.title + ", limitsAndFeatures=" + this.limitsAndFeatures + ", upgradeButtonText=" + this.upgradeButtonText + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AccountDetails)) {
            return false;
        }
        AccountDetails that = (AccountDetails) o;
        if (this.title != null) {
            if (this.title.equals(that.getTitle())) {
            }
            return false;
        }
        if (this.limitsAndFeatures != null) {
            if (this.limitsAndFeatures.equals(that.getLimitsAndFeatures())) {
            }
            return false;
        }
        if (this.upgradeButtonText == null) {
            if (that.getUpgradeButtonText() == null) {
                return true;
            }
        } else if (this.upgradeButtonText.equals(that.getUpgradeButtonText())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.title == null ? 0 : this.title.hashCode())) * 1000003) ^ (this.limitsAndFeatures == null ? 0 : this.limitsAndFeatures.hashCode())) * 1000003;
        if (this.upgradeButtonText != null) {
            i = this.upgradeButtonText.hashCode();
        }
        return h ^ i;
    }
}
