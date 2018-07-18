package com.coinbase.api.internal.models.dashboard;

import com.google.gson.annotations.SerializedName;

abstract class C$AutoValue_RemainingVerification extends RemainingVerification {
    private final boolean required;
    private final String status;
    private final String step;

    static final class Builder extends com.coinbase.api.internal.models.dashboard.RemainingVerification.Builder {
        private Boolean required;
        private String status;
        private String step;

        Builder() {
        }

        Builder(RemainingVerification source) {
            this.step = source.getStep();
            this.status = source.getStatus();
            this.required = Boolean.valueOf(source.isRequired());
        }

        public com.coinbase.api.internal.models.dashboard.RemainingVerification.Builder setStep(String step) {
            this.step = step;
            return this;
        }

        public com.coinbase.api.internal.models.dashboard.RemainingVerification.Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public com.coinbase.api.internal.models.dashboard.RemainingVerification.Builder setRequired(boolean required) {
            this.required = Boolean.valueOf(required);
            return this;
        }

        public RemainingVerification build() {
            String missing = "";
            if (this.required == null) {
                missing = missing + " required";
            }
            if (missing.isEmpty()) {
                return new AutoValue_RemainingVerification(this.step, this.status, this.required.booleanValue());
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_RemainingVerification(String step, String status, boolean required) {
        this.step = step;
        this.status = status;
        this.required = required;
    }

    @SerializedName("step")
    public String getStep() {
        return this.step;
    }

    @SerializedName("status")
    public String getStatus() {
        return this.status;
    }

    @SerializedName("required")
    public boolean isRequired() {
        return this.required;
    }

    public String toString() {
        return "RemainingVerification{step=" + this.step + ", status=" + this.status + ", required=" + this.required + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof RemainingVerification)) {
            return false;
        }
        RemainingVerification that = (RemainingVerification) o;
        if (this.step != null) {
            if (this.step.equals(that.getStep())) {
            }
            return false;
        }
        if (this.status != null) {
            if (this.status.equals(that.getStatus())) {
            }
            return false;
        }
        if (this.required == that.isRequired()) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((1 * 1000003) ^ (this.step == null ? 0 : this.step.hashCode())) * 1000003;
        if (this.status != null) {
            i = this.status.hashCode();
        }
        return ((h ^ i) * 1000003) ^ (this.required ? 1231 : 1237);
    }
}
