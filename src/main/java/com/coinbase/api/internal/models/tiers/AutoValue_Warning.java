package com.coinbase.api.internal.models.tiers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Warning extends C$AutoValue_Warning {

    public static final class GsonTypeAdapter extends TypeAdapter<Warning> {
        private String defaultId = null;
        private String defaultMessage = null;
        private String defaultUrl = null;
        private final TypeAdapter<String> idAdapter;
        private final TypeAdapter<String> messageAdapter;
        private final TypeAdapter<String> urlAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.idAdapter = gson.getAdapter(String.class);
            this.messageAdapter = gson.getAdapter(String.class);
            this.urlAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultId(String defaultId) {
            this.defaultId = defaultId;
            return this;
        }

        public GsonTypeAdapter setDefaultMessage(String defaultMessage) {
            this.defaultMessage = defaultMessage;
            return this;
        }

        public GsonTypeAdapter setDefaultUrl(String defaultUrl) {
            this.defaultUrl = defaultUrl;
            return this;
        }

        public void write(JsonWriter jsonWriter, Warning object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("id");
            this.idAdapter.write(jsonWriter, object.getId());
            jsonWriter.name("message");
            this.messageAdapter.write(jsonWriter, object.getMessage());
            jsonWriter.name("url");
            this.urlAdapter.write(jsonWriter, object.getUrl());
            jsonWriter.endObject();
        }

        public Warning read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String id = this.defaultId;
            String message = this.defaultMessage;
            String url = this.defaultUrl;
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
                        case 116079:
                            if (_name.equals("url")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 954925063:
                            if (_name.equals("message")) {
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
                            message = (String) this.messageAdapter.read(jsonReader);
                            break;
                        case 2:
                            url = (String) this.urlAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Warning(id, message, url);
        }
    }

    AutoValue_Warning(String id, String message, String url) {
        super(id, message, url);
    }
}
