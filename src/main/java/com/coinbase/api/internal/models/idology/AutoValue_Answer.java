package com.coinbase.api.internal.models.idology;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Answer extends C$AutoValue_Answer {

    public static final class GsonTypeAdapter extends TypeAdapter<Answer> {
        private final TypeAdapter<String> answerAdapter;
        private String defaultAnswer = null;
        private String defaultType = null;
        private final TypeAdapter<String> typeAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.typeAdapter = gson.getAdapter(String.class);
            this.answerAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultType(String defaultType) {
            this.defaultType = defaultType;
            return this;
        }

        public GsonTypeAdapter setDefaultAnswer(String defaultAnswer) {
            this.defaultAnswer = defaultAnswer;
            return this;
        }

        public void write(JsonWriter jsonWriter, Answer object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("type");
            this.typeAdapter.write(jsonWriter, object.getType());
            jsonWriter.name("answer");
            this.answerAdapter.write(jsonWriter, object.getAnswer());
            jsonWriter.endObject();
        }

        public Answer read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String type = this.defaultType;
            String answer = this.defaultAnswer;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1412808770:
                            if (_name.equals("answer")) {
                                obj = 1;
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
                            answer = (String) this.answerAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Answer(type, answer);
        }
    }

    AutoValue_Answer(String type, String answer) {
        super(type, answer);
    }
}
