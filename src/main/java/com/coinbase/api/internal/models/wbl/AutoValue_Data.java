package com.coinbase.api.internal.models.wbl;

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
        private List<PendingHold> defaultPendingHolds = null;
        private final TypeAdapter<List<PendingHold>> pendingHoldsAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.pendingHoldsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, PendingHold.class));
        }

        public GsonTypeAdapter setDefaultPendingHolds(List<PendingHold> defaultPendingHolds) {
            this.defaultPendingHolds = defaultPendingHolds;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("pending_holds");
            this.pendingHoldsAdapter.write(jsonWriter, object.getPendingHolds());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            List<PendingHold> pendingHolds = this.defaultPendingHolds;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 752665324:
                            if (_name.equals("pending_holds")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            pendingHolds = (List) this.pendingHoldsAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(pendingHolds);
        }
    }

    AutoValue_Data(List<PendingHold> pendingHolds) {
        super(pendingHolds);
    }
}
