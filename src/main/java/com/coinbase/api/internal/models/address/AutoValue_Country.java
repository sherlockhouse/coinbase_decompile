package com.coinbase.api.internal.models.address;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Country extends C$AutoValue_Country {

    public static final class GsonTypeAdapter extends TypeAdapter<Country> {
        private final TypeAdapter<String> codeAdapter;
        private String defaultCode = null;
        private String defaultName = null;
        private final TypeAdapter<String> nameAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.codeAdapter = gson.getAdapter(String.class);
            this.nameAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultCode(String defaultCode) {
            this.defaultCode = defaultCode;
            return this;
        }

        public GsonTypeAdapter setDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public void write(JsonWriter jsonWriter, Country object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("code");
            this.codeAdapter.write(jsonWriter, object.getCode());
            jsonWriter.name("name");
            this.nameAdapter.write(jsonWriter, object.getName());
            jsonWriter.endObject();
        }

        public Country read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String code = this.defaultCode;
            String name = this.defaultName;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 3059181:
                            if (_name.equals("code")) {
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
                    }
                    switch (obj) {
                        case null:
                            code = (String) this.codeAdapter.read(jsonReader);
                            break;
                        case 1:
                            name = (String) this.nameAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Country(code, name);
        }
    }

    AutoValue_Country(String code, String name) {
        super(code, name);
    }
}
