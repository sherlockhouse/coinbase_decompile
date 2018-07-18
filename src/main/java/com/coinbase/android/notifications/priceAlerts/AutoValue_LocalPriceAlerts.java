package com.coinbase.android.notifications.priceAlerts;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

final class AutoValue_LocalPriceAlerts extends C$AutoValue_LocalPriceAlerts {

    public static final class GsonTypeAdapter extends TypeAdapter<LocalPriceAlerts> {
        private List<LocalPriceAlert> defaultPriceAlerts = null;
        private final TypeAdapter<List<LocalPriceAlert>> priceAlertsAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.priceAlertsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, LocalPriceAlert.class));
        }

        public GsonTypeAdapter setDefaultPriceAlerts(List<LocalPriceAlert> defaultPriceAlerts) {
            this.defaultPriceAlerts = defaultPriceAlerts;
            return this;
        }

        public void write(JsonWriter jsonWriter, LocalPriceAlerts object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("price_alerts");
            this.priceAlertsAdapter.write(jsonWriter, object.getPriceAlerts());
            jsonWriter.endObject();
        }

        public LocalPriceAlerts read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            List<LocalPriceAlert> priceAlerts = this.defaultPriceAlerts;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 2098502221:
                            if (_name.equals("price_alerts")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            priceAlerts = (List) this.priceAlertsAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_LocalPriceAlerts(priceAlerts);
        }
    }

    AutoValue_LocalPriceAlerts(List<LocalPriceAlert> priceAlerts) {
        super(priceAlerts);
    }
}
