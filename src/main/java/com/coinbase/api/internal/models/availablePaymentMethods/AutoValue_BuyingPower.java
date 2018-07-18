package com.coinbase.api.internal.models.availablePaymentMethods;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_BuyingPower extends C$AutoValue_BuyingPower {

    public static final class GsonTypeAdapter extends TypeAdapter<BuyingPower> {
        private String defaultText = null;
        private String defaultType = null;
        private final TypeAdapter<String> textAdapter;
        private final TypeAdapter<String> typeAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.typeAdapter = gson.getAdapter(String.class);
            this.textAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultType(String defaultType) {
            this.defaultType = defaultType;
            return this;
        }

        public GsonTypeAdapter setDefaultText(String defaultText) {
            this.defaultText = defaultText;
            return this;
        }

        public void write(JsonWriter jsonWriter, BuyingPower object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("type");
            this.typeAdapter.write(jsonWriter, object.getType());
            jsonWriter.name("text");
            this.textAdapter.write(jsonWriter, object.getText());
            jsonWriter.endObject();
        }

        public BuyingPower read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String type = this.defaultType;
            String text = this.defaultText;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 3556653:
                            if (_name.equals("text")) {
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
            return new AutoValue_BuyingPower(type, text);
        }
    }

    AutoValue_BuyingPower(String type, String text) {
        super(type, text);
    }
}
