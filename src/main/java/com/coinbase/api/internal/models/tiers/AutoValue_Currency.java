package com.coinbase.api.internal.models.tiers;

import com.coinbase.api.internal.models.currency.Image;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Currency extends C$AutoValue_Currency {

    public static final class GsonTypeAdapter extends TypeAdapter<Currency> {
        private final TypeAdapter<String> codeAdapter;
        private final TypeAdapter<String> colorAdapter;
        private String defaultCode = null;
        private String defaultColor = null;
        private Integer defaultExponent = null;
        private Image defaultImage = null;
        private String defaultName = null;
        private Boolean defaultPriceAlertsEnabled = null;
        private String defaultType = null;
        private final TypeAdapter<Integer> exponentAdapter;
        private final TypeAdapter<Image> imageAdapter;
        private final TypeAdapter<String> nameAdapter;
        private final TypeAdapter<Boolean> priceAlertsEnabledAdapter;
        private final TypeAdapter<String> typeAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.codeAdapter = gson.getAdapter(String.class);
            this.nameAdapter = gson.getAdapter(String.class);
            this.colorAdapter = gson.getAdapter(String.class);
            this.exponentAdapter = gson.getAdapter(Integer.class);
            this.typeAdapter = gson.getAdapter(String.class);
            this.imageAdapter = gson.getAdapter(Image.class);
            this.priceAlertsEnabledAdapter = gson.getAdapter(Boolean.class);
        }

        public GsonTypeAdapter setDefaultCode(String defaultCode) {
            this.defaultCode = defaultCode;
            return this;
        }

        public GsonTypeAdapter setDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public GsonTypeAdapter setDefaultColor(String defaultColor) {
            this.defaultColor = defaultColor;
            return this;
        }

        public GsonTypeAdapter setDefaultExponent(Integer defaultExponent) {
            this.defaultExponent = defaultExponent;
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

        public GsonTypeAdapter setDefaultPriceAlertsEnabled(Boolean defaultPriceAlertsEnabled) {
            this.defaultPriceAlertsEnabled = defaultPriceAlertsEnabled;
            return this;
        }

        public void write(JsonWriter jsonWriter, Currency object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("code");
            this.codeAdapter.write(jsonWriter, object.getCode());
            jsonWriter.name("name");
            this.nameAdapter.write(jsonWriter, object.getName());
            jsonWriter.name("color");
            this.colorAdapter.write(jsonWriter, object.getColor());
            jsonWriter.name("exponent");
            this.exponentAdapter.write(jsonWriter, object.getExponent());
            jsonWriter.name("type");
            this.typeAdapter.write(jsonWriter, object.getType());
            jsonWriter.name("image");
            this.imageAdapter.write(jsonWriter, object.getImage());
            jsonWriter.name("price_alerts_enabled");
            this.priceAlertsEnabledAdapter.write(jsonWriter, object.getPriceAlertsEnabled());
            jsonWriter.endObject();
        }

        public Currency read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String code = this.defaultCode;
            String name = this.defaultName;
            String color = this.defaultColor;
            Integer exponent = this.defaultExponent;
            String type = this.defaultType;
            Image image = this.defaultImage;
            Boolean priceAlertsEnabled = this.defaultPriceAlertsEnabled;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1926169937:
                            if (_name.equals("exponent")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3059181:
                            if (_name.equals("code")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 3373707:
                            if (_name.equals("name")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 3575610:
                            if (_name.equals("type")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 94842723:
                            if (_name.equals("color")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 100313435:
                            if (_name.equals("image")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 420425551:
                            if (_name.equals("price_alerts_enabled")) {
                                obj = 6;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            code = (String) this.codeAdapter.read(jsonReader);
                            break;
                        case 1:
                            name = (String) this.nameAdapter.read(jsonReader);
                            break;
                        case 2:
                            color = (String) this.colorAdapter.read(jsonReader);
                            break;
                        case 3:
                            exponent = (Integer) this.exponentAdapter.read(jsonReader);
                            break;
                        case 4:
                            type = (String) this.typeAdapter.read(jsonReader);
                            break;
                        case 5:
                            image = (Image) this.imageAdapter.read(jsonReader);
                            break;
                        case 6:
                            priceAlertsEnabled = (Boolean) this.priceAlertsEnabledAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Currency(code, name, color, exponent, type, image, priceAlertsEnabled);
        }
    }

    AutoValue_Currency(String code, String name, String color, Integer exponent, String type, Image image, Boolean priceAlertsEnabled) {
        super(code, name, color, exponent, type, image, priceAlertsEnabled);
    }
}
