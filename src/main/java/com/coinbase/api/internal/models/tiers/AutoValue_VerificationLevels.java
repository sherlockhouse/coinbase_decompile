package com.coinbase.api.internal.models.tiers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

final class AutoValue_VerificationLevels extends C$AutoValue_VerificationLevels {

    public static final class GsonTypeAdapter extends TypeAdapter<VerificationLevels> {
        private List<Level> defaultLevels = null;
        private final TypeAdapter<List<Level>> levelsAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.levelsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, Level.class));
        }

        public GsonTypeAdapter setDefaultLevels(List<Level> defaultLevels) {
            this.defaultLevels = defaultLevels;
            return this;
        }

        public void write(JsonWriter jsonWriter, VerificationLevels object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("levels");
            this.levelsAdapter.write(jsonWriter, object.getLevels());
            jsonWriter.endObject();
        }

        public VerificationLevels read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            List<Level> levels = this.defaultLevels;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1106127505:
                            if (_name.equals("levels")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            levels = (List) this.levelsAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_VerificationLevels(levels);
        }
    }

    AutoValue_VerificationLevels(List<Level> levels) {
        super(levels);
    }
}
