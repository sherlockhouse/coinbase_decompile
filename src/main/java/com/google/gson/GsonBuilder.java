package com.google.gson;

import com.google.gson.internal.Excluder;
import com.google.gson.internal.bind.TreeTypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class GsonBuilder {
    private boolean complexMapKeySerialization = false;
    private String datePattern;
    private int dateStyle = 2;
    private boolean escapeHtmlChars = true;
    private Excluder excluder = Excluder.DEFAULT;
    private final List<TypeAdapterFactory> factories = new ArrayList();
    private FieldNamingStrategy fieldNamingPolicy = FieldNamingPolicy.IDENTITY;
    private boolean generateNonExecutableJson = false;
    private final List<TypeAdapterFactory> hierarchyFactories = new ArrayList();
    private final Map<Type, InstanceCreator<?>> instanceCreators = new HashMap();
    private boolean lenient = false;
    private LongSerializationPolicy longSerializationPolicy = LongSerializationPolicy.DEFAULT;
    private boolean prettyPrinting = false;
    private boolean serializeNulls = false;
    private boolean serializeSpecialFloatingPointValues = false;
    private int timeStyle = 2;

    public GsonBuilder registerTypeAdapterFactory(TypeAdapterFactory factory) {
        this.factories.add(factory);
        return this;
    }

    public Gson create() {
        List<TypeAdapterFactory> factories = new ArrayList();
        factories.addAll(this.factories);
        Collections.reverse(factories);
        factories.addAll(this.hierarchyFactories);
        addTypeAdaptersForDate(this.datePattern, this.dateStyle, this.timeStyle, factories);
        return new Gson(this.excluder, this.fieldNamingPolicy, this.instanceCreators, this.serializeNulls, this.complexMapKeySerialization, this.generateNonExecutableJson, this.escapeHtmlChars, this.prettyPrinting, this.lenient, this.serializeSpecialFloatingPointValues, this.longSerializationPolicy, factories);
    }

    private void addTypeAdaptersForDate(String datePattern, int dateStyle, int timeStyle, List<TypeAdapterFactory> factories) {
        DefaultDateTypeAdapter dateTypeAdapter;
        if (datePattern != null && !"".equals(datePattern.trim())) {
            dateTypeAdapter = new DefaultDateTypeAdapter(datePattern);
        } else if (dateStyle != 2 && timeStyle != 2) {
            dateTypeAdapter = new DefaultDateTypeAdapter(dateStyle, timeStyle);
        } else {
            return;
        }
        factories.add(TreeTypeAdapter.newFactory(TypeToken.get(Date.class), dateTypeAdapter));
        factories.add(TreeTypeAdapter.newFactory(TypeToken.get(Timestamp.class), dateTypeAdapter));
        factories.add(TreeTypeAdapter.newFactory(TypeToken.get(java.sql.Date.class), dateTypeAdapter));
    }
}