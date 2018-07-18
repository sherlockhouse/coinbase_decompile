package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import java.io.IOException;
import java.io.InputStream;

public abstract class GeneratorBase extends JsonGenerator {
    protected boolean _cfgNumbersAsStrings;
    protected boolean _closed;
    protected int _features;
    protected ObjectCodec _objectCodec;
    protected JsonWriteContext _writeContext;

    protected abstract void _releaseBuffers();

    protected abstract void _verifyValueWrite(String str) throws IOException;

    protected GeneratorBase(int features, ObjectCodec codec) {
        this._features = features;
        this._writeContext = JsonWriteContext.createRootContext(Feature.STRICT_DUPLICATE_DETECTION.enabledIn(features) ? DupDetector.rootDetector((JsonGenerator) this) : null);
        this._objectCodec = codec;
        this._cfgNumbersAsStrings = Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(features);
    }

    public JsonGenerator enable(Feature f) {
        this._features |= f.getMask();
        if (f == Feature.WRITE_NUMBERS_AS_STRINGS) {
            this._cfgNumbersAsStrings = true;
        } else if (f == Feature.ESCAPE_NON_ASCII) {
            setHighestNonEscapedChar(127);
        }
        return this;
    }

    public JsonGenerator disable(Feature f) {
        this._features &= f.getMask() ^ -1;
        if (f == Feature.WRITE_NUMBERS_AS_STRINGS) {
            this._cfgNumbersAsStrings = false;
        } else if (f == Feature.ESCAPE_NON_ASCII) {
            setHighestNonEscapedChar(0);
        }
        return this;
    }

    public final boolean isEnabled(Feature f) {
        return (this._features & f.getMask()) != 0;
    }

    public JsonGenerator useDefaultPrettyPrinter() {
        return getPrettyPrinter() != null ? this : setPrettyPrinter(new DefaultPrettyPrinter());
    }

    public final JsonWriteContext getOutputContext() {
        return this._writeContext;
    }

    public void writeFieldName(SerializableString name) throws IOException {
        writeFieldName(name.getValue());
    }

    public void writeString(SerializableString text) throws IOException {
        writeString(text.getValue());
    }

    public void writeRawValue(String text) throws IOException {
        _verifyValueWrite("write raw value");
        writeRaw(text);
    }

    public int writeBinary(Base64Variant b64variant, InputStream data, int dataLength) throws IOException {
        _reportUnsupportedOperation();
        return 0;
    }

    public void writeObject(Object value) throws IOException {
        if (value == null) {
            writeNull();
        } else if (this._objectCodec != null) {
            this._objectCodec.writeValue(this, value);
        } else {
            _writeSimpleObject(value);
        }
    }

    public void close() throws IOException {
        this._closed = true;
    }
}
