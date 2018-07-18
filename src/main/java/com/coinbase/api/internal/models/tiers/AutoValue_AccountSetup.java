package com.coinbase.api.internal.models.tiers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_AccountSetup extends C$AutoValue_AccountSetup {

    public static final class GsonTypeAdapter extends TypeAdapter<AccountSetup> {
        private final TypeAdapter<String> buttonTextAdapter;
        private String defaultButtonText = null;
        private String defaultDescription = null;
        private String defaultTitle = null;
        private final TypeAdapter<String> descriptionAdapter;
        private final TypeAdapter<String> titleAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.titleAdapter = gson.getAdapter(String.class);
            this.descriptionAdapter = gson.getAdapter(String.class);
            this.buttonTextAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultTitle(String defaultTitle) {
            this.defaultTitle = defaultTitle;
            return this;
        }

        public GsonTypeAdapter setDefaultDescription(String defaultDescription) {
            this.defaultDescription = defaultDescription;
            return this;
        }

        public GsonTypeAdapter setDefaultButtonText(String defaultButtonText) {
            this.defaultButtonText = defaultButtonText;
            return this;
        }

        public void write(JsonWriter jsonWriter, AccountSetup object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("title");
            this.titleAdapter.write(jsonWriter, object.getTitle());
            jsonWriter.name("description");
            this.descriptionAdapter.write(jsonWriter, object.getDescription());
            jsonWriter.name("button_text");
            this.buttonTextAdapter.write(jsonWriter, object.getButtonText());
            jsonWriter.endObject();
        }

        public AccountSetup read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String title = this.defaultTitle;
            String description = this.defaultDescription;
            String buttonText = this.defaultButtonText;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1759410662:
                            if (_name.equals("button_text")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -1724546052:
                            if (_name.equals("description")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 110371416:
                            if (_name.equals("title")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            title = (String) this.titleAdapter.read(jsonReader);
                            break;
                        case 1:
                            description = (String) this.descriptionAdapter.read(jsonReader);
                            break;
                        case 2:
                            buttonText = (String) this.buttonTextAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_AccountSetup(title, description, buttonText);
        }
    }

    AutoValue_AccountSetup(String title, String description, String buttonText) {
        super(title, description, buttonText);
    }
}
