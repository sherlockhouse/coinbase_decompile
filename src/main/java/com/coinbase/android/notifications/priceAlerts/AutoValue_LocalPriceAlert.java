package com.coinbase.android.notifications.priceAlerts;

import com.coinbase.api.internal.models.currency.Data;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import org.joda.money.CurrencyUnit;

final class AutoValue_LocalPriceAlert extends C$AutoValue_LocalPriceAlert {

    public static final class GsonTypeAdapter extends TypeAdapter<LocalPriceAlert> {
        private final TypeAdapter<String> amountAdapter;
        private final TypeAdapter<Long> createdOnAdapter;
        private final TypeAdapter<Data> currencyAdapter;
        private final TypeAdapter<CurrencyUnit> currencyUnitAdapter;
        private String defaultAmount = null;
        private long defaultCreatedOn = 0;
        private Data defaultCurrency = null;
        private CurrencyUnit defaultCurrencyUnit = null;
        private String defaultDisplayText = null;
        private boolean defaultEnabled = false;
        private String defaultId = null;
        private boolean defaultIsAbove = false;
        private String defaultNotificationMessage = null;
        private String defaultNotificationTitle = null;
        private long defaultTriggeredOn = 0;
        private final TypeAdapter<String> displayTextAdapter;
        private final TypeAdapter<Boolean> enabledAdapter;
        private final TypeAdapter<String> idAdapter;
        private final TypeAdapter<Boolean> isAboveAdapter;
        private final TypeAdapter<String> notificationMessageAdapter;
        private final TypeAdapter<String> notificationTitleAdapter;
        private final TypeAdapter<Long> triggeredOnAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.idAdapter = gson.getAdapter(String.class);
            this.amountAdapter = gson.getAdapter(String.class);
            this.isAboveAdapter = gson.getAdapter(Boolean.class);
            this.notificationTitleAdapter = gson.getAdapter(String.class);
            this.notificationMessageAdapter = gson.getAdapter(String.class);
            this.displayTextAdapter = gson.getAdapter(String.class);
            this.createdOnAdapter = gson.getAdapter(Long.class);
            this.triggeredOnAdapter = gson.getAdapter(Long.class);
            this.enabledAdapter = gson.getAdapter(Boolean.class);
            this.currencyUnitAdapter = gson.getAdapter(CurrencyUnit.class);
            this.currencyAdapter = gson.getAdapter(Data.class);
        }

        public GsonTypeAdapter setDefaultId(String defaultId) {
            this.defaultId = defaultId;
            return this;
        }

        public GsonTypeAdapter setDefaultAmount(String defaultAmount) {
            this.defaultAmount = defaultAmount;
            return this;
        }

        public GsonTypeAdapter setDefaultIsAbove(boolean defaultIsAbove) {
            this.defaultIsAbove = defaultIsAbove;
            return this;
        }

        public GsonTypeAdapter setDefaultNotificationTitle(String defaultNotificationTitle) {
            this.defaultNotificationTitle = defaultNotificationTitle;
            return this;
        }

        public GsonTypeAdapter setDefaultNotificationMessage(String defaultNotificationMessage) {
            this.defaultNotificationMessage = defaultNotificationMessage;
            return this;
        }

        public GsonTypeAdapter setDefaultDisplayText(String defaultDisplayText) {
            this.defaultDisplayText = defaultDisplayText;
            return this;
        }

        public GsonTypeAdapter setDefaultCreatedOn(long defaultCreatedOn) {
            this.defaultCreatedOn = defaultCreatedOn;
            return this;
        }

        public GsonTypeAdapter setDefaultTriggeredOn(long defaultTriggeredOn) {
            this.defaultTriggeredOn = defaultTriggeredOn;
            return this;
        }

        public GsonTypeAdapter setDefaultEnabled(boolean defaultEnabled) {
            this.defaultEnabled = defaultEnabled;
            return this;
        }

        public GsonTypeAdapter setDefaultCurrencyUnit(CurrencyUnit defaultCurrencyUnit) {
            this.defaultCurrencyUnit = defaultCurrencyUnit;
            return this;
        }

        public GsonTypeAdapter setDefaultCurrency(Data defaultCurrency) {
            this.defaultCurrency = defaultCurrency;
            return this;
        }

        public void write(JsonWriter jsonWriter, LocalPriceAlert object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("id");
            this.idAdapter.write(jsonWriter, object.getId());
            jsonWriter.name("amount");
            this.amountAdapter.write(jsonWriter, object.getAmount());
            jsonWriter.name("is_above");
            this.isAboveAdapter.write(jsonWriter, Boolean.valueOf(object.getIsAbove()));
            jsonWriter.name("notification_title");
            this.notificationTitleAdapter.write(jsonWriter, object.getNotificationTitle());
            jsonWriter.name("notification_message");
            this.notificationMessageAdapter.write(jsonWriter, object.getNotificationMessage());
            jsonWriter.name("display_text");
            this.displayTextAdapter.write(jsonWriter, object.getDisplayText());
            jsonWriter.name("created_on");
            this.createdOnAdapter.write(jsonWriter, Long.valueOf(object.getCreatedOn()));
            jsonWriter.name("triggered_on");
            this.triggeredOnAdapter.write(jsonWriter, Long.valueOf(object.getTriggeredOn()));
            jsonWriter.name("enabled");
            this.enabledAdapter.write(jsonWriter, Boolean.valueOf(object.getEnabled()));
            jsonWriter.name("currency_unit");
            this.currencyUnitAdapter.write(jsonWriter, object.getCurrencyUnit());
            jsonWriter.name("currency");
            this.currencyAdapter.write(jsonWriter, object.getCurrency());
            jsonWriter.endObject();
        }

        public LocalPriceAlert read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String id = this.defaultId;
            String amount = this.defaultAmount;
            boolean isAbove = this.defaultIsAbove;
            String notificationTitle = this.defaultNotificationTitle;
            String notificationMessage = this.defaultNotificationMessage;
            String displayText = this.defaultDisplayText;
            long createdOn = this.defaultCreatedOn;
            long triggeredOn = this.defaultTriggeredOn;
            boolean enabled = this.defaultEnabled;
            CurrencyUnit currencyUnit = this.defaultCurrencyUnit;
            Data currency = this.defaultCurrency;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1609594047:
                            if (_name.equals("enabled")) {
                                obj = 8;
                                break;
                            }
                            break;
                        case -1484951036:
                            if (_name.equals("notification_title")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case -1413853096:
                            if (_name.equals("amount")) {
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
                        case 107580488:
                            if (_name.equals("is_above")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 380873415:
                            if (_name.equals("triggered_on")) {
                                obj = 7;
                                break;
                            }
                            break;
                        case 575402001:
                            if (_name.equals("currency")) {
                                obj = 10;
                                break;
                            }
                            break;
                        case 1109263602:
                            if (_name.equals("currency_unit")) {
                                obj = 9;
                                break;
                            }
                            break;
                        case 1153373363:
                            if (_name.equals("notification_message")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 1369680534:
                            if (_name.equals("created_on")) {
                                obj = 6;
                                break;
                            }
                            break;
                        case 1615269514:
                            if (_name.equals("display_text")) {
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
                            amount = (String) this.amountAdapter.read(jsonReader);
                            break;
                        case 2:
                            isAbove = ((Boolean) this.isAboveAdapter.read(jsonReader)).booleanValue();
                            break;
                        case 3:
                            notificationTitle = (String) this.notificationTitleAdapter.read(jsonReader);
                            break;
                        case 4:
                            notificationMessage = (String) this.notificationMessageAdapter.read(jsonReader);
                            break;
                        case 5:
                            displayText = (String) this.displayTextAdapter.read(jsonReader);
                            break;
                        case 6:
                            createdOn = ((Long) this.createdOnAdapter.read(jsonReader)).longValue();
                            break;
                        case 7:
                            triggeredOn = ((Long) this.triggeredOnAdapter.read(jsonReader)).longValue();
                            break;
                        case 8:
                            enabled = ((Boolean) this.enabledAdapter.read(jsonReader)).booleanValue();
                            break;
                        case 9:
                            currencyUnit = (CurrencyUnit) this.currencyUnitAdapter.read(jsonReader);
                            break;
                        case 10:
                            currency = (Data) this.currencyAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_LocalPriceAlert(id, amount, isAbove, notificationTitle, notificationMessage, displayText, createdOn, triggeredOn, enabled, currencyUnit, currency);
        }
    }

    AutoValue_LocalPriceAlert(String id, String amount, boolean isAbove, String notificationTitle, String notificationMessage, String displayText, long createdOn, long triggeredOn, boolean enabled, CurrencyUnit currencyUnit, Data currency) {
        super(id, amount, isAbove, notificationTitle, notificationMessage, displayText, createdOn, triggeredOn, enabled, currencyUnit, currency);
    }
}
