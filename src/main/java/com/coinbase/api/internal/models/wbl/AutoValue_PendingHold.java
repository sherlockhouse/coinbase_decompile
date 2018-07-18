package com.coinbase.api.internal.models.wbl;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_PendingHold extends C$AutoValue_PendingHold {

    public static final class GsonTypeAdapter extends TypeAdapter<PendingHold> {
        private final TypeAdapter<Amount> amountAdapter;
        private final TypeAdapter<String> availableInAdapter;
        private final TypeAdapter<String> dateAdapter;
        private Amount defaultAmount = null;
        private String defaultAvailableIn = null;
        private String defaultDate = null;

        public GsonTypeAdapter(Gson gson) {
            this.dateAdapter = gson.getAdapter(String.class);
            this.amountAdapter = gson.getAdapter(Amount.class);
            this.availableInAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultDate(String defaultDate) {
            this.defaultDate = defaultDate;
            return this;
        }

        public GsonTypeAdapter setDefaultAmount(Amount defaultAmount) {
            this.defaultAmount = defaultAmount;
            return this;
        }

        public GsonTypeAdapter setDefaultAvailableIn(String defaultAvailableIn) {
            this.defaultAvailableIn = defaultAvailableIn;
            return this;
        }

        public void write(JsonWriter jsonWriter, PendingHold object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("date");
            this.dateAdapter.write(jsonWriter, object.getDate());
            jsonWriter.name("amount");
            this.amountAdapter.write(jsonWriter, object.getAmount());
            jsonWriter.name("available_in");
            this.availableInAdapter.write(jsonWriter, object.getAvailableIn());
            jsonWriter.endObject();
        }

        public PendingHold read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String date = this.defaultDate;
            Amount amount = this.defaultAmount;
            String availableIn = this.defaultAvailableIn;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1413853096:
                            if (_name.equals("amount")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 3076014:
                            if (_name.equals("date")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 2000094811:
                            if (_name.equals("available_in")) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            date = (String) this.dateAdapter.read(jsonReader);
                            break;
                        case 1:
                            amount = (Amount) this.amountAdapter.read(jsonReader);
                            break;
                        case 2:
                            availableIn = (String) this.availableInAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_PendingHold(date, amount, availableIn);
        }
    }

    AutoValue_PendingHold(String date, Amount amount, String availableIn) {
        super(date, amount, availableIn);
    }
}
