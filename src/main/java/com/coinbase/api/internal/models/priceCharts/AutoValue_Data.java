package com.coinbase.api.internal.models.priceCharts;

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
        private final TypeAdapter<String> currencyAdapter;
        private String defaultCurrency = null;
        private List<Price> defaultPrices = null;
        private final TypeAdapter<List<Price>> pricesAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.currencyAdapter = gson.getAdapter(String.class);
            this.pricesAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, Price.class));
        }

        public GsonTypeAdapter setDefaultCurrency(String defaultCurrency) {
            this.defaultCurrency = defaultCurrency;
            return this;
        }

        public GsonTypeAdapter setDefaultPrices(List<Price> defaultPrices) {
            this.defaultPrices = defaultPrices;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("currency");
            this.currencyAdapter.write(jsonWriter, object.getCurrency());
            jsonWriter.name("prices");
            this.pricesAdapter.write(jsonWriter, object.getPrices());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String currency = this.defaultCurrency;
            List<Price> prices = this.defaultPrices;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -979994550:
                            if (_name.equals("prices")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 575402001:
                            if (_name.equals("currency")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            currency = (String) this.currencyAdapter.read(jsonReader);
                            break;
                        case 1:
                            prices = (List) this.pricesAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(currency, prices);
        }
    }

    AutoValue_Data(String currency, List<Price> prices) {
        super(currency, prices);
    }
}
