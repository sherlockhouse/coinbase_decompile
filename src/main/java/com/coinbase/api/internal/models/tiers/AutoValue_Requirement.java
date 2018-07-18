package com.coinbase.api.internal.models.tiers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Requirement extends C$AutoValue_Requirement {

    public static final class GsonTypeAdapter extends TypeAdapter<Requirement> {
        private String defaultDescription = null;
        private String defaultIdentifier = null;
        private String defaultStatus = null;
        private final TypeAdapter<String> descriptionAdapter;
        private final TypeAdapter<String> identifierAdapter;
        private final TypeAdapter<String> statusAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.identifierAdapter = gson.getAdapter(String.class);
            this.descriptionAdapter = gson.getAdapter(String.class);
            this.statusAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultIdentifier(String defaultIdentifier) {
            this.defaultIdentifier = defaultIdentifier;
            return this;
        }

        public GsonTypeAdapter setDefaultDescription(String defaultDescription) {
            this.defaultDescription = defaultDescription;
            return this;
        }

        public GsonTypeAdapter setDefaultStatus(String defaultStatus) {
            this.defaultStatus = defaultStatus;
            return this;
        }

        public void write(JsonWriter jsonWriter, Requirement object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("identifier");
            this.identifierAdapter.write(jsonWriter, object.getIdentifier());
            jsonWriter.name("description");
            this.descriptionAdapter.write(jsonWriter, object.getDescription());
            jsonWriter.name("status");
            this.statusAdapter.write(jsonWriter, object.getStatus());
            jsonWriter.endObject();
        }

        public Requirement read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String identifier = this.defaultIdentifier;
            String description = this.defaultDescription;
            String status = this.defaultStatus;
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
                        case -1618432855:
                            if (_name.equals("identifier")) {
                                obj = null;
                                break;
                            }
                            break;
                        case -892481550:
                            if (_name.equals("status")) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            identifier = (String) this.identifierAdapter.read(jsonReader);
                            break;
                        case 1:
                            description = (String) this.descriptionAdapter.read(jsonReader);
                            break;
                        case 2:
                            status = (String) this.statusAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Requirement(identifier, description, status);
        }
    }

    AutoValue_Requirement(String identifier, String description, String status) {
        super(identifier, description, status);
    }
}
