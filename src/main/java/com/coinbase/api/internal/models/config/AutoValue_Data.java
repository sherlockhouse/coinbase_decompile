package com.coinbase.api.internal.models.config;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Data extends C$AutoValue_Data {

    public static final class GsonTypeAdapter extends TypeAdapter<Data> {
        private final TypeAdapter<Android> androidAdapter;
        private Android defaultAndroid = null;
        private Ios defaultIos = null;
        private final TypeAdapter<Ios> iosAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.androidAdapter = gson.getAdapter(Android.class);
            this.iosAdapter = gson.getAdapter(Ios.class);
        }

        public GsonTypeAdapter setDefaultAndroid(Android defaultAndroid) {
            this.defaultAndroid = defaultAndroid;
            return this;
        }

        public GsonTypeAdapter setDefaultIos(Ios defaultIos) {
            this.defaultIos = defaultIos;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("android");
            this.androidAdapter.write(jsonWriter, object.getAndroid());
            jsonWriter.name("ios");
            this.iosAdapter.write(jsonWriter, object.getIos());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Android android = this.defaultAndroid;
            Ios ios = this.defaultIos;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -861391249:
                            if (_name.equals("android")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 104461:
                            if (_name.equals("ios")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            android = (Android) this.androidAdapter.read(jsonReader);
                            break;
                        case 1:
                            ios = (Ios) this.iosAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(android, ios);
        }
    }

    AutoValue_Data(Android android, Ios ios) {
        super(android, ios);
    }
}
