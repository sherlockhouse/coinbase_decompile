package com.coinbase.api.internal.models.institutions;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Image extends C$AutoValue_Image {

    public static final class GsonTypeAdapter extends TypeAdapter<Image> {
        private String defaultPng = null;
        private String defaultSvg = null;
        private final TypeAdapter<String> pngAdapter;
        private final TypeAdapter<String> svgAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.pngAdapter = gson.getAdapter(String.class);
            this.svgAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultPng(String defaultPng) {
            this.defaultPng = defaultPng;
            return this;
        }

        public GsonTypeAdapter setDefaultSvg(String defaultSvg) {
            this.defaultSvg = defaultSvg;
            return this;
        }

        public void write(JsonWriter jsonWriter, Image object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("png");
            this.pngAdapter.write(jsonWriter, object.getPng());
            jsonWriter.name("svg");
            this.svgAdapter.write(jsonWriter, object.getSvg());
            jsonWriter.endObject();
        }

        public Image read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String png = this.defaultPng;
            String svg = this.defaultSvg;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 111145:
                            if (_name.equals("png")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 114276:
                            if (_name.equals("svg")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            png = (String) this.pngAdapter.read(jsonReader);
                            break;
                        case 1:
                            svg = (String) this.svgAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Image(png, svg);
        }
    }

    AutoValue_Image(String png, String svg) {
        super(png, svg);
    }
}
