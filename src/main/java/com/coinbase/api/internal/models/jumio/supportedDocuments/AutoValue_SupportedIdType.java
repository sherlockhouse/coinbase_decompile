package com.coinbase.api.internal.models.jumio.supportedDocuments;

import com.coinbase.api.internal.models.jumio.JumioProfiles.Type;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_SupportedIdType extends C$AutoValue_SupportedIdType {

    public static final class GsonTypeAdapter extends TypeAdapter<SupportedIdType> {
        private final TypeAdapter<Boolean> backsideImageRequiredAdapter;
        private Boolean defaultBacksideImageRequired = null;
        private Type defaultType = null;
        private final TypeAdapter<Type> typeAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.typeAdapter = gson.getAdapter(Type.class);
            this.backsideImageRequiredAdapter = gson.getAdapter(Boolean.class);
        }

        public GsonTypeAdapter setDefaultType(Type defaultType) {
            this.defaultType = defaultType;
            return this;
        }

        public GsonTypeAdapter setDefaultBacksideImageRequired(Boolean defaultBacksideImageRequired) {
            this.defaultBacksideImageRequired = defaultBacksideImageRequired;
            return this;
        }

        public void write(JsonWriter jsonWriter, SupportedIdType object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("type");
            this.typeAdapter.write(jsonWriter, object.getType());
            jsonWriter.name("backside_image_required");
            this.backsideImageRequiredAdapter.write(jsonWriter, object.getBacksideImageRequired());
            jsonWriter.endObject();
        }

        public SupportedIdType read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Type type = this.defaultType;
            Boolean backsideImageRequired = this.defaultBacksideImageRequired;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 3575610:
                            if (_name.equals("type")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 1059868068:
                            if (_name.equals("backside_image_required")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            type = (Type) this.typeAdapter.read(jsonReader);
                            break;
                        case 1:
                            backsideImageRequired = (Boolean) this.backsideImageRequiredAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_SupportedIdType(type, backsideImageRequired);
        }
    }

    AutoValue_SupportedIdType(Type type, Boolean backsideImageRequired) {
        super(type, backsideImageRequired);
    }
}
