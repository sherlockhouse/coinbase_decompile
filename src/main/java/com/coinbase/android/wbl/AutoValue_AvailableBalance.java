package com.coinbase.android.wbl;

import com.coinbase.api.internal.models.wbl.PendingHold;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.joda.money.Money;

final class AutoValue_AvailableBalance extends C$AutoValue_AvailableBalance {

    public static final class GsonTypeAdapter extends TypeAdapter<AvailableBalance> {
        private final TypeAdapter<Map<String, Money>> accountBalancesAdapter;
        private final TypeAdapter<Map<String, Money>> accountCryptoBalancesAdapter;
        private Map<String, Money> defaultAccountBalances = null;
        private Map<String, Money> defaultAccountCryptoBalances = null;
        private List<PendingHold> defaultPendingHolds = null;
        private Money defaultTotalAvailableBalance = null;
        private Money defaultTotalHoldBalance = null;
        private Money defaultTotalPortfolioBalance = null;
        private final TypeAdapter<List<PendingHold>> pendingHoldsAdapter;
        private final TypeAdapter<Money> totalAvailableBalanceAdapter;
        private final TypeAdapter<Money> totalHoldBalanceAdapter;
        private final TypeAdapter<Money> totalPortfolioBalanceAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.totalPortfolioBalanceAdapter = gson.getAdapter(Money.class);
            this.totalHoldBalanceAdapter = gson.getAdapter(Money.class);
            this.totalAvailableBalanceAdapter = gson.getAdapter(Money.class);
            this.pendingHoldsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, PendingHold.class));
            this.accountBalancesAdapter = gson.getAdapter(TypeToken.getParameterized(Map.class, String.class, Money.class));
            this.accountCryptoBalancesAdapter = gson.getAdapter(TypeToken.getParameterized(Map.class, String.class, Money.class));
        }

        public GsonTypeAdapter setDefaultTotalPortfolioBalance(Money defaultTotalPortfolioBalance) {
            this.defaultTotalPortfolioBalance = defaultTotalPortfolioBalance;
            return this;
        }

        public GsonTypeAdapter setDefaultTotalHoldBalance(Money defaultTotalHoldBalance) {
            this.defaultTotalHoldBalance = defaultTotalHoldBalance;
            return this;
        }

        public GsonTypeAdapter setDefaultTotalAvailableBalance(Money defaultTotalAvailableBalance) {
            this.defaultTotalAvailableBalance = defaultTotalAvailableBalance;
            return this;
        }

        public GsonTypeAdapter setDefaultPendingHolds(List<PendingHold> defaultPendingHolds) {
            this.defaultPendingHolds = defaultPendingHolds;
            return this;
        }

        public GsonTypeAdapter setDefaultAccountBalances(Map<String, Money> defaultAccountBalances) {
            this.defaultAccountBalances = defaultAccountBalances;
            return this;
        }

        public GsonTypeAdapter setDefaultAccountCryptoBalances(Map<String, Money> defaultAccountCryptoBalances) {
            this.defaultAccountCryptoBalances = defaultAccountCryptoBalances;
            return this;
        }

        public void write(JsonWriter jsonWriter, AvailableBalance object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("total_portfolio_balance");
            this.totalPortfolioBalanceAdapter.write(jsonWriter, object.getTotalPortfolioBalance());
            jsonWriter.name("total_hold_balance");
            this.totalHoldBalanceAdapter.write(jsonWriter, object.getTotalHoldBalance());
            jsonWriter.name("total_available_balance");
            this.totalAvailableBalanceAdapter.write(jsonWriter, object.getTotalAvailableBalance());
            jsonWriter.name("pending_holds");
            this.pendingHoldsAdapter.write(jsonWriter, object.getPendingHolds());
            jsonWriter.name("account_balances");
            this.accountBalancesAdapter.write(jsonWriter, object.getAccountBalances());
            jsonWriter.name("account_crypto_balances");
            this.accountCryptoBalancesAdapter.write(jsonWriter, object.getAccountCryptoBalances());
            jsonWriter.endObject();
        }

        public AvailableBalance read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Money totalPortfolioBalance = this.defaultTotalPortfolioBalance;
            Money totalHoldBalance = this.defaultTotalHoldBalance;
            Money totalAvailableBalance = this.defaultTotalAvailableBalance;
            List<PendingHold> pendingHolds = this.defaultPendingHolds;
            Map<String, Money> accountBalances = this.defaultAccountBalances;
            Map<String, Money> accountCryptoBalances = this.defaultAccountCryptoBalances;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1931158333:
                            if (_name.equals("account_crypto_balances")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case -1178972502:
                            if (_name.equals("total_portfolio_balance")) {
                                obj = null;
                                break;
                            }
                            break;
                        case -1105512727:
                            if (_name.equals("account_balances")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -347749589:
                            if (_name.equals("total_available_balance")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 752665324:
                            if (_name.equals("pending_holds")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 999413879:
                            if (_name.equals("total_hold_balance")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            totalPortfolioBalance = (Money) this.totalPortfolioBalanceAdapter.read(jsonReader);
                            break;
                        case 1:
                            totalHoldBalance = (Money) this.totalHoldBalanceAdapter.read(jsonReader);
                            break;
                        case 2:
                            totalAvailableBalance = (Money) this.totalAvailableBalanceAdapter.read(jsonReader);
                            break;
                        case 3:
                            pendingHolds = (List) this.pendingHoldsAdapter.read(jsonReader);
                            break;
                        case 4:
                            accountBalances = (Map) this.accountBalancesAdapter.read(jsonReader);
                            break;
                        case 5:
                            accountCryptoBalances = (Map) this.accountCryptoBalancesAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_AvailableBalance(totalPortfolioBalance, totalHoldBalance, totalAvailableBalance, pendingHolds, accountBalances, accountCryptoBalances);
        }
    }

    AutoValue_AvailableBalance(Money totalPortfolioBalance, Money totalHoldBalance, Money totalAvailableBalance, List<PendingHold> pendingHolds, Map<String, Money> accountBalances, Map<String, Money> accountCryptoBalances) {
        super(totalPortfolioBalance, totalHoldBalance, totalAvailableBalance, pendingHolds, accountBalances, accountCryptoBalances);
    }
}
