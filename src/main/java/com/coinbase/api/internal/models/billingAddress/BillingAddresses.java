package com.coinbase.api.internal.models.billingAddress;

import com.coinbase.api.internal.models.Pagination;
import com.coinbase.api.internal.models.billingAddress.AutoValue_BillingAddresses.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class BillingAddresses {
    @SerializedName("data")
    public abstract List<Data> getData();

    @SerializedName("pagination")
    public abstract Pagination getPagination();

    public static TypeAdapter<BillingAddresses> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultData(new ArrayList());
    }
}
