package com.coinbase.api.internal.models.phoneNumber;

import com.coinbase.ApiConstants;
import com.coinbase.android.db.TransactionORM;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Data extends C$AutoValue_Data {

    public static final class GsonTypeAdapter extends TypeAdapter<Data> {
        private final TypeAdapter<String> createdAtAdapter;
        private String defaultCreatedAt = null;
        private String defaultId = null;
        private String defaultNumber = null;
        private Boolean defaultPrimary = null;
        private String defaultResource = null;
        private String defaultResourcePath = null;
        private String defaultUpdatedAt = null;
        private Boolean defaultVerified = null;
        private final TypeAdapter<String> idAdapter;
        private final TypeAdapter<String> numberAdapter;
        private final TypeAdapter<Boolean> primaryAdapter;
        private final TypeAdapter<String> resourceAdapter;
        private final TypeAdapter<String> resourcePathAdapter;
        private final TypeAdapter<String> updatedAtAdapter;
        private final TypeAdapter<Boolean> verifiedAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.idAdapter = gson.getAdapter(String.class);
            this.numberAdapter = gson.getAdapter(String.class);
            this.primaryAdapter = gson.getAdapter(Boolean.class);
            this.verifiedAdapter = gson.getAdapter(Boolean.class);
            this.createdAtAdapter = gson.getAdapter(String.class);
            this.updatedAtAdapter = gson.getAdapter(String.class);
            this.resourceAdapter = gson.getAdapter(String.class);
            this.resourcePathAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultId(String defaultId) {
            this.defaultId = defaultId;
            return this;
        }

        public GsonTypeAdapter setDefaultNumber(String defaultNumber) {
            this.defaultNumber = defaultNumber;
            return this;
        }

        public GsonTypeAdapter setDefaultPrimary(Boolean defaultPrimary) {
            this.defaultPrimary = defaultPrimary;
            return this;
        }

        public GsonTypeAdapter setDefaultVerified(Boolean defaultVerified) {
            this.defaultVerified = defaultVerified;
            return this;
        }

        public GsonTypeAdapter setDefaultCreatedAt(String defaultCreatedAt) {
            this.defaultCreatedAt = defaultCreatedAt;
            return this;
        }

        public GsonTypeAdapter setDefaultUpdatedAt(String defaultUpdatedAt) {
            this.defaultUpdatedAt = defaultUpdatedAt;
            return this;
        }

        public GsonTypeAdapter setDefaultResource(String defaultResource) {
            this.defaultResource = defaultResource;
            return this;
        }

        public GsonTypeAdapter setDefaultResourcePath(String defaultResourcePath) {
            this.defaultResourcePath = defaultResourcePath;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("id");
            this.idAdapter.write(jsonWriter, object.getId());
            jsonWriter.name("number");
            this.numberAdapter.write(jsonWriter, object.getNumber());
            jsonWriter.name(ApiConstants.PRIMARY);
            this.primaryAdapter.write(jsonWriter, object.getPrimary());
            jsonWriter.name("verified");
            this.verifiedAdapter.write(jsonWriter, object.getVerified());
            jsonWriter.name(TransactionORM.COLUMN_CREATED_AT);
            this.createdAtAdapter.write(jsonWriter, object.getCreatedAt());
            jsonWriter.name("updated_at");
            this.updatedAtAdapter.write(jsonWriter, object.getUpdatedAt());
            jsonWriter.name(TransactionORM.COLUMN_RESOURCE);
            this.resourceAdapter.write(jsonWriter, object.getResource());
            jsonWriter.name("resource_path");
            this.resourcePathAdapter.write(jsonWriter, object.getResourcePath());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String id = this.defaultId;
            String number = this.defaultNumber;
            Boolean primary = this.defaultPrimary;
            Boolean verified = this.defaultVerified;
            String createdAt = this.defaultCreatedAt;
            String updatedAt = this.defaultUpdatedAt;
            String resource = this.defaultResource;
            String resourcePath = this.defaultResourcePath;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1994383672:
                            if (_name.equals("verified")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case -1034364087:
                            if (_name.equals("number")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -341064690:
                            if (_name.equals(TransactionORM.COLUMN_RESOURCE)) {
                                obj = 6;
                                break;
                            }
                            break;
                        case -314765822:
                            if (_name.equals(ApiConstants.PRIMARY)) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -295464393:
                            if (_name.equals("updated_at")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 3355:
                            if (_name.equals("id")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 979481014:
                            if (_name.equals("resource_path")) {
                                obj = 7;
                                break;
                            }
                            break;
                        case 1369680106:
                            if (_name.equals(TransactionORM.COLUMN_CREATED_AT)) {
                                obj = 4;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            id = (String) this.idAdapter.read(jsonReader);
                            break;
                        case 1:
                            number = (String) this.numberAdapter.read(jsonReader);
                            break;
                        case 2:
                            primary = (Boolean) this.primaryAdapter.read(jsonReader);
                            break;
                        case 3:
                            verified = (Boolean) this.verifiedAdapter.read(jsonReader);
                            break;
                        case 4:
                            createdAt = (String) this.createdAtAdapter.read(jsonReader);
                            break;
                        case 5:
                            updatedAt = (String) this.updatedAtAdapter.read(jsonReader);
                            break;
                        case 6:
                            resource = (String) this.resourceAdapter.read(jsonReader);
                            break;
                        case 7:
                            resourcePath = (String) this.resourcePathAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(id, number, primary, verified, createdAt, updatedAt, resource, resourcePath);
        }
    }

    AutoValue_Data(String id, String number, Boolean primary, Boolean verified, String createdAt, String updatedAt, String resource, String resourcePath) {
        super(id, number, primary, verified, createdAt, updatedAt, resource, resourcePath);
    }
}
