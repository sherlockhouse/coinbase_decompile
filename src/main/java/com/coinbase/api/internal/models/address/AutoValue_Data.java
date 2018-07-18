package com.coinbase.api.internal.models.address;

import com.coinbase.android.db.TransactionORM;
import com.coinbase.api.internal.ApiConstants;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Data extends C$AutoValue_Data {

    public static final class GsonTypeAdapter extends TypeAdapter<Data> {
        private final TypeAdapter<String> cityAdapter;
        private final TypeAdapter<Country> countryAdapter;
        private String defaultCity = null;
        private Country defaultCountry = null;
        private String defaultId = null;
        private String defaultLine1 = null;
        private Object defaultLine2 = null;
        private Object defaultLine3 = null;
        private String defaultPostalCode = null;
        private String defaultResource = null;
        private String defaultResourcePath = null;
        private String defaultState = null;
        private final TypeAdapter<String> idAdapter;
        private final TypeAdapter<String> line1Adapter;
        private final TypeAdapter<Object> line2Adapter;
        private final TypeAdapter<Object> line3Adapter;
        private final TypeAdapter<String> postalCodeAdapter;
        private final TypeAdapter<String> resourceAdapter;
        private final TypeAdapter<String> resourcePathAdapter;
        private final TypeAdapter<String> stateAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.idAdapter = gson.getAdapter(String.class);
            this.line1Adapter = gson.getAdapter(String.class);
            this.line2Adapter = gson.getAdapter(Object.class);
            this.line3Adapter = gson.getAdapter(Object.class);
            this.cityAdapter = gson.getAdapter(String.class);
            this.stateAdapter = gson.getAdapter(String.class);
            this.postalCodeAdapter = gson.getAdapter(String.class);
            this.countryAdapter = gson.getAdapter(Country.class);
            this.resourceAdapter = gson.getAdapter(String.class);
            this.resourcePathAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultId(String defaultId) {
            this.defaultId = defaultId;
            return this;
        }

        public GsonTypeAdapter setDefaultLine1(String defaultLine1) {
            this.defaultLine1 = defaultLine1;
            return this;
        }

        public GsonTypeAdapter setDefaultLine2(Object defaultLine2) {
            this.defaultLine2 = defaultLine2;
            return this;
        }

        public GsonTypeAdapter setDefaultLine3(Object defaultLine3) {
            this.defaultLine3 = defaultLine3;
            return this;
        }

        public GsonTypeAdapter setDefaultCity(String defaultCity) {
            this.defaultCity = defaultCity;
            return this;
        }

        public GsonTypeAdapter setDefaultState(String defaultState) {
            this.defaultState = defaultState;
            return this;
        }

        public GsonTypeAdapter setDefaultPostalCode(String defaultPostalCode) {
            this.defaultPostalCode = defaultPostalCode;
            return this;
        }

        public GsonTypeAdapter setDefaultCountry(Country defaultCountry) {
            this.defaultCountry = defaultCountry;
            return this;
        }

        public GsonTypeAdapter setDefaultResource(String defaultResource) {
            this.defaultResource = defaultResource;
            return this;
        }

        public GsonTypeAdapter setDefaultResourcePath(String defaultResourcePath) {
            this.defaultResourcePath = defaultResourcePath;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("id");
            this.idAdapter.write(jsonWriter, object.getId());
            jsonWriter.name(ApiConstants.LINE1);
            this.line1Adapter.write(jsonWriter, object.getLine1());
            jsonWriter.name(ApiConstants.LINE2);
            this.line2Adapter.write(jsonWriter, object.getLine2());
            jsonWriter.name(ApiConstants.LINE3);
            this.line3Adapter.write(jsonWriter, object.getLine3());
            jsonWriter.name(ApiConstants.CITY);
            this.cityAdapter.write(jsonWriter, object.getCity());
            jsonWriter.name("state");
            this.stateAdapter.write(jsonWriter, object.getState());
            jsonWriter.name(ApiConstants.POSTAL_CODE);
            this.postalCodeAdapter.write(jsonWriter, object.getPostalCode());
            jsonWriter.name("country");
            this.countryAdapter.write(jsonWriter, object.getCountry());
            jsonWriter.name(TransactionORM.COLUMN_RESOURCE);
            this.resourceAdapter.write(jsonWriter, object.getResource());
            jsonWriter.name("resource_path");
            this.resourcePathAdapter.write(jsonWriter, object.getResourcePath());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String id = this.defaultId;
            String line1 = this.defaultLine1;
            Object line2 = this.defaultLine2;
            Object line3 = this.defaultLine3;
            String city = this.defaultCity;
            String state = this.defaultState;
            String postalCode = this.defaultPostalCode;
            Country country = this.defaultCountry;
            String resource = this.defaultResource;
            String resourcePath = this.defaultResourcePath;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -2053263135:
                            if (_name.equals(ApiConstants.POSTAL_CODE)) {
                                obj = 6;
                                break;
                            }
                            break;
                        case -341064690:
                            if (_name.equals(TransactionORM.COLUMN_RESOURCE)) {
                                obj = 8;
                                break;
                            }
                            break;
                        case 3355:
                            if (_name.equals("id")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 3053931:
                            if (_name.equals(ApiConstants.CITY)) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 102977213:
                            if (_name.equals(ApiConstants.LINE1)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 102977214:
                            if (_name.equals(ApiConstants.LINE2)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 102977215:
                            if (_name.equals(ApiConstants.LINE3)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 109757585:
                            if (_name.equals("state")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 957831062:
                            if (_name.equals("country")) {
                                obj = 7;
                                break;
                            }
                            break;
                        case 979481014:
                            if (_name.equals("resource_path")) {
                                obj = 9;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            id = (String) this.idAdapter.read(jsonReader);
                            break;
                        case 1:
                            line1 = (String) this.line1Adapter.read(jsonReader);
                            break;
                        case 2:
                            line2 = this.line2Adapter.read(jsonReader);
                            break;
                        case 3:
                            line3 = this.line3Adapter.read(jsonReader);
                            break;
                        case 4:
                            city = (String) this.cityAdapter.read(jsonReader);
                            break;
                        case 5:
                            state = (String) this.stateAdapter.read(jsonReader);
                            break;
                        case 6:
                            postalCode = (String) this.postalCodeAdapter.read(jsonReader);
                            break;
                        case 7:
                            country = (Country) this.countryAdapter.read(jsonReader);
                            break;
                        case 8:
                            resource = (String) this.resourceAdapter.read(jsonReader);
                            break;
                        case 9:
                            resourcePath = (String) this.resourcePathAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(id, line1, line2, line3, city, state, postalCode, country, resource, resourcePath);
        }
    }

    AutoValue_Data(String id, String line1, Object line2, Object line3, String city, String state, String postalCode, Country country, String resource, String resourcePath) {
        super(id, line1, line2, line3, city, state, postalCode, country, resource, resourcePath);
    }
}
