package com.coinbase.api.internal.models.idology;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

final class AutoValue_UserAnswers extends C$AutoValue_UserAnswers {

    public static final class GsonTypeAdapter extends TypeAdapter<UserAnswers> {
        private final TypeAdapter<List<Answer>> answersAdapter;
        private List<Answer> defaultAnswers = null;

        public GsonTypeAdapter(Gson gson) {
            this.answersAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, Answer.class));
        }

        public GsonTypeAdapter setDefaultAnswers(List<Answer> defaultAnswers) {
            this.defaultAnswers = defaultAnswers;
            return this;
        }

        public void write(JsonWriter jsonWriter, UserAnswers object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("answers");
            this.answersAdapter.write(jsonWriter, object.getAnswers());
            jsonWriter.endObject();
        }

        public UserAnswers read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            List<Answer> answers = this.defaultAnswers;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -847398795:
                            if (_name.equals("answers")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
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
            return new AutoValue_UserAnswers(answers);
        }
    }

    AutoValue_UserAnswers(List<Answer> answers) {
        super(answers);
    }
}
