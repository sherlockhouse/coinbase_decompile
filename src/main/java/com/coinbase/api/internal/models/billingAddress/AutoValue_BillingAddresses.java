package com.coinbase.api.internal.models.billingAddress;

import com.coinbase.api.internal.models.Pagination;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

final class AutoValue_BillingAddresses extends C$AutoValue_BillingAddresses {

    public static final class GsonTypeAdapter extends TypeAdapter<BillingAddresses> {
        private final TypeAdapter<List<Data>> dataAdapter;
        private List<Data> defaultData = null;
        private Pagination defaultPagination = null;
        private final TypeAdapter<Pagination> paginationAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.paginationAdapter = gson.getAdapter(Pagination.class);
            this.dataAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, Data.class));
        }

        public GsonTypeAdapter setDefaultPagination(Pagination defaultPagination) {
            this.defaultPagination = defaultPagination;
            return this;
        }

        public GsonTypeAdapter setDefaultData(List<Data> defaultData) {
            this.defaultData = defaultData;
            return this;
        }

        public void write(JsonWriter jsonWriter, BillingAddresses object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("pagination");
            this.paginationAdapter.write(jsonWriter, object.getPagination());
            jsonWriter.name("data");
            this.dataAdapter.write(jsonWriter, object.getData());
            jsonWriter.endObject();
        }

        public BillingAddresses read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Pagination pagination = this.defaultPagination;
            List<Data> data = this.defaultData;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case 3076010:
                            if (_name.equals("data")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1297692570:
                            if (_name.equals("pagination")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            pagination = (Pagination) this.paginationAdapter.read(jsonReader);
                            break;
                        case 1:
                            data = (List) this.dataAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_BillingAddresses(pagination, data);
        }
    }

    AutoValue_BillingAddresses(Pagination pagination, List<Data> data) {
        super(pagination, data);
    }
}
