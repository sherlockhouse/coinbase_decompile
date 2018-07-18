package com.coinbase.api.internal.models.tiers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Data extends C$AutoValue_Data {

    public static final class GsonTypeAdapter extends TypeAdapter<Data> {
        private final TypeAdapter<AccountDetails> accountDetailsAdapter;
        private final TypeAdapter<AccountSetup> accountSetupAdapter;
        private AccountDetails defaultAccountDetails = null;
        private AccountSetup defaultAccountSetup = null;
        private String defaultTitle = null;
        private VerificationLevels defaultVerificationLevels = null;
        private final TypeAdapter<String> titleAdapter;
        private final TypeAdapter<VerificationLevels> verificationLevelsAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.titleAdapter = gson.getAdapter(String.class);
            this.accountSetupAdapter = gson.getAdapter(AccountSetup.class);
            this.verificationLevelsAdapter = gson.getAdapter(VerificationLevels.class);
            this.accountDetailsAdapter = gson.getAdapter(AccountDetails.class);
        }

        public GsonTypeAdapter setDefaultTitle(String defaultTitle) {
            this.defaultTitle = defaultTitle;
            return this;
        }

        public GsonTypeAdapter setDefaultAccountSetup(AccountSetup defaultAccountSetup) {
            this.defaultAccountSetup = defaultAccountSetup;
            return this;
        }

        public GsonTypeAdapter setDefaultVerificationLevels(VerificationLevels defaultVerificationLevels) {
            this.defaultVerificationLevels = defaultVerificationLevels;
            return this;
        }

        public GsonTypeAdapter setDefaultAccountDetails(AccountDetails defaultAccountDetails) {
            this.defaultAccountDetails = defaultAccountDetails;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("title");
            this.titleAdapter.write(jsonWriter, object.getTitle());
            jsonWriter.name("account_setup");
            this.accountSetupAdapter.write(jsonWriter, object.getAccountSetup());
            jsonWriter.name("verification_levels");
            this.verificationLevelsAdapter.write(jsonWriter, object.getVerificationLevels());
            jsonWriter.name("account_details");
            this.accountDetailsAdapter.write(jsonWriter, object.getAccountDetails());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String title = this.defaultTitle;
            AccountSetup accountSetup = this.defaultAccountSetup;
            VerificationLevels verificationLevels = this.defaultVerificationLevels;
            AccountDetails accountDetails = this.defaultAccountDetails;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -526577173:
                            if (_name.equals("account_setup")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 110371416:
                            if (_name.equals("title")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 337225264:
                            if (_name.equals("account_details")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 1486813203:
                            if (_name.equals("verification_levels")) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            title = (String) this.titleAdapter.read(jsonReader);
                            break;
                        case 1:
                            accountSetup = (AccountSetup) this.accountSetupAdapter.read(jsonReader);
                            break;
                        case 2:
                            verificationLevels = (VerificationLevels) this.verificationLevelsAdapter.read(jsonReader);
                            break;
                        case 3:
                            accountDetails = (AccountDetails) this.accountDetailsAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(title, accountSetup, verificationLevels, accountDetails);
        }
    }

    AutoValue_Data(String title, AccountSetup accountSetup, VerificationLevels verificationLevels, AccountDetails accountDetails) {
        super(title, accountSetup, verificationLevels, accountDetails);
    }
}
