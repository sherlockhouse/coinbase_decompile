package com.coinbase.api.internal.models.config;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_VersionRange extends C$AutoValue_VersionRange {

    public static final class GsonTypeAdapter extends TypeAdapter<VersionRange> {
        private String defaultMax = null;
        private String defaultMin = null;
        private final TypeAdapter<String> maxAdapter;
        private final TypeAdapter<String> minAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.minAdapter = gson.getAdapter(String.class);
            this.maxAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultMin(String defaultMin) {
            this.defaultMin = defaultMin;
            return this;
        }

        public GsonTypeAdapter setDefaultMax(String defaultMax) {
            this.defaultMax = defaultMax;
            return this;
        }

        public void write(JsonWriter jsonWriter, VersionRange object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("min");
            this.minAdapter.write(jsonWriter, object.getMin());
            jsonWriter.name("max");
            this.maxAdapter.write(jsonWriter, object.getMax());
            jsonWriter.endObject();
        }

        public VersionRange read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String min = this.defaultMin;
            String max = this.defaultMax;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 107876:
                            if (_name.equals("max")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 108114:
                            if (_name.equals("min")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            min = (String) this.minAdapter.read(jsonReader);
                            break;
                        case 1:
                            max = (String) this.maxAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_VersionRange(min, max);
        }
    }

    AutoValue_VersionRange(String min, String max) {
        super(min, max);
    }
}
