package com.coinbase.api.internal.models.verifications.allowedPaymentMethods;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

final class AutoValue_AllowedPaymentMethods extends C$AutoValue_AllowedPaymentMethods {

    public static final class GsonTypeAdapter extends TypeAdapter<AllowedPaymentMethods> {
        private final TypeAdapter<List<Data>> dataAdapter;
        private List<Data> defaultData = null;

        public GsonTypeAdapter(Gson gson) {
            this.dataAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, Data.class));
        }

        public GsonTypeAdapter setDefaultData(List<Data> defaultData) {
            this.defaultData = defaultData;
            return this;
        }

        public void write(JsonWriter jsonWriter, AllowedPaymentMethods object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("data");
            this.dataAdapter.write(jsonWriter, object.getData());
            jsonWriter.endObject();
        }

        public AllowedPaymentMethods read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            List<Data> data = this.defaultData;
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
                            data = (List) this.dataAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_AllowedPaymentMethods(data);
        }
    }

    AutoValue_AllowedPaymentMethods(List<Data> data) {
        super(data);
    }
}
