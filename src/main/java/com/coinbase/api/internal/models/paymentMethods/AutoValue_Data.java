package com.coinbase.api.internal.models.paymentMethods;

import com.coinbase.api.internal.ApiConstants;
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
        private List<Mapping> defaultMapping = null;
        private String defaultOneTimeToken = null;
        private String defaultProcessUrl = null;
        private final TypeAdapter<List<Mapping>> mappingAdapter;
        private final TypeAdapter<String> oneTimeTokenAdapter;
        private final TypeAdapter<String> processUrlAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.oneTimeTokenAdapter = gson.getAdapter(String.class);
            this.processUrlAdapter = gson.getAdapter(String.class);
            this.mappingAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, Mapping.class));
        }

        public GsonTypeAdapter setDefaultOneTimeToken(String defaultOneTimeToken) {
            this.defaultOneTimeToken = defaultOneTimeToken;
            return this;
        }

        public GsonTypeAdapter setDefaultProcessUrl(String defaultProcessUrl) {
            this.defaultProcessUrl = defaultProcessUrl;
            return this;
        }

        public GsonTypeAdapter setDefaultMapping(List<Mapping> defaultMapping) {
            this.defaultMapping = defaultMapping;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(ApiConstants.ONE_TIME_TOKEN);
            this.oneTimeTokenAdapter.write(jsonWriter, object.getOneTimeToken());
            jsonWriter.name("process_url");
            this.processUrlAdapter.write(jsonWriter, object.getProcessUrl());
            jsonWriter.name("mapping");
            this.mappingAdapter.write(jsonWriter, object.getMapping());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String oneTimeToken = this.defaultOneTimeToken;
            String processUrl = this.defaultProcessUrl;
            List<Mapping> mapping = this.defaultMapping;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -114479744:
                            if (_name.equals(ApiConstants.ONE_TIME_TOKEN)) {
                                obj = null;
                                break;
                            }
                            break;
                        case 202851231:
                            if (_name.equals("process_url")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 837556430:
                            if (_name.equals("mapping")) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            oneTimeToken = (String) this.oneTimeTokenAdapter.read(jsonReader);
                            break;
                        case 1:
                            processUrl = (String) this.processUrlAdapter.read(jsonReader);
                            break;
                        case 2:
                            mapping = (List) this.mappingAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(oneTimeToken, processUrl, mapping);
        }
    }

    AutoValue_Data(String oneTimeToken, String processUrl, List<Mapping> mapping) {
        super(oneTimeToken, processUrl, mapping);
    }
}
