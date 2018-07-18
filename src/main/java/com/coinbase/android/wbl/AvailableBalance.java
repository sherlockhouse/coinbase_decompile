package com.coinbase.android.wbl;

import com.coinbase.android.wbl.AutoValue_AvailableBalance.GsonTypeAdapter;
import com.coinbase.api.internal.models.wbl.PendingHold;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.money.Money;

public abstract class AvailableBalance {

    public static abstract class Builder {
        public abstract AvailableBalance build();

        public abstract Builder setAccountBalances(Map<String, Money> map);

        public abstract Builder setAccountCryptoBalances(Map<String, Money> map);

        public abstract Builder setPendingHolds(List<PendingHold> list);

        public abstract Builder setTotalAvailableBalance(Money money);

        public abstract Builder setTotalHoldBalance(Money money);

        public abstract Builder setTotalPortfolioBalance(Money money);
    }

    @SerializedName("account_balances")
    public abstract Map<String, Money> getAccountBalances();

    @SerializedName("account_crypto_balances")
    public abstract Map<String, Money> getAccountCryptoBalances();

    @SerializedName("pending_holds")
    public abstract List<PendingHold> getPendingHolds();

    @SerializedName("total_available_balance")
    public abstract Money getTotalAvailableBalance();

    @SerializedName("total_hold_balance")
    public abstract Money getTotalHoldBalance();

    @SerializedName("total_portfolio_balance")
    public abstract Money getTotalPortfolioBalance();

    public static Builder builder() {
        return new Builder().setPendingHolds(new ArrayList()).setAccountBalances(new HashMap()).setAccountCryptoBalances(new HashMap());
    }

    public static TypeAdapter<AvailableBalance> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson).setDefaultPendingHolds(new ArrayList()).setDefaultAccountBalances(new HashMap()).setDefaultAccountCryptoBalances(new HashMap());
    }
}
