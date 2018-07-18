package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ByteBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.DoubleBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.FloatBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.IntBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.LongBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ShortBuilder;
import java.io.IOException;

public abstract class PrimitiveArrayDeserializers<T> extends StdDeserializer<T> {

    @JacksonStdImpl
    static final class BooleanDeser extends PrimitiveArrayDeserializers<boolean[]> {
        public BooleanDeser() {
            super(boolean[].class);
        }

        public boolean[] deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (!jp.isExpectedStartArrayToken()) {
                return handleNonArray(jp, ctxt);
            }
            BooleanBuilder builder = ctxt.getArrayBuilders().getBooleanBuilder();
            boolean[] chunk = (boolean[]) builder.resetAndStart();
            int ix = 0;
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                boolean value = _parseBooleanPrimitive(jp, ctxt);
                if (ix >= chunk.length) {
                    chunk = (boolean[]) builder.appendCompletedChunk(chunk, ix);
                    ix = 0;
                }
                int ix2 = ix + 1;
                chunk[ix] = value;
                ix = ix2;
            }
            return (boolean[]) builder.completeAndClearBuffer(chunk, ix);
        }

        private final boolean[] handleNonArray(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (jp.getCurrentToken() == JsonToken.VALUE_STRING && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jp.getText().length() == 0) {
                return null;
            }
            if (ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new boolean[]{_parseBooleanPrimitive(jp, ctxt)};
            }
            throw ctxt.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    static final class ByteDeser extends PrimitiveArrayDeserializers<byte[]> {
        public ByteDeser() {
            super(byte[].class);
        }

        public byte[] deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonToken t = jp.getCurrentToken();
            if (t == JsonToken.VALUE_STRING) {
                return jp.getBinaryValue(ctxt.getBase64Variant());
            }
            if (t == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object ob = jp.getEmbeddedObject();
                if (ob == null) {
                    return null;
                }
                if (ob instanceof byte[]) {
                    return (byte[]) ob;
                }
            }
            if (!jp.isExpectedStartArrayToken()) {
                return handleNonArray(jp, ctxt);
            }
            ByteBuilder builder = ctxt.getArrayBuilders().getByteBuilder();
            byte[] chunk = (byte[]) builder.resetAndStart();
            int ix = 0;
            while (true) {
                t = jp.nextToken();
                if (t == JsonToken.END_ARRAY) {
                    return (byte[]) builder.completeAndClearBuffer(chunk, ix);
                }
                byte value;
                if (t == JsonToken.VALUE_NUMBER_INT || t == JsonToken.VALUE_NUMBER_FLOAT) {
                    value = jp.getByteValue();
                } else if (t != JsonToken.VALUE_NULL) {
                    throw ctxt.mappingException(this._valueClass.getComponentType());
                } else {
                    value = (byte) 0;
                }
                if (ix >= chunk.length) {
                    chunk = (byte[]) builder.appendCompletedChunk(chunk, ix);
                    ix = 0;
                }
                int ix2 = ix + 1;
                chunk[ix] = value;
                ix = ix2;
            }
        }

        private final byte[] handleNonArray(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (jp.getCurrentToken() == JsonToken.VALUE_STRING && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jp.getText().length() == 0) {
                return null;
            }
            if (ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                byte value;
                JsonToken t = jp.getCurrentToken();
                if (t == JsonToken.VALUE_NUMBER_INT || t == JsonToken.VALUE_NUMBER_FLOAT) {
                    value = jp.getByteValue();
                } else if (t != JsonToken.VALUE_NULL) {
                    throw ctxt.mappingException(this._valueClass.getComponentType());
                } else {
                    value = (byte) 0;
                }
                return new byte[]{value};
            }
            throw ctxt.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    static final class CharDeser extends PrimitiveArrayDeserializers<char[]> {
        public CharDeser() {
            super(char[].class);
        }

        public char[] deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            JsonToken t = jp.getCurrentToken();
            if (t == JsonToken.VALUE_STRING) {
                char[] buffer = jp.getTextCharacters();
                int offset = jp.getTextOffset();
                int len = jp.getTextLength();
                char[] result = new char[len];
                System.arraycopy(buffer, offset, result, 0, len);
                return result;
            } else if (jp.isExpectedStartArrayToken()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    t = jp.nextToken();
                    if (t == JsonToken.END_ARRAY) {
                        return sb.toString().toCharArray();
                    }
                    if (t != JsonToken.VALUE_STRING) {
                        throw ctxt.mappingException(Character.TYPE);
                    }
                    String str = jp.getText();
                    if (str.length() != 1) {
                        throw JsonMappingException.from(jp, "Can not convert a JSON String of length " + str.length() + " into a char element of char array");
                    }
                    sb.append(str.charAt(0));
                }
            } else {
                if (t == JsonToken.VALUE_EMBEDDED_OBJECT) {
                    Object ob = jp.getEmbeddedObject();
                    if (ob == null) {
                        return null;
                    }
                    if (ob instanceof char[]) {
                        return (char[]) ob;
                    }
                    if (ob instanceof String) {
                        return ((String) ob).toCharArray();
                    }
                    if (ob instanceof byte[]) {
                        return Base64Variants.getDefaultVariant().encode((byte[]) ob, false).toCharArray();
                    }
                }
                throw ctxt.mappingException(this._valueClass);
            }
        }
    }

    @JacksonStdImpl
    static final class DoubleDeser extends PrimitiveArrayDeserializers<double[]> {
        public DoubleDeser() {
            super(double[].class);
        }

        public double[] deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (!jp.isExpectedStartArrayToken()) {
                return handleNonArray(jp, ctxt);
            }
            DoubleBuilder builder = ctxt.getArrayBuilders().getDoubleBuilder();
            double[] chunk = (double[]) builder.resetAndStart();
            int ix = 0;
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                double value = _parseDoublePrimitive(jp, ctxt);
                if (ix >= chunk.length) {
                    chunk = (double[]) builder.appendCompletedChunk(chunk, ix);
                    ix = 0;
                }
                int ix2 = ix + 1;
                chunk[ix] = value;
                ix = ix2;
            }
            return (double[]) builder.completeAndClearBuffer(chunk, ix);
        }

        private final double[] handleNonArray(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (jp.getCurrentToken() == JsonToken.VALUE_STRING && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jp.getText().length() == 0) {
                return null;
            }
            if (ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new double[]{_parseDoublePrimitive(jp, ctxt)};
            }
            throw ctxt.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    static final class FloatDeser extends PrimitiveArrayDeserializers<float[]> {
        public FloatDeser() {
            super(float[].class);
        }

        public float[] deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (!jp.isExpectedStartArrayToken()) {
                return handleNonArray(jp, ctxt);
            }
            FloatBuilder builder = ctxt.getArrayBuilders().getFloatBuilder();
            float[] chunk = (float[]) builder.resetAndStart();
            int ix = 0;
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                float value = _parseFloatPrimitive(jp, ctxt);
                if (ix >= chunk.length) {
                    chunk = (float[]) builder.appendCompletedChunk(chunk, ix);
                    ix = 0;
                }
                int ix2 = ix + 1;
                chunk[ix] = value;
                ix = ix2;
            }
            return (float[]) builder.completeAndClearBuffer(chunk, ix);
        }

        private final float[] handleNonArray(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (jp.getCurrentToken() == JsonToken.VALUE_STRING && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jp.getText().length() == 0) {
                return null;
            }
            if (ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new float[]{_parseFloatPrimitive(jp, ctxt)};
            }
            throw ctxt.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    static final class IntDeser extends PrimitiveArrayDeserializers<int[]> {
        public static final IntDeser instance = new IntDeser();

        public IntDeser() {
            super(int[].class);
        }

        public int[] deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (!jp.isExpectedStartArrayToken()) {
                return handleNonArray(jp, ctxt);
            }
            IntBuilder builder = ctxt.getArrayBuilders().getIntBuilder();
            int[] chunk = (int[]) builder.resetAndStart();
            int ix = 0;
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                int value = _parseIntPrimitive(jp, ctxt);
                if (ix >= chunk.length) {
                    chunk = (int[]) builder.appendCompletedChunk(chunk, ix);
                    ix = 0;
                }
                int ix2 = ix + 1;
                chunk[ix] = value;
                ix = ix2;
            }
            return (int[]) builder.completeAndClearBuffer(chunk, ix);
        }

        private final int[] handleNonArray(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (jp.getCurrentToken() == JsonToken.VALUE_STRING && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jp.getText().length() == 0) {
                return null;
            }
            if (ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new int[]{_parseIntPrimitive(jp, ctxt)};
            }
            throw ctxt.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    static final class LongDeser extends PrimitiveArrayDeserializers<long[]> {
        public static final LongDeser instance = new LongDeser();

        public LongDeser() {
            super(long[].class);
        }

        public long[] deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (!jp.isExpectedStartArrayToken()) {
                return handleNonArray(jp, ctxt);
            }
            LongBuilder builder = ctxt.getArrayBuilders().getLongBuilder();
            long[] chunk = (long[]) builder.resetAndStart();
            int ix = 0;
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                long value = _parseLongPrimitive(jp, ctxt);
                if (ix >= chunk.length) {
                    chunk = (long[]) builder.appendCompletedChunk(chunk, ix);
                    ix = 0;
                }
                int ix2 = ix + 1;
                chunk[ix] = value;
                ix = ix2;
            }
            return (long[]) builder.completeAndClearBuffer(chunk, ix);
        }

        private final long[] handleNonArray(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (jp.getCurrentToken() == JsonToken.VALUE_STRING && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jp.getText().length() == 0) {
                return null;
            }
            if (ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new long[]{_parseLongPrimitive(jp, ctxt)};
            }
            throw ctxt.mappingException(this._valueClass);
        }
    }

    @JacksonStdImpl
    static final class ShortDeser extends PrimitiveArrayDeserializers<short[]> {
        public ShortDeser() {
            super(short[].class);
        }

        public short[] deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (!jp.isExpectedStartArrayToken()) {
                return handleNonArray(jp, ctxt);
            }
            ShortBuilder builder = ctxt.getArrayBuilders().getShortBuilder();
            short[] chunk = (short[]) builder.resetAndStart();
            int ix = 0;
            while (jp.nextToken() != JsonToken.END_ARRAY) {
                short value = _parseShortPrimitive(jp, ctxt);
                if (ix >= chunk.length) {
                    chunk = (short[]) builder.appendCompletedChunk(chunk, ix);
                    ix = 0;
                }
                int ix2 = ix + 1;
                chunk[ix] = value;
                ix = ix2;
            }
            return (short[]) builder.completeAndClearBuffer(chunk, ix);
        }

        private final short[] handleNonArray(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (jp.getCurrentToken() == JsonToken.VALUE_STRING && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && jp.getText().length() == 0) {
                return null;
            }
            if (ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
                return new short[]{_parseShortPrimitive(jp, ctxt)};
            }
            throw ctxt.mappingException(this._valueClass);
        }
    }

    protected PrimitiveArrayDeserializers(Class<T> cls) {
        super((Class) cls);
    }

    public static JsonDeserializer<?> forType(Class<?> rawType) {
        if (rawType == Integer.TYPE) {
            return IntDeser.instance;
        }
        if (rawType == Long.TYPE) {
            return LongDeser.instance;
        }
        if (rawType == Byte.TYPE) {
            return new ByteDeser();
        }
        if (rawType == Short.TYPE) {
            return new ShortDeser();
        }
        if (rawType == Float.TYPE) {
            return new FloatDeser();
        }
        if (rawType == Double.TYPE) {
            return new DoubleDeser();
        }
        if (rawType == Boolean.TYPE) {
            return new BooleanDeser();
        }
        if (rawType == Character.TYPE) {
            return new CharDeser();
        }
        throw new IllegalStateException();
    }

    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromArray(jp, ctxt);
    }
}
