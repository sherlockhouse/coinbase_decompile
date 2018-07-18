package com.coinbase.api.internal.models.availablePaymentMethods;

import com.coinbase.api.internal.models.availablePaymentMethods.AutoValue_AvailablePaymentMethod.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class AvailablePaymentMethod {

    public static abstract class Builder {
        public abstract AvailablePaymentMethod build();

        public abstract Builder setAdditionalFees(String str);

        public abstract Builder setBuyTime(String str);

        public abstract Builder setBuyingPower(BuyingPower buyingPower);

        public abstract Builder setCurrency(Currency currency);

        public abstract Builder setDescription(String str);

        public abstract Builder setFeeDescription(String str);

        public abstract Builder setName(String str);

        public abstract Builder setRecommended(Boolean bool);

        public abstract Builder setRelativeLimits(String str);

        public abstract Builder setRequirements(List<String> list);

        public abstract Builder setSupports(List<String> list);

        public abstract Builder setType(String str);

        public abstract Builder setVerifyRequirements(List<String> list);
    }

    @SerializedName("additional_fees")
    public abstract String getAdditionalFees();

    @SerializedName("buy_time")
    public abstract String getBuyTime();

    @SerializedName("buying_power")
    public abstract BuyingPower getBuyingPower();

    @SerializedName("currency")
    public abstract Currency getCurrency();

    @SerializedName("description")
    public abstract String getDescription();

    @SerializedName("fee_description")
    public abstract String getFeeDescription();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("recommended")
    public abstract Boolean getRecommended();

    @SerializedName("relative_limits")
    public abstract String getRelativeLimits();

    @SerializedName("requirements")
    public abstract List<String> getRequirements();

    @SerializedName("supports")
    public abstract List<String> getSupports();

    @SerializedName("type")
    public abstract String getType();

    @SerializedName("verify_requirements")
    public abstract List<String> getVerifyRequirements();

    public static Builder builder() {
        return new Builder().setRequirements(new ArrayList()).setVerifyRequirements(new ArrayList()).setSupports(new ArrayList());
    }

    public static TypeAdapter<AvailablePaymentMethod> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultRequirements(new ArrayList()).setDefaultVerifyRequirements(new ArrayList()).setDefaultSupports(new ArrayList());
    }
}
