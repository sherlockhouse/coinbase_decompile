package com.coinbase.api.internal.models.currency;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Data extends C$AutoValue_Data {

    public static final class GsonTypeAdapter extends TypeAdapter<Data> {
        private final TypeAdapter<String> addressRegexAdapter;
        private final TypeAdapter<Boolean> canBuyAdapter;
        private final TypeAdapter<Boolean> canSellAdapter;
        private final TypeAdapter<String> codeAdapter;
        private final TypeAdapter<String> colorAdapter;
        private String defaultAddressRegex = null;
        private boolean defaultCanBuy = false;
        private boolean defaultCanSell = false;
        private String defaultCode = null;
        private String defaultColor = null;
        private int defaultExponent = 0;
        private Image defaultImage = null;
        private String defaultName = null;
        private boolean defaultPriceAlertsEnabled = false;
        private String defaultUriScheme = null;
        private final TypeAdapter<Integer> exponentAdapter;
        private final TypeAdapter<Image> imageAdapter;
        private final TypeAdapter<String> nameAdapter;
        private final TypeAdapter<Boolean> priceAlertsEnabledAdapter;
        private final TypeAdapter<String> uriSchemeAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.codeAdapter = gson.getAdapter(String.class);
            this.nameAdapter = gson.getAdapter(String.class);
            this.colorAdapter = gson.getAdapter(String.class);
            this.exponentAdapter = gson.getAdapter(Integer.class);
            this.addressRegexAdapter = gson.getAdapter(String.class);
            this.uriSchemeAdapter = gson.getAdapter(String.class);
            this.canBuyAdapter = gson.getAdapter(Boolean.class);
            this.canSellAdapter = gson.getAdapter(Boolean.class);
            this.priceAlertsEnabledAdapter = gson.getAdapter(Boolean.class);
            this.imageAdapter = gson.getAdapter(Image.class);
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

        public GsonTypeAdapter setDefaultExponent(int defaultExponent) {
            this.defaultExponent = defaultExponent;
            return this;
        }

        public GsonTypeAdapter setDefaultAddressRegex(String defaultAddressRegex) {
            this.defaultAddressRegex = defaultAddressRegex;
            return this;
        }

        public GsonTypeAdapter setDefaultUriScheme(String defaultUriScheme) {
            this.defaultUriScheme = defaultUriScheme;
            return this;
        }

        public GsonTypeAdapter setDefaultCanBuy(boolean defaultCanBuy) {
            this.defaultCanBuy = defaultCanBuy;
            return this;
        }

        public GsonTypeAdapter setDefaultCanSell(boolean defaultCanSell) {
            this.defaultCanSell = defaultCanSell;
            return this;
        }

        public GsonTypeAdapter setDefaultPriceAlertsEnabled(boolean defaultPriceAlertsEnabled) {
            this.defaultPriceAlertsEnabled = defaultPriceAlertsEnabled;
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
            jsonWriter.name("code");
            this.codeAdapter.write(jsonWriter, object.getCode());
            jsonWriter.name("name");
            this.nameAdapter.write(jsonWriter, object.getName());
            jsonWriter.name("color");
            this.colorAdapter.write(jsonWriter, object.getColor());
            jsonWriter.name("exponent");
            this.exponentAdapter.write(jsonWriter, Integer.valueOf(object.getExponent()));
            jsonWriter.name("address_regex");
            this.addressRegexAdapter.write(jsonWriter, object.getAddressRegex());
            jsonWriter.name("uri_scheme");
            this.uriSchemeAdapter.write(jsonWriter, object.getUriScheme());
            jsonWriter.name("can_buy");
            this.canBuyAdapter.write(jsonWriter, Boolean.valueOf(object.getCanBuy()));
            jsonWriter.name("can_sell");
            this.canSellAdapter.write(jsonWriter, Boolean.valueOf(object.getCanSell()));
            jsonWriter.name("price_alerts_enabled");
            this.priceAlertsEnabledAdapter.write(jsonWriter, Boolean.valueOf(object.getPriceAlertsEnabled()));
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
            String code = this.defaultCode;
            String name = this.defaultName;
            String color = this.defaultColor;
            int exponent = this.defaultExponent;
            String addressRegex = this.defaultAddressRegex;
            String uriScheme = this.defaultUriScheme;
            boolean canBuy = this.defaultCanBuy;
            boolean canSell = this.defaultCanSell;
            boolean priceAlertsEnabled = this.defaultPriceAlertsEnabled;
            Image image = this.defaultImage;
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
                        case -1296647208:
                            if (_name.equals("uri_scheme")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case -398841796:
                            if (_name.equals("address_regex")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -126457247:
                            if (_name.equals("can_sell")) {
                                obj = 7;
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
                        case 94842723:
                            if (_name.equals("color")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 100313435:
                            if (_name.equals("image")) {
                                obj = 9;
                                break;
                            }
                            break;
                        case 420425551:
                            if (_name.equals("price_alerts_enabled")) {
                                obj = 8;
                                break;
                            }
                            break;
                        case 550094231:
                            if (_name.equals("can_buy")) {
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
                            exponent = ((Integer) this.exponentAdapter.read(jsonReader)).intValue();
                            break;
                        case 4:
                            addressRegex = (String) this.addressRegexAdapter.read(jsonReader);
                            break;
                        case 5:
                            uriScheme = (String) this.uriSchemeAdapter.read(jsonReader);
                            break;
                        case 6:
                            canBuy = ((Boolean) this.canBuyAdapter.read(jsonReader)).booleanValue();
                            break;
                        case 7:
                            canSell = ((Boolean) this.canSellAdapter.read(jsonReader)).booleanValue();
                            break;
                        case 8:
                            priceAlertsEnabled = ((Boolean) this.priceAlertsEnabledAdapter.read(jsonReader)).booleanValue();
                            break;
                        case 9:
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
            return new AutoValue_Data(code, name, color, exponent, addressRegex, uriScheme, canBuy, canSell, priceAlertsEnabled, image);
        }
    }

    AutoValue_Data(String code, String name, String color, int exponent, String addressRegex, String uriScheme, boolean canBuy, boolean canSell, boolean priceAlertsEnabled, Image image) {
        super(code, name, color, exponent, addressRegex, uriScheme, canBuy, canSell, priceAlertsEnabled, image);
    }
}
