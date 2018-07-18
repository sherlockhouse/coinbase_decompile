package com.coinbase.api.internal.models.alerts;

import com.coinbase.api.internal.models.alerts.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Data {
    @SerializedName("currencies")
    public abstract List<String> getCurrencies();

    @SerializedName("description")
    public abstract String getDescription();

    @SerializedName("dismissable")
    public abstract Boolean getDismissable();

    @SerializedName("id")
    public abstract String getId();

    @SerializedName("image_url")
    public abstract String getImageUrl();

    @SerializedName("payment_methods")
    public abstract List<String> getPaymentMethods();

    @SerializedName("title")
    public abstract String getTitle();

    @SerializedName("type")
    public abstract String getType();

    @SerializedName("url")
    public abstract String getUrl();

    @SerializedName("views")
    public abstract List<String> getViews();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultViews(new ArrayList()).setDefaultPaymentMethods(new ArrayList()).setDefaultCurrencies(new ArrayList());
    }
}
