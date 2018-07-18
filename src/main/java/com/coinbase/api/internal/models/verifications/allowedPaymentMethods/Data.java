package com.coinbase.api.internal.models.verifications.allowedPaymentMethods;

import com.coinbase.api.internal.models.verifications.allowedPaymentMethods.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Data {
    @SerializedName("description")
    public abstract String getDescription();

    @SerializedName("name")
    public abstract String getName();

    @SerializedName("picker_buy_time")
    public abstract String getPickerBuyTime();

    @SerializedName("picker_fee_description")
    public abstract String getPickerFeeDescription();

    @SerializedName("picker_relative_limits")
    public abstract String getPickerRelativeLimits();

    @SerializedName("requirements")
    public abstract List<String> getRequirements();

    @SerializedName("type")
    public abstract String getType();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultRequirements(new ArrayList());
    }
}
