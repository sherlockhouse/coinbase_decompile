package com.coinbase.api.internal.models.tiers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_LevelFeature extends C$AutoValue_LevelFeature {

    public static final class GsonTypeAdapter extends TypeAdapter<LevelFeature> {
        private String defaultDescription = null;
        private Boolean defaultEnabled = null;
        private String defaultStatusText = null;
        private final TypeAdapter<String> descriptionAdapter;
        private final TypeAdapter<Boolean> enabledAdapter;
        private final TypeAdapter<String> statusTextAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.descriptionAdapter = gson.getAdapter(String.class);
            this.enabledAdapter = gson.getAdapter(Boolean.class);
            this.statusTextAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultDescription(String defaultDescription) {
            this.defaultDescription = defaultDescription;
            return this;
        }

        public GsonTypeAdapter setDefaultEnabled(Boolean defaultEnabled) {
            this.defaultEnabled = defaultEnabled;
            return this;
        }

        public GsonTypeAdapter setDefaultStatusText(String defaultStatusText) {
            this.defaultStatusText = defaultStatusText;
            return this;
        }

        public void write(JsonWriter jsonWriter, LevelFeature object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("description");
            this.descriptionAdapter.write(jsonWriter, object.getDescription());
            jsonWriter.name("enabled");
            this.enabledAdapter.write(jsonWriter, object.getEnabled());
            jsonWriter.name("status_text");
            this.statusTextAdapter.write(jsonWriter, object.getStatusText());
            jsonWriter.endObject();
        }

        public LevelFeature read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String description = this.defaultDescription;
            Boolean enabled = this.defaultEnabled;
            String statusText = this.defaultStatusText;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1724546052:
                            if (_name.equals("description")) {
                                obj = null;
                                break;
                            }
                            break;
                        case -1609594047:
                            if (_name.equals("enabled")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -891202214:
                            if (_name.equals("status_text")) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            description = (String) this.descriptionAdapter.read(jsonReader);
                            break;
                        case 1:
                            enabled = (Boolean) this.enabledAdapter.read(jsonReader);
                            break;
                        case 2:
                            statusText = (String) this.statusTextAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_LevelFeature(description, enabled, statusText);
        }
    }

    AutoValue_LevelFeature(String description, Boolean enabled, String statusText) {
        super(description, enabled, statusText);
    }
}
