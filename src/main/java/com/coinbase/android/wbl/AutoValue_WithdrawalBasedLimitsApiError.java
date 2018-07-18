package com.coinbase.android.wbl;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_WithdrawalBasedLimitsApiError extends C$AutoValue_WithdrawalBasedLimitsApiError {

    public static final class GsonTypeAdapter extends TypeAdapter<WithdrawalBasedLimitsApiError> {
        private String defaultDismissText = null;
        private String defaultId = null;
        private String defaultLearnMoreLocation = null;
        private String defaultLearnMoreText = null;
        private String defaultMessage = null;
        private String defaultTitle = null;
        private final TypeAdapter<String> dismissTextAdapter;
        private final TypeAdapter<String> idAdapter;
        private final TypeAdapter<String> learnMoreLocationAdapter;
        private final TypeAdapter<String> learnMoreTextAdapter;
        private final TypeAdapter<String> messageAdapter;
        private final TypeAdapter<String> titleAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.idAdapter = gson.getAdapter(String.class);
            this.messageAdapter = gson.getAdapter(String.class);
            this.titleAdapter = gson.getAdapter(String.class);
            this.learnMoreTextAdapter = gson.getAdapter(String.class);
            this.learnMoreLocationAdapter = gson.getAdapter(String.class);
            this.dismissTextAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultId(String defaultId) {
            this.defaultId = defaultId;
            return this;
        }

        public GsonTypeAdapter setDefaultMessage(String defaultMessage) {
            this.defaultMessage = defaultMessage;
            return this;
        }

        public GsonTypeAdapter setDefaultTitle(String defaultTitle) {
            this.defaultTitle = defaultTitle;
            return this;
        }

        public GsonTypeAdapter setDefaultLearnMoreText(String defaultLearnMoreText) {
            this.defaultLearnMoreText = defaultLearnMoreText;
            return this;
        }

        public GsonTypeAdapter setDefaultLearnMoreLocation(String defaultLearnMoreLocation) {
            this.defaultLearnMoreLocation = defaultLearnMoreLocation;
            return this;
        }

        public GsonTypeAdapter setDefaultDismissText(String defaultDismissText) {
            this.defaultDismissText = defaultDismissText;
            return this;
        }

        public void write(JsonWriter jsonWriter, WithdrawalBasedLimitsApiError object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("id");
            this.idAdapter.write(jsonWriter, object.getId());
            jsonWriter.name("message");
            this.messageAdapter.write(jsonWriter, object.getMessage());
            jsonWriter.name("title");
            this.titleAdapter.write(jsonWriter, object.getTitle());
            jsonWriter.name("learn_more_text");
            this.learnMoreTextAdapter.write(jsonWriter, object.getLearnMoreText());
            jsonWriter.name("learn_more_location");
            this.learnMoreLocationAdapter.write(jsonWriter, object.getLearnMoreLocation());
            jsonWriter.name("dismiss_text");
            this.dismissTextAdapter.write(jsonWriter, object.getDismissText());
            jsonWriter.endObject();
        }

        public WithdrawalBasedLimitsApiError read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String id = this.defaultId;
            String message = this.defaultMessage;
            String title = this.defaultTitle;
            String learnMoreText = this.defaultLearnMoreText;
            String learnMoreLocation = this.defaultLearnMoreLocation;
            String dismissText = this.defaultDismissText;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -877830492:
                            if (_name.equals("learn_more_location")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -546384996:
                            if (_name.equals("learn_more_text")) {
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
                        case 110371416:
                            if (_name.equals("title")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 432624066:
                            if (_name.equals("dismiss_text")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 954925063:
                            if (_name.equals("message")) {
                                obj = 1;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            id = (String) this.idAdapter.read(jsonReader);
                            break;
                        case 1:
                            message = (String) this.messageAdapter.read(jsonReader);
                            break;
                        case 2:
                            title = (String) this.titleAdapter.read(jsonReader);
                            break;
                        case 3:
                            learnMoreText = (String) this.learnMoreTextAdapter.read(jsonReader);
                            break;
                        case 4:
                            learnMoreLocation = (String) this.learnMoreLocationAdapter.read(jsonReader);
                            break;
                        case 5:
                            dismissText = (String) this.dismissTextAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_WithdrawalBasedLimitsApiError(id, message, title, learnMoreText, learnMoreLocation, dismissText);
        }
    }

    AutoValue_WithdrawalBasedLimitsApiError(String id, String message, String title, String learnMoreText, String learnMoreLocation, String dismissText) {
        super(id, message, title, learnMoreText, learnMoreLocation, dismissText);
    }
}
