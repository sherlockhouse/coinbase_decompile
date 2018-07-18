package com.coinbase.api.internal.models.wbl;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Amount extends C$AutoValue_Amount {

    public static final class GsonTypeAdapter extends TypeAdapter<Amount> {
        private final TypeAdapter<String> amountAdapter;
        private final TypeAdapter<String> currencyAdapter;
        private String defaultAmount = null;
        private String defaultCurrency = null;

        public GsonTypeAdapter(Gson gson) {
            this.amountAdapter = gson.getAdapter(String.class);
            this.currencyAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultAmount(String defaultAmount) {
            this.defaultAmount = defaultAmount;
            return this;
        }

        public GsonTypeAdapter setDefaultCurrency(String defaultCurrency) {
            this.defaultCurrency = defaultCurrency;
            return this;
        }

        public void write(JsonWriter jsonWriter, Amount object) throws IOException {
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

        public Amount read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String amount = this.defaultAmount;
            String currency = this.defaultCurrency;
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
                            amount = (String) this.amountAdapter.read(jsonReader);
                            break;
                        case 1:
                            currency = (String) this.currencyAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Amount(amount, currency);
        }
    }

    AutoValue_Amount(String amount, String currency) {
        super(amount, currency);
    }
}
