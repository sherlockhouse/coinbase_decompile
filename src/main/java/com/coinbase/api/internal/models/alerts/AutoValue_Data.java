package com.coinbase.api.internal.models.alerts;

import com.coinbase.ApiConstants;
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
        private final TypeAdapter<List<String>> currenciesAdapter;
        private List<String> defaultCurrencies = null;
        private String defaultDescription = null;
        private Boolean defaultDismissable = null;
        private String defaultId = null;
        private String defaultImageUrl = null;
        private List<String> defaultPaymentMethods = null;
        private String defaultTitle = null;
        private String defaultType = null;
        private String defaultUrl = null;
        private List<String> defaultViews = null;
        private final TypeAdapter<String> descriptionAdapter;
        private final TypeAdapter<Boolean> dismissableAdapter;
        private final TypeAdapter<String> idAdapter;
        private final TypeAdapter<String> imageUrlAdapter;
        private final TypeAdapter<List<String>> paymentMethodsAdapter;
        private final TypeAdapter<String> titleAdapter;
        private final TypeAdapter<String> typeAdapter;
        private final TypeAdapter<String> urlAdapter;
        private final TypeAdapter<List<String>> viewsAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.titleAdapter = gson.getAdapter(String.class);
            this.descriptionAdapter = gson.getAdapter(String.class);
            this.urlAdapter = gson.getAdapter(String.class);
            this.imageUrlAdapter = gson.getAdapter(String.class);
            this.typeAdapter = gson.getAdapter(String.class);
            this.viewsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, String.class));
            this.paymentMethodsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, String.class));
            this.currenciesAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, String.class));
            this.dismissableAdapter = gson.getAdapter(Boolean.class);
            this.idAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultTitle(String defaultTitle) {
            this.defaultTitle = defaultTitle;
            return this;
        }

        public GsonTypeAdapter setDefaultDescription(String defaultDescription) {
            this.defaultDescription = defaultDescription;
            return this;
        }

        public GsonTypeAdapter setDefaultUrl(String defaultUrl) {
            this.defaultUrl = defaultUrl;
            return this;
        }

        public GsonTypeAdapter setDefaultImageUrl(String defaultImageUrl) {
            this.defaultImageUrl = defaultImageUrl;
            return this;
        }

        public GsonTypeAdapter setDefaultType(String defaultType) {
            this.defaultType = defaultType;
            return this;
        }

        public GsonTypeAdapter setDefaultViews(List<String> defaultViews) {
            this.defaultViews = defaultViews;
            return this;
        }

        public GsonTypeAdapter setDefaultPaymentMethods(List<String> defaultPaymentMethods) {
            this.defaultPaymentMethods = defaultPaymentMethods;
            return this;
        }

        public GsonTypeAdapter setDefaultCurrencies(List<String> defaultCurrencies) {
            this.defaultCurrencies = defaultCurrencies;
            return this;
        }

        public GsonTypeAdapter setDefaultDismissable(Boolean defaultDismissable) {
            this.defaultDismissable = defaultDismissable;
            return this;
        }

        public GsonTypeAdapter setDefaultId(String defaultId) {
            this.defaultId = defaultId;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("title");
            this.titleAdapter.write(jsonWriter, object.getTitle());
            jsonWriter.name("description");
            this.descriptionAdapter.write(jsonWriter, object.getDescription());
            jsonWriter.name("url");
            this.urlAdapter.write(jsonWriter, object.getUrl());
            jsonWriter.name("image_url");
            this.imageUrlAdapter.write(jsonWriter, object.getImageUrl());
            jsonWriter.name("type");
            this.typeAdapter.write(jsonWriter, object.getType());
            jsonWriter.name("views");
            this.viewsAdapter.write(jsonWriter, object.getViews());
            jsonWriter.name("payment_methods");
            this.paymentMethodsAdapter.write(jsonWriter, object.getPaymentMethods());
            jsonWriter.name(ApiConstants.CURRENCIES);
            this.currenciesAdapter.write(jsonWriter, object.getCurrencies());
            jsonWriter.name("dismissable");
            this.dismissableAdapter.write(jsonWriter, object.getDismissable());
            jsonWriter.name("id");
            this.idAdapter.write(jsonWriter, object.getId());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String title = this.defaultTitle;
            String description = this.defaultDescription;
            String url = this.defaultUrl;
            String imageUrl = this.defaultImageUrl;
            String type = this.defaultType;
            List<String> views = this.defaultViews;
            List<String> paymentMethods = this.defaultPaymentMethods;
            List<String> currencies = this.defaultCurrencies;
            Boolean dismissable = this.defaultDismissable;
            String id = this.defaultId;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1847017863:
                            if (_name.equals("payment_methods")) {
                                obj = 6;
                                break;
                            }
                            break;
                        case -1724546052:
                            if (_name.equals("description")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -1371475228:
                            if (_name.equals("dismissable")) {
                                obj = 8;
                                break;
                            }
                            break;
                        case -1089470353:
                            if (_name.equals(ApiConstants.CURRENCIES)) {
                                obj = 7;
                                break;
                            }
                            break;
                        case -877823861:
                            if (_name.equals("image_url")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3355:
                            if (_name.equals("id")) {
                                obj = 9;
                                break;
                            }
                            break;
                        case 116079:
                            if (_name.equals("url")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 3575610:
                            if (_name.equals("type")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 110371416:
                            if (_name.equals("title")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 112204398:
                            if (_name.equals("views")) {
                                obj = 5;
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
                            url = (String) this.urlAdapter.read(jsonReader);
                            break;
                        case 3:
                            imageUrl = (String) this.imageUrlAdapter.read(jsonReader);
                            break;
                        case 4:
                            type = (String) this.typeAdapter.read(jsonReader);
                            break;
                        case 5:
                            views = (List) this.viewsAdapter.read(jsonReader);
                            break;
                        case 6:
                            paymentMethods = (List) this.paymentMethodsAdapter.read(jsonReader);
                            break;
                        case 7:
                            currencies = (List) this.currenciesAdapter.read(jsonReader);
                            break;
                        case 8:
                            dismissable = (Boolean) this.dismissableAdapter.read(jsonReader);
                            break;
                        case 9:
                            id = (String) this.idAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(title, description, url, imageUrl, type, views, paymentMethods, currencies, dismissable, id);
        }
    }

    AutoValue_Data(String title, String description, String url, String imageUrl, String type, List<String> views, List<String> paymentMethods, List<String> currencies, Boolean dismissable, String id) {
        super(title, description, url, imageUrl, type, views, paymentMethods, currencies, dismissable, id);
    }
}
