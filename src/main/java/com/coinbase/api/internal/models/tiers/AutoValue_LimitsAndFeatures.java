package com.coinbase.api.internal.models.tiers;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.List;

final class AutoValue_LimitsAndFeatures extends C$AutoValue_LimitsAndFeatures {

    public static final class GsonTypeAdapter extends TypeAdapter<LimitsAndFeatures> {
        private final TypeAdapter<List<BuySellLimit>> buyDepositLimitsAdapter;
        private List<BuySellLimit> defaultBuyDepositLimits = null;
        private LifetimeLimit defaultLifetimeLimit = null;
        private LevelFeature defaultReceives = null;
        private LevelFeature defaultSends = null;
        private String defaultTitle = null;
        private final TypeAdapter<LifetimeLimit> lifetimeLimitAdapter;
        private final TypeAdapter<LevelFeature> receivesAdapter;
        private final TypeAdapter<LevelFeature> sendsAdapter;
        private final TypeAdapter<String> titleAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.titleAdapter = gson.getAdapter(String.class);
            this.lifetimeLimitAdapter = gson.getAdapter(LifetimeLimit.class);
            this.buyDepositLimitsAdapter = gson.getAdapter(TypeToken.getParameterized(List.class, BuySellLimit.class));
            this.sendsAdapter = gson.getAdapter(LevelFeature.class);
            this.receivesAdapter = gson.getAdapter(LevelFeature.class);
        }

        public GsonTypeAdapter setDefaultTitle(String defaultTitle) {
            this.defaultTitle = defaultTitle;
            return this;
        }

        public GsonTypeAdapter setDefaultLifetimeLimit(LifetimeLimit defaultLifetimeLimit) {
            this.defaultLifetimeLimit = defaultLifetimeLimit;
            return this;
        }

        public GsonTypeAdapter setDefaultBuyDepositLimits(List<BuySellLimit> defaultBuyDepositLimits) {
            this.defaultBuyDepositLimits = defaultBuyDepositLimits;
            return this;
        }

        public GsonTypeAdapter setDefaultSends(LevelFeature defaultSends) {
            this.defaultSends = defaultSends;
            return this;
        }

        public GsonTypeAdapter setDefaultReceives(LevelFeature defaultReceives) {
            this.defaultReceives = defaultReceives;
            return this;
        }

        public void write(JsonWriter jsonWriter, LimitsAndFeatures object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("title");
            this.titleAdapter.write(jsonWriter, object.getTitle());
            jsonWriter.name("lifetime_limit");
            this.lifetimeLimitAdapter.write(jsonWriter, object.getLifetimeLimit());
            jsonWriter.name("buy_deposit_limits");
            this.buyDepositLimitsAdapter.write(jsonWriter, object.getBuyDepositLimits());
            jsonWriter.name("sends");
            this.sendsAdapter.write(jsonWriter, object.getSends());
            jsonWriter.name("receives");
            this.receivesAdapter.write(jsonWriter, object.getReceives());
            jsonWriter.endObject();
        }

        public LimitsAndFeatures read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String title = this.defaultTitle;
            LifetimeLimit lifetimeLimit = this.defaultLifetimeLimit;
            List<BuySellLimit> buyDepositLimits = this.defaultBuyDepositLimits;
            LevelFeature sends = this.defaultSends;
            LevelFeature receives = this.defaultReceives;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -808719888:
                            if (_name.equals("receives")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 109322731:
                            if (_name.equals("sends")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 110371416:
                            if (_name.equals("title")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 798955461:
                            if (_name.equals("lifetime_limit")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 1407014834:
                            if (_name.equals("buy_deposit_limits")) {
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
                            lifetimeLimit = (LifetimeLimit) this.lifetimeLimitAdapter.read(jsonReader);
                            break;
                        case 2:
                            buyDepositLimits = (List) this.buyDepositLimitsAdapter.read(jsonReader);
                            break;
                        case 3:
                            sends = (LevelFeature) this.sendsAdapter.read(jsonReader);
                            break;
                        case 4:
                            receives = (LevelFeature) this.receivesAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_LimitsAndFeatures(title, lifetimeLimit, buyDepositLimits, sends, receives);
        }
    }

    AutoValue_LimitsAndFeatures(String title, LifetimeLimit lifetimeLimit, List<BuySellLimit> buyDepositLimits, LevelFeature sends, LevelFeature receives) {
        super(title, lifetimeLimit, buyDepositLimits, sends, receives);
    }
}
