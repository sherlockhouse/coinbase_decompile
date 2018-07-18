package com.coinbase.api.internal.models.dashboard;

import com.coinbase.v2.models.transactions.Data;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

final class AutoValue_Data extends C$AutoValue_Data {

    public static final class GsonTypeAdapter extends TypeAdapter<Data> {
        private final TypeAdapter<List<Balance>> balanceAdapter;
        private List<Balance> defaultBalance = null;
        private String defaultFirstBuyStatus = null;
        private List<String> defaultFlags = null;
        private Object defaultPromo = null;
        private List<Data> defaultRecentTransactions = null;
        private List<RemainingVerification> defaultRemainingVerifications = null;
        private final TypeAdapter<String> firstBuyStatusAdapter;
        private final TypeAdapter<List<String>> flagsAdapter;
        private final TypeAdapter<Object> promoAdapter;
        private final TypeAdapter<List<Data>> recentTransactionsAdapter;
        private final TypeAdapter<List<RemainingVerification>> remainingVerificationsAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.balanceAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, Balance.class));
            this.flagsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, String.class));
            this.remainingVerificationsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, RemainingVerification.class));
            this.promoAdapter = gson.getAdapter(Object.class);
            this.recentTransactionsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, Data.class));
            this.firstBuyStatusAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultBalance(List<Balance> defaultBalance) {
            this.defaultBalance = defaultBalance;
            return this;
        }

        public GsonTypeAdapter setDefaultFlags(List<String> defaultFlags) {
            this.defaultFlags = defaultFlags;
            return this;
        }

        public GsonTypeAdapter setDefaultRemainingVerifications(List<RemainingVerification> defaultRemainingVerifications) {
            this.defaultRemainingVerifications = defaultRemainingVerifications;
            return this;
        }

        public GsonTypeAdapter setDefaultPromo(Object defaultPromo) {
            this.defaultPromo = defaultPromo;
            return this;
        }

        public GsonTypeAdapter setDefaultRecentTransactions(List<Data> defaultRecentTransactions) {
            this.defaultRecentTransactions = defaultRecentTransactions;
            return this;
        }

        public GsonTypeAdapter setDefaultFirstBuyStatus(String defaultFirstBuyStatus) {
            this.defaultFirstBuyStatus = defaultFirstBuyStatus;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("balances");
            this.balanceAdapter.write(jsonWriter, object.getBalance());
            jsonWriter.name("flags");
            this.flagsAdapter.write(jsonWriter, object.getFlags());
            jsonWriter.name("remaining_verifications");
            this.remainingVerificationsAdapter.write(jsonWriter, object.getRemainingVerifications());
            jsonWriter.name("promo");
            this.promoAdapter.write(jsonWriter, object.getPromo());
            jsonWriter.name("recent_transactions");
            this.recentTransactionsAdapter.write(jsonWriter, object.getRecentTransactions());
            jsonWriter.name("first_buy_status");
            this.firstBuyStatusAdapter.write(jsonWriter, object.getFirstBuyStatus());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            List<Balance> balance = this.defaultBalance;
            List<String> flags = this.defaultFlags;
            List<RemainingVerification> remainingVerifications = this.defaultRemainingVerifications;
            Object promo = this.defaultPromo;
            List<Data> recentTransactions = this.defaultRecentTransactions;
            String firstBuyStatus = this.defaultFirstBuyStatus;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1924829929:
                            if (_name.equals("balances")) {
                                obj = null;
                                break;
                            }
                            break;
                        case -14024518:
                            if (_name.equals("first_buy_status")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 97513095:
                            if (_name.equals("flags")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 106940687:
                            if (_name.equals("promo")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 1066080441:
                            if (_name.equals("recent_transactions")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 1409337135:
                            if (_name.equals("remaining_verifications")) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            balance = (List) this.balanceAdapter.read(jsonReader);
                            break;
                        case 1:
                            flags = (List) this.flagsAdapter.read(jsonReader);
                            break;
                        case 2:
                            remainingVerifications = (List) this.remainingVerificationsAdapter.read(jsonReader);
                            break;
                        case 3:
                            promo = this.promoAdapter.read(jsonReader);
                            break;
                        case 4:
                            recentTransactions = (List) this.recentTransactionsAdapter.read(jsonReader);
                            break;
                        case 5:
                            firstBuyStatus = (String) this.firstBuyStatusAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(balance, flags, remainingVerifications, promo, recentTransactions, firstBuyStatus);
        }
    }

    AutoValue_Data(List<Balance> balance, List<String> flags, List<RemainingVerification> remainingVerifications, Object promo, List<Data> recentTransactions, String firstBuyStatus) {
        super(balance, flags, remainingVerifications, promo, recentTransactions, firstBuyStatus);
    }
}
