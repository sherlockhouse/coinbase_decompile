package com.coinbase.api.internal.models.tiers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_LifetimeLimit extends C$AutoValue_LifetimeLimit {

    public static final class GsonTypeAdapter extends TypeAdapter<LifetimeLimit> {
        private final TypeAdapter<String> amountAdapter;
        private final TypeAdapter<Currency> currencyAdapter;
        private String defaultAmount = null;
        private Currency defaultCurrency = null;
        private String defaultDescription = null;
        private String defaultName = null;
        private String defaultRemaining = null;
        private Boolean defaultUnlimited = null;
        private String defaultUsed = null;
        private final TypeAdapter<String> descriptionAdapter;
        private final TypeAdapter<String> nameAdapter;
        private final TypeAdapter<String> remainingAdapter;
        private final TypeAdapter<Boolean> unlimitedAdapter;
        private final TypeAdapter<String> usedAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.nameAdapter = gson.getAdapter(String.class);
            this.descriptionAdapter = gson.getAdapter(String.class);
            this.currencyAdapter = gson.getAdapter(Currency.class);
            this.amountAdapter = gson.getAdapter(String.class);
            this.usedAdapter = gson.getAdapter(String.class);
            this.remainingAdapter = gson.getAdapter(String.class);
            this.unlimitedAdapter = gson.getAdapter(Boolean.class);
        }

        public GsonTypeAdapter setDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public GsonTypeAdapter setDefaultDescription(String defaultDescription) {
            this.defaultDescription = defaultDescription;
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

        public GsonTypeAdapter setDefaultUsed(String defaultUsed) {
            this.defaultUsed = defaultUsed;
            return this;
        }

        public GsonTypeAdapter setDefaultRemaining(String defaultRemaining) {
            this.defaultRemaining = defaultRemaining;
            return this;
        }

        public GsonTypeAdapter setDefaultUnlimited(Boolean defaultUnlimited) {
            this.defaultUnlimited = defaultUnlimited;
            return this;
        }

        public void write(JsonWriter jsonWriter, LifetimeLimit object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("name");
            this.nameAdapter.write(jsonWriter, object.getName());
            jsonWriter.name("description");
            this.descriptionAdapter.write(jsonWriter, object.getDescription());
            jsonWriter.name("currency");
            this.currencyAdapter.write(jsonWriter, object.getCurrency());
            jsonWriter.name("amount");
            this.amountAdapter.write(jsonWriter, object.getAmount());
            jsonWriter.name("used");
            this.usedAdapter.write(jsonWriter, object.getUsed());
            jsonWriter.name("remaining");
            this.remainingAdapter.write(jsonWriter, object.getRemaining());
            jsonWriter.name("unlimited");
            this.unlimitedAdapter.write(jsonWriter, object.getUnlimited());
            jsonWriter.endObject();
        }

        public LifetimeLimit read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String name = this.defaultName;
            String description = this.defaultDescription;
            Currency currency = this.defaultCurrency;
            String amount = this.defaultAmount;
            String used = this.defaultUsed;
            String remaining = this.defaultRemaining;
            Boolean unlimited = this.defaultUnlimited;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1724546052:
                            if (_name.equals("description")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -1413853096:
                            if (_name.equals("amount")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 3373707:
                            if (_name.equals("name")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 3599293:
                            if (_name.equals("used")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 575402001:
                            if (_name.equals("currency")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 869838326:
                            if (_name.equals("remaining")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 1887918305:
                            if (_name.equals("unlimited")) {
                                obj = 6;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            name = (String) this.nameAdapter.read(jsonReader);
                            break;
                        case 1:
                            description = (String) this.descriptionAdapter.read(jsonReader);
                            break;
                        case 2:
                            currency = (Currency) this.currencyAdapter.read(jsonReader);
                            break;
                        case 3:
                            amount = (String) this.amountAdapter.read(jsonReader);
                            break;
                        case 4:
                            used = (String) this.usedAdapter.read(jsonReader);
                            break;
                        case 5:
                            remaining = (String) this.remainingAdapter.read(jsonReader);
                            break;
                        case 6:
                            unlimited = (Boolean) this.unlimitedAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_LifetimeLimit(name, description, currency, amount, used, remaining, unlimited);
        }
    }

    AutoValue_LifetimeLimit(String name, String description, Currency currency, String amount, String used, String remaining, Boolean unlimited) {
        super(name, description, currency, amount, used, remaining, unlimited);
    }
}
