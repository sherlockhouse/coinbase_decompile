package com.coinbase.api.internal.models.currency;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Image extends C$AutoValue_Image {

    public static final class GsonTypeAdapter extends TypeAdapter<Image> {
        private String defaultUrl = null;
        private final TypeAdapter<String> urlAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.urlAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultUrl(String defaultUrl) {
            this.defaultUrl = defaultUrl;
            return this;
        }

        public void write(JsonWriter jsonWriter, Image object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("url");
            this.urlAdapter.write(jsonWriter, object.getUrl());
            jsonWriter.endObject();
        }

        public Image read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String url = this.defaultUrl;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 116079:
                            if (_name.equals("url")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            url = (String) this.urlAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Image(url);
        }
    }

    AutoValue_Image(String url) {
        super(url);
    }
}
