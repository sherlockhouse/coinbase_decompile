package com.coinbase.api.internal.models.auth;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Auth extends C$AutoValue_Auth {

    public static final class GsonTypeAdapter extends TypeAdapter<Auth> {
        private final TypeAdapter<String> codeAdapter;
        private String defaultCode = null;
        private Boolean defaultSuccess = null;
        private final TypeAdapter<Boolean> successAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.successAdapter = gson.getAdapter(Boolean.class);
            this.codeAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultSuccess(Boolean defaultSuccess) {
            this.defaultSuccess = defaultSuccess;
            return this;
        }

        public GsonTypeAdapter setDefaultCode(String defaultCode) {
            this.defaultCode = defaultCode;
            return this;
        }

        public void write(JsonWriter jsonWriter, Auth object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("success");
            this.successAdapter.write(jsonWriter, object.getSuccess());
            jsonWriter.name("code");
            this.codeAdapter.write(jsonWriter, object.getCode());
            jsonWriter.endObject();
        }

        public Auth read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Boolean success = this.defaultSuccess;
            String code = this.defaultCode;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1867169789:
                            if (_name.equals("success")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 3059181:
                            if (_name.equals("code")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            success = (Boolean) this.successAdapter.read(jsonReader);
                            break;
                        case 1:
                            code = (String) this.codeAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Auth(success, code);
        }
    }

    AutoValue_Auth(Boolean success, String code) {
        super(success, code);
    }
}
