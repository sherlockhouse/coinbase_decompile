package com.coinbase.api.internal.models.institutions;

import com.coinbase.api.internal.ApiConstants;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

final class AutoValue_Data extends C$AutoValue_Data {

    public static final class GsonTypeAdapter extends TypeAdapter<Data> {
        private final TypeAdapter<Credentials> credentialsAdapter;
        private Credentials defaultCredentials = null;
        private Boolean defaultHasMfa = null;
        private Image defaultImage = null;
        private List<Object> defaultMfa = null;
        private String defaultName = null;
        private String defaultType = null;
        private final TypeAdapter<Boolean> hasMfaAdapter;
        private final TypeAdapter<Image> imageAdapter;
        private final TypeAdapter<List<Object>> mfaAdapter;
        private final TypeAdapter<String> nameAdapter;
        private final TypeAdapter<String> typeAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.credentialsAdapter = gson.getAdapter(Credentials.class);
            this.hasMfaAdapter = gson.getAdapter(Boolean.class);
            this.mfaAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, Object.class));
            this.nameAdapter = gson.getAdapter(String.class);
            this.typeAdapter = gson.getAdapter(String.class);
            this.imageAdapter = gson.getAdapter(Image.class);
        }

        public GsonTypeAdapter setDefaultCredentials(Credentials defaultCredentials) {
            this.defaultCredentials = defaultCredentials;
            return this;
        }

        public GsonTypeAdapter setDefaultHasMfa(Boolean defaultHasMfa) {
            this.defaultHasMfa = defaultHasMfa;
            return this;
        }

        public GsonTypeAdapter setDefaultMfa(List<Object> defaultMfa) {
            this.defaultMfa = defaultMfa;
            return this;
        }

        public GsonTypeAdapter setDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public GsonTypeAdapter setDefaultType(String defaultType) {
            this.defaultType = defaultType;
            return this;
        }

        public GsonTypeAdapter setDefaultImage(Image defaultImage) {
            this.defaultImage = defaultImage;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("credentials");
            this.credentialsAdapter.write(jsonWriter, object.getCredentials());
            jsonWriter.name("has_mfa");
            this.hasMfaAdapter.write(jsonWriter, object.getHasMfa());
            jsonWriter.name(ApiConstants.MFA);
            this.mfaAdapter.write(jsonWriter, object.getMfa());
            jsonWriter.name("name");
            this.nameAdapter.write(jsonWriter, object.getName());
            jsonWriter.name("type");
            this.typeAdapter.write(jsonWriter, object.getType());
            jsonWriter.name("image");
            this.imageAdapter.write(jsonWriter, object.getImage());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Credentials credentials = this.defaultCredentials;
            Boolean hasMfa = this.defaultHasMfa;
            List<Object> mfa = this.defaultMfa;
            String name = this.defaultName;
            String type = this.defaultType;
            Image image = this.defaultImage;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 108008:
                            if (_name.equals(ApiConstants.MFA)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 3373707:
                            if (_name.equals("name")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3575610:
                            if (_name.equals("type")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 100313435:
                            if (_name.equals("image")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 288957180:
                            if (_name.equals("credentials")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 697273027:
                            if (_name.equals("has_mfa")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            credentials = (Credentials) this.credentialsAdapter.read(jsonReader);
                            break;
                        case 1:
                            hasMfa = (Boolean) this.hasMfaAdapter.read(jsonReader);
                            break;
                        case 2:
                            mfa = (List) this.mfaAdapter.read(jsonReader);
                            break;
                        case 3:
                            name = (String) this.nameAdapter.read(jsonReader);
                            break;
                        case 4:
                            type = (String) this.typeAdapter.read(jsonReader);
                            break;
                        case 5:
                            image = (Image) this.imageAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(credentials, hasMfa, mfa, name, type, image);
        }
    }

    AutoValue_Data(Credentials credentials, Boolean hasMfa, List<Object> mfa, String name, String type, Image image) {
        super(credentials, hasMfa, mfa, name, type, image);
    }
}
