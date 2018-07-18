package com.coinbase.api.internal.models.idology;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

final class AutoValue_Question extends C$AutoValue_Question {

    public static final class GsonTypeAdapter extends TypeAdapter<Question> {
        private final TypeAdapter<List<String>> answersAdapter;
        private List<String> defaultAnswers = null;
        private String defaultPrompt = null;
        private String defaultType = null;
        private final TypeAdapter<String> promptAdapter;
        private final TypeAdapter<String> typeAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.typeAdapter = gson.getAdapter(String.class);
            this.promptAdapter = gson.getAdapter(String.class);
            this.answersAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, String.class));
        }

        public GsonTypeAdapter setDefaultType(String defaultType) {
            this.defaultType = defaultType;
            return this;
        }

        public GsonTypeAdapter setDefaultPrompt(String defaultPrompt) {
            this.defaultPrompt = defaultPrompt;
            return this;
        }

        public GsonTypeAdapter setDefaultAnswers(List<String> defaultAnswers) {
            this.defaultAnswers = defaultAnswers;
            return this;
        }

        public void write(JsonWriter jsonWriter, Question object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("type");
            this.typeAdapter.write(jsonWriter, object.getType());
            jsonWriter.name("prompt");
            this.promptAdapter.write(jsonWriter, object.getPrompt());
            jsonWriter.name("answers");
            this.answersAdapter.write(jsonWriter, object.getAnswers());
            jsonWriter.endObject();
        }

        public Question read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String type = this.defaultType;
            String prompt = this.defaultPrompt;
            List<String> answers = this.defaultAnswers;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -979805852:
                            if (_name.equals("prompt")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -847398795:
                            if (_name.equals("answers")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 3575610:
                            if (_name.equals("type")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            type = (String) this.typeAdapter.read(jsonReader);
                            break;
                        case 1:
                            prompt = (String) this.promptAdapter.read(jsonReader);
                            break;
                        case 2:
                            answers = (List) this.answersAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Question(type, prompt, answers);
        }
    }

    AutoValue_Question(String type, String prompt, List<String> answers) {
        super(type, prompt, answers);
    }
}
