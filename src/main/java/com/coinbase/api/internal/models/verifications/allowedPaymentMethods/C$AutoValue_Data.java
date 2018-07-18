package com.coinbase.api.internal.models.verifications.allowedPaymentMethods;

import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Data extends Data {
    private final String description;
    private final String name;
    private final String pickerBuyTime;
    private final String pickerFeeDescription;
    private final String pickerRelativeLimits;
    private final List<String> requirements;
    private final String type;

    C$AutoValue_Data(String type, String name, List<String> requirements, String description, String pickerBuyTime, String pickerRelativeLimits, String pickerFeeDescription) {
        this.type = type;
        this.name = name;
        this.requirements = requirements;
        this.description = description;
        this.pickerBuyTime = pickerBuyTime;
        this.pickerRelativeLimits = pickerRelativeLimits;
        this.pickerFeeDescription = pickerFeeDescription;
    }

    @SerializedName("type")
    public String getType() {
        return this.type;
    }

    @SerializedName("name")
    public String getName() {
        return this.name;
    }

    @SerializedName("requirements")
    public List<String> getRequirements() {
        return this.requirements;
    }

    @SerializedName("description")
    public String getDescription() {
        return this.description;
    }

    @SerializedName("picker_buy_time")
    public String getPickerBuyTime() {
        return this.pickerBuyTime;
    }

    @SerializedName("picker_relative_limits")
    public String getPickerRelativeLimits() {
        return this.pickerRelativeLimits;
    }

    @SerializedName("picker_fee_description")
    public String getPickerFeeDescription() {
        return this.pickerFeeDescription;
    }

    public String toString() {
        return "Data{type=" + this.type + ", name=" + this.name + ", requirements=" + this.requirements + ", description=" + this.description + ", pickerBuyTime=" + this.pickerBuyTime + ", pickerRelativeLimits=" + this.pickerRelativeLimits + ", pickerFeeDescription=" + this.pickerFeeDescription + "}";
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
        if (this.type != null) {
            if (this.type.equals(that.getType())) {
            }
            return false;
        }
        if (this.name != null) {
            if (this.name.equals(that.getName())) {
            }
            return false;
        }
        if (this.requirements != null) {
            if (this.requirements.equals(that.getRequirements())) {
            }
            return false;
        }
        if (this.description != null) {
            if (this.description.equals(that.getDescription())) {
            }
            return false;
        }
        if (this.pickerBuyTime != null) {
            if (this.pickerBuyTime.equals(that.getPickerBuyTime())) {
            }
            return false;
        }
        if (this.pickerRelativeLimits != null) {
            if (this.pickerRelativeLimits.equals(that.getPickerRelativeLimits())) {
            }
            return false;
        }
        if (this.pickerFeeDescription == null) {
            if (that.getPickerFeeDescription() == null) {
                return true;
            }
        } else if (this.pickerFeeDescription.equals(that.getPickerFeeDescription())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((1 * 1000003) ^ (this.type == null ? 0 : this.type.hashCode())) * 1000003) ^ (this.name == null ? 0 : this.name.hashCode())) * 1000003) ^ (this.requirements == null ? 0 : this.requirements.hashCode())) * 1000003) ^ (this.description == null ? 0 : this.description.hashCode())) * 1000003) ^ (this.pickerBuyTime == null ? 0 : this.pickerBuyTime.hashCode())) * 1000003) ^ (this.pickerRelativeLimits == null ? 0 : this.pickerRelativeLimits.hashCode())) * 1000003;
        if (this.pickerFeeDescription != null) {
            i = this.pickerFeeDescription.hashCode();
        }
        return h ^ i;
    }
}
