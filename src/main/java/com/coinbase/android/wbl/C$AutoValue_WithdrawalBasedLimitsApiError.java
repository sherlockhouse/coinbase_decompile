package com.coinbase.android.wbl;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_WithdrawalBasedLimitsApiError extends WithdrawalBasedLimitsApiError {
    private final String dismissText;
    private final String id;
    private final String learnMoreLocation;
    private final String learnMoreText;
    private final String message;
    private final String title;

    static final class Builder extends com.coinbase.android.wbl.WithdrawalBasedLimitsApiError.Builder {
        private String dismissText;
        private String id;
        private String learnMoreLocation;
        private String learnMoreText;
        private String message;
        private String title;

        Builder() {
        }

        Builder(WithdrawalBasedLimitsApiError source) {
            this.id = source.getId();
            this.message = source.getMessage();
            this.title = source.getTitle();
            this.learnMoreText = source.getLearnMoreText();
            this.learnMoreLocation = source.getLearnMoreLocation();
            this.dismissText = source.getDismissText();
        }

        public com.coinbase.android.wbl.WithdrawalBasedLimitsApiError.Builder setId(String id) {
            this.id = id;
            return this;
        }

        public com.coinbase.android.wbl.WithdrawalBasedLimitsApiError.Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public com.coinbase.android.wbl.WithdrawalBasedLimitsApiError.Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public com.coinbase.android.wbl.WithdrawalBasedLimitsApiError.Builder setLearnMoreText(String learnMoreText) {
            this.learnMoreText = learnMoreText;
            return this;
        }

        public com.coinbase.android.wbl.WithdrawalBasedLimitsApiError.Builder setLearnMoreLocation(String learnMoreLocation) {
            this.learnMoreLocation = learnMoreLocation;
            return this;
        }

        public com.coinbase.android.wbl.WithdrawalBasedLimitsApiError.Builder setDismissText(String dismissText) {
            this.dismissText = dismissText;
            return this;
        }

        public WithdrawalBasedLimitsApiError build() {
            return new AutoValue_WithdrawalBasedLimitsApiError(this.id, this.message, this.title, this.learnMoreText, this.learnMoreLocation, this.dismissText);
        }
    }

    C$AutoValue_WithdrawalBasedLimitsApiError(String id, String message, String title, String learnMoreText, String learnMoreLocation, String dismissText) {
        this.id = id;
        this.message = message;
        this.title = title;
        this.learnMoreText = learnMoreText;
        this.learnMoreLocation = learnMoreLocation;
        this.dismissText = dismissText;
    }

    @SerializedName("id")
    public String getId() {
        return this.id;
    }

    @SerializedName("message")
    public String getMessage() {
        return this.message;
    }

    @SerializedName("title")
    public String getTitle() {
        return this.title;
    }

    @SerializedName("learn_more_text")
    public String getLearnMoreText() {
        return this.learnMoreText;
    }

    @SerializedName("learn_more_location")
    public String getLearnMoreLocation() {
        return this.learnMoreLocation;
    }

    @SerializedName("dismiss_text")
    public String getDismissText() {
        return this.dismissText;
    }

    public String toString() {
        return "WithdrawalBasedLimitsApiError{id=" + this.id + ", message=" + this.message + ", title=" + this.title + ", learnMoreText=" + this.learnMoreText + ", learnMoreLocation=" + this.learnMoreLocation + ", dismissText=" + this.dismissText + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof WithdrawalBasedLimitsApiError)) {
            return false;
        }
        WithdrawalBasedLimitsApiError that = (WithdrawalBasedLimitsApiError) o;
        if (this.id != null) {
            if (this.id.equals(that.getId())) {
            }
            return false;
        }
        if (this.message != null) {
            if (this.message.equals(that.getMessage())) {
            }
            return false;
        }
        if (this.title != null) {
            if (this.title.equals(that.getTitle())) {
            }
            return false;
        }
        if (this.learnMoreText != null) {
            if (this.learnMoreText.equals(that.getLearnMoreText())) {
            }
            return false;
        }
        if (this.learnMoreLocation != null) {
            if (this.learnMoreLocation.equals(that.getLearnMoreLocation())) {
            }
            return false;
        }
        if (this.dismissText == null) {
            if (that.getDismissText() == null) {
                return true;
            }
        } else if (this.dismissText.equals(that.getDismissText())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((1 * 1000003) ^ (this.id == null ? 0 : this.id.hashCode())) * 1000003) ^ (this.message == null ? 0 : this.message.hashCode())) * 1000003) ^ (this.title == null ? 0 : this.title.hashCode())) * 1000003) ^ (this.learnMoreText == null ? 0 : this.learnMoreText.hashCode())) * 1000003) ^ (this.learnMoreLocation == null ? 0 : this.learnMoreLocation.hashCode())) * 1000003;
        if (this.dismissText != null) {
            i = this.dismissText.hashCode();
        }
        return h ^ i;
    }
}
