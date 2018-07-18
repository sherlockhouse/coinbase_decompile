package com.coinbase.api.internal.models.achSetupSession;

import com.coinbase.api.internal.models.achSetupSession.AutoValue_Data.GsonTypeAdapter;
import com.coinbase.api.internal.models.achSetupSession.mfa.Mfa;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Data {
    @SerializedName("accounts")
    public abstract List<com.coinbase.v2.models.account.Data> getAccounts();

    @SerializedName("id")
    public abstract String getId();

    @SerializedName("institution")
    public abstract String getInstitution();

    @SerializedName("mfa")
    public abstract Mfa getMfa();

    @SerializedName("status")
    public abstract String getStatus();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultAccounts(new ArrayList());
    }
}
