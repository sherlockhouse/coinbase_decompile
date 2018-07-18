package com.coinbase.api.internal.models.achSetupSession;

import com.coinbase.api.internal.ApiConstants;
import com.coinbase.api.internal.models.achSetupSession.mfa.Mfa;
import com.coinbase.v2.models.account.Data;
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
        private final TypeAdapter<List<Data>> accountsAdapter;
        private List<Data> defaultAccounts = null;
        private String defaultId = null;
        private String defaultInstitution = null;
        private Mfa defaultMfa = null;
        private String defaultStatus = null;
        private final TypeAdapter<String> idAdapter;
        private final TypeAdapter<String> institutionAdapter;
        private final TypeAdapter<Mfa> mfaAdapter;
        private final TypeAdapter<String> statusAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.idAdapter = gson.getAdapter(String.class);
            this.statusAdapter = gson.getAdapter(String.class);
            this.institutionAdapter = gson.getAdapter(String.class);
            this.accountsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, Data.class));
            this.mfaAdapter = gson.getAdapter(Mfa.class);
        }

        public GsonTypeAdapter setDefaultId(String defaultId) {
            this.defaultId = defaultId;
            return this;
        }

        public GsonTypeAdapter setDefaultStatus(String defaultStatus) {
            this.defaultStatus = defaultStatus;
            return this;
        }

        public GsonTypeAdapter setDefaultInstitution(String defaultInstitution) {
            this.defaultInstitution = defaultInstitution;
            return this;
        }

        public GsonTypeAdapter setDefaultAccounts(List<Data> defaultAccounts) {
            this.defaultAccounts = defaultAccounts;
            return this;
        }

        public GsonTypeAdapter setDefaultMfa(Mfa defaultMfa) {
            this.defaultMfa = defaultMfa;
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
            jsonWriter.name("status");
            this.statusAdapter.write(jsonWriter, object.getStatus());
            jsonWriter.name(ApiConstants.INSTITUTION);
            this.institutionAdapter.write(jsonWriter, object.getInstitution());
            jsonWriter.name("accounts");
            this.accountsAdapter.write(jsonWriter, object.getAccounts());
            jsonWriter.name(ApiConstants.MFA);
            this.mfaAdapter.write(jsonWriter, object.getMfa());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String id = this.defaultId;
            String status = this.defaultStatus;
            String institution = this.defaultInstitution;
            List<Data> accounts = this.defaultAccounts;
            Mfa mfa = this.defaultMfa;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -2137146394:
                            if (_name.equals("accounts")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case -892481550:
                            if (_name.equals("status")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 3355:
                            if (_name.equals("id")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 108008:
                            if (_name.equals(ApiConstants.MFA)) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 891921848:
                            if (_name.equals(ApiConstants.INSTITUTION)) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            id = (String) this.idAdapter.read(jsonReader);
                            break;
                        case 1:
                            status = (String) this.statusAdapter.read(jsonReader);
                            break;
                        case 2:
                            institution = (String) this.institutionAdapter.read(jsonReader);
                            break;
                        case 3:
                            accounts = (List) this.accountsAdapter.read(jsonReader);
                            break;
                        case 4:
                            mfa = (Mfa) this.mfaAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(id, status, institution, accounts, mfa);
        }
    }

    AutoValue_Data(String id, String status, String institution, List<Data> accounts, Mfa mfa) {
        super(id, status, institution, accounts, mfa);
    }
}
