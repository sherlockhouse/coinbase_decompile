package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.ApiConstants;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Address extends C$AutoValue_Address {

    public static final class GsonTypeAdapter extends TypeAdapter<Address> {
        private final TypeAdapter<String> cityAdapter;
        private final TypeAdapter<Country> countryAdapter;
        private String defaultCity = null;
        private Country defaultCountry = null;
        private String defaultLine1 = null;
        private String defaultLine2 = null;
        private String defaultPostalCode = null;
        private String defaultState = null;
        private final TypeAdapter<String> line1Adapter;
        private final TypeAdapter<String> line2Adapter;
        private final TypeAdapter<String> postalCodeAdapter;
        private final TypeAdapter<String> stateAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.line1Adapter = gson.getAdapter(String.class);
            this.line2Adapter = gson.getAdapter(String.class);
            this.cityAdapter = gson.getAdapter(String.class);
            this.stateAdapter = gson.getAdapter(String.class);
            this.postalCodeAdapter = gson.getAdapter(String.class);
            this.countryAdapter = gson.getAdapter(Country.class);
        }

        public GsonTypeAdapter setDefaultLine1(String defaultLine1) {
            this.defaultLine1 = defaultLine1;
            return this;
        }

        public GsonTypeAdapter setDefaultLine2(String defaultLine2) {
            this.defaultLine2 = defaultLine2;
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

        public void write(JsonWriter jsonWriter, Address object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(ApiConstants.LINE1);
            this.line1Adapter.write(jsonWriter, object.getLine1());
            jsonWriter.name(ApiConstants.LINE2);
            this.line2Adapter.write(jsonWriter, object.getLine2());
            jsonWriter.name(ApiConstants.CITY);
            this.cityAdapter.write(jsonWriter, object.getCity());
            jsonWriter.name("state");
            this.stateAdapter.write(jsonWriter, object.getState());
            jsonWriter.name(ApiConstants.POSTAL_CODE);
            this.postalCodeAdapter.write(jsonWriter, object.getPostalCode());
            jsonWriter.name("country");
            this.countryAdapter.write(jsonWriter, object.getCountry());
            jsonWriter.endObject();
        }

        public Address read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String line1 = this.defaultLine1;
            String line2 = this.defaultLine2;
            String city = this.defaultCity;
            String state = this.defaultState;
            String postalCode = this.defaultPostalCode;
            Country country = this.defaultCountry;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -2053263135:
                            if (_name.equals(ApiConstants.POSTAL_CODE)) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 3053931:
                            if (_name.equals(ApiConstants.CITY)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 102977213:
                            if (_name.equals(ApiConstants.LINE1)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 102977214:
                            if (_name.equals(ApiConstants.LINE2)) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 109757585:
                            if (_name.equals("state")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 957831062:
                            if (_name.equals("country")) {
                                obj = 5;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            line1 = (String) this.line1Adapter.read(jsonReader);
                            break;
                        case 1:
                            line2 = (String) this.line2Adapter.read(jsonReader);
                            break;
                        case 2:
                            city = (String) this.cityAdapter.read(jsonReader);
                            break;
                        case 3:
                            state = (String) this.stateAdapter.read(jsonReader);
                            break;
                        case 4:
                            postalCode = (String) this.postalCodeAdapter.read(jsonReader);
                            break;
                        case 5:
                            country = (Country) this.countryAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Address(line1, line2, city, state, postalCode, country);
        }
    }

    AutoValue_Address(String line1, String line2, String city, String state, String postalCode, Country country) {
        super(line1, line2, city, state, postalCode, country);
    }
}
