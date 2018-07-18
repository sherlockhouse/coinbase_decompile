package com.coinbase.api.internal.models.dashboard;

import com.coinbase.api.internal.models.dashboard.AutoValue_Data.GsonTypeAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public abstract class Data {

    public static abstract class Builder {
        public abstract Data build();

        public abstract Builder setBalance(List<Balance> list);

        public abstract Builder setFirstBuyStatus(String str);

        public abstract Builder setFlags(List<String> list);

        public abstract Builder setPromo(Object obj);

        public abstract Builder setRecentTransactions(List<com.coinbase.v2.models.transactions.Data> list);

        public abstract Builder setRemainingVerifications(List<RemainingVerification> list);
    }

    @SerializedName("balances")
    public abstract List<Balance> getBalance();

    @SerializedName("first_buy_status")
    public abstract String getFirstBuyStatus();

    @SerializedName("flags")
    public abstract List<String> getFlags();

    @SerializedName("promo")
    public abstract Object getPromo();

    @SerializedName("recent_transactions")
    public abstract List<com.coinbase.v2.models.transactions.Data> getRecentTransactions();

    @SerializedName("remaining_verifications")
    public abstract List<RemainingVerification> getRemainingVerifications();

    public static TypeAdapter<Data> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }

    public static Builder builder() {
        return new Builder().setBalance(new ArrayList()).setRecentTransactions(new ArrayList()).setFlags(new ArrayList()).setRemainingVerifications(new ArrayList());
    }
}
