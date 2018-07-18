package com.coinbase.api.internal.models.paymentMethods;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_CardSetup extends C$AutoValue_CardSetup {

    public static final class GsonTypeAdapter extends TypeAdapter<CardSetup> {
        private final TypeAdapter<Data> dataAdapter;
        private Data defaultData = null;

        public GsonTypeAdapter(Gson gson) {
            this.dataAdapter = gson.getAdapter(Data.class);
        }

        public GsonTypeAdapter setDefaultData(Data defaultData) {
            this.defaultData = defaultData;
            return this;
        }

        public void write(JsonWriter jsonWriter, CardSetup object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("data");
            this.dataAdapter.write(jsonWriter, object.getData());
            jsonWriter.endObject();
        }

        public CardSetup read(JsonReader jsonReader) throws IOException {
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
            return new AutoValue_CardSetup(data);
        }
    }

    AutoValue_CardSetup(Data data) {
        super(data);
    }
}
