package com.coinbase.api.internal.models.availablePaymentMethods;

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
        private final TypeAdapter<List<AvailablePaymentMethod>> availablePaymentMethodsAdapter;
        private final TypeAdapter<String> baseFeesAdapter;
        private List<AvailablePaymentMethod> defaultAvailablePaymentMethods = null;
        private String defaultBaseFees = null;

        public GsonTypeAdapter(Gson gson) {
            this.availablePaymentMethodsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, AvailablePaymentMethod.class));
            this.baseFeesAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultAvailablePaymentMethods(List<AvailablePaymentMethod> defaultAvailablePaymentMethods) {
            this.defaultAvailablePaymentMethods = defaultAvailablePaymentMethods;
            return this;
        }

        public GsonTypeAdapter setDefaultBaseFees(String defaultBaseFees) {
            this.defaultBaseFees = defaultBaseFees;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("available_payment_methods");
            this.availablePaymentMethodsAdapter.write(jsonWriter, object.getAvailablePaymentMethods());
            jsonWriter.name("base_fees");
            this.baseFeesAdapter.write(jsonWriter, object.getBaseFees());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            List<AvailablePaymentMethod> availablePaymentMethods = this.defaultAvailablePaymentMethods;
            String baseFees = this.defaultBaseFees;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1816841637:
                            if (_name.equals("base_fees")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1616309507:
                            if (_name.equals("available_payment_methods")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            availablePaymentMethods = (List) this.availablePaymentMethodsAdapter.read(jsonReader);
                            break;
                        case 1:
                            baseFees = (String) this.baseFeesAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(availablePaymentMethods, baseFees);
        }
    }

    AutoValue_Data(List<AvailablePaymentMethod> availablePaymentMethods, String baseFees) {
        super(availablePaymentMethods, baseFees);
    }
}
