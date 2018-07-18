package com.coinbase.api.internal.models.availablePaymentMethods;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_AvailablePaymentMethod extends AvailablePaymentMethod {
    private final String additionalFees;
    private final String buyTime;
    private final BuyingPower buyingPower;
    private final Currency currency;
    private final String description;
    private final String feeDescription;
    private final String name;
    private final Boolean recommended;
    private final String relativeLimits;
    private final List<String> requirements;
    private final List<String> supports;
    private final String type;
    private final List<String> verifyRequirements;

    static final class Builder extends com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder {
        private String additionalFees;
        private String buyTime;
        private BuyingPower buyingPower;
        private Currency currency;
        private String description;
        private String feeDescription;
        private String name;
        private Boolean recommended;
        private String relativeLimits;
        private List<String> requirements;
        private List<String> supports;
        private String type;
        private List<String> verifyRequirements;

        Builder() {
        }

        Builder(AvailablePaymentMethod source) {
            this.type = source.getType();
            this.requirements = source.getRequirements();
            this.verifyRequirements = source.getVerifyRequirements();
            this.name = source.getName();
            this.description = source.getDescription();
            this.buyTime = source.getBuyTime();
            this.relativeLimits = source.getRelativeLimits();
            this.supports = source.getSupports();
            this.feeDescription = source.getFeeDescription();
            this.additionalFees = source.getAdditionalFees();
            this.recommended = source.getRecommended();
            this.buyingPower = source.getBuyingPower();
            this.currency = source.getCurrency();
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setType(String type) {
            this.type = type;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setRequirements(List<String> requirements) {
            this.requirements = requirements;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setVerifyRequirements(List<String> verifyRequirements) {
            this.verifyRequirements = verifyRequirements;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setName(String name) {
            this.name = name;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setBuyTime(String buyTime) {
            this.buyTime = buyTime;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setRelativeLimits(String relativeLimits) {
            this.relativeLimits = relativeLimits;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setSupports(List<String> supports) {
            this.supports = supports;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setFeeDescription(String feeDescription) {
            this.feeDescription = feeDescription;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setAdditionalFees(String additionalFees) {
            this.additionalFees = additionalFees;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setRecommended(Boolean recommended) {
            this.recommended = recommended;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setBuyingPower(BuyingPower buyingPower) {
            this.buyingPower = buyingPower;
            return this;
        }

        public com.coinbase.api.internal.models.availablePaymentMethods.AvailablePaymentMethod.Builder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public AvailablePaymentMethod build() {
            return new AutoValue_AvailablePaymentMethod(this.type, this.requirements, this.verifyRequirements, this.name, this.description, this.buyTime, this.relativeLimits, this.supports, this.feeDescription, this.additionalFees, this.recommended, this.buyingPower, this.currency);
        }
    }

    C$AutoValue_AvailablePaymentMethod(String type, List<String> requirements, List<String> verifyRequirements, String name, String description, String buyTime, String relativeLimits, List<String> supports, String feeDescription, String additionalFees, Boolean recommended, BuyingPower buyingPower, Currency currency) {
        this.type = type;
        this.requirements = requirements;
        this.verifyRequirements = verifyRequirements;
        this.name = name;
        this.description = description;
        this.buyTime = buyTime;
        this.relativeLimits = relativeLimits;
        this.supports = supports;
        this.feeDescription = feeDescription;
        this.additionalFees = additionalFees;
        this.recommended = recommended;
        this.buyingPower = buyingPower;
        this.currency = currency;
    }

    @SerializedName("type")
    public String getType() {
        return this.type;
    }

    @SerializedName("requirements")
    public List<String> getRequirements() {
        return this.requirements;
    }

    @SerializedName("verify_requirements")
    public List<String> getVerifyRequirements() {
        return this.verifyRequirements;
    }

    @SerializedName("name")
    public String getName() {
        return this.name;
    }

    @SerializedName("description")
    public String getDescription() {
        return this.description;
    }

    @SerializedName("buy_time")
    public String getBuyTime() {
        return this.buyTime;
    }

    @SerializedName("relative_limits")
    public String getRelativeLimits() {
        return this.relativeLimits;
    }

    @SerializedName("supports")
    public List<String> getSupports() {
        return this.supports;
    }

    @SerializedName("fee_description")
    public String getFeeDescription() {
        return this.feeDescription;
    }

    @SerializedName("additional_fees")
    public String getAdditionalFees() {
        return this.additionalFees;
    }

    @SerializedName("recommended")
    public Boolean getRecommended() {
        return this.recommended;
    }

    @SerializedName("buying_power")
    public BuyingPower getBuyingPower() {
        return this.buyingPower;
    }

    @SerializedName("currency")
    public Currency getCurrency() {
        return this.currency;
    }

    public String toString() {
        return "AvailablePaymentMethod{type=" + this.type + ", requirements=" + this.requirements + ", verifyRequirements=" + this.verifyRequirements + ", name=" + this.name + ", description=" + this.description + ", buyTime=" + this.buyTime + ", relativeLimits=" + this.relativeLimits + ", supports=" + this.supports + ", feeDescription=" + this.feeDescription + ", additionalFees=" + this.additionalFees + ", recommended=" + this.recommended + ", buyingPower=" + this.buyingPower + ", currency=" + this.currency + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AvailablePaymentMethod)) {
            return false;
        }
        AvailablePaymentMethod that = (AvailablePaymentMethod) o;
        if (this.type != null) {
            if (this.type.equals(that.getType())) {
            }
            return false;
        }
        if (this.requirements != null) {
            if (this.requirements.equals(that.getRequirements())) {
            }
            return false;
        }
        if (this.verifyRequirements != null) {
            if (this.verifyRequirements.equals(that.getVerifyRequirements())) {
            }
            return false;
        }
        if (this.name != null) {
            if (this.name.equals(that.getName())) {
            }
            return false;
        }
        if (this.description != null) {
            if (this.description.equals(that.getDescription())) {
            }
            return false;
        }
        if (this.buyTime != null) {
            if (this.buyTime.equals(that.getBuyTime())) {
            }
            return false;
        }
        if (this.relativeLimits != null) {
            if (this.relativeLimits.equals(that.getRelativeLimits())) {
            }
            return false;
        }
        if (this.supports != null) {
            if (this.supports.equals(that.getSupports())) {
            }
            return false;
        }
        if (this.feeDescription != null) {
            if (this.feeDescription.equals(that.getFeeDescription())) {
            }
            return false;
        }
        if (this.additionalFees != null) {
            if (this.additionalFees.equals(that.getAdditionalFees())) {
            }
            return false;
        }
        if (this.recommended != null) {
            if (this.recommended.equals(that.getRecommended())) {
            }
            return false;
        }
        if (this.buyingPower != null) {
            if (this.buyingPower.equals(that.getBuyingPower())) {
            }
            return false;
        }
        if (this.currency == null) {
            if (that.getCurrency() == null) {
                return true;
            }
        } else if (this.currency.equals(that.getCurrency())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((((((((((1 * 1000003) ^ (this.type == null ? 0 : this.type.hashCode())) * 1000003) ^ (this.requirements == null ? 0 : this.requirements.hashCode())) * 1000003) ^ (this.verifyRequirements == null ? 0 : this.verifyRequirements.hashCode())) * 1000003) ^ (this.name == null ? 0 : this.name.hashCode())) * 1000003) ^ (this.description == null ? 0 : this.description.hashCode())) * 1000003) ^ (this.buyTime == null ? 0 : this.buyTime.hashCode())) * 1000003) ^ (this.relativeLimits == null ? 0 : this.relativeLimits.hashCode())) * 1000003) ^ (this.supports == null ? 0 : this.supports.hashCode())) * 1000003) ^ (this.feeDescription == null ? 0 : this.feeDescription.hashCode())) * 1000003) ^ (this.additionalFees == null ? 0 : this.additionalFees.hashCode())) * 1000003) ^ (this.recommended == null ? 0 : this.recommended.hashCode())) * 1000003) ^ (this.buyingPower == null ? 0 : this.buyingPower.hashCode())) * 1000003;
        if (this.currency != null) {
            i = this.currency.hashCode();
        }
        return h ^ i;
    }
}
