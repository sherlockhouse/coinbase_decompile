package com.coinbase.api.internal.models.policyRestrictions;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_PolicyRestrictions extends C$AutoValue_PolicyRestrictions {

    public static final class GsonTypeAdapter extends TypeAdapter<PolicyRestrictions> {
        private String defaultDescription = null;
        private String defaultError = null;
        private String defaultId = null;
        private String defaultMessage = null;
        private Boolean defaultPending = null;
        private Boolean defaultRequired = null;
        private Url defaultUrl = null;
        private String defaultUserType = null;
        private final TypeAdapter<String> descriptionAdapter;
        private final TypeAdapter<String> errorAdapter;
        private final TypeAdapter<String> idAdapter;
        private final TypeAdapter<String> messageAdapter;
        private final TypeAdapter<Boolean> pendingAdapter;
        private final TypeAdapter<Boolean> requiredAdapter;
        private final TypeAdapter<Url> urlAdapter;
        private final TypeAdapter<String> userTypeAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.idAdapter = gson.getAdapter(String.class);
            this.errorAdapter = gson.getAdapter(String.class);
            this.userTypeAdapter = gson.getAdapter(String.class);
            this.requiredAdapter = gson.getAdapter(Boolean.class);
            this.pendingAdapter = gson.getAdapter(Boolean.class);
            this.messageAdapter = gson.getAdapter(String.class);
            this.descriptionAdapter = gson.getAdapter(String.class);
            this.urlAdapter = gson.getAdapter(Url.class);
        }

        public GsonTypeAdapter setDefaultId(String defaultId) {
            this.defaultId = defaultId;
            return this;
        }

        public GsonTypeAdapter setDefaultError(String defaultError) {
            this.defaultError = defaultError;
            return this;
        }

        public GsonTypeAdapter setDefaultUserType(String defaultUserType) {
            this.defaultUserType = defaultUserType;
            return this;
        }

        public GsonTypeAdapter setDefaultRequired(Boolean defaultRequired) {
            this.defaultRequired = defaultRequired;
            return this;
        }

        public GsonTypeAdapter setDefaultPending(Boolean defaultPending) {
            this.defaultPending = defaultPending;
            return this;
        }

        public GsonTypeAdapter setDefaultMessage(String defaultMessage) {
            this.defaultMessage = defaultMessage;
            return this;
        }

        public GsonTypeAdapter setDefaultDescription(String defaultDescription) {
            this.defaultDescription = defaultDescription;
            return this;
        }

        public GsonTypeAdapter setDefaultUrl(Url defaultUrl) {
            this.defaultUrl = defaultUrl;
            return this;
        }

        public void write(JsonWriter jsonWriter, PolicyRestrictions object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("id");
            this.idAdapter.write(jsonWriter, object.getId());
            jsonWriter.name("error");
            this.errorAdapter.write(jsonWriter, object.getError());
            jsonWriter.name("user_type");
            this.userTypeAdapter.write(jsonWriter, object.getUserType());
            jsonWriter.name("required");
            this.requiredAdapter.write(jsonWriter, object.getRequired());
            jsonWriter.name("pending");
            this.pendingAdapter.write(jsonWriter, object.getPending());
            jsonWriter.name("message");
            this.messageAdapter.write(jsonWriter, object.getMessage());
            jsonWriter.name("description");
            this.descriptionAdapter.write(jsonWriter, object.getDescription());
            jsonWriter.name("url");
            this.urlAdapter.write(jsonWriter, object.getUrl());
            jsonWriter.endObject();
        }

        public PolicyRestrictions read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String id = this.defaultId;
            String error = this.defaultError;
            String userType = this.defaultUserType;
            Boolean required = this.defaultRequired;
            Boolean pending = this.defaultPending;
            String message = this.defaultMessage;
            String description = this.defaultDescription;
            Url url = this.defaultUrl;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1724546052:
                            if (_name.equals("description")) {
                                obj = 6;
                                break;
                            }
                            break;
                        case -682587753:
                            if (_name.equals("pending")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -393139297:
                            if (_name.equals("required")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3355:
                            if (_name.equals("id")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 116079:
                            if (_name.equals("url")) {
                                obj = 7;
                                break;
                            }
                            break;
                        case 96784904:
                            if (_name.equals("error")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 339542830:
                            if (_name.equals("user_type")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 954925063:
                            if (_name.equals("message")) {
                                obj = 5;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            id = (String) this.idAdapter.read(jsonReader);
                            break;
                        case 1:
                            error = (String) this.errorAdapter.read(jsonReader);
                            break;
                        case 2:
                            userType = (String) this.userTypeAdapter.read(jsonReader);
                            break;
                        case 3:
                            required = (Boolean) this.requiredAdapter.read(jsonReader);
                            break;
                        case 4:
                            pending = (Boolean) this.pendingAdapter.read(jsonReader);
                            break;
                        case 5:
                            message = (String) this.messageAdapter.read(jsonReader);
                            break;
                        case 6:
                            description = (String) this.descriptionAdapter.read(jsonReader);
                            break;
                        case 7:
                            url = (Url) this.urlAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_PolicyRestrictions(id, error, userType, required, pending, message, description, url);
        }
    }

    AutoValue_PolicyRestrictions(String id, String error, String userType, Boolean required, Boolean pending, String message, String description, Url url) {
        super(id, error, userType, required, pending, message, description, url);
    }
}
