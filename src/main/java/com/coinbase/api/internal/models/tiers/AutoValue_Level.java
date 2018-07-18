package com.coinbase.api.internal.models.tiers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

final class AutoValue_Level extends C$AutoValue_Level {

    public static final class GsonTypeAdapter extends TypeAdapter<Level> {
        private String defaultDescription = null;
        private String defaultName = null;
        private List<Requirement> defaultRequirements = null;
        private Status defaultStatus = null;
        private final TypeAdapter<String> descriptionAdapter;
        private final TypeAdapter<String> nameAdapter;
        private final TypeAdapter<List<Requirement>> requirementsAdapter;
        private final TypeAdapter<Status> statusAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.nameAdapter = gson.getAdapter(String.class);
            this.descriptionAdapter = gson.getAdapter(String.class);
            this.requirementsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, Requirement.class));
            this.statusAdapter = gson.getAdapter(Status.class);
        }

        public GsonTypeAdapter setDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public GsonTypeAdapter setDefaultDescription(String defaultDescription) {
            this.defaultDescription = defaultDescription;
            return this;
        }

        public GsonTypeAdapter setDefaultRequirements(List<Requirement> defaultRequirements) {
            this.defaultRequirements = defaultRequirements;
            return this;
        }

        public GsonTypeAdapter setDefaultStatus(Status defaultStatus) {
            this.defaultStatus = defaultStatus;
            return this;
        }

        public void write(JsonWriter jsonWriter, Level object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("name");
            this.nameAdapter.write(jsonWriter, object.getName());
            jsonWriter.name("description");
            this.descriptionAdapter.write(jsonWriter, object.getDescription());
            jsonWriter.name("requirements");
            this.requirementsAdapter.write(jsonWriter, object.getRequirements());
            jsonWriter.name("status");
            this.statusAdapter.write(jsonWriter, object.getStatus());
            jsonWriter.endObject();
        }

        public Level read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String name = this.defaultName;
            String description = this.defaultDescription;
            List<Requirement> requirements = this.defaultRequirements;
            Status status = this.defaultStatus;
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
                        case -1619874672:
                            if (_name.equals("requirements")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -892481550:
                            if (_name.equals("status")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3373707:
                            if (_name.equals("name")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            name = (String) this.nameAdapter.read(jsonReader);
                            break;
                        case 1:
                            description = (String) this.descriptionAdapter.read(jsonReader);
                            break;
                        case 2:
                            requirements = (List) this.requirementsAdapter.read(jsonReader);
                            break;
                        case 3:
                            status = (Status) this.statusAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Level(name, description, requirements, status);
        }
    }

    AutoValue_Level(String name, String description, List<Requirement> requirements, Status status) {
        super(name, description, requirements, status);
    }
}
