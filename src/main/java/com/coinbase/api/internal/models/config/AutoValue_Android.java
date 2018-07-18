package com.coinbase.api.internal.models.config;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Android extends C$AutoValue_Android {

    public static final class GsonTypeAdapter extends TypeAdapter<Android> {
        private Message defaultMessage = null;
        private Message defaultVersion = null;
        private final TypeAdapter<Message> messageAdapter;
        private final TypeAdapter<Message> versionAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.versionAdapter = gson.getAdapter(Message.class);
            this.messageAdapter = gson.getAdapter(Message.class);
        }

        public GsonTypeAdapter setDefaultVersion(Message defaultVersion) {
            this.defaultVersion = defaultVersion;
            return this;
        }

        public GsonTypeAdapter setDefaultMessage(Message defaultMessage) {
            this.defaultMessage = defaultMessage;
            return this;
        }

        public void write(JsonWriter jsonWriter, Android object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("version");
            this.versionAdapter.write(jsonWriter, object.getVersion());
            jsonWriter.name("message");
            this.messageAdapter.write(jsonWriter, object.getMessage());
            jsonWriter.endObject();
        }

        public Android read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Message version = this.defaultVersion;
            Message message = this.defaultMessage;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 351608024:
                            if (_name.equals("version")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 954925063:
                            if (_name.equals("message")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            version = (Message) this.versionAdapter.read(jsonReader);
                            break;
                        case 1:
                            message = (Message) this.messageAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Android(version, message);
        }
    }

    AutoValue_Android(Message version, Message message) {
        super(version, message);
    }
}
