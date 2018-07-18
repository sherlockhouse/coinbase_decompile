package com.coinbase.api.internal.models.tiers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Status extends C$AutoValue_Status {

    public static final class GsonTypeAdapter extends TypeAdapter<Status> {
        private final TypeAdapter<Boolean> completeAdapter;
        private Boolean defaultComplete = null;
        private String defaultDescription = null;
        private final TypeAdapter<String> descriptionAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.completeAdapter = gson.getAdapter(Boolean.class);
            this.descriptionAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultComplete(Boolean defaultComplete) {
            this.defaultComplete = defaultComplete;
            return this;
        }

        public GsonTypeAdapter setDefaultDescription(String defaultDescription) {
            this.defaultDescription = defaultDescription;
            return this;
        }

        public void write(JsonWriter jsonWriter, Status object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("complete");
            this.completeAdapter.write(jsonWriter, object.getComplete());
            jsonWriter.name("description");
            this.descriptionAdapter.write(jsonWriter, object.getDescription());
            jsonWriter.endObject();
        }

        public Status read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Boolean complete = this.defaultComplete;
            String description = this.defaultDescription;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1724546052:
                            if (_name.equals("description")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -599445191:
                            if (_name.equals("complete")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            complete = (Boolean) this.completeAdapter.read(jsonReader);
                            break;
                        case 1:
                            description = (String) this.descriptionAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Status(complete, description);
        }
    }

    AutoValue_Status(Boolean complete, String description) {
        super(complete, description);
    }
}
