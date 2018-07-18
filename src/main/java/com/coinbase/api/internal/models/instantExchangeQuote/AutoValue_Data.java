package com.coinbase.api.internal.models.instantExchangeQuote;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Data extends C$AutoValue_Data {

    public static final class GsonTypeAdapter extends TypeAdapter<Data> {
        private final TypeAdapter<Amount> amountAdapter;
        private final TypeAdapter<Amount> bitcoinAdapter;
        private Amount defaultAmount = null;
        private Amount defaultBitcoin = null;
        private Amount defaultFiat = null;
        private String defaultId = null;
        private final TypeAdapter<Amount> fiatAdapter;
        private final TypeAdapter<String> idAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.idAdapter = gson.getAdapter(String.class);
            this.fiatAdapter = gson.getAdapter(Amount.class);
            this.bitcoinAdapter = gson.getAdapter(Amount.class);
            this.amountAdapter = gson.getAdapter(Amount.class);
        }

        public GsonTypeAdapter setDefaultId(String defaultId) {
            this.defaultId = defaultId;
            return this;
        }

        public GsonTypeAdapter setDefaultFiat(Amount defaultFiat) {
            this.defaultFiat = defaultFiat;
            return this;
        }

        public GsonTypeAdapter setDefaultBitcoin(Amount defaultBitcoin) {
            this.defaultBitcoin = defaultBitcoin;
            return this;
        }

        public GsonTypeAdapter setDefaultAmount(Amount defaultAmount) {
            this.defaultAmount = defaultAmount;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("id");
            this.idAdapter.write(jsonWriter, object.getId());
            jsonWriter.name("fiat");
            this.fiatAdapter.write(jsonWriter, object.getFiat());
            jsonWriter.name("bitcoin");
            this.bitcoinAdapter.write(jsonWriter, object.getBitcoin());
            jsonWriter.name("amount");
            this.amountAdapter.write(jsonWriter, object.getAmount());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String id = this.defaultId;
            Amount fiat = this.defaultFiat;
            Amount bitcoin = this.defaultBitcoin;
            Amount amount = this.defaultAmount;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1413853096:
                            if (_name.equals("amount")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case -102703842:
                            if (_name.equals("bitcoin")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 3355:
                            if (_name.equals("id")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 3142710:
                            if (_name.equals("fiat")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            id = (String) this.idAdapter.read(jsonReader);
                            break;
                        case 1:
                            fiat = (Amount) this.fiatAdapter.read(jsonReader);
                            break;
                        case 2:
                            bitcoin = (Amount) this.bitcoinAdapter.read(jsonReader);
                            break;
                        case 3:
                            amount = (Amount) this.amountAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(id, fiat, bitcoin, amount);
        }
    }

    AutoValue_Data(String id, Amount fiat, Amount bitcoin, Amount amount) {
        super(id, fiat, bitcoin, amount);
    }
}
