package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class NumberSerializers {

    protected static abstract class Base<T> extends StdScalarSerializer<T> implements ContextualSerializer {
        protected final NumberType _numberType;
        protected final String _schemaType;

        protected Base(Class<T> cls, NumberType numberType, String schemaType) {
            super(cls);
            this._numberType = numberType;
            this._schemaType = schemaType;
        }

        public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
            return createSchemaNode(this._schemaType, true);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
            JsonIntegerFormatVisitor v2 = visitor.expectIntegerFormat(typeHint);
            if (v2 != null) {
                v2.numberType(this._numberType);
            }
        }

        public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
            if (property == null) {
                return this;
            }
            Value format = prov.getAnnotationIntrospector().findFormat(property.getMember());
            if (format == null) {
                return this;
            }
            switch (format.getShape()) {
                case STRING:
                    return ToStringSerializer.instance;
                default:
                    return this;
            }
        }
    }

    @JacksonStdImpl
    public static final class DoubleSerializer extends Base<Double> {
        static final DoubleSerializer instance = new DoubleSerializer();

        public /* bridge */ /* synthetic */ void acceptJsonFormatVisitor(JsonFormatVisitorWrapper x0, JavaType x1) throws JsonMappingException {
            super.acceptJsonFormatVisitor(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonNode getSchema(SerializerProvider x0, Type x1) {
            return super.getSchema(x0, x1);
        }

        public DoubleSerializer() {
            super(Double.class, NumberType.DOUBLE, "number");
        }

        public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
            jgen.writeNumber(value.doubleValue());
        }

        public void serializeWithType(Double value, JsonGenerator jgen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
            serialize(value, jgen, provider);
        }
    }

    @JacksonStdImpl
    public static final class FloatSerializer extends Base<Float> {
        static final FloatSerializer instance = new FloatSerializer();

        public /* bridge */ /* synthetic */ void acceptJsonFormatVisitor(JsonFormatVisitorWrapper x0, JavaType x1) throws JsonMappingException {
            super.acceptJsonFormatVisitor(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonNode getSchema(SerializerProvider x0, Type x1) {
            return super.getSchema(x0, x1);
        }

        public FloatSerializer() {
            super(Float.class, NumberType.FLOAT, "number");
        }

        public void serialize(Float value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
            jgen.writeNumber(value.floatValue());
        }
    }

    @JacksonStdImpl
    public static final class IntLikeSerializer extends Base<Number> {
        static final IntLikeSerializer instance = new IntLikeSerializer();

        public /* bridge */ /* synthetic */ void acceptJsonFormatVisitor(JsonFormatVisitorWrapper x0, JavaType x1) throws JsonMappingException {
            super.acceptJsonFormatVisitor(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonNode getSchema(SerializerProvider x0, Type x1) {
            return super.getSchema(x0, x1);
        }

        public IntLikeSerializer() {
            super(Number.class, NumberType.INT, "integer");
        }

        public void serialize(Number value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
            jgen.writeNumber(value.intValue());
        }
    }

    @JacksonStdImpl
    public static final class IntegerSerializer extends Base<Integer> {
        public /* bridge */ /* synthetic */ void acceptJsonFormatVisitor(JsonFormatVisitorWrapper x0, JavaType x1) throws JsonMappingException {
            super.acceptJsonFormatVisitor(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonNode getSchema(SerializerProvider x0, Type x1) {
            return super.getSchema(x0, x1);
        }

        public IntegerSerializer() {
            super(Integer.class, NumberType.INT, "integer");
        }

        public void serialize(Integer value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
            jgen.writeNumber(value.intValue());
        }

        public void serializeWithType(Integer value, JsonGenerator jgen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
            serialize(value, jgen, provider);
        }
    }

    @JacksonStdImpl
    public static final class LongSerializer extends Base<Long> {
        static final LongSerializer instance = new LongSerializer();

        public /* bridge */ /* synthetic */ void acceptJsonFormatVisitor(JsonFormatVisitorWrapper x0, JavaType x1) throws JsonMappingException {
            super.acceptJsonFormatVisitor(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonNode getSchema(SerializerProvider x0, Type x1) {
            return super.getSchema(x0, x1);
        }

        public LongSerializer() {
            super(Long.class, NumberType.LONG, "number");
        }

        public void serialize(Long value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonGenerationException {
            jgen.writeNumber(value.longValue());
        }
    }

    @JacksonStdImpl
    public static final class ShortSerializer extends Base<Short> {
        static final ShortSerializer instance = new ShortSerializer();

        public /* bridge */ /* synthetic */ void acceptJsonFormatVisitor(JsonFormatVisitorWrapper x0, JavaType x1) throws JsonMappingException {
            super.acceptJsonFormatVisitor(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonNode getSchema(SerializerProvider x0, Type x1) {
            return super.getSchema(x0, x1);
        }

        public ShortSerializer() {
            super(Short.class, NumberType.INT, "number");
        }

        public void serialize(Short value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
            jgen.writeNumber(value.shortValue());
        }
    }

    public static void addAll(Map<String, JsonSerializer<?>> allDeserializers) {
        JsonSerializer<?> intS = new IntegerSerializer();
        allDeserializers.put(Integer.class.getName(), intS);
        allDeserializers.put(Integer.TYPE.getName(), intS);
        allDeserializers.put(Long.class.getName(), LongSerializer.instance);
        allDeserializers.put(Long.TYPE.getName(), LongSerializer.instance);
        allDeserializers.put(Byte.class.getName(), IntLikeSerializer.instance);
        allDeserializers.put(Byte.TYPE.getName(), IntLikeSerializer.instance);
        allDeserializers.put(Short.class.getName(), ShortSerializer.instance);
        allDeserializers.put(Short.TYPE.getName(), ShortSerializer.instance);
        allDeserializers.put(Float.class.getName(), FloatSerializer.instance);
        allDeserializers.put(Float.TYPE.getName(), FloatSerializer.instance);
        allDeserializers.put(Double.class.getName(), DoubleSerializer.instance);
        allDeserializers.put(Double.TYPE.getName(), DoubleSerializer.instance);
    }
}
