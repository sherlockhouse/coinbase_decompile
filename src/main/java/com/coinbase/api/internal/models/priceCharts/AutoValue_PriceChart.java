package com.coinbase.api.internal.models.priceCharts;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_PriceChart extends C$AutoValue_PriceChart {

    public static final class GsonTypeAdapter extends TypeAdapter<PriceChart> {
        private final TypeAdapter<Data> dataAdapter;
        private Data defaultData = null;

        public GsonTypeAdapter(Gson gson) {
            this.dataAdapter = gson.getAdapter(Data.class);
        }

        public GsonTypeAdapter setDefaultData(Data defaultData) {
            this.defaultData = defaultData;
            return this;
        }

        public void write(JsonWriter jsonWriter, PriceChart object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("data");
            this.dataAdapter.write(jsonWriter, object.getData());
            jsonWriter.endObject();
        }

        public PriceChart read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Data data = this.defaultData;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 3076010:
                            if (_name.equals("data")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            data = (Data) this.dataAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_PriceChart(data);
        }
    }

    AutoValue_PriceChart(Data data) {
        super(data);
    }
}
