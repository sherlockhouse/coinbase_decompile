package com.coinbase.api.internal.models.dashboard;

import com.coinbase.api.internal.models.currency.Data;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Balance extends C$AutoValue_Balance {

    public static final class GsonTypeAdapter extends TypeAdapter<Balance> {
        private final TypeAdapter<Amount> amountAdapter;
        private final TypeAdapter<Data> currencyAdapter;
        private Amount defaultAmount = null;
        private Data defaultCurrency = null;

        public GsonTypeAdapter(Gson gson) {
            this.amountAdapter = gson.getAdapter(Amount.class);
            this.currencyAdapter = gson.getAdapter(Data.class);
        }

        public GsonTypeAdapter setDefaultAmount(Amount defaultAmount) {
            this.defaultAmount = defaultAmount;
            return this;
        }

        public GsonTypeAdapter setDefaultCurrency(Data defaultCurrency) {
            this.defaultCurrency = defaultCurrency;
            return this;
        }

        public void write(JsonWriter jsonWriter, Balance object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("amount");
            this.amountAdapter.write(jsonWriter, object.getAmount());
            jsonWriter.name("currency");
            this.currencyAdapter.write(jsonWriter, object.getCurrency());
            jsonWriter.endObject();
        }

        public Balance read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Amount amount = this.defaultAmount;
            Data currency = this.defaultCurrency;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1413853096:
                            if (_name.equals("amount")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 575402001:
                            if (_name.equals("currency")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            amount = (Amount) this.amountAdapter.read(jsonReader);
                            break;
                        case 1:
                            currency = (Data) this.currencyAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Balance(amount, currency);
        }
    }

    AutoValue_Balance(Amount amount, Data currency) {
        super(amount, currency);
    }
}
