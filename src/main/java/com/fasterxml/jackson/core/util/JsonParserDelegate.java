package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonParserDelegate extends JsonParser {
    protected JsonParser delegate;

    public JsonParserDelegate(JsonParser d) {
        this.delegate = d;
    }

    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }

    public boolean isEnabled(Feature f) {
        return this.delegate.isEnabled(f);
    }

    public void close() throws IOException {
        this.delegate.close();
    }

    public JsonToken getCurrentToken() {
        return this.delegate.getCurrentToken();
    }

    public int getCurrentTokenId() {
        return this.delegate.getCurrentTokenId();
    }

    public boolean hasCurrentToken() {
        return this.delegate.hasCurrentToken();
    }

    public String getCurrentName() throws IOException, JsonParseException {
        return this.delegate.getCurrentName();
    }

    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    public boolean isExpectedStartArrayToken() {
        return this.delegate.isExpectedStartArrayToken();
    }

    public void clearCurrentToken() {
        this.delegate.clearCurrentToken();
    }

    public String getText() throws IOException, JsonParseException {
        return this.delegate.getText();
    }

    public boolean hasTextCharacters() {
        return this.delegate.hasTextCharacters();
    }

    public char[] getTextCharacters() throws IOException, JsonParseException {
        return this.delegate.getTextCharacters();
    }

    public int getTextLength() throws IOException, JsonParseException {
        return this.delegate.getTextLength();
    }

    public int getTextOffset() throws IOException, JsonParseException {
        return this.delegate.getTextOffset();
    }

    public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
        return this.delegate.getBigIntegerValue();
    }

    public byte getByteValue() throws IOException, JsonParseException {
        return this.delegate.getByteValue();
    }

    public short getShortValue() throws IOException, JsonParseException {
        return this.delegate.getShortValue();
    }

    public BigDecimal getDecimalValue() throws IOException, JsonParseException {
        return this.delegate.getDecimalValue();
    }

    public double getDoubleValue() throws IOException, JsonParseException {
        return this.delegate.getDoubleValue();
    }

    public float getFloatValue() throws IOException, JsonParseException {
        return this.delegate.getFloatValue();
    }

    public int getIntValue() throws IOException, JsonParseException {
        return this.delegate.getIntValue();
    }

    public long getLongValue() throws IOException, JsonParseException {
        return this.delegate.getLongValue();
    }

    public NumberType getNumberType() throws IOException, JsonParseException {
        return this.delegate.getNumberType();
    }

    public Number getNumberValue() throws IOException, JsonParseException {
        return this.delegate.getNumberValue();
    }

    public int getValueAsInt() throws IOException, JsonParseException {
        return this.delegate.getValueAsInt();
    }

    public int getValueAsInt(int defaultValue) throws IOException, JsonParseException {
        return this.delegate.getValueAsInt(defaultValue);
    }

    public long getValueAsLong() throws IOException, JsonParseException {
        return this.delegate.getValueAsLong();
    }

    public long getValueAsLong(long defaultValue) throws IOException, JsonParseException {
        return this.delegate.getValueAsLong(defaultValue);
    }

    public String getValueAsString() throws IOException, JsonParseException {
        return this.delegate.getValueAsString();
    }

    public String getValueAsString(String defaultValue) throws IOException, JsonParseException {
        return this.delegate.getValueAsString(defaultValue);
    }

    public Object getEmbeddedObject() throws IOException, JsonParseException {
        return this.delegate.getEmbeddedObject();
    }

    public byte[] getBinaryValue(Base64Variant b64variant) throws IOException, JsonParseException {
        return this.delegate.getBinaryValue(b64variant);
    }

    public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException, JsonParseException {
        return this.delegate.readBinaryValue(b64variant, out);
    }

    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    public JsonToken nextToken() throws IOException, JsonParseException {
        return this.delegate.nextToken();
    }

    public JsonToken nextValue() throws IOException, JsonParseException {
        return this.delegate.nextValue();
    }

    public JsonParser skipChildren() throws IOException, JsonParseException {
        this.delegate.skipChildren();
        return this;
    }

    public boolean canReadObjectId() {
        return this.delegate.canReadObjectId();
    }

    public boolean canReadTypeId() {
        return this.delegate.canReadTypeId();
    }

    public Object getObjectId() throws IOException, JsonGenerationException {
        return this.delegate.getObjectId();
    }

    public Object getTypeId() throws IOException, JsonGenerationException {
        return this.delegate.getTypeId();
    }
}
