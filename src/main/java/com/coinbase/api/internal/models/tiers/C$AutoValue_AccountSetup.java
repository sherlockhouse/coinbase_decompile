package com.coinbase.api.internal.models.tiers;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_AccountSetup extends AccountSetup {
    private final String buttonText;
    private final String description;
    private final String title;

    C$AutoValue_AccountSetup(String title, String description, String buttonText) {
        this.title = title;
        this.description = description;
        this.buttonText = buttonText;
    }

    @SerializedName("title")
    public String getTitle() {
        return this.title;
    }

    @SerializedName("description")
    public String getDescription() {
        return this.description;
    }

    @SerializedName("button_text")
    public String getButtonText() {
        return this.buttonText;
    }

    public String toString() {
        return "AccountSetup{title=" + this.title + ", description=" + this.description + ", buttonText=" + this.buttonText + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AccountSetup)) {
            return false;
        }
        AccountSetup that = (AccountSetup) o;
        if (this.title != null) {
            if (this.title.equals(that.getTitle())) {
            }
            return false;
        }
        if (this.description != null) {
            if (this.description.equals(that.getDescription())) {
            }
            return false;
        }
        if (this.buttonText == null) {
            if (that.getButtonText() == null) {
                return true;
            }
        } else if (this.buttonText.equals(that.getButtonText())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((1 * 1000003) ^ (this.title == null ? 0 : this.title.hashCode())) * 1000003) ^ (this.description == null ? 0 : this.description.hashCode())) * 1000003;
        if (this.buttonText != null) {
            i = this.buttonText.hashCode();
        }
        return h ^ i;
    }
}
