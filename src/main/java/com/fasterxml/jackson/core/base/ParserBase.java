package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.NumberInput;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class ParserBase extends ParserMinimalBase {
    static final BigDecimal BD_MAX_INT = new BigDecimal(BI_MAX_INT);
    static final BigDecimal BD_MAX_LONG = new BigDecimal(BI_MAX_LONG);
    static final BigDecimal BD_MIN_INT = new BigDecimal(BI_MIN_INT);
    static final BigDecimal BD_MIN_LONG = new BigDecimal(BI_MIN_LONG);
    static final BigInteger BI_MAX_INT = BigInteger.valueOf(2147483647L);
    static final BigInteger BI_MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
    static final BigInteger BI_MIN_INT = BigInteger.valueOf(-2147483648L);
    static final BigInteger BI_MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
    protected byte[] _binaryValue;
    protected ByteArrayBuilder _byteArrayBuilder = null;
    protected boolean _closed;
    protected long _currInputProcessed = 0;
    protected int _currInputRow = 1;
    protected int _currInputRowStart = 0;
    protected int _expLength;
    protected int _fractLength;
    protected int _inputEnd = 0;
    protected int _inputPtr = 0;
    protected int _intLength;
    protected final IOContext _ioContext;
    protected boolean _nameCopied = false;
    protected char[] _nameCopyBuffer = null;
    protected JsonToken _nextToken;
    protected int _numTypesValid = 0;
    protected BigDecimal _numberBigDecimal;
    protected BigInteger _numberBigInt;
    protected double _numberDouble;
    protected int _numberInt;
    protected long _numberLong;
    protected boolean _numberNegative;
    protected JsonReadContext _parsingContext;
    protected final TextBuffer _textBuffer;
    protected int _tokenInputCol = 0;
    protected int _tokenInputRow = 1;
    protected long _tokenInputTotal = 0;

    protected abstract void _closeInput() throws IOException;

    protected abstract void _finishString() throws IOException;

    protected abstract boolean loadMore() throws IOException;

    protected ParserBase(IOContext ctxt, int features) {
        DupDetector dups = null;
        this._features = features;
        this._ioContext = ctxt;
        this._textBuffer = ctxt.constructTextBuffer();
        if (Feature.STRICT_DUPLICATE_DETECTION.enabledIn(features)) {
            dups = DupDetector.rootDetector((JsonParser) this);
        }
        this._parsingContext = JsonReadContext.createRootContext(dups);
    }

    public String getCurrentName() throws IOException {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            return this._parsingContext.getParent().getCurrentName();
        }
        return this._parsingContext.getCurrentName();
    }

    public void close() throws IOException {
        if (!this._closed) {
            this._closed = true;
            try {
                _closeInput();
            } finally {
                _releaseBuffers();
            }
        }
    }

    public JsonLocation getTokenLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), -1, getTokenCharacterOffset(), getTokenLineNr(), getTokenColumnNr());
    }

    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), -1, this._currInputProcessed + ((long) this._inputPtr), this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
    }

    public boolean hasTextCharacters() {
        if (this._currToken == JsonToken.VALUE_STRING) {
            return true;
        }
        if (this._currToken == JsonToken.FIELD_NAME) {
            return this._nameCopied;
        }
        return false;
    }

    public Object getEmbeddedObject() throws IOException {
        return null;
    }

    public long getTokenCharacterOffset() {
        return this._tokenInputTotal;
    }

    public int getTokenLineNr() {
        return this._tokenInputRow;
    }

    public int getTokenColumnNr() {
        int col = this._tokenInputCol;
        return col < 0 ? col : col + 1;
    }

    protected final void loadMoreGuaranteed() throws IOException {
        if (!loadMore()) {
            _reportInvalidEOF();
        }
    }

    protected void _releaseBuffers() throws IOException {
        this._textBuffer.releaseBuffers();
        char[] buf = this._nameCopyBuffer;
        if (buf != null) {
            this._nameCopyBuffer = null;
            this._ioContext.releaseNameCopyBuffer(buf);
        }
    }

    protected void _handleEOF() throws JsonParseException {
        if (!this._parsingContext.inRoot()) {
            _reportInvalidEOF(": expected close marker for " + this._parsingContext.getTypeDesc() + " (from " + this._parsingContext.getStartLocation(this._ioContext.getSourceReference()) + ")");
        }
    }

    protected final int _eofAsNextChar() throws JsonParseException {
        _handleEOF();
        return -1;
    }

    protected void _reportMismatchedEndMarker(int actCh, char expCh) throws JsonParseException {
        _reportError("Unexpected close marker '" + ((char) actCh) + "': expected '" + expCh + "' (for " + this._parsingContext.getTypeDesc() + " starting at " + ("" + this._parsingContext.getStartLocation(this._ioContext.getSourceReference())) + ")");
    }

    public ByteArrayBuilder _getByteArrayBuilder() {
        if (this._byteArrayBuilder == null) {
            this._byteArrayBuilder = new ByteArrayBuilder();
        } else {
            this._byteArrayBuilder.reset();
        }
        return this._byteArrayBuilder;
    }

    protected final JsonToken reset(boolean negative, int intLen, int fractLen, int expLen) {
        if (fractLen >= 1 || expLen >= 1) {
            return resetFloat(negative, intLen, fractLen, expLen);
        }
        return resetInt(negative, intLen);
    }

    protected final JsonToken resetInt(boolean negative, int intLen) {
        this._numberNegative = negative;
        this._intLength = intLen;
        this._fractLength = 0;
        this._expLength = 0;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_INT;
    }

    protected final JsonToken resetFloat(boolean negative, int intLen, int fractLen, int expLen) {
        this._numberNegative = negative;
        this._intLength = intLen;
        this._fractLength = fractLen;
        this._expLength = expLen;
        this._numTypesValid = 0;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    protected final JsonToken resetAsNaN(String valueStr, double value) {
        this._textBuffer.resetWithString(valueStr);
        this._numberDouble = value;
        this._numTypesValid = 8;
        return JsonToken.VALUE_NUMBER_FLOAT;
    }

    public Number getNumberValue() throws IOException {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            if ((this._numTypesValid & 1) != 0) {
                return Integer.valueOf(this._numberInt);
            }
            if ((this._numTypesValid & 2) != 0) {
                return Long.valueOf(this._numberLong);
            }
            if ((this._numTypesValid & 4) != 0) {
                return this._numberBigInt;
            }
            return this._numberBigDecimal;
        } else if ((this._numTypesValid & 16) != 0) {
            return this._numberBigDecimal;
        } else {
            if ((this._numTypesValid & 8) == 0) {
                _throwInternal();
            }
            return Double.valueOf(this._numberDouble);
        }
    }

    public NumberType getNumberType() throws IOException {
        if (this._numTypesValid == 0) {
            _parseNumericValue(0);
        }
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            if ((this._numTypesValid & 1) != 0) {
                return NumberType.INT;
            }
            if ((this._numTypesValid & 2) != 0) {
                return NumberType.LONG;
            }
            return NumberType.BIG_INTEGER;
        } else if ((this._numTypesValid & 16) != 0) {
            return NumberType.BIG_DECIMAL;
        } else {
            return NumberType.DOUBLE;
        }
    }

    public int getIntValue() throws IOException {
        if ((this._numTypesValid & 1) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(1);
            }
            if ((this._numTypesValid & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    public long getLongValue() throws IOException {
        if ((this._numTypesValid & 2) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(2);
            }
            if ((this._numTypesValid & 2) == 0) {
                convertNumberToLong();
            }
        }
        return this._numberLong;
    }

    public BigInteger getBigIntegerValue() throws IOException {
        if ((this._numTypesValid & 4) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(4);
            }
            if ((this._numTypesValid & 4) == 0) {
                convertNumberToBigInteger();
            }
        }
        return this._numberBigInt;
    }

    public float getFloatValue() throws IOException {
        return (float) getDoubleValue();
    }

    public double getDoubleValue() throws IOException {
        if ((this._numTypesValid & 8) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(8);
            }
            if ((this._numTypesValid & 8) == 0) {
                convertNumberToDouble();
            }
        }
        return this._numberDouble;
    }

    public BigDecimal getDecimalValue() throws IOException {
        if ((this._numTypesValid & 16) == 0) {
            if (this._numTypesValid == 0) {
                _parseNumericValue(16);
            }
            if ((this._numTypesValid & 16) == 0) {
                convertNumberToBigDecimal();
            }
        }
        return this._numberBigDecimal;
    }

    protected void _parseNumericValue(int expType) throws IOException {
        if (this._currToken == JsonToken.VALUE_NUMBER_INT) {
            char[] buf = this._textBuffer.getTextBuffer();
            int offset = this._textBuffer.getTextOffset();
            int len = this._intLength;
            if (this._numberNegative) {
                offset++;
            }
            if (len <= 9) {
                int i = NumberInput.parseInt(buf, offset, len);
                if (this._numberNegative) {
                    i = -i;
                }
                this._numberInt = i;
                this._numTypesValid = 1;
            } else if (len <= 18) {
                long l = NumberInput.parseLong(buf, offset, len);
                if (this._numberNegative) {
                    l = -l;
                }
                if (len == 10) {
                    if (this._numberNegative) {
                        if (l >= -2147483648L) {
                            this._numberInt = (int) l;
                            this._numTypesValid = 1;
                            return;
                        }
                    } else if (l <= 2147483647L) {
                        this._numberInt = (int) l;
                        this._numTypesValid = 1;
                        return;
                    }
                }
                this._numberLong = l;
                this._numTypesValid = 2;
            } else {
                _parseSlowInt(expType, buf, offset, len);
            }
        } else if (this._currToken == JsonToken.VALUE_NUMBER_FLOAT) {
            _parseSlowFloat(expType);
        } else {
            _reportError("Current token (" + this._currToken + ") not numeric, can not use numeric value accessors");
        }
    }

    private void _parseSlowFloat(int expType) throws IOException {
        if (expType == 16) {
            try {
                this._numberBigDecimal = this._textBuffer.contentsAsDecimal();
                this._numTypesValid = 16;
                return;
            } catch (NumberFormatException nex) {
                _wrapError("Malformed numeric value '" + this._textBuffer.contentsAsString() + "'", nex);
                return;
            }
        }
        this._numberDouble = this._textBuffer.contentsAsDouble();
        this._numTypesValid = 8;
    }

    private void _parseSlowInt(int expType, char[] buf, int offset, int len) throws IOException {
        String numStr = this._textBuffer.contentsAsString();
        try {
            if (NumberInput.inLongRange(buf, offset, len, this._numberNegative)) {
                this._numberLong = Long.parseLong(numStr);
                this._numTypesValid = 2;
                return;
            }
            this._numberBigInt = new BigInteger(numStr);
            this._numTypesValid = 4;
        } catch (NumberFormatException nex) {
            _wrapError("Malformed numeric value '" + numStr + "'", nex);
        }
    }

    protected void convertNumberToInt() throws IOException {
        if ((this._numTypesValid & 2) != 0) {
            int result = (int) this._numberLong;
            if (((long) result) != this._numberLong) {
                _reportError("Numeric value (" + getText() + ") out of range of int");
            }
            this._numberInt = result;
        } else if ((this._numTypesValid & 4) != 0) {
            if (BI_MIN_INT.compareTo(this._numberBigInt) > 0 || BI_MAX_INT.compareTo(this._numberBigInt) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigInt.intValue();
        } else if ((this._numTypesValid & 8) != 0) {
            if (this._numberDouble < -2.147483648E9d || this._numberDouble > 2.147483647E9d) {
                reportOverflowInt();
            }
            this._numberInt = (int) this._numberDouble;
        } else if ((this._numTypesValid & 16) != 0) {
            if (BD_MIN_INT.compareTo(this._numberBigDecimal) > 0 || BD_MAX_INT.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowInt();
            }
            this._numberInt = this._numberBigDecimal.intValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 1;
    }

    protected void convertNumberToLong() throws IOException {
        if ((this._numTypesValid & 1) != 0) {
            this._numberLong = (long) this._numberInt;
        } else if ((this._numTypesValid & 4) != 0) {
            if (BI_MIN_LONG.compareTo(this._numberBigInt) > 0 || BI_MAX_LONG.compareTo(this._numberBigInt) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigInt.longValue();
        } else if ((this._numTypesValid & 8) != 0) {
            if (this._numberDouble < -9.223372036854776E18d || this._numberDouble > 9.223372036854776E18d) {
                reportOverflowLong();
            }
            this._numberLong = (long) this._numberDouble;
        } else if ((this._numTypesValid & 16) != 0) {
            if (BD_MIN_LONG.compareTo(this._numberBigDecimal) > 0 || BD_MAX_LONG.compareTo(this._numberBigDecimal) < 0) {
                reportOverflowLong();
            }
            this._numberLong = this._numberBigDecimal.longValue();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 2;
    }

    protected void convertNumberToBigInteger() throws IOException {
        if ((this._numTypesValid & 16) != 0) {
            this._numberBigInt = this._numberBigDecimal.toBigInteger();
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberBigInt = BigInteger.valueOf(this._numberLong);
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberBigInt = BigInteger.valueOf((long) this._numberInt);
        } else if ((this._numTypesValid & 8) != 0) {
            this._numberBigInt = BigDecimal.valueOf(this._numberDouble).toBigInteger();
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 4;
    }

    protected void convertNumberToDouble() throws IOException {
        if ((this._numTypesValid & 16) != 0) {
            this._numberDouble = this._numberBigDecimal.doubleValue();
        } else if ((this._numTypesValid & 4) != 0) {
            this._numberDouble = this._numberBigInt.doubleValue();
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberDouble = (double) this._numberLong;
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberDouble = (double) this._numberInt;
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 8;
    }

    protected void convertNumberToBigDecimal() throws IOException {
        if ((this._numTypesValid & 8) != 0) {
            this._numberBigDecimal = NumberInput.parseBigDecimal(getText());
        } else if ((this._numTypesValid & 4) != 0) {
            this._numberBigDecimal = new BigDecimal(this._numberBigInt);
        } else if ((this._numTypesValid & 2) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf(this._numberLong);
        } else if ((this._numTypesValid & 1) != 0) {
            this._numberBigDecimal = BigDecimal.valueOf((long) this._numberInt);
        } else {
            _throwInternal();
        }
        this._numTypesValid |= 16;
    }

    protected void reportUnexpectedNumberChar(int ch, String comment) throws JsonParseException {
        String msg = "Unexpected character (" + ParserMinimalBase._getCharDesc(ch) + ") in numeric value";
        if (comment != null) {
            msg = msg + ": " + comment;
        }
        _reportError(msg);
    }

    protected void reportInvalidNumber(String msg) throws JsonParseException {
        _reportError("Invalid numeric value: " + msg);
    }

    protected void reportOverflowInt() throws IOException {
        _reportError("Numeric value (" + getText() + ") out of range of int (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")");
    }

    protected void reportOverflowLong() throws IOException {
        _reportError("Numeric value (" + getText() + ") out of range of long (" + Long.MIN_VALUE + " - " + Long.MAX_VALUE + ")");
    }

    protected char _decodeEscaped() throws IOException {
        throw new UnsupportedOperationException();
    }

    protected final int _decodeBase64Escape(Base64Variant b64variant, char ch, int index) throws IOException {
        if (ch != '\\') {
            throw reportInvalidBase64Char(b64variant, ch, index);
        }
        char unescaped = _decodeEscaped();
        if (unescaped <= ' ' && index == 0) {
            return -1;
        }
        int bits = b64variant.decodeBase64Char(unescaped);
        if (bits >= 0) {
            return bits;
        }
        throw reportInvalidBase64Char(b64variant, unescaped, index);
    }

    protected IllegalArgumentException reportInvalidBase64Char(Base64Variant b64variant, int ch, int bindex) throws IllegalArgumentException {
        return reportInvalidBase64Char(b64variant, ch, bindex, null);
    }

    protected IllegalArgumentException reportInvalidBase64Char(Base64Variant b64variant, int ch, int bindex, String msg) throws IllegalArgumentException {
        String base;
        if (ch <= 32) {
            base = "Illegal white space character (code 0x" + Integer.toHexString(ch) + ") as character #" + (bindex + 1) + " of 4-char base64 unit: can only used between units";
        } else if (b64variant.usesPaddingChar(ch)) {
            base = "Unexpected padding character ('" + b64variant.getPaddingChar() + "') as character #" + (bindex + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(ch) || Character.isISOControl(ch)) {
            base = "Illegal character (code 0x" + Integer.toHexString(ch) + ") in base64 content";
        } else {
            base = "Illegal character '" + ((char) ch) + "' (code 0x" + Integer.toHexString(ch) + ") in base64 content";
        }
        if (msg != null) {
            base = base + ": " + msg;
        }
        return new IllegalArgumentException(base);
    }
}
