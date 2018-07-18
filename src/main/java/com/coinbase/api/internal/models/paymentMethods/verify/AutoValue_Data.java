package com.coinbase.api.internal.models.paymentMethods.verify;

import com.coinbase.android.db.TransactionORM;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Data extends C$AutoValue_Data {

    public static final class GsonTypeAdapter extends TypeAdapter<Data> {
        private final TypeAdapter<Boolean> allowBuyAdapter;
        private final TypeAdapter<Boolean> allowSellAdapter;
        private final TypeAdapter<String> createdAtAdapter;
        private final TypeAdapter<String> currencyAdapter;
        private Boolean defaultAllowBuy = null;
        private Boolean defaultAllowSell = null;
        private String defaultCreatedAt = null;
        private String defaultCurrency = null;
        private String defaultId = null;
        private String defaultName = null;
        private Boolean defaultPrimaryBuy = null;
        private Boolean defaultPrimarySell = null;
        private String defaultType = null;
        private String defaultUpdatedAt = null;
        private Boolean defaultVerified = null;
        private final TypeAdapter<String> idAdapter;
        private final TypeAdapter<String> nameAdapter;
        private final TypeAdapter<Boolean> primaryBuyAdapter;
        private final TypeAdapter<Boolean> primarySellAdapter;
        private final TypeAdapter<String> typeAdapter;
        private final TypeAdapter<String> updatedAtAdapter;
        private final TypeAdapter<Boolean> verifiedAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.idAdapter = gson.getAdapter(String.class);
            this.typeAdapter = gson.getAdapter(String.class);
            this.nameAdapter = gson.getAdapter(String.class);
            this.currencyAdapter = gson.getAdapter(String.class);
            this.primaryBuyAdapter = gson.getAdapter(Boolean.class);
            this.primarySellAdapter = gson.getAdapter(Boolean.class);
            this.allowBuyAdapter = gson.getAdapter(Boolean.class);
            this.allowSellAdapter = gson.getAdapter(Boolean.class);
            this.createdAtAdapter = gson.getAdapter(String.class);
            this.updatedAtAdapter = gson.getAdapter(String.class);
            this.verifiedAdapter = gson.getAdapter(Boolean.class);
        }

        public GsonTypeAdapter setDefaultId(String defaultId) {
            this.defaultId = defaultId;
            return this;
        }

        public GsonTypeAdapter setDefaultType(String defaultType) {
            this.defaultType = defaultType;
            return this;
        }

        public GsonTypeAdapter setDefaultName(String defaultName) {
            this.defaultName = defaultName;
            return this;
        }

        public GsonTypeAdapter setDefaultCurrency(String defaultCurrency) {
            this.defaultCurrency = defaultCurrency;
            return this;
        }

        public GsonTypeAdapter setDefaultPrimaryBuy(Boolean defaultPrimaryBuy) {
            this.defaultPrimaryBuy = defaultPrimaryBuy;
            return this;
        }

        public GsonTypeAdapter setDefaultPrimarySell(Boolean defaultPrimarySell) {
            this.defaultPrimarySell = defaultPrimarySell;
            return this;
        }

        public GsonTypeAdapter setDefaultAllowBuy(Boolean defaultAllowBuy) {
            this.defaultAllowBuy = defaultAllowBuy;
            return this;
        }

        public GsonTypeAdapter setDefaultAllowSell(Boolean defaultAllowSell) {
            this.defaultAllowSell = defaultAllowSell;
            return this;
        }

        public GsonTypeAdapter setDefaultCreatedAt(String defaultCreatedAt) {
            this.defaultCreatedAt = defaultCreatedAt;
            return this;
        }

        public GsonTypeAdapter setDefaultUpdatedAt(String defaultUpdatedAt) {
            this.defaultUpdatedAt = defaultUpdatedAt;
            return this;
        }

        public GsonTypeAdapter setDefaultVerified(Boolean defaultVerified) {
            this.defaultVerified = defaultVerified;
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
            jsonWriter.name("type");
            this.typeAdapter.write(jsonWriter, object.getType());
            jsonWriter.name("name");
            this.nameAdapter.write(jsonWriter, object.getName());
            jsonWriter.name("currency");
            this.currencyAdapter.write(jsonWriter, object.getCurrency());
            jsonWriter.name("primary_buy");
            this.primaryBuyAdapter.write(jsonWriter, object.getPrimaryBuy());
            jsonWriter.name("primary_sell");
            this.primarySellAdapter.write(jsonWriter, object.getPrimarySell());
            jsonWriter.name("allow_buy");
            this.allowBuyAdapter.write(jsonWriter, object.getAllowBuy());
            jsonWriter.name("allow_sell");
            this.allowSellAdapter.write(jsonWriter, object.getAllowSell());
            jsonWriter.name(TransactionORM.COLUMN_CREATED_AT);
            this.createdAtAdapter.write(jsonWriter, object.getCreatedAt());
            jsonWriter.name("updated_at");
            this.updatedAtAdapter.write(jsonWriter, object.getUpdatedAt());
            jsonWriter.name("verified");
            this.verifiedAdapter.write(jsonWriter, object.getVerified());
            jsonWriter.endObject();
        }

        public Data read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String id = this.defaultId;
            String type = this.defaultType;
            String name = this.defaultName;
            String currency = this.defaultCurrency;
            Boolean primaryBuy = this.defaultPrimaryBuy;
            Boolean primarySell = this.defaultPrimarySell;
            Boolean allowBuy = this.defaultAllowBuy;
            Boolean allowSell = this.defaultAllowSell;
            String createdAt = this.defaultCreatedAt;
            String updatedAt = this.defaultUpdatedAt;
            Boolean verified = this.defaultVerified;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1994383672:
                            if (_name.equals("verified")) {
                                obj = 10;
                                break;
                            }
                            break;
                        case -1341748600:
                            if (_name.equals("allow_sell")) {
                                obj = 7;
                                break;
                            }
                            break;
                        case -1114248337:
                            if (_name.equals("primary_sell")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case -867243319:
                            if (_name.equals("primary_buy")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -295464393:
                            if (_name.equals("updated_at")) {
                                obj = 9;
                                break;
                            }
                            break;
                        case 3355:
                            if (_name.equals("id")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 3373707:
                            if (_name.equals("name")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 3575610:
                            if (_name.equals("type")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 372343952:
                            if (_name.equals("allow_buy")) {
                                obj = 6;
                                break;
                            }
                            break;
                        case 575402001:
                            if (_name.equals("currency")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 1369680106:
                            if (_name.equals(TransactionORM.COLUMN_CREATED_AT)) {
                                obj = 8;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            id = (String) this.idAdapter.read(jsonReader);
                            break;
                        case 1:
                            type = (String) this.typeAdapter.read(jsonReader);
                            break;
                        case 2:
                            name = (String) this.nameAdapter.read(jsonReader);
                            break;
                        case 3:
                            currency = (String) this.currencyAdapter.read(jsonReader);
                            break;
                        case 4:
                            primaryBuy = (Boolean) this.primaryBuyAdapter.read(jsonReader);
                            break;
                        case 5:
                            primarySell = (Boolean) this.primarySellAdapter.read(jsonReader);
                            break;
                        case 6:
                            allowBuy = (Boolean) this.allowBuyAdapter.read(jsonReader);
                            break;
                        case 7:
                            allowSell = (Boolean) this.allowSellAdapter.read(jsonReader);
                            break;
                        case 8:
                            createdAt = (String) this.createdAtAdapter.read(jsonReader);
                            break;
                        case 9:
                            updatedAt = (String) this.updatedAtAdapter.read(jsonReader);
                            break;
                        case 10:
                            verified = (Boolean) this.verifiedAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Data(id, type, name, currency, primaryBuy, primarySell, allowBuy, allowSell, createdAt, updatedAt, verified);
        }
    }

    AutoValue_Data(String id, String type, String name, String currency, Boolean primaryBuy, Boolean primarySell, Boolean allowBuy, Boolean allowSell, String createdAt, String updatedAt, Boolean verified) {
        super(id, type, name, currency, primaryBuy, primarySell, allowBuy, allowSell, createdAt, updatedAt, verified);
    }
}
