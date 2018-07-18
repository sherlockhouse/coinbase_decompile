package com.fasterxml.jackson.core;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class JsonParser implements Closeable {
    protected int _features;

    public enum Feature {
        AUTO_CLOSE_SOURCE(true),
        ALLOW_COMMENTS(false),
        ALLOW_YAML_COMMENTS(false),
        ALLOW_UNQUOTED_FIELD_NAMES(false),
        ALLOW_SINGLE_QUOTES(false),
        ALLOW_UNQUOTED_CONTROL_CHARS(false),
        ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER(false),
        ALLOW_NUMERIC_LEADING_ZEROS(false),
        ALLOW_NON_NUMERIC_NUMBERS(false),
        STRICT_DUPLICATE_DETECTION(false);
        
        private final boolean _defaultState;
        private final int _mask;

        public static int collectDefaults() {
            int flags = 0;
            for (Feature f : values()) {
                if (f.enabledByDefault()) {
                    flags |= f.getMask();
                }
            }
            return flags;
        }

        private Feature(boolean defaultState) {
            this._mask = 1 << ordinal();
            this._defaultState = defaultState;
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int flags) {
            return (this._mask & flags) != 0;
        }

        public int getMask() {
            return 1 << ordinal();
        }
    }

    public enum NumberType {
        INT,
        LONG,
        BIG_INTEGER,
        FLOAT,
        DOUBLE,
        BIG_DECIMAL
    }

    public abstract void clearCurrentToken();

    public abstract void close() throws IOException;

    public abstract BigInteger getBigIntegerValue() throws IOException;

    public abstract byte[] getBinaryValue(Base64Variant base64Variant) throws IOException;

    public abstract ObjectCodec getCodec();

    public abstract JsonLocation getCurrentLocation();

    public abstract String getCurrentName() throws IOException;

    public abstract JsonToken getCurrentToken();

    public abstract int getCurrentTokenId();

    public abstract BigDecimal getDecimalValue() throws IOException;

    public abstract double getDoubleValue() throws IOException;

    public abstract Object getEmbeddedObject() throws IOException;

    public abstract float getFloatValue() throws IOException;

    public abstract int getIntValue() throws IOException;

    public abstract long getLongValue() throws IOException;

    public abstract NumberType getNumberType() throws IOException;

    public abstract Number getNumberValue() throws IOException;

    public abstract String getText() throws IOException;

    public abstract char[] getTextCharacters() throws IOException;

    public abstract int getTextLength() throws IOException;

    public abstract int getTextOffset() throws IOException;

    public abstract JsonLocation getTokenLocation();

    public abstract String getValueAsString(String str) throws IOException;

    public abstract boolean hasCurrentToken();

    public abstract boolean hasTextCharacters();

    public abstract JsonToken nextToken() throws IOException, JsonParseException;

    public abstract JsonToken nextValue() throws IOException, JsonParseException;

    public abstract JsonParser skipChildren() throws IOException, JsonParseException;

    protected JsonParser() {
    }

    protected JsonParser(int features) {
        this._features = features;
    }

    public boolean isEnabled(Feature f) {
        return (this._features & f.getMask()) != 0;
    }

    public boolean isExpectedStartArrayToken() {
        return getCurrentToken() == JsonToken.START_ARRAY;
    }

    public byte getByteValue() throws IOException {
        int value = getIntValue();
        if (value >= -128 && value <= 255) {
            return (byte) value;
        }
        throw _constructError("Numeric value (" + getText() + ") out of range of Java byte");
    }

    public short getShortValue() throws IOException {
        int value = getIntValue();
        if (value >= -32768 && value <= 32767) {
            return (short) value;
        }
        throw _constructError("Numeric value (" + getText() + ") out of range of Java short");
    }

    public byte[] getBinaryValue() throws IOException {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public int readBinaryValue(Base64Variant bv, OutputStream out) throws IOException {
        _reportUnsupportedOperation();
        return 0;
    }

    public int getValueAsInt() throws IOException {
        return getValueAsInt(0);
    }

    public int getValueAsInt(int def) throws IOException {
        return def;
    }

    public long getValueAsLong() throws IOException {
        return getValueAsLong(0);
    }

    public long getValueAsLong(long def) throws IOException {
        return def;
    }

    public String getValueAsString() throws IOException {
        return getValueAsString(null);
    }

    public boolean canReadObjectId() {
        return false;
    }

    public boolean canReadTypeId() {
        return false;
    }

    public Object getObjectId() throws IOException {
        return null;
    }

    public Object getTypeId() throws IOException {
        return null;
    }

    public <T extends TreeNode> T readValueAsTree() throws IOException {
        return _codec().readTree(this);
    }

    protected ObjectCodec _codec() {
        ObjectCodec c = getCodec();
        if (c != null) {
            return c;
        }
        throw new IllegalStateException("No ObjectCodec defined for parser, needed for deserialization");
    }

    protected JsonParseException _constructError(String msg) {
        return new JsonParseException(msg, getCurrentLocation());
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by parser of type " + getClass().getName());
    }
}
