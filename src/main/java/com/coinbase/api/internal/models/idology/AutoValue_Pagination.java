package com.coinbase.api.internal.models.idology;

import com.coinbase.api.internal.ApiConstants;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

final class AutoValue_Pagination extends C$AutoValue_Pagination {

    public static final class GsonTypeAdapter extends TypeAdapter<Pagination> {
        private Object defaultEndingBefore = null;
        private Integer defaultLimit = null;
        private Object defaultNextUri = null;
        private String defaultOrder = null;
        private Object defaultPreviousUri = null;
        private Object defaultStartingAfter = null;
        private final TypeAdapter<Object> endingBeforeAdapter;
        private final TypeAdapter<Integer> limitAdapter;
        private final TypeAdapter<Object> nextUriAdapter;
        private final TypeAdapter<String> orderAdapter;
        private final TypeAdapter<Object> previousUriAdapter;
        private final TypeAdapter<Object> startingAfterAdapter;

        public GsonTypeAdapter(Gson gson) {
            this.endingBeforeAdapter = gson.getAdapter(Object.class);
            this.startingAfterAdapter = gson.getAdapter(Object.class);
            this.orderAdapter = gson.getAdapter(String.class);
            this.limitAdapter = gson.getAdapter(Integer.class);
            this.previousUriAdapter = gson.getAdapter(Object.class);
            this.nextUriAdapter = gson.getAdapter(Object.class);
        }

        public GsonTypeAdapter setDefaultEndingBefore(Object defaultEndingBefore) {
            this.defaultEndingBefore = defaultEndingBefore;
            return this;
        }

        public GsonTypeAdapter setDefaultStartingAfter(Object defaultStartingAfter) {
            this.defaultStartingAfter = defaultStartingAfter;
            return this;
        }

        public GsonTypeAdapter setDefaultOrder(String defaultOrder) {
            this.defaultOrder = defaultOrder;
            return this;
        }

        public GsonTypeAdapter setDefaultLimit(Integer defaultLimit) {
            this.defaultLimit = defaultLimit;
            return this;
        }

        public GsonTypeAdapter setDefaultPreviousUri(Object defaultPreviousUri) {
            this.defaultPreviousUri = defaultPreviousUri;
            return this;
        }

        public GsonTypeAdapter setDefaultNextUri(Object defaultNextUri) {
            this.defaultNextUri = defaultNextUri;
            return this;
        }

        public void write(JsonWriter jsonWriter, Pagination object) throws IOException {
            if (object == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name("ending_before");
            this.endingBeforeAdapter.write(jsonWriter, object.getEndingBefore());
            jsonWriter.name("starting_after");
            this.startingAfterAdapter.write(jsonWriter, object.getStartingAfter());
            jsonWriter.name("order");
            this.orderAdapter.write(jsonWriter, object.getOrder());
            jsonWriter.name(ApiConstants.LIMIT);
            this.limitAdapter.write(jsonWriter, object.getLimit());
            jsonWriter.name("previous_uri");
            this.previousUriAdapter.write(jsonWriter, object.getPreviousUri());
            jsonWriter.name("next_uri");
            this.nextUriAdapter.write(jsonWriter, object.getNextUri());
            jsonWriter.endObject();
        }

        public Pagination read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Object endingBefore = this.defaultEndingBefore;
            Object startingAfter = this.defaultStartingAfter;
            String order = this.defaultOrder;
            Integer limit = this.defaultLimit;
            Object previousUri = this.defaultPreviousUri;
            Object nextUri = this.defaultNextUri;
            while (jsonReader.hasNext()) {
                String _name = jsonReader.nextName();
                if (jsonReader.peek() != JsonToken.NULL) {
                    Object obj = -1;
                    switch (_name.hashCode()) {
                        case -1830365801:
                            if (_name.equals("ending_before")) {
                                obj = null;
                                break;
                            }
                            break;
                        case -1116971996:
                            if (_name.equals("previous_uri")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case -695789283:
                            if (_name.equals("starting_after")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case 102976443:
                            if (_name.equals(ApiConstants.LIMIT)) {
                                obj = 3;
                                break;
                            }
                            break;
                        case 106006350:
                            if (_name.equals("order")) {
                                obj = 2;
                                break;
                            }
                            break;
                        case 1424739872:
                            if (_name.equals("next_uri")) {
                                obj = 5;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            endingBefore = this.endingBeforeAdapter.read(jsonReader);
                            break;
                        case 1:
                            startingAfter = this.startingAfterAdapter.read(jsonReader);
                            break;
                        case 2:
                            order = (String) this.orderAdapter.read(jsonReader);
                            break;
                        case 3:
                            limit = (Integer) this.limitAdapter.read(jsonReader);
                            break;
                        case 4:
                            previousUri = this.previousUriAdapter.read(jsonReader);
                            break;
                        case 5:
                            nextUri = this.nextUriAdapter.read(jsonReader);
                            break;
                        default:
                            jsonReader.skipValue();
                            break;
                    }
                }
                jsonReader.nextNull();
            }
            jsonReader.endObject();
            return new AutoValue_Pagination(endingBefore, startingAfter, order, limit, previousUri, nextUri);
        }
    }

    AutoValue_Pagination(Object endingBefore, Object startingAfter, String order, Integer limit, Object previousUri, Object nextUri) {
        super(endingBefore, startingAfter, order, limit, previousUri, nextUri);
    }
}
