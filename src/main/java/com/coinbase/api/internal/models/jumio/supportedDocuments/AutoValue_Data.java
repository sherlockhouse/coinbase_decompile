package com.coinbase.api.internal.models.jumio.supportedDocuments;

import com.coinbase.api.internal.models.Country;
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
        private final TypeAdapter<Country> countryAdapter;
        private Country defaultCountry = null;
        private List<SupportedIdType> defaultSupportedIdTypes = null;
        private final TypeAdapter<List<SupportedIdType>> supportedIdTypesAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.countryAdapter = gson.getAdapter(Country.class);
            this.supportedIdTypesAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, SupportedIdType.class));
        }

        public GsonTypeAdapter setDefaultCountry(Country defaultCountry) {
            this.defaultCountry = defaultCountry;
            return this;
        }

        public GsonTypeAdapter setDefaultSupportedIdTypes(List<SupportedIdType> defaultSupportedIdTypes) {
            this.defaultSupportedIdTypes = defaultSupportedIdTypes;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("country");
            this.countryAdapter.write(jsonWriter, object.getCountry());
            jsonWriter.name("supported_id_types");
            this.supportedIdTypesAdapter.write(jsonWriter, object.getSupportedIdTypes());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Country country = this.defaultCountry;
            List<SupportedIdType> supportedIdTypes = this.defaultSupportedIdTypes;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -347141946:
                            if (_name.equals("supported_id_types")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 957831062:
                            if (_name.equals("country")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            country = (Country) this.countryAdapter.read(jsonReader);
                            break;
                        case 1:
                            supportedIdTypes = (List) this.supportedIdTypesAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(country, supportedIdTypes);
        }
    }

    AutoValue_Data(Country country, List<SupportedIdType> supportedIdTypes) {
        super(country, supportedIdTypes);
    }
}
