package com.coinbase.api.internal.models.tiers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_BuySellLimit extends C$AutoValue_BuySellLimit {

    public static final class GsonTypeAdapter extends TypeAdapter<BuySellLimit> {
        private final TypeAdapter<String> amountAdapter;
        private final TypeAdapter<Currency> currencyAdapter;
        private String defaultAmount = null;
        private Currency defaultCurrency = null;
        private Boolean defaultEnabled = null;
        private String defaultName = null;
        private String defaultPerUnit = null;
        private Integer defaultPeriodInDays = null;
        private String defaultStatusText = null;
        private String defaultType = null;
        private final TypeAdapter<Boolean> enabledAdapter;
        private final TypeAdapter<String> nameAdapter;
        private final TypeAdapter<String> perUnitAdapter;
        private final TypeAdapter<Integer> periodInDaysAdapter;
        private final TypeAdapter<String> statusTextAdapter;
        private final TypeAdapter<String> typeAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.typeAdapter = gson.getAdapter(String.class);
            this.nameAdapter = gson.getAdapter(String.class);
            this.currencyAdapter = gson.getAdapter(Currency.class);
            this.amountAdapter = gson.getAdapter(String.class);
            this.periodInDaysAdapter = gson.getAdapter(Integer.class);
            this.perUnitAdapter = gson.getAdapter(String.class);
            this.enabledAdapter = gson.getAdapter(Boolean.class);
            this.statusTextAdapter = gson.getAdapter(String.class);
        }

        public GsonTypeAdapter setDefaultType(String defaultType) {
            this.defaultType = defaultType;
            return this;
        }

        public GsonTypeAdapter setDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public GsonTypeAdapter setDefaultCurrency(Currency defaultCurrency) {
            this.defaultCurrency = defaultCurrency;
            return this;
        }

        public GsonTypeAdapter setDefaultAmount(String defaultAmount) {
            this.defaultAmount = defaultAmount;
            return this;
        }

        public GsonTypeAdapter setDefaultPeriodInDays(Integer defaultPeriodInDays) {
            this.defaultPeriodInDays = defaultPeriodInDays;
            return this;
        }

        public GsonTypeAdapter setDefaultPerUnit(String defaultPerUnit) {
            this.defaultPerUnit = defaultPerUnit;
            return this;
        }

        public GsonTypeAdapter setDefaultEnabled(Boolean defaultEnabled) {
            this.defaultEnabled = defaultEnabled;
            return this;
        }

        public GsonTypeAdapter setDefaultStatusText(String defaultStatusText) {
            this.defaultStatusText = defaultStatusText;
            return this;
        }

        public void write(JsonWriter jsonWriter, BuySellLimit object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("type");
            this.typeAdapter.write(jsonWriter, object.getType());
            jsonWriter.name("name");
            this.nameAdapter.write(jsonWriter, object.getName());
            jsonWriter.name("currency");
            this.currencyAdapter.write(jsonWriter, object.getCurrency());
            jsonWriter.name("amount");
            this.amountAdapter.write(jsonWriter, object.getAmount());
            jsonWriter.name("period_in_days");
            this.periodInDaysAdapter.write(jsonWriter, object.getPeriodInDays());
            jsonWriter.name("per_unit");
            this.perUnitAdapter.write(jsonWriter, object.getPerUnit());
            jsonWriter.name("enabled");
            this.enabledAdapter.write(jsonWriter, object.getEnabled());
            jsonWriter.name("status_text");
            this.statusTextAdapter.write(jsonWriter, object.getStatusText());
            jsonWriter.endObject();
        }

        public BuySellLimit read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String type = this.defaultType;
            String name = this.defaultName;
            Currency currency = this.defaultCurrency;
            String amount = this.defaultAmount;
            Integer periodInDays = this.defaultPeriodInDays;
            String perUnit = this.defaultPerUnit;
            Boolean enabled = this.defaultEnabled;
            String statusText = this.defaultStatusText;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1609594047:
                            if (_name.equals("enabled")) {
                                obj = 6;
                                break;
                            }
                            break;
                        case -1413853096:
                            if (_name.equals("amount")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case -891202214:
                            if (_name.equals("status_text")) {
                                obj = 7;
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
                        case 424872806:
                            if (_name.equals("per_unit")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 575402001:
                            if (_name.equals("currency")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 1079170739:
                            if (_name.equals("period_in_days")) {
                                obj = 4;
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
                            currency = (Currency) this.currencyAdapter.read(jsonReader);
                            break;
                        case 3:
                            amount = (String) this.amountAdapter.read(jsonReader);
                            break;
                        case 4:
                            periodInDays = (Integer) this.periodInDaysAdapter.read(jsonReader);
                            break;
                        case 5:
                            perUnit = (String) this.perUnitAdapter.read(jsonReader);
                            break;
                        case 6:
                            enabled = (Boolean) this.enabledAdapter.read(jsonReader);
                            break;
                        case 7:
                            statusText = (String) this.statusTextAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_BuySellLimit(type, name, currency, amount, periodInDays, perUnit, enabled, statusText);
        }
    }

    AutoValue_BuySellLimit(String type, String name, Currency currency, String amount, Integer periodInDays, String perUnit, Boolean enabled, String statusText) {
        super(type, name, currency, amount, periodInDays, perUnit, enabled, statusText);
    }
}
