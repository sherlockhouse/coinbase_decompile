package com.coinbase.api.internal.models.availablePaymentMethods;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

final class AutoValue_AvailablePaymentMethod extends C$AutoValue_AvailablePaymentMethod {

    public static final class GsonTypeAdapter extends TypeAdapter<AvailablePaymentMethod> {
        private final TypeAdapter<String> additionalFeesAdapter;
        private final TypeAdapter<String> buyTimeAdapter;
        private final TypeAdapter<BuyingPower> buyingPowerAdapter;
        private final TypeAdapter<Currency> currencyAdapter;
        private String defaultAdditionalFees = null;
        private String defaultBuyTime = null;
        private BuyingPower defaultBuyingPower = null;
        private Currency defaultCurrency = null;
        private String defaultDescription = null;
        private String defaultFeeDescription = null;
        private String defaultName = null;
        private Boolean defaultRecommended = null;
        private String defaultRelativeLimits = null;
        private List<String> defaultRequirements = null;
        private List<String> defaultSupports = null;
        private String defaultType = null;
        private List<String> defaultVerifyRequirements = null;
        private final TypeAdapter<String> descriptionAdapter;
        private final TypeAdapter<String> feeDescriptionAdapter;
        private final TypeAdapter<String> nameAdapter;
        private final TypeAdapter<Boolean> recommendedAdapter;
        private final TypeAdapter<String> relativeLimitsAdapter;
        private final TypeAdapter<List<String>> requirementsAdapter;
        private final TypeAdapter<List<String>> supportsAdapter;
        private final TypeAdapter<String> typeAdapter;
        private final TypeAdapter<List<String>> verifyRequirementsAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.typeAdapter = gson.getAdapter(String.class);
            this.requirementsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, String.class));
            this.verifyRequirementsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, String.class));
            this.nameAdapter = gson.getAdapter(String.class);
            this.descriptionAdapter = gson.getAdapter(String.class);
            this.buyTimeAdapter = gson.getAdapter(String.class);
            this.relativeLimitsAdapter = gson.getAdapter(String.class);
            this.supportsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, String.class));
            this.feeDescriptionAdapter = gson.getAdapter(String.class);
            this.additionalFeesAdapter = gson.getAdapter(String.class);
            this.recommendedAdapter = gson.getAdapter(Boolean.class);
            this.buyingPowerAdapter = gson.getAdapter(BuyingPower.class);
            this.currencyAdapter = gson.getAdapter(Currency.class);
        }

        public GsonTypeAdapter setDefaultType(String defaultType) {
            this.defaultType = defaultType;
            return this;
        }

        public GsonTypeAdapter setDefaultRequirements(List<String> defaultRequirements) {
            this.defaultRequirements = defaultRequirements;
            return this;
        }

        public GsonTypeAdapter setDefaultVerifyRequirements(List<String> defaultVerifyRequirements) {
            this.defaultVerifyRequirements = defaultVerifyRequirements;
            return this;
        }

        public GsonTypeAdapter setDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public GsonTypeAdapter setDefaultDescription(String defaultDescription) {
            this.defaultDescription = defaultDescription;
            return this;
        }

        public GsonTypeAdapter setDefaultBuyTime(String defaultBuyTime) {
            this.defaultBuyTime = defaultBuyTime;
            return this;
        }

        public GsonTypeAdapter setDefaultRelativeLimits(String defaultRelativeLimits) {
            this.defaultRelativeLimits = defaultRelativeLimits;
            return this;
        }

        public GsonTypeAdapter setDefaultSupports(List<String> defaultSupports) {
            this.defaultSupports = defaultSupports;
            return this;
        }

        public GsonTypeAdapter setDefaultFeeDescription(String defaultFeeDescription) {
            this.defaultFeeDescription = defaultFeeDescription;
            return this;
        }

        public GsonTypeAdapter setDefaultAdditionalFees(String defaultAdditionalFees) {
            this.defaultAdditionalFees = defaultAdditionalFees;
            return this;
        }

        public GsonTypeAdapter setDefaultRecommended(Boolean defaultRecommended) {
            this.defaultRecommended = defaultRecommended;
            return this;
        }

        public GsonTypeAdapter setDefaultBuyingPower(BuyingPower defaultBuyingPower) {
            this.defaultBuyingPower = defaultBuyingPower;
            return this;
        }

        public GsonTypeAdapter setDefaultCurrency(Currency defaultCurrency) {
            this.defaultCurrency = defaultCurrency;
            return this;
        }

        public void write(JsonWriter jsonWriter, AvailablePaymentMethod object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("type");
            this.typeAdapter.write(jsonWriter, object.getType());
            jsonWriter.name("requirements");
            this.requirementsAdapter.write(jsonWriter, object.getRequirements());
            jsonWriter.name("verify_requirements");
            this.verifyRequirementsAdapter.write(jsonWriter, object.getVerifyRequirements());
            jsonWriter.name("name");
            this.nameAdapter.write(jsonWriter, object.getName());
            jsonWriter.name("description");
            this.descriptionAdapter.write(jsonWriter, object.getDescription());
            jsonWriter.name("buy_time");
            this.buyTimeAdapter.write(jsonWriter, object.getBuyTime());
            jsonWriter.name("relative_limits");
            this.relativeLimitsAdapter.write(jsonWriter, object.getRelativeLimits());
            jsonWriter.name("supports");
            this.supportsAdapter.write(jsonWriter, object.getSupports());
            jsonWriter.name("fee_description");
            this.feeDescriptionAdapter.write(jsonWriter, object.getFeeDescription());
            jsonWriter.name("additional_fees");
            this.additionalFeesAdapter.write(jsonWriter, object.getAdditionalFees());
            jsonWriter.name("recommended");
            this.recommendedAdapter.write(jsonWriter, object.getRecommended());
            jsonWriter.name("buying_power");
            this.buyingPowerAdapter.write(jsonWriter, object.getBuyingPower());
            jsonWriter.name("currency");
            this.currencyAdapter.write(jsonWriter, object.getCurrency());
            jsonWriter.endObject();
        }

        public AvailablePaymentMethod read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String type = this.defaultType;
            List<String> requirements = this.defaultRequirements;
            List<String> verifyRequirements = this.defaultVerifyRequirements;
            String name = this.defaultName;
            String description = this.defaultDescription;
            String buyTime = this.defaultBuyTime;
            String relativeLimits = this.defaultRelativeLimits;
            List<String> supports = this.defaultSupports;
            String feeDescription = this.defaultFeeDescription;
            String additionalFees = this.defaultAdditionalFees;
            Boolean recommended = this.defaultRecommended;
            BuyingPower buyingPower = this.defaultBuyingPower;
            Currency currency = this.defaultCurrency;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1724546052:
                            if (_name.equals("description")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -1663206780:
                            if (_name.equals("supports")) {
                                obj = 7;
                                break;
                            }
                            break;
                        case -1619874672:
                            if (_name.equals("requirements")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -984108826:
                            if (_name.equals("buy_time")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case -651359582:
                            if (_name.equals("buying_power")) {
                                obj = 11;
                                break;
                            }
                            break;
                        case -118380859:
                            if (_name.equals("additional_fees")) {
                                obj = 9;
                                break;
                            }
                            break;
                        case 3373707:
                            if (_name.equals("name")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3575610:
                            if (_name.equals("type")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 111456491:
                            if (_name.equals("relative_limits")) {
                                obj = 6;
                                break;
                            }
                            break;
                        case 575402001:
                            if (_name.equals("currency")) {
                                obj = 12;
                                break;
                            }
                            break;
                        case 1437916763:
                            if (_name.equals("recommended")) {
                                obj = 10;
                                break;
                            }
                            break;
                        case 2009093315:
                            if (_name.equals("fee_description")) {
                                obj = 8;
                                break;
                            }
                            break;
                        case 2078464662:
                            if (_name.equals("verify_requirements")) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            type = (String) this.typeAdapter.read(jsonReader);
                            break;
                        case 1:
                            requirements = (List) this.requirementsAdapter.read(jsonReader);
                            break;
                        case 2:
                            verifyRequirements = (List) this.verifyRequirementsAdapter.read(jsonReader);
                            break;
                        case 3:
                            name = (String) this.nameAdapter.read(jsonReader);
                            break;
                        case 4:
                            description = (String) this.descriptionAdapter.read(jsonReader);
                            break;
                        case 5:
                            buyTime = (String) this.buyTimeAdapter.read(jsonReader);
                            break;
                        case 6:
                            relativeLimits = (String) this.relativeLimitsAdapter.read(jsonReader);
                            break;
                        case 7:
                            supports = (List) this.supportsAdapter.read(jsonReader);
                            break;
                        case 8:
                            feeDescription = (String) this.feeDescriptionAdapter.read(jsonReader);
                            break;
                        case 9:
                            additionalFees = (String) this.additionalFeesAdapter.read(jsonReader);
                            break;
                        case 10:
                            recommended = (Boolean) this.recommendedAdapter.read(jsonReader);
                            break;
                        case 11:
                            buyingPower = (BuyingPower) this.buyingPowerAdapter.read(jsonReader);
                            break;
                        case 12:
                            currency = (Currency) this.currencyAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_AvailablePaymentMethod(type, requirements, verifyRequirements, name, description, buyTime, relativeLimits, supports, feeDescription, additionalFees, recommended, buyingPower, currency);
        }
    }

    AutoValue_AvailablePaymentMethod(String type, List<String> requirements, List<String> verifyRequirements, String name, String description, String buyTime, String relativeLimits, List<String> supports, String feeDescription, String additionalFees, Boolean recommended, BuyingPower buyingPower, Currency currency) {
        super(type, requirements, verifyRequirements, name, description, buyTime, relativeLimits, supports, feeDescription, additionalFees, recommended, buyingPower, currency);
    }
}
