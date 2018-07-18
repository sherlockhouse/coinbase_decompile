package com.coinbase.api.internal.models.verifications.allowedPaymentMethods;

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
        private String defaultDescription = null;
        private String defaultName = null;
        private String defaultPickerBuyTime = null;
        private String defaultPickerFeeDescription = null;
        private String defaultPickerRelativeLimits = null;
        private List<String> defaultRequirements = null;
        private String defaultType = null;
        private final TypeAdapter<String> descriptionAdapter;
        private final TypeAdapter<String> nameAdapter;
        private final TypeAdapter<String> pickerBuyTimeAdapter;
        private final TypeAdapter<String> pickerFeeDescriptionAdapter;
        private final TypeAdapter<String> pickerRelativeLimitsAdapter;
        private final TypeAdapter<List<String>> requirementsAdapter;
        private final TypeAdapter<String> typeAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.typeAdapter = gson.getAdapter(String.class);
            this.nameAdapter = gson.getAdapter(String.class);
            this.requirementsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, String.class));
            this.descriptionAdapter = gson.getAdapter(String.class);
            this.pickerBuyTimeAdapter = gson.getAdapter(String.class);
            this.pickerRelativeLimitsAdapter = gson.getAdapter(String.class);
            this.pickerFeeDescriptionAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultType(String defaultType) {
            this.defaultType = defaultType;
            return this;
        }

        public GsonTypeAdapter setDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public GsonTypeAdapter setDefaultRequirements(List<String> defaultRequirements) {
            this.defaultRequirements = defaultRequirements;
            return this;
        }

        public GsonTypeAdapter setDefaultDescription(String defaultDescription) {
            this.defaultDescription = defaultDescription;
            return this;
        }

        public GsonTypeAdapter setDefaultPickerBuyTime(String defaultPickerBuyTime) {
            this.defaultPickerBuyTime = defaultPickerBuyTime;
            return this;
        }

        public GsonTypeAdapter setDefaultPickerRelativeLimits(String defaultPickerRelativeLimits) {
            this.defaultPickerRelativeLimits = defaultPickerRelativeLimits;
            return this;
        }

        public GsonTypeAdapter setDefaultPickerFeeDescription(String defaultPickerFeeDescription) {
            this.defaultPickerFeeDescription = defaultPickerFeeDescription;
            return this;
        }

        public void write(JsonWriter jsonWriter, Data object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("type");
            this.typeAdapter.write(jsonWriter, object.getType());
            jsonWriter.name("name");
            this.nameAdapter.write(jsonWriter, object.getName());
            jsonWriter.name("requirements");
            this.requirementsAdapter.write(jsonWriter, object.getRequirements());
            jsonWriter.name("description");
            this.descriptionAdapter.write(jsonWriter, object.getDescription());
            jsonWriter.name("picker_buy_time");
            this.pickerBuyTimeAdapter.write(jsonWriter, object.getPickerBuyTime());
            jsonWriter.name("picker_relative_limits");
            this.pickerRelativeLimitsAdapter.write(jsonWriter, object.getPickerRelativeLimits());
            jsonWriter.name("picker_fee_description");
            this.pickerFeeDescriptionAdapter.write(jsonWriter, object.getPickerFeeDescription());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String type = this.defaultType;
            String name = this.defaultName;
            List<String> requirements = this.defaultRequirements;
            String description = this.defaultDescription;
            String pickerBuyTime = this.defaultPickerBuyTime;
            String pickerRelativeLimits = this.defaultPickerRelativeLimits;
            String pickerFeeDescription = this.defaultPickerFeeDescription;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1724546052:
                            if (_name.equals("description")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case -1619874672:
                            if (_name.equals("requirements")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case -1181902473:
                            if (_name.equals("picker_buy_time")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -1006357702:
                            if (_name.equals("picker_relative_limits")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 3373707:
                            if (_name.equals("name")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 3575610:
                            if (_name.equals("type")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 891279122:
                            if (_name.equals("picker_fee_description")) {
                                obj = 6;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            type = (String) this.typeAdapter.read(jsonReader);
                            break;
                        case 1:
                            name = (String) this.nameAdapter.read(jsonReader);
                            break;
                        case 2:
                            requirements = (List) this.requirementsAdapter.read(jsonReader);
                            break;
                        case 3:
                            description = (String) this.descriptionAdapter.read(jsonReader);
                            break;
                        case 4:
                            pickerBuyTime = (String) this.pickerBuyTimeAdapter.read(jsonReader);
                            break;
                        case 5:
                            pickerRelativeLimits = (String) this.pickerRelativeLimitsAdapter.read(jsonReader);
                            break;
                        case 6:
                            pickerFeeDescription = (String) this.pickerFeeDescriptionAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(type, name, requirements, description, pickerBuyTime, pickerRelativeLimits, pickerFeeDescription);
        }
    }

    AutoValue_Data(String type, String name, List<String> requirements, String description, String pickerBuyTime, String pickerRelativeLimits, String pickerFeeDescription) {
        super(type, name, requirements, description, pickerBuyTime, pickerRelativeLimits, pickerFeeDescription);
    }
}
