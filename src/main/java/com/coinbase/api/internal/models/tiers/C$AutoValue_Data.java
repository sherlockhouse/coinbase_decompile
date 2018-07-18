package com.coinbase.api.internal.models.tiers;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_Data extends Data {
    private final AccountDetails accountDetails;
    private final AccountSetup accountSetup;
    private final String title;
    private final VerificationLevels verificationLevels;

    C$AutoValue_Data(String title, AccountSetup accountSetup, VerificationLevels verificationLevels, AccountDetails accountDetails) {
        this.title = title;
        this.accountSetup = accountSetup;
        this.verificationLevels = verificationLevels;
        this.accountDetails = accountDetails;
    }

    @SerializedName("title")
    public String getTitle() {
        return this.title;
    }

    @SerializedName("account_setup")
    public AccountSetup getAccountSetup() {
        return this.accountSetup;
    }

    @SerializedName("verification_levels")
    public VerificationLevels getVerificationLevels() {
        return this.verificationLevels;
    }

    @SerializedName("account_details")
    public AccountDetails getAccountDetails() {
        return this.accountDetails;
    }

    public String toString() {
        return "Data{title=" + this.title + ", accountSetup=" + this.accountSetup + ", verificationLevels=" + this.verificationLevels + ", accountDetails=" + this.accountDetails + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Data)) {
            return false;
        }
        Data that = (Data) o;
        if (this.title != null) {
            if (this.title.equals(that.getTitle())) {
            }
            return false;
        }
        if (this.accountSetup != null) {
            if (this.accountSetup.equals(that.getAccountSetup())) {
            }
            return false;
        }
        if (this.verificationLevels != null) {
            if (this.verificationLevels.equals(that.getVerificationLevels())) {
            }
            return false;
        }
        if (this.accountDetails == null) {
            if (that.getAccountDetails() == null) {
                return true;
            }
        } else if (this.accountDetails.equals(that.getAccountDetails())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((1 * 1000003) ^ (this.title == null ? 0 : this.title.hashCode())) * 1000003) ^ (this.accountSetup == null ? 0 : this.accountSetup.hashCode())) * 1000003) ^ (this.verificationLevels == null ? 0 : this.verificationLevels.hashCode())) * 1000003;
        if (this.accountDetails != null) {
            i = this.accountDetails.hashCode();
        }
        return h ^ i;
    }
}
