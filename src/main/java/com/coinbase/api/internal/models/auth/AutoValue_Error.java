package com.coinbase.api.internal.models.auth;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Error extends C$AutoValue_Error {

    public static final class GsonTypeAdapter extends TypeAdapter<Error> {
        private final TypeAdapter<String> _2faAuthenticationAdapter;
        private String defaultError = null;
        private Boolean defaultSuccess = null;
        private String default_2faAuthentication = null;
        private final TypeAdapter<String> errorAdapter;
        private final TypeAdapter<Boolean> successAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.successAdapter = gson.getAdapter(Boolean.class);
            this.errorAdapter = gson.getAdapter(String.class);
            this._2faAuthenticationAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultSuccess(Boolean defaultSuccess) {
            this.defaultSuccess = defaultSuccess;
            return this;
        }

        public GsonTypeAdapter setDefaultError(String defaultError) {
            this.defaultError = defaultError;
            return this;
        }

        public GsonTypeAdapter setDefault_2faAuthentication(String default_2faAuthentication) {
            this.default_2faAuthentication = default_2faAuthentication;
            return this;
        }

        public void write(JsonWriter jsonWriter, Error object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("success");
            this.successAdapter.write(jsonWriter, object.getSuccess());
            jsonWriter.name("error");
            this.errorAdapter.write(jsonWriter, object.getError());
            jsonWriter.name("2fa_authentication");
            this._2faAuthenticationAdapter.write(jsonWriter, object.get_2faAuthentication());
            jsonWriter.endObject();
        }

        public Error read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Boolean success = this.defaultSuccess;
            String error = this.defaultError;
            String _2faAuthentication = this.default_2faAuthentication;
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
                        case 96784904:
                            if (_name.equals("error")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1921384586:
                            if (_name.equals("2fa_authentication")) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            success = (Boolean) this.successAdapter.read(jsonReader);
                            break;
                        case 1:
                            error = (String) this.errorAdapter.read(jsonReader);
                            break;
                        case 2:
                            _2faAuthentication = (String) this._2faAuthenticationAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Error(success, error, _2faAuthentication);
        }
    }

    AutoValue_Error(Boolean success, String error, String _2faAuthentication) {
        super(success, error, _2faAuthentication);
    }
}
