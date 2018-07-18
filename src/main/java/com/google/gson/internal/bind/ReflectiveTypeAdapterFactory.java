package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory implements TypeAdapterFactory {
    private final ConstructorConstructor constructorConstructor;
    private final Excluder excluder;
    private final FieldNamingStrategy fieldNamingPolicy;
    private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;

    static abstract class BoundField {
        final boolean deserialized;
        final String name;
        final boolean serialized;

        abstract void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException;

        abstract void write(JsonWriter jsonWriter, Object obj) throws IOException, IllegalAccessException;

        abstract boolean writeField(Object obj) throws IOException, IllegalAccessException;

        protected BoundField(String name, boolean serialized, boolean deserialized) {
            this.name = name;
            this.serialized = serialized;
            this.deserialized = deserialized;
        }
    }

    public static final class Adapter<T> extends TypeAdapter<T> {
        private final Map<String, BoundField> boundFields;
        private final ObjectConstructor<T> constructor;

        Adapter(ObjectConstructor<T> constructor, Map<String, BoundField> boundFields) {
            this.constructor = constructor;
            this.boundFields = boundFields;
        }

        public T read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            }
            T instance = this.constructor.construct();
            try {
                in.beginObject();
                while (in.hasNext()) {
                    BoundField field = (BoundField) this.boundFields.get(in.nextName());
                    if (field == null || !field.deserialized) {
                        in.skipValue();
                    } else {
                        field.read(in, instance);
                    }
                }
                in.endObject();
                return instance;
            } catch (Throwable e) {
                throw new JsonSyntaxException(e);
            } catch (IllegalAccessException e2) {
                throw new AssertionError(e2);
            }
        }

        public void write(JsonWriter out, T value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            out.beginObject();
            try {
                for (BoundField boundField : this.boundFields.values()) {
                    if (boundField.writeField(value)) {
                        out.name(boundField.name);
                        boundField.write(out, value);
                    }
                }
                out.endObject();
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            }
        }
    }

    public ReflectiveTypeAdapterFactory(ConstructorConstructor constructorConstructor, FieldNamingStrategy fieldNamingPolicy, Excluder excluder, JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory) {
        this.constructorConstructor = constructorConstructor;
        this.fieldNamingPolicy = fieldNamingPolicy;
        this.excluder = excluder;
        this.jsonAdapterFactory = jsonAdapterFactory;
    }

    public boolean excludeField(Field f, boolean serialize) {
        return excludeField(f, serialize, this.excluder);
    }

    static boolean excludeField(Field f, boolean serialize, Excluder excluder) {
        return (excluder.excludeClass(f.getType(), serialize) || excluder.excludeField(f, serialize)) ? false : true;
    }

    private List<String> getFieldNames(Field f) {
        SerializedName annotation = (SerializedName) f.getAnnotation(SerializedName.class);
        if (annotation == null) {
            return Collections.singletonList(this.fieldNamingPolicy.translateName(f));
        }
        String serializedName = annotation.value();
        String[] alternates = annotation.alternate();
        if (alternates.length == 0) {
            return Collections.singletonList(serializedName);
        }
        List<String> fieldNames = new ArrayList(alternates.length + 1);
        fieldNames.add(serializedName);
        for (String alternate : alternates) {
            fieldNames.add(alternate);
        }
        return fieldNames;
    }

    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<? super T> raw = type.getRawType();
        if (Object.class.isAssignableFrom(raw)) {
            return new Adapter(this.constructorConstructor.get(type), getBoundFields(gson, type, raw));
        }
        return null;
    }

    private BoundField createBoundField(Gson context, Field field, String name, TypeToken<?> fieldType, boolean serialize, boolean deserialize) {
        final boolean isPrimitive = Primitives.isPrimitive(fieldType.getRawType());
        JsonAdapter annotation = (JsonAdapter) field.getAnnotation(JsonAdapter.class);
        TypeAdapter<?> mapped = null;
        if (annotation != null) {
            mapped = this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, context, fieldType, annotation);
        }
        final boolean jsonAdapterPresent = mapped != null;
        if (mapped == null) {
            mapped = context.getAdapter((TypeToken) fieldType);
        }
        final TypeAdapter<?> typeAdapter = mapped;
        final Field field2 = field;
        final Gson gson = context;
        final TypeToken<?> typeToken = fieldType;
        return new BoundField(name, serialize, deserialize) {
            void write(JsonWriter writer, Object value) throws IOException, IllegalAccessException {
                TypeAdapter t;
                Object fieldValue = field2.get(value);
                if (jsonAdapterPresent) {
                    t = typeAdapter;
                } else {
                    t = new TypeAdapterRuntimeTypeWrapper(gson, typeAdapter, typeToken.getType());
                }
                t.write(writer, fieldValue);
            }

            void read(JsonReader reader, Object value) throws IOException, IllegalAccessException {
                Object fieldValue = typeAdapter.read(reader);
                if (fieldValue != null || !isPrimitive) {
                    field2.set(value, fieldValue);
                }
            }

            public boolean writeField(Object value) throws IOException, IllegalAccessException {
                if (this.serialized && field2.get(value) != value) {
                    return true;
                }
                return false;
            }
        };
    }

    private Map<String, BoundField> getBoundFields(Gson context, TypeToken<?> type, Class<?> raw) {
        Map<String, BoundField> result = new LinkedHashMap();
        if (!raw.isInterface()) {
            Type declaredType = type.getType();
            while (raw != Object.class) {
                for (Field field : raw.getDeclaredFields()) {
                    boolean serialize = excludeField(field, true);
                    boolean deserialize = excludeField(field, false);
                    if (serialize || deserialize) {
                        field.setAccessible(true);
                        Type fieldType = C$Gson$Types.resolve(type.getType(), raw, field.getGenericType());
                        List<String> fieldNames = getFieldNames(field);
                        BoundField previous = null;
                        for (int i = 0; i < fieldNames.size(); i++) {
                            String name = (String) fieldNames.get(i);
                            if (i != 0) {
                                serialize = false;
                            }
                            BoundField replaced = (BoundField) result.put(name, createBoundField(context, field, name, TypeToken.get(fieldType), serialize, deserialize));
                            if (previous == null) {
                                previous = replaced;
                            }
                        }
                        if (previous != null) {
                            throw new IllegalArgumentException(declaredType + " declares multiple JSON fields named " + previous.name);
                        }
                    }
                }
                type = TypeToken.get(C$Gson$Types.resolve(type.getType(), raw, raw.getGenericSuperclass()));
                raw = type.getRawType();
            }
        }
        return result;
    }
}
