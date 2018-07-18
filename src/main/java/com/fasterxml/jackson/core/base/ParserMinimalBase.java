package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;

public abstract class ParserMinimalBase extends JsonParser {
    protected JsonToken _currToken;
    protected JsonToken _lastClearedToken;

    protected abstract void _handleEOF() throws JsonParseException;

    public abstract String getText() throws IOException;

    public abstract JsonToken nextToken() throws IOException, JsonParseException;

    protected ParserMinimalBase() {
    }

    protected ParserMinimalBase(int features) {
        super(features);
    }

    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    public final int getCurrentTokenId() {
        JsonToken t = this._currToken;
        return t == null ? 0 : t.id();
    }

    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    public JsonToken nextValue() throws IOException {
        JsonToken t = nextToken();
        if (t == JsonToken.FIELD_NAME) {
            return nextToken();
        }
        return t;
    }

    public JsonParser skipChildren() throws IOException {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            int open = 1;
            while (true) {
                JsonToken t = nextToken();
                if (t == null) {
                    break;
                } else if (t.isStructStart()) {
                    open++;
                } else if (t.isStructEnd()) {
                    open--;
                    if (open == 0) {
                        break;
                    }
                } else {
                    continue;
                }
            }
            _handleEOF();
        }
        return this;
    }

    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._lastClearedToken = this._currToken;
            this._currToken = null;
        }
    }

    public int getValueAsInt(int defaultValue) throws IOException {
        JsonToken t = this._currToken;
        if (t == null) {
            return defaultValue;
        }
        switch (t.id()) {
            case 6:
                String str = getText();
                if (_hasTextualNull(str)) {
                    return 0;
                }
                return NumberInput.parseAsInt(str, defaultValue);
            case 7:
            case 8:
                return getIntValue();
            case 9:
                return 1;
            case 10:
                return 0;
            case 11:
                return 0;
            case 12:
                Object value = getEmbeddedObject();
                if (value instanceof Number) {
                    return ((Number) value).intValue();
                }
                return defaultValue;
            default:
                return defaultValue;
        }
    }

    public long getValueAsLong(long defaultValue) throws IOException {
        JsonToken t = this._currToken;
        if (t == null) {
            return defaultValue;
        }
        switch (t.id()) {
            case 6:
                String str = getText();
                if (_hasTextualNull(str)) {
                    return 0;
                }
                return NumberInput.parseAsLong(str, defaultValue);
            case 7:
            case 8:
                return getLongValue();
            case 9:
                return 1;
            case 10:
            case 11:
                return 0;
            case 12:
                Object value = getEmbeddedObject();
                if (value instanceof Number) {
                    return ((Number) value).longValue();
                }
                return defaultValue;
            default:
                return defaultValue;
        }
    }

    public String getValueAsString(String defaultValue) throws IOException {
        return (this._currToken == JsonToken.VALUE_STRING || !(this._currToken == null || this._currToken == JsonToken.VALUE_NULL || !this._currToken.isScalarValue())) ? getText() : defaultValue;
    }

    protected void _decodeBase64(String str, ByteArrayBuilder builder, Base64Variant b64variant) throws IOException {
        try {
            b64variant.decode(str, builder);
        } catch (IllegalArgumentException e) {
            _reportError(e.getMessage());
        }
    }

    protected boolean _hasTextualNull(String value) {
        return "null".equals(value);
    }

    protected void _reportUnexpectedChar(int ch, String comment) throws JsonParseException {
        if (ch < 0) {
            _reportInvalidEOF();
        }
        String msg = "Unexpected character (" + _getCharDesc(ch) + ")";
        if (comment != null) {
            msg = msg + ": " + comment;
        }
        _reportError(msg);
    }

    protected void _reportInvalidEOF() throws JsonParseException {
        _reportInvalidEOF(" in " + this._currToken);
    }

    protected void _reportInvalidEOF(String msg) throws JsonParseException {
        _reportError("Unexpected end-of-input" + msg);
    }

    protected void _reportInvalidEOFInValue() throws JsonParseException {
        _reportInvalidEOF(" in a value");
    }

    protected void _reportMissingRootWS(int ch) throws JsonParseException {
        _reportUnexpectedChar(ch, "Expected space separating root-level values");
    }

    protected void _throwInvalidSpace(int i) throws JsonParseException {
        _reportError("Illegal character (" + _getCharDesc((char) i) + "): only regular white space (\\r, \\n, \\t) is allowed between tokens");
    }

    protected void _throwUnquotedSpace(int i, String ctxtDesc) throws JsonParseException {
        if (!isEnabled(Feature.ALLOW_UNQUOTED_CONTROL_CHARS) || i > 32) {
            _reportError("Illegal unquoted character (" + _getCharDesc((char) i) + "): has to be escaped using backslash to be included in " + ctxtDesc);
        }
    }

    protected char _handleUnrecognizedCharacterEscape(char ch) throws JsonProcessingException {
        if (!(isEnabled(Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER) || (ch == '\'' && isEnabled(Feature.ALLOW_SINGLE_QUOTES)))) {
            _reportError("Unrecognized character escape " + _getCharDesc(ch));
        }
        return ch;
    }

    protected static final String _getCharDesc(int ch) {
        char c = (char) ch;
        if (Character.isISOControl(c)) {
            return "(CTRL-CHAR, code " + ch + ")";
        }
        if (ch > 255) {
            return "'" + c + "' (code " + ch + " / 0x" + Integer.toHexString(ch) + ")";
        }
        return "'" + c + "' (code " + ch + ")";
    }

    protected final void _reportError(String msg) throws JsonParseException {
        throw _constructError(msg);
    }

    protected final void _wrapError(String msg, Throwable t) throws JsonParseException {
        throw _constructError(msg, t);
    }

    protected final void _throwInternal() {
        VersionUtil.throwInternal();
    }

    protected final JsonParseException _constructError(String msg, Throwable t) {
        return new JsonParseException(msg, getCurrentLocation(), t);
    }
}
