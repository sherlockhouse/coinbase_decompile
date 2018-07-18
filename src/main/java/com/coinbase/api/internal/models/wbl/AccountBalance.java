package com.coinbase.api.internal.models.wbl;

import com.coinbase.api.internal.models.wbl.AutoValue_AccountBalance.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

public abstract class AccountBalance {
    @SerializedName("account_id")
    public abstract String getAccountId();

    @SerializedName("available_balance")
    public abstract Amount getAvailableBalance();

    @SerializedName("available_native_balance")
    public abstract Amount getAvailableNativeBalance();

    public static TypeAdapter<AccountBalance> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
