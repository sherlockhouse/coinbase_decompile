package com.coinbase.api.internal.models.wbl;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_AccountBalance extends C$AutoValue_AccountBalance {

    public static final class GsonTypeAdapter extends TypeAdapter<AccountBalance> {
        private final TypeAdapter<String> accountIdAdapter;
        private final TypeAdapter<Amount> availableBalanceAdapter;
        private final TypeAdapter<Amount> availableNativeBalanceAdapter;
        private String defaultAccountId = null;
        private Amount defaultAvailableBalance = null;
        private Amount defaultAvailableNativeBalance = null;

        public GsonTypeAdapter(Gson gson) {
            this.accountIdAdapter = gson.getAdapter(String.class);
            this.availableBalanceAdapter = gson.getAdapter(Amount.class);
            this.availableNativeBalanceAdapter = gson.getAdapter(Amount.class);
        }

        public GsonTypeAdapter setDefaultAccountId(String defaultAccountId) {
            this.defaultAccountId = defaultAccountId;
            return this;
        }

        public GsonTypeAdapter setDefaultAvailableBalance(Amount defaultAvailableBalance) {
            this.defaultAvailableBalance = defaultAvailableBalance;
            return this;
        }

        public GsonTypeAdapter setDefaultAvailableNativeBalance(Amount defaultAvailableNativeBalance) {
            this.defaultAvailableNativeBalance = defaultAvailableNativeBalance;
            return this;
        }

        public void write(JsonWriter jsonWriter, AccountBalance object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("account_id");
            this.accountIdAdapter.write(jsonWriter, object.getAccountId());
            jsonWriter.name("available_balance");
            this.availableBalanceAdapter.write(jsonWriter, object.getAvailableBalance());
            jsonWriter.name("available_native_balance");
            this.availableNativeBalanceAdapter.write(jsonWriter, object.getAvailableNativeBalance());
            jsonWriter.endObject();
        }

        public AccountBalance read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String accountId = this.defaultAccountId;
            Amount availableBalance = this.defaultAvailableBalance;
            Amount availableNativeBalance = this.defaultAvailableNativeBalance;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1549977878:
                            if (_name.equals("available_native_balance")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -919943322:
                            if (_name.equals("available_balance")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -803333011:
                            if (_name.equals("account_id")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            accountId = (String) this.accountIdAdapter.read(jsonReader);
                            break;
                        case 1:
                            availableBalance = (Amount) this.availableBalanceAdapter.read(jsonReader);
                            break;
                        case 2:
                            availableNativeBalance = (Amount) this.availableNativeBalanceAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_AccountBalance(accountId, availableBalance, availableNativeBalance);
        }
    }

    AutoValue_AccountBalance(String accountId, Amount availableBalance, Amount availableNativeBalance) {
        super(accountId, availableBalance, availableNativeBalance);
    }
}
