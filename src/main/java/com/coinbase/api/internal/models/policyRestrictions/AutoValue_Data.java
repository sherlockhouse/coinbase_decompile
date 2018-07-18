package com.coinbase.api.internal.models.policyRestrictions;

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
        private List<PolicyRestrictions> defaultPolicyRestrictions = null;
        private final TypeAdapter<List<PolicyRestrictions>> policyRestrictionsAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.policyRestrictionsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, PolicyRestrictions.class));
        }

        public GsonTypeAdapter setDefaultPolicyRestrictions(List<PolicyRestrictions> defaultPolicyRestrictions) {
            this.defaultPolicyRestrictions = defaultPolicyRestrictions;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("policy_restrictions");
            this.policyRestrictionsAdapter.write(jsonWriter, object.getPolicyRestrictions());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            List<PolicyRestrictions> policyRestrictions = this.defaultPolicyRestrictions;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -2111460332:
                            if (_name.equals("policy_restrictions")) {
                                obj = null;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            policyRestrictions = (List) this.policyRestrictionsAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(policyRestrictions);
        }
    }

    AutoValue_Data(List<PolicyRestrictions> policyRestrictions) {
        super(policyRestrictions);
    }
}
