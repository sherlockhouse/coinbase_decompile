package com.coinbase.api.internal.models.tiers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_AccountDetails extends C$AutoValue_AccountDetails {

    public static final class GsonTypeAdapter extends TypeAdapter<AccountDetails> {
        private LimitsAndFeatures defaultLimitsAndFeatures = null;
        private String defaultTitle = null;
        private String defaultUpgradeButtonText = null;
        private final TypeAdapter<LimitsAndFeatures> limitsAndFeaturesAdapter;
        private final TypeAdapter<String> titleAdapter;
        private final TypeAdapter<String> upgradeButtonTextAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.titleAdapter = gson.getAdapter(String.class);
            this.limitsAndFeaturesAdapter = gson.getAdapter(LimitsAndFeatures.class);
            this.upgradeButtonTextAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultTitle(String defaultTitle) {
            this.defaultTitle = defaultTitle;
            return this;
        }

        public GsonTypeAdapter setDefaultLimitsAndFeatures(LimitsAndFeatures defaultLimitsAndFeatures) {
            this.defaultLimitsAndFeatures = defaultLimitsAndFeatures;
            return this;
        }

        public GsonTypeAdapter setDefaultUpgradeButtonText(String defaultUpgradeButtonText) {
            this.defaultUpgradeButtonText = defaultUpgradeButtonText;
            return this;
        }

        public void write(JsonWriter jsonWriter, AccountDetails object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("title");
            this.titleAdapter.write(jsonWriter, object.getTitle());
            jsonWriter.name("limits_and_features");
            this.limitsAndFeaturesAdapter.write(jsonWriter, object.getLimitsAndFeatures());
            jsonWriter.name("upgrade_button_text");
            this.upgradeButtonTextAdapter.write(jsonWriter, object.getUpgradeButtonText());
            jsonWriter.endObject();
        }

        public AccountDetails read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String title = this.defaultTitle;
            LimitsAndFeatures limitsAndFeatures = this.defaultLimitsAndFeatures;
            String upgradeButtonText = this.defaultUpgradeButtonText;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1199295369:
                            if (_name.equals("upgrade_button_text")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 110371416:
                            if (_name.equals("title")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 920805996:
                            if (_name.equals("limits_and_features")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            title = (String) this.titleAdapter.read(jsonReader);
                            break;
                        case 1:
                            limitsAndFeatures = (LimitsAndFeatures) this.limitsAndFeaturesAdapter.read(jsonReader);
                            break;
                        case 2:
                            upgradeButtonText = (String) this.upgradeButtonTextAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_AccountDetails(title, limitsAndFeatures, upgradeButtonText);
        }
    }

    AutoValue_AccountDetails(String title, LimitsAndFeatures limitsAndFeatures, String upgradeButtonText) {
        super(title, limitsAndFeatures, upgradeButtonText);
    }
}
