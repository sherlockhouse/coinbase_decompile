package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class JsonGenerator implements Closeable, Flushable {
    protected PrettyPrinter _cfgPrettyPrinter;

    public enum Feature {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        WRITE_NUMBERS_AS_STRINGS(false),
        WRITE_BIGDECIMAL_AS_PLAIN(false),
        FLUSH_PASSED_TO_STREAM(true),
        ESCAPE_NON_ASCII(false),
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
            return this._mask;
        }
    }

    public abstract void close() throws IOException;

    public abstract JsonGenerator disable(Feature feature);

    public abstract JsonGenerator enable(Feature feature);

    public abstract void flush() throws IOException;

    public abstract JsonGenerator useDefaultPrettyPrinter();

    public abstract int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) throws IOException;

    public abstract void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException;

    public abstract void writeBoolean(boolean z) throws IOException;

    public abstract void writeEndArray() throws IOException;

    public abstract void writeEndObject() throws IOException;

    public abstract void writeFieldName(SerializableString serializableString) throws IOException;

    public abstract void writeFieldName(String str) throws IOException;

    public abstract void writeNull() throws IOException;

    public abstract void writeNumber(double d) throws IOException;

    public abstract void writeNumber(float f) throws IOException;

    public abstract void writeNumber(int i) throws IOException;

    public abstract void writeNumber(long j) throws IOException;

    public abstract void writeNumber(String str) throws IOException;

    public abstract void writeNumber(BigDecimal bigDecimal) throws IOException;

    public abstract void writeNumber(BigInteger bigInteger) throws IOException;

    public abstract void writeObject(Object obj) throws IOException;

    public abstract void writeRaw(char c) throws IOException;

    public abstract void writeRaw(String str) throws IOException;

    public abstract void writeRaw(char[] cArr, int i, int i2) throws IOException;

    public abstract void writeRawValue(String str) throws IOException;

    public abstract void writeStartArray() throws IOException;

    public abstract void writeStartObject() throws IOException;

    public abstract void writeString(SerializableString serializableString) throws IOException;

    public abstract void writeString(String str) throws IOException;

    public abstract void writeString(char[] cArr, int i, int i2) throws IOException;

    protected JsonGenerator() {
    }

    public JsonGenerator setPrettyPrinter(PrettyPrinter pp) {
        this._cfgPrettyPrinter = pp;
        return this;
    }

    public PrettyPrinter getPrettyPrinter() {
        return this._cfgPrettyPrinter;
    }

    public JsonGenerator setHighestNonEscapedChar(int charCode) {
        return this;
    }

    public JsonGenerator setCharacterEscapes(CharacterEscapes esc) {
        return this;
    }

    public JsonGenerator setRootValueSeparator(SerializableString sep) {
        throw new UnsupportedOperationException();
    }

    public boolean canWriteObjectId() {
        return false;
    }

    public boolean canWriteTypeId() {
        return false;
    }

    public boolean canWriteBinaryNatively() {
        return false;
    }

    public boolean canOmitFields() {
        return true;
    }

    public void writeRaw(SerializableString raw) throws IOException {
        writeRaw(raw.getValue());
    }

    public void writeBinary(byte[] data, int offset, int len) throws IOException {
        writeBinary(Base64Variants.getDefaultVariant(), data, offset, len);
    }

    public void writeBinary(byte[] data) throws IOException {
        writeBinary(Base64Variants.getDefaultVariant(), data, 0, data.length);
    }

    public int writeBinary(InputStream data, int dataLength) throws IOException {
        return writeBinary(Base64Variants.getDefaultVariant(), data, dataLength);
    }

    public void writeNumber(short v) throws IOException {
        writeNumber((int) v);
    }

    public void writeObjectId(Object id) throws IOException {
        throw new JsonGenerationException("No native support for writing Object Ids");
    }

    public void writeObjectRef(Object id) throws IOException {
        throw new JsonGenerationException("No native support for writing Object Ids");
    }

    public void writeTypeId(Object id) throws IOException {
        throw new JsonGenerationException("No native support for writing Type Ids");
    }

    public void writeStringField(String fieldName, String value) throws IOException {
        writeFieldName(fieldName);
        writeString(value);
    }

    public final void writeArrayFieldStart(String fieldName) throws IOException {
        writeFieldName(fieldName);
        writeStartArray();
    }

    public final void writeObjectFieldStart(String fieldName) throws IOException {
        writeFieldName(fieldName);
        writeStartObject();
    }

    public final void writeObjectField(String fieldName, Object pojo) throws IOException {
        writeFieldName(fieldName);
        writeObject(pojo);
    }

    public void writeOmittedField(String fieldName) throws IOException {
    }

    public void copyCurrentEvent(JsonParser jp) throws IOException {
        JsonToken t = jp.getCurrentToken();
        if (t == null) {
            _reportError("No current event to copy");
        }
        NumberType n;
        switch (t.id()) {
            case -1:
                _reportError("No current event to copy");
                break;
            case 1:
                break;
            case 2:
                writeEndObject();
                return;
            case 3:
                writeStartArray();
                return;
            case 4:
                writeEndArray();
                return;
            case 5:
                writeFieldName(jp.getCurrentName());
                return;
            case 6:
                if (jp.hasTextCharacters()) {
                    writeString(jp.getTextCharacters(), jp.getTextOffset(), jp.getTextLength());
                    return;
                } else {
                    writeString(jp.getText());
                    return;
                }
            case 7:
                n = jp.getNumberType();
                if (n == NumberType.INT) {
                    writeNumber(jp.getIntValue());
                    return;
                } else if (n == NumberType.BIG_INTEGER) {
                    writeNumber(jp.getBigIntegerValue());
                    return;
                } else {
                    writeNumber(jp.getLongValue());
                    return;
                }
            case 8:
                n = jp.getNumberType();
                if (n == NumberType.BIG_DECIMAL) {
                    writeNumber(jp.getDecimalValue());
                    return;
                } else if (n == NumberType.FLOAT) {
                    writeNumber(jp.getFloatValue());
                    return;
                } else {
                    writeNumber(jp.getDoubleValue());
                    return;
                }
            case 9:
                writeBoolean(true);
                return;
            case 10:
                writeBoolean(false);
                return;
            case 11:
                writeNull();
                return;
            case 12:
                writeObject(jp.getEmbeddedObject());
                return;
            default:
                _throwInternal();
                return;
        }
        writeStartObject();
    }

    public void copyCurrentStructure(JsonParser jp) throws IOException {
        JsonToken t = jp.getCurrentToken();
        if (t == null) {
            _reportError("No current event to copy");
        }
        int id = t.id();
        if (id == 5) {
            writeFieldName(jp.getCurrentName());
            id = jp.nextToken().id();
        }
        switch (id) {
            case 1:
                writeStartObject();
                while (jp.nextToken() != JsonToken.END_OBJECT) {
                    copyCurrentStructure(jp);
                }
                writeEndObject();
                return;
            case 3:
                writeStartArray();
                while (jp.nextToken() != JsonToken.END_ARRAY) {
                    copyCurrentStructure(jp);
                }
                writeEndArray();
                return;
            default:
                copyCurrentEvent(jp);
                return;
        }
    }

    protected void _reportError(String msg) throws JsonGenerationException {
        throw new JsonGenerationException(msg);
    }

    protected final void _throwInternal() {
        VersionUtil.throwInternal();
    }

    protected void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by generator of type " + getClass().getName());
    }

    protected void _writeSimpleObject(Object value) throws IOException {
        if (value == null) {
            writeNull();
        } else if (value instanceof String) {
            writeString((String) value);
        } else {
            if (value instanceof Number) {
                Number n = (Number) value;
                if (n instanceof Integer) {
                    writeNumber(n.intValue());
                    return;
                } else if (n instanceof Long) {
                    writeNumber(n.longValue());
                    return;
                } else if (n instanceof Double) {
                    writeNumber(n.doubleValue());
                    return;
                } else if (n instanceof Float) {
                    writeNumber(n.floatValue());
                    return;
                } else if (n instanceof Short) {
                    writeNumber(n.shortValue());
                    return;
                } else if (n instanceof Byte) {
                    writeNumber((short) n.byteValue());
                    return;
                } else if (n instanceof BigInteger) {
                    writeNumber((BigInteger) n);
                    return;
                } else if (n instanceof BigDecimal) {
                    writeNumber((BigDecimal) n);
                    return;
                } else if (n instanceof AtomicInteger) {
                    writeNumber(((AtomicInteger) n).get());
                    return;
                } else if (n instanceof AtomicLong) {
                    writeNumber(((AtomicLong) n).get());
                    return;
                }
            } else if (value instanceof byte[]) {
                writeBinary((byte[]) value);
                return;
            } else if (value instanceof Boolean) {
                writeBoolean(((Boolean) value).booleanValue());
                return;
            } else if (value instanceof AtomicBoolean) {
                writeBoolean(((AtomicBoolean) value).get());
                return;
            }
            throw new IllegalStateException("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed " + value.getClass().getName() + ")");
        }
    }
}
