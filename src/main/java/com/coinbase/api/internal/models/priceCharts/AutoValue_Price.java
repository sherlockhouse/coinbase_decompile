package com.coinbase.api.internal.models.priceCharts;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Price extends C$AutoValue_Price {

    public static final class GsonTypeAdapter extends TypeAdapter<Price> {
        private String defaultPrice = null;
        private String defaultTime = null;
        private final TypeAdapter<String> priceAdapter;
        private final TypeAdapter<String> timeAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.priceAdapter = gson.getAdapter(String.class);
            this.timeAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultPrice(String defaultPrice) {
            this.defaultPrice = defaultPrice;
            return this;
        }

        public GsonTypeAdapter setDefaultTime(String defaultTime) {
            this.defaultTime = defaultTime;
            return this;
        }

        public void write(JsonWriter jsonWriter, Price object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("price");
            this.priceAdapter.write(jsonWriter, object.getPrice());
            jsonWriter.name("time");
            this.timeAdapter.write(jsonWriter, object.getTime());
            jsonWriter.endObject();
        }

        public Price read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String price = this.defaultPrice;
            String time = this.defaultTime;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 3560141:
                            if (_name.equals("time")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 106934601:
                            if (_name.equals("price")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            price = (String) this.priceAdapter.read(jsonReader);
                            break;
                        case 1:
                            time = (String) this.timeAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Price(price, time);
        }
    }

    AutoValue_Price(String price, String time) {
        super(price, time);
    }
}
