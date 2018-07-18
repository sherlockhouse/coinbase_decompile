package com.coinbase.api.internal.models.policyRestrictions;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Url extends C$AutoValue_Url {

    public static final class GsonTypeAdapter extends TypeAdapter<Url> {
        private String defaultLink = null;
        private String defaultText = null;
        private final TypeAdapter<String> linkAdapter;
        private final TypeAdapter<String> textAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.linkAdapter = gson.getAdapter(String.class);
            this.textAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultLink(String defaultLink) {
            this.defaultLink = defaultLink;
            return this;
        }

        public GsonTypeAdapter setDefaultText(String defaultText) {
            this.defaultText = defaultText;
            return this;
        }

        public void write(JsonWriter jsonWriter, Url object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("link");
            this.linkAdapter.write(jsonWriter, object.getLink());
            jsonWriter.name("text");
            this.textAdapter.write(jsonWriter, object.getText());
            jsonWriter.endObject();
        }

        public Url read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String link = this.defaultLink;
            String text = this.defaultText;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 3321850:
                            if (_name.equals("link")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 3556653:
                            if (_name.equals("text")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            link = (String) this.linkAdapter.read(jsonReader);
                            break;
                        case 1:
                            text = (String) this.textAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Url(link, text);
        }
    }

    AutoValue_Url(String link, String text) {
        super(link, text);
    }
}
