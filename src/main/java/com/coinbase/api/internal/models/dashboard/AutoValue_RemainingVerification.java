package com.coinbase.api.internal.models.dashboard;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_RemainingVerification extends C$AutoValue_RemainingVerification {

    public static final class GsonTypeAdapter extends TypeAdapter<RemainingVerification> {
        private boolean defaultRequired = false;
        private String defaultStatus = null;
        private String defaultStep = null;
        private final TypeAdapter<Boolean> requiredAdapter;
        private final TypeAdapter<String> statusAdapter;
        private final TypeAdapter<String> stepAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.stepAdapter = gson.getAdapter(String.class);
            this.statusAdapter = gson.getAdapter(String.class);
            this.requiredAdapter = gson.getAdapter(Boolean.class);
        }

        public GsonTypeAdapter setDefaultStep(String defaultStep) {
            this.defaultStep = defaultStep;
            return this;
        }

        public GsonTypeAdapter setDefaultStatus(String defaultStatus) {
            this.defaultStatus = defaultStatus;
            return this;
        }

        public GsonTypeAdapter setDefaultRequired(boolean defaultRequired) {
            this.defaultRequired = defaultRequired;
            return this;
        }

        public void write(JsonWriter jsonWriter, RemainingVerification object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("step");
            this.stepAdapter.write(jsonWriter, object.getStep());
            jsonWriter.name("status");
            this.statusAdapter.write(jsonWriter, object.getStatus());
            jsonWriter.name("required");
            this.requiredAdapter.write(jsonWriter, Boolean.valueOf(object.isRequired()));
            jsonWriter.endObject();
        }

        public RemainingVerification read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String step = this.defaultStep;
            String status = this.defaultStatus;
            boolean required = this.defaultRequired;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -892481550:
                            if (_name.equals("status")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -393139297:
                            if (_name.equals("required")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 3540684:
                            if (_name.equals("step")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            step = (String) this.stepAdapter.read(jsonReader);
                            break;
                        case 1:
                            status = (String) this.statusAdapter.read(jsonReader);
                            break;
                        case 2:
                            required = ((Boolean) this.requiredAdapter.read(jsonReader)).booleanValue();
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_RemainingVerification(step, status, required);
        }
    }

    AutoValue_RemainingVerification(String step, String status, boolean required) {
        super(step, status, required);
    }
}
