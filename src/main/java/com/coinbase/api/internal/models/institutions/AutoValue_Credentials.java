package com.coinbase.api.internal.models.institutions;

import com.coinbase.api.internal.ApiConstants;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Credentials extends C$AutoValue_Credentials {

    public static final class GsonTypeAdapter extends TypeAdapter<Credentials> {
        private String defaultPassword = null;
        private String defaultPin = null;
        private String defaultUsername = null;
        private final TypeAdapter<String> passwordAdapter;
        private final TypeAdapter<String> pinAdapter;
        private final TypeAdapter<String> usernameAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.usernameAdapter = gson.getAdapter(String.class);
            this.passwordAdapter = gson.getAdapter(String.class);
            this.pinAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultUsername(String defaultUsername) {
            this.defaultUsername = defaultUsername;
            return this;
        }

        public GsonTypeAdapter setDefaultPassword(String defaultPassword) {
            this.defaultPassword = defaultPassword;
            return this;
        }

        public GsonTypeAdapter setDefaultPin(String defaultPin) {
            this.defaultPin = defaultPin;
            return this;
        }

        public void write(JsonWriter jsonWriter, Credentials object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(ApiConstants.USERNAME);
            this.usernameAdapter.write(jsonWriter, object.getUsername());
            jsonWriter.name("password");
            this.passwordAdapter.write(jsonWriter, object.getPassword());
            jsonWriter.name(ApiConstants.PIN);
            this.pinAdapter.write(jsonWriter, object.getPin());
            jsonWriter.endObject();
        }

        public Credentials read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String username = this.defaultUsername;
            String password = this.defaultPassword;
            String pin = this.defaultPin;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -265713450:
                            if (_name.equals(ApiConstants.USERNAME)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 110997:
                            if (_name.equals(ApiConstants.PIN)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 1216985755:
                            if (_name.equals("password")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            username = (String) this.usernameAdapter.read(jsonReader);
                            break;
                        case 1:
                            password = (String) this.passwordAdapter.read(jsonReader);
                            break;
                        case 2:
                            pin = (String) this.pinAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Credentials(username, password, pin);
        }
    }

    AutoValue_Credentials(String username, String password, String pin) {
        super(username, password, pin);
    }
}
