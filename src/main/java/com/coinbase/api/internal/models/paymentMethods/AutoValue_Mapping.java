package com.coinbase.api.internal.models.paymentMethods;

import com.coinbase.android.utils.CryptoUri;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Mapping extends C$AutoValue_Mapping {

    public static final class GsonTypeAdapter extends TypeAdapter<Mapping> {
        private String defaultId = null;
        private String defaultName = null;
        private String defaultValue = null;
        private final TypeAdapter<String> idAdapter;
        private final TypeAdapter<String> nameAdapter;
        private final TypeAdapter<String> valueAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.idAdapter = gson.getAdapter(String.class);
            this.nameAdapter = gson.getAdapter(String.class);
            this.valueAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultId(String defaultId) {
            this.defaultId = defaultId;
            return this;
        }

        public GsonTypeAdapter setDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public GsonTypeAdapter setDefaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public void write(JsonWriter jsonWriter, Mapping object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("id");
            this.idAdapter.write(jsonWriter, object.getId());
            jsonWriter.name("name");
            this.nameAdapter.write(jsonWriter, object.getName());
            jsonWriter.name(CryptoUri.VALUE);
            this.valueAdapter.write(jsonWriter, object.getValue());
            jsonWriter.endObject();
        }

        public Mapping read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String id = this.defaultId;
            String name = this.defaultName;
            String value = this.defaultValue;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 3355:
                            if (_name.equals("id")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 3373707:
                            if (_name.equals("name")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 111972721:
                            if (_name.equals(CryptoUri.VALUE)) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            id = (String) this.idAdapter.read(jsonReader);
                            break;
                        case 1:
                            name = (String) this.nameAdapter.read(jsonReader);
                            break;
                        case 2:
                            value = (String) this.valueAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Mapping(id, name, value);
        }
    }

    AutoValue_Mapping(String id, String name, String value) {
        super(id, name, value);
    }
}
