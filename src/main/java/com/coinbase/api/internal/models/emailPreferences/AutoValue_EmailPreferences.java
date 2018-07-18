package com.coinbase.api.internal.models.emailPreferences;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Map;

final class AutoValue_EmailPreferences extends C$AutoValue_EmailPreferences {

    public static final class GsonTypeAdapter extends TypeAdapter<EmailPreferences> {
        private final TypeAdapter<Map<String, Boolean>> dataAdapter;
        private Map<String, Boolean> defaultData = null;

        public GsonTypeAdapter(Gson gson) {
            this.dataAdapter = gson.getAdapter(TypeToken.getParameterized(Map.class, String.class, Boolean.class));
        }

        public GsonTypeAdapter setDefaultData(Map<String, Boolean> defaultData) {
            this.defaultData = defaultData;
            return this;
        }

        public void write(JsonWriter jsonWriter, EmailPreferences object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("data");
            this.dataAdapter.write(jsonWriter, object.getData());
            jsonWriter.endObject();
        }

        public EmailPreferences read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Map<String, Boolean> data = this.defaultData;
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
                            data = (Map) this.dataAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_EmailPreferences(data);
        }
    }

    AutoValue_EmailPreferences(Map<String, Boolean> data) {
        super(data);
    }
}
