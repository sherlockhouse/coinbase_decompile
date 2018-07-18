package com.fasterxml.jackson.core.json;

import com.coinbase.android.ui.NumericKeypadConnector;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

public class ReaderBasedJsonParser extends ParserBase {
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
    protected boolean _bufferRecyclable;
    protected final int _hashSeed;
    protected char[] _inputBuffer;
    protected ObjectCodec _objectCodec;
    protected Reader _reader;
    protected final CharsToNameCanonicalizer _symbols;
    protected boolean _tokenIncomplete = false;

    public ReaderBasedJsonParser(IOContext ctxt, int features, Reader r, ObjectCodec codec, CharsToNameCanonicalizer st, char[] inputBuffer, int start, int end, boolean bufferRecyclable) {
        super(ctxt, features);
        this._reader = r;
        this._inputBuffer = inputBuffer;
        this._inputPtr = start;
        this._inputEnd = end;
        this._objectCodec = codec;
        this._symbols = st;
        this._hashSeed = st.hashSeed();
        this._bufferRecyclable = bufferRecyclable;
    }

    public ReaderBasedJsonParser(IOContext ctxt, int features, Reader r, ObjectCodec codec, CharsToNameCanonicalizer st) {
        super(ctxt, features);
        this._reader = r;
        this._inputBuffer = ctxt.allocTokenBuffer();
        this._inputPtr = 0;
        this._inputEnd = 0;
        this._objectCodec = codec;
        this._symbols = st;
        this._hashSeed = st.hashSeed();
        this._bufferRecyclable = true;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    protected boolean loadMore() throws IOException {
        this._currInputProcessed += (long) this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        if (this._reader == null) {
            return false;
        }
        int count = this._reader.read(this._inputBuffer, 0, this._inputBuffer.length);
        if (count > 0) {
            this._inputPtr = 0;
            this._inputEnd = count;
            return true;
        }
        _closeInput();
        if (count != 0) {
            return false;
        }
        throw new IOException("Reader returned 0 characters when trying to read " + this._inputEnd);
    }

    protected char getNextChar(String eofMsg) throws IOException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(eofMsg);
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return cArr[i];
    }

    protected void _closeInput() throws IOException {
        if (this._reader != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(Feature.AUTO_CLOSE_SOURCE)) {
                this._reader.close();
            }
            this._reader = null;
        }
    }

    protected void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        this._symbols.release();
        if (this._bufferRecyclable) {
            char[] buf = this._inputBuffer;
            if (buf != null) {
                this._inputBuffer = null;
                this._ioContext.releaseTokenBuffer(buf);
            }
        }
    }

    public final String getText() throws IOException {
        JsonToken t = this._currToken;
        if (t != JsonToken.VALUE_STRING) {
            return _getText2(t);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    public final String getValueAsString() throws IOException {
        if (this._currToken != JsonToken.VALUE_STRING) {
            return super.getValueAsString(null);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    public final String getValueAsString(String defValue) throws IOException {
        if (this._currToken != JsonToken.VALUE_STRING) {
            return super.getValueAsString(defValue);
        }
        if (this._tokenIncomplete) {
            this._tokenIncomplete = false;
            _finishString();
        }
        return this._textBuffer.contentsAsString();
    }

    protected final String _getText2(JsonToken t) {
        if (t == null) {
            return null;
        }
        switch (t.id()) {
            case 5:
                return this._parsingContext.getCurrentName();
            case 6:
            case 7:
            case 8:
                return this._textBuffer.contentsAsString();
            default:
                return t.asString();
        }
    }

    public final char[] getTextCharacters() throws IOException {
        if (this._currToken == null) {
            return null;
        }
        switch (this._currToken.id()) {
            case 5:
                if (!this._nameCopied) {
                    String name = this._parsingContext.getCurrentName();
                    int nameLen = name.length();
                    if (this._nameCopyBuffer == null) {
                        this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(nameLen);
                    } else if (this._nameCopyBuffer.length < nameLen) {
                        this._nameCopyBuffer = new char[nameLen];
                    }
                    name.getChars(0, nameLen, this._nameCopyBuffer, 0);
                    this._nameCopied = true;
                }
                return this._nameCopyBuffer;
            case 6:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case 7:
            case 8:
                break;
            default:
                return this._currToken.asCharArray();
        }
        return this._textBuffer.getTextBuffer();
    }

    public final int getTextLength() throws IOException {
        if (this._currToken == null) {
            return 0;
        }
        switch (this._currToken.id()) {
            case 5:
                return this._parsingContext.getCurrentName().length();
            case 6:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case 7:
            case 8:
                break;
            default:
                return this._currToken.asCharArray().length;
        }
        return this._textBuffer.size();
    }

    public final int getTextOffset() throws IOException {
        if (this._currToken == null) {
            return 0;
        }
        switch (this._currToken.id()) {
            case 6:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case 7:
            case 8:
                break;
            default:
                return 0;
        }
        return this._textBuffer.getTextOffset();
    }

    public byte[] getBinaryValue(Base64Variant b64variant) throws IOException {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(b64variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException iae) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + b64variant + "): " + iae.getMessage());
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder builder = _getByteArrayBuilder();
            _decodeBase64(getText(), builder, b64variant);
            this._binaryValue = builder.toByteArray();
        }
        return this._binaryValue;
    }

    public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException {
        if (this._tokenIncomplete && this._currToken == JsonToken.VALUE_STRING) {
            byte[] buf = this._ioContext.allocBase64Buffer();
            try {
                int _readBinary = _readBinary(b64variant, out, buf);
                return _readBinary;
            } finally {
                this._ioContext.releaseBase64Buffer(buf);
            }
        } else {
            byte[] b = getBinaryValue(b64variant);
            out.write(b);
            return b.length;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected int _readBinary(Base64Variant b64variant, OutputStream out, byte[] buffer) throws IOException {
        int outputPtr = 0;
        int outputEnd = buffer.length - 3;
        int outputCount = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char ch = cArr[i];
            if (ch > ' ') {
                int outputPtr2;
                int bits = b64variant.decodeBase64Char(ch);
                if (bits < 0) {
                    if (ch == '\"') {
                        break;
                    }
                    bits = _decodeBase64Escape(b64variant, ch, 0);
                    if (bits < 0) {
                        continue;
                    }
                }
                if (outputPtr > outputEnd) {
                    outputCount += outputPtr;
                    out.write(buffer, 0, outputPtr);
                    outputPtr = 0;
                }
                int decodedData = bits;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                cArr = this._inputBuffer;
                i = this._inputPtr;
                this._inputPtr = i + 1;
                ch = cArr[i];
                bits = b64variant.decodeBase64Char(ch);
                if (bits < 0) {
                    bits = _decodeBase64Escape(b64variant, ch, 1);
                }
                decodedData = (decodedData << 6) | bits;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                cArr = this._inputBuffer;
                i = this._inputPtr;
                this._inputPtr = i + 1;
                ch = cArr[i];
                bits = b64variant.decodeBase64Char(ch);
                if (bits < 0) {
                    if (bits != -2) {
                        if (ch == '\"' && !b64variant.usesPadding()) {
                            break;
                        }
                        bits = _decodeBase64Escape(b64variant, ch, 2);
                    }
                    if (bits == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        cArr = this._inputBuffer;
                        i = this._inputPtr;
                        this._inputPtr = i + 1;
                        ch = cArr[i];
                        if (b64variant.usesPaddingChar(ch)) {
                            outputPtr2 = outputPtr + 1;
                            buffer[outputPtr] = (byte) (decodedData >> 4);
                            outputPtr = outputPtr2;
                        } else {
                            throw reportInvalidBase64Char(b64variant, ch, 3, "expected padding character '" + b64variant.getPaddingChar() + "'");
                        }
                    }
                }
                decodedData = (decodedData << 6) | bits;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                cArr = this._inputBuffer;
                i = this._inputPtr;
                this._inputPtr = i + 1;
                ch = cArr[i];
                bits = b64variant.decodeBase64Char(ch);
                if (bits < 0) {
                    if (bits != -2) {
                        if (ch == '\"' && !b64variant.usesPadding()) {
                            break;
                        }
                        bits = _decodeBase64Escape(b64variant, ch, 3);
                    }
                    if (bits == -2) {
                        decodedData >>= 2;
                        outputPtr2 = outputPtr + 1;
                        buffer[outputPtr] = (byte) (decodedData >> 8);
                        outputPtr = outputPtr2 + 1;
                        buffer[outputPtr2] = (byte) decodedData;
                    }
                }
                decodedData = (decodedData << 6) | bits;
                outputPtr2 = outputPtr + 1;
                buffer[outputPtr] = (byte) (decodedData >> 16);
                outputPtr = outputPtr2 + 1;
                buffer[outputPtr2] = (byte) (decodedData >> 8);
                outputPtr2 = outputPtr + 1;
                buffer[outputPtr] = (byte) decodedData;
                outputPtr = outputPtr2;
            }
        }
        this._tokenIncomplete = false;
        if (outputPtr <= 0) {
            return outputCount;
        }
        outputCount += outputPtr;
        out.write(buffer, 0, outputPtr);
        return outputCount;
    }

    public final JsonToken nextToken() throws IOException {
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        if (this._tokenIncomplete) {
            _skipString();
        }
        int i = _skipWSOrEnd();
        if (i < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._tokenInputTotal = (this._currInputProcessed + ((long) this._inputPtr)) - 1;
        this._tokenInputRow = this._currInputRow;
        this._tokenInputCol = (this._inputPtr - this._currInputRowStart) - 1;
        this._binaryValue = null;
        JsonToken jsonToken;
        if (i == 93) {
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(i, '}');
            }
            this._parsingContext = this._parsingContext.getParent();
            jsonToken = JsonToken.END_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        } else if (i == 125) {
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(i, ']');
            }
            this._parsingContext = this._parsingContext.getParent();
            jsonToken = JsonToken.END_OBJECT;
            this._currToken = jsonToken;
            return jsonToken;
        } else {
            if (this._parsingContext.expectComma()) {
                i = _skipComma(i);
            }
            boolean inObject = this._parsingContext.inObject();
            if (inObject) {
                this._parsingContext.setCurrentName(i == 34 ? _parseName() : _handleOddName(i));
                this._currToken = JsonToken.FIELD_NAME;
                i = _skipColon();
            }
            switch (i) {
                case 34:
                    this._tokenIncomplete = true;
                    jsonToken = JsonToken.VALUE_STRING;
                    break;
                case 45:
                    jsonToken = _parseNegNumber();
                    break;
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    jsonToken = _parsePosNumber(i);
                    break;
                case 91:
                    if (!inObject) {
                        this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                    }
                    jsonToken = JsonToken.START_ARRAY;
                    break;
                case 93:
                case 125:
                    _reportUnexpectedChar(i, "expected a value");
                    break;
                case 102:
                    _matchFalse();
                    jsonToken = JsonToken.VALUE_FALSE;
                    break;
                case 110:
                    _matchNull();
                    jsonToken = JsonToken.VALUE_NULL;
                    break;
                case 116:
                    break;
                case 123:
                    if (!inObject) {
                        this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                    }
                    jsonToken = JsonToken.START_OBJECT;
                    break;
                default:
                    jsonToken = _handleOddValue(i);
                    break;
            }
            _matchTrue();
            jsonToken = JsonToken.VALUE_TRUE;
            if (inObject) {
                this._nextToken = jsonToken;
                return this._currToken;
            }
            this._currToken = jsonToken;
            return jsonToken;
        }
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken t = this._nextToken;
        this._nextToken = null;
        if (t == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (t == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = t;
        return t;
    }

    protected final JsonToken _parsePosNumber(int ch) throws IOException {
        int ptr = this._inputPtr;
        int startPtr = ptr - 1;
        int inputLen = this._inputEnd;
        if (ch == 48) {
            return _parseNumber2(false, startPtr);
        }
        int intLen = 1;
        int ptr2 = ptr;
        while (ptr2 < inputLen) {
            ptr = ptr2 + 1;
            ch = this._inputBuffer[ptr2];
            if (ch >= 48 && ch <= 57) {
                intLen++;
                ptr2 = ptr;
            } else if (ch == 46 || ch == 101 || ch == 69) {
                this._inputPtr = ptr;
                return _parseFloat(ch, startPtr, ptr, false, intLen);
            } else {
                ptr--;
                this._inputPtr = ptr;
                if (this._parsingContext.inRoot()) {
                    _verifyRootSpace(ch);
                }
                this._textBuffer.resetWithShared(this._inputBuffer, startPtr, ptr - startPtr);
                return resetInt(false, intLen);
            }
        }
        this._inputPtr = startPtr;
        ptr = ptr2;
        return _parseNumber2(false, startPtr);
    }

    private final JsonToken _parseFloat(int ch, int startPtr, int ptr, boolean neg, int intLen) throws IOException {
        int ptr2;
        int inputLen = this._inputEnd;
        int fractLen = 0;
        if (ch == 46) {
            ptr2 = ptr;
            while (ptr2 < inputLen) {
                ptr = ptr2 + 1;
                ch = this._inputBuffer[ptr2];
                if (ch >= 48 && ch <= 57) {
                    fractLen++;
                    ptr2 = ptr;
                } else if (fractLen == 0) {
                    reportUnexpectedNumberChar(ch, "Decimal point not followed by a digit");
                }
            }
            ptr = ptr2;
            return _parseNumber2(neg, startPtr);
        }
        ptr2 = ptr;
        int expLen = 0;
        if (ch == 101 || ch == 69) {
            if (ptr2 >= inputLen) {
                this._inputPtr = startPtr;
                ptr = ptr2;
                return _parseNumber2(neg, startPtr);
            }
            ptr = ptr2 + 1;
            ch = this._inputBuffer[ptr2];
            if (ch != 45 && ch != 43) {
                ptr2 = ptr;
            } else if (ptr >= inputLen) {
                this._inputPtr = startPtr;
                return _parseNumber2(false, startPtr);
            } else {
                ptr2 = ptr + 1;
                ch = this._inputBuffer[ptr];
            }
            while (ch <= 57 && ch >= 48) {
                expLen++;
                if (ptr2 >= inputLen) {
                    this._inputPtr = startPtr;
                    ptr = ptr2;
                    return _parseNumber2(neg, startPtr);
                }
                ptr = ptr2 + 1;
                ch = this._inputBuffer[ptr2];
                ptr2 = ptr;
            }
            if (expLen == 0) {
                reportUnexpectedNumberChar(ch, "Exponent indicator not followed by a digit");
            }
        }
        ptr = ptr2 - 1;
        this._inputPtr = ptr;
        if (this._parsingContext.inRoot()) {
            _verifyRootSpace(ch);
        }
        this._textBuffer.resetWithShared(this._inputBuffer, startPtr, ptr - startPtr);
        return resetFloat(neg, intLen, fractLen, expLen);
    }

    protected final JsonToken _parseNegNumber() throws IOException {
        int ptr = this._inputPtr;
        int startPtr = ptr - 1;
        int inputLen = this._inputEnd;
        if (ptr >= inputLen) {
            return _parseNumber2(true, startPtr);
        }
        int ptr2 = ptr + 1;
        int ch = this._inputBuffer[ptr];
        if (ch > 57 || ch < 48) {
            this._inputPtr = ptr2;
            ptr = ptr2;
            return _handleInvalidNumberStart(ch, true);
        } else if (ch == 48) {
            ptr = ptr2;
            return _parseNumber2(true, startPtr);
        } else {
            int intLen = 1;
            while (ptr2 < inputLen) {
                ptr = ptr2 + 1;
                ch = this._inputBuffer[ptr2];
                if (ch >= 48 && ch <= 57) {
                    intLen++;
                    ptr2 = ptr;
                } else if (ch == 46 || ch == 101 || ch == 69) {
                    this._inputPtr = ptr;
                    return _parseFloat(ch, startPtr, ptr, true, intLen);
                } else {
                    ptr--;
                    this._inputPtr = ptr;
                    if (this._parsingContext.inRoot()) {
                        _verifyRootSpace(ch);
                    }
                    this._textBuffer.resetWithShared(this._inputBuffer, startPtr, ptr - startPtr);
                    return resetInt(true, intLen);
                }
            }
            ptr = ptr2;
            return _parseNumber2(true, startPtr);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final JsonToken _parseNumber2(boolean neg, int startPtr) throws IOException {
        int outPtr;
        char c;
        if (neg) {
            startPtr++;
        }
        this._inputPtr = startPtr;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int i = 0;
        if (neg) {
            outPtr = 0 + 1;
            outBuf[0] = '-';
            i = outPtr;
        }
        int intLen = 0;
        if (this._inputPtr < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            c = cArr[i2];
        } else {
            c = getNextChar("No digit following minus sign");
        }
        if (c == NumericKeypadConnector.ZERO) {
            c = _verifyNoLeadingZeroes();
        }
        boolean eof = false;
        while (c >= NumericKeypadConnector.ZERO && c <= '9') {
            intLen++;
            if (i >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            outPtr = i + 1;
            outBuf[i] = c;
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                c = '\u0000';
                eof = true;
                break;
            }
            cArr = this._inputBuffer;
            i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            c = cArr[i2];
            i = outPtr;
        }
        outPtr = i;
        if (intLen == 0) {
            reportInvalidNumber("Missing integer part (next char " + ParserMinimalBase._getCharDesc(c) + ")");
        }
        int fractLen = 0;
        if (c == NumericKeypadConnector.DOT) {
            i = outPtr + 1;
            outBuf[outPtr] = c;
            while (true) {
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    break;
                }
                cArr = this._inputBuffer;
                i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                c = cArr[i2];
                if (c < NumericKeypadConnector.ZERO || c > '9') {
                    break;
                }
                fractLen++;
                if (i >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    i = 0;
                }
                outPtr = i + 1;
                outBuf[i] = c;
                i = outPtr;
            }
            if (fractLen == 0) {
                reportUnexpectedNumberChar(c, "Decimal point not followed by a digit");
            }
        } else {
            i = outPtr;
        }
        int expLen = 0;
        if (c == 'e' || c == 'E') {
            if (i >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                i = 0;
            }
            outPtr = i + 1;
            outBuf[i] = c;
            if (this._inputPtr < this._inputEnd) {
                cArr = this._inputBuffer;
                i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                c = cArr[i2];
            } else {
                c = getNextChar("expected a digit for number exponent");
            }
            if (c == '-' || c == '+') {
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    i = 0;
                } else {
                    i = outPtr;
                }
                outPtr = i + 1;
                outBuf[i] = c;
                if (this._inputPtr < this._inputEnd) {
                    cArr = this._inputBuffer;
                    i2 = this._inputPtr;
                    this._inputPtr = i2 + 1;
                    c = cArr[i2];
                } else {
                    c = getNextChar("expected a digit for number exponent");
                }
                i = outPtr;
            } else {
                i = outPtr;
            }
            while (c <= '9' && c >= NumericKeypadConnector.ZERO) {
                expLen++;
                if (i >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    i = 0;
                }
                outPtr = i + 1;
                outBuf[i] = c;
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    eof = true;
                    i = outPtr;
                    break;
                }
                cArr = this._inputBuffer;
                i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                c = cArr[i2];
                i = outPtr;
            }
            if (expLen == 0) {
                reportUnexpectedNumberChar(c, "Exponent indicator not followed by a digit");
            }
        }
        if (!eof) {
            this._inputPtr--;
            if (this._parsingContext.inRoot()) {
                _verifyRootSpace(c);
            }
        }
        this._textBuffer.setCurrentLength(i);
        return reset(neg, intLen, fractLen, expLen);
    }

    private final char _verifyNoLeadingZeroes() throws IOException {
        if (this._inputPtr < this._inputEnd) {
            char ch = this._inputBuffer[this._inputPtr];
            if (ch < NumericKeypadConnector.ZERO || ch > '9') {
                return NumericKeypadConnector.ZERO;
            }
        }
        return _verifyNLZ2();
    }

    private char _verifyNLZ2() throws IOException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return NumericKeypadConnector.ZERO;
        }
        char ch = this._inputBuffer[this._inputPtr];
        if (ch < NumericKeypadConnector.ZERO || ch > '9') {
            return NumericKeypadConnector.ZERO;
        }
        if (!isEnabled(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (ch != NumericKeypadConnector.ZERO) {
            return ch;
        }
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return ch;
            }
            ch = this._inputBuffer[this._inputPtr];
            if (ch < NumericKeypadConnector.ZERO || ch > '9') {
                return NumericKeypadConnector.ZERO;
            }
            this._inputPtr++;
        } while (ch == NumericKeypadConnector.ZERO);
        return ch;
    }

    protected JsonToken _handleInvalidNumberStart(int ch, boolean negative) throws IOException {
        double d = Double.NEGATIVE_INFINITY;
        if (ch == 73) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOFInValue();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            ch = cArr[i];
            String match;
            if (ch == 78) {
                match = negative ? "-INF" : "+INF";
                _matchToken(match, 3);
                if (isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    if (!negative) {
                        d = Double.POSITIVE_INFINITY;
                    }
                    return resetAsNaN(match, d);
                }
                _reportError("Non-standard token '" + match + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            } else if (ch == 110) {
                match = negative ? "-Infinity" : "+Infinity";
                _matchToken(match, 3);
                if (isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    if (!negative) {
                        d = Double.POSITIVE_INFINITY;
                    }
                    return resetAsNaN(match, d);
                }
                _reportError("Non-standard token '" + match + "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
            }
        }
        reportUnexpectedNumberChar(ch, "expected digit (0-9) to follow minus sign, for valid numeric value");
        return null;
    }

    private final void _verifyRootSpace(int ch) throws IOException {
        this._inputPtr++;
        switch (ch) {
            case 9:
            case 32:
                return;
            case 10:
                this._currInputRow++;
                this._currInputRowStart = this._inputPtr;
                return;
            case 13:
                _skipCR();
                return;
            default:
                _reportMissingRootWS(ch);
                return;
        }
    }

    protected final String _parseName() throws IOException {
        int start;
        int ptr = this._inputPtr;
        int hash = this._hashSeed;
        int[] codes = _icLatin1;
        while (ptr < this._inputEnd) {
            int ch = this._inputBuffer[ptr];
            if (ch >= codes.length || codes[ch] == 0) {
                hash = (hash * 33) + ch;
                ptr++;
            } else {
                if (ch == 34) {
                    start = this._inputPtr;
                    this._inputPtr = ptr + 1;
                    return this._symbols.findSymbol(this._inputBuffer, start, ptr - start, hash);
                }
                start = this._inputPtr;
                this._inputPtr = ptr;
                return _parseName2(start, hash, 34);
            }
        }
        start = this._inputPtr;
        this._inputPtr = ptr;
        return _parseName2(start, hash, 34);
    }

    private String _parseName2(int startPtr, int hash, int endChar) throws IOException {
        this._textBuffer.resetWithShared(this._inputBuffer, startPtr, this._inputPtr - startPtr);
        char[] outBuf = this._textBuffer.getCurrentSegment();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing '" + ((char) endChar) + "' for name");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            char i2 = c;
            if (i2 <= '\\') {
                if (i2 == '\\') {
                    c = _decodeEscaped();
                } else if (i2 <= endChar) {
                    if (i2 == endChar) {
                        this._textBuffer.setCurrentLength(outPtr);
                        TextBuffer tb = this._textBuffer;
                        return this._symbols.findSymbol(tb.getTextBuffer(), tb.getTextOffset(), tb.size(), hash);
                    } else if (i2 < ' ') {
                        _throwUnquotedSpace(i2, "name");
                    }
                }
            }
            hash = (hash * 33) + i2;
            int outPtr2 = outPtr + 1;
            outBuf[outPtr] = c;
            if (outPtr2 >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            } else {
                outPtr = outPtr2;
            }
        }
    }

    protected String _handleOddName(int i) throws IOException {
        if (i == 39 && isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseAposName();
        }
        int start;
        if (!isEnabled(Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        int[] codes = CharTypes.getInputCodeLatin1JsNames();
        int maxCode = codes.length;
        boolean firstOk = i < maxCode ? codes[i] == 0 : Character.isJavaIdentifierPart((char) i);
        if (!firstOk) {
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int ptr = this._inputPtr;
        int hash = this._hashSeed;
        int inputLen = this._inputEnd;
        if (ptr < inputLen) {
            do {
                int ch = this._inputBuffer[ptr];
                if (ch < maxCode) {
                    if (codes[ch] != 0) {
                        start = this._inputPtr - 1;
                        this._inputPtr = ptr;
                        return this._symbols.findSymbol(this._inputBuffer, start, ptr - start, hash);
                    }
                } else if (!Character.isJavaIdentifierPart((char) ch)) {
                    start = this._inputPtr - 1;
                    this._inputPtr = ptr;
                    return this._symbols.findSymbol(this._inputBuffer, start, ptr - start, hash);
                }
                hash = (hash * 33) + ch;
                ptr++;
            } while (ptr < inputLen);
        }
        start = this._inputPtr - 1;
        this._inputPtr = ptr;
        return _handleOddName2(start, hash, codes);
    }

    protected String _parseAposName() throws IOException {
        int start;
        int ptr = this._inputPtr;
        int hash = this._hashSeed;
        int inputLen = this._inputEnd;
        if (ptr < inputLen) {
            int[] codes = _icLatin1;
            int maxCode = codes.length;
            do {
                int ch = this._inputBuffer[ptr];
                if (ch != 39) {
                    if (ch < maxCode && codes[ch] != 0) {
                        break;
                    }
                    hash = (hash * 33) + ch;
                    ptr++;
                } else {
                    start = this._inputPtr;
                    this._inputPtr = ptr + 1;
                    return this._symbols.findSymbol(this._inputBuffer, start, ptr - start, hash);
                }
            } while (ptr < inputLen);
        }
        start = this._inputPtr;
        this._inputPtr = ptr;
        return _parseName2(start, hash, 39);
    }

    protected JsonToken _handleOddValue(int i) throws IOException {
        switch (i) {
            case 39:
                if (isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
                    return _handleApos();
                }
                break;
            case 43:
                if (this._inputPtr >= this._inputEnd && !loadMore()) {
                    _reportInvalidEOFInValue();
                }
                char[] cArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                return _handleInvalidNumberStart(cArr[i2], false);
            case 73:
                _matchToken("Infinity", 1);
                if (!isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    _reportError("Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                    break;
                }
                return resetAsNaN("Infinity", Double.POSITIVE_INFINITY);
            case 78:
                _matchToken("NaN", 1);
                if (!isEnabled(Feature.ALLOW_NON_NUMERIC_NUMBERS)) {
                    _reportError("Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow");
                    break;
                }
                return resetAsNaN("NaN", Double.NaN);
        }
        if (Character.isJavaIdentifierStart(i)) {
            _reportInvalidToken("" + ((char) i), "('true', 'false' or 'null')");
        }
        _reportUnexpectedChar(i, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
        return null;
    }

    protected JsonToken _handleApos() throws IOException {
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            char i2 = c;
            if (i2 <= '\\') {
                if (i2 == '\\') {
                    c = _decodeEscaped();
                } else if (i2 <= '\'') {
                    if (i2 == '\'') {
                        this._textBuffer.setCurrentLength(outPtr);
                        return JsonToken.VALUE_STRING;
                    } else if (i2 < ' ') {
                        _throwUnquotedSpace(i2, "string value");
                    }
                }
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            int outPtr2 = outPtr + 1;
            outBuf[outPtr] = c;
            outPtr = outPtr2;
        }
    }

    private String _handleOddName2(int startPtr, int hash, int[] codes) throws IOException {
        this._textBuffer.resetWithShared(this._inputBuffer, startPtr, this._inputPtr - startPtr);
        char[] outBuf = this._textBuffer.getCurrentSegment();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        char maxCode = codes.length;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char c = this._inputBuffer[this._inputPtr];
            char i = c;
            if (i > maxCode) {
                if (!Character.isJavaIdentifierPart(c)) {
                    break;
                }
            } else if (codes[i] != 0) {
                break;
            }
            this._inputPtr++;
            hash = (hash * 33) + i;
            int outPtr2 = outPtr + 1;
            outBuf[outPtr] = c;
            if (outPtr2 >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            } else {
                outPtr = outPtr2;
            }
        }
        this._textBuffer.setCurrentLength(outPtr);
        TextBuffer tb = this._textBuffer;
        return this._symbols.findSymbol(tb.getTextBuffer(), tb.getTextOffset(), tb.size(), hash);
    }

    protected final void _finishString() throws IOException {
        int ptr = this._inputPtr;
        int inputLen = this._inputEnd;
        if (ptr < inputLen) {
            int[] codes = _icLatin1;
            int maxCode = codes.length;
            do {
                int ch = this._inputBuffer[ptr];
                if (ch >= maxCode || codes[ch] == 0) {
                    ptr++;
                } else if (ch == 34) {
                    this._textBuffer.resetWithShared(this._inputBuffer, this._inputPtr, ptr - this._inputPtr);
                    this._inputPtr = ptr + 1;
                    return;
                }
            } while (ptr < inputLen);
        }
        this._textBuffer.resetWithCopy(this._inputBuffer, this._inputPtr, ptr - this._inputPtr);
        this._inputPtr = ptr;
        _finishString2();
    }

    protected void _finishString2() throws IOException {
        char[] outBuf = this._textBuffer.getCurrentSegment();
        int outPtr = this._textBuffer.getCurrentSegmentSize();
        int[] codes = _icLatin1;
        char maxCode = codes.length;
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidEOF(": was expecting closing quote for a string value");
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = cArr[i];
            char i2 = c;
            if (i2 < maxCode && codes[i2] != 0) {
                if (i2 == '\"') {
                    this._textBuffer.setCurrentLength(outPtr);
                    return;
                } else if (i2 == '\\') {
                    c = _decodeEscaped();
                } else if (i2 < ' ') {
                    _throwUnquotedSpace(i2, "string value");
                }
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            int outPtr2 = outPtr + 1;
            outBuf[outPtr] = c;
            outPtr = outPtr2;
        }
    }

    protected final void _skipString() throws IOException {
        this._tokenIncomplete = false;
        int inPtr = this._inputPtr;
        int inLen = this._inputEnd;
        char[] inBuf = this._inputBuffer;
        while (true) {
            if (inPtr >= inLen) {
                this._inputPtr = inPtr;
                if (!loadMore()) {
                    _reportInvalidEOF(": was expecting closing quote for a string value");
                }
                inPtr = this._inputPtr;
                inLen = this._inputEnd;
            }
            int inPtr2 = inPtr + 1;
            char i = inBuf[inPtr];
            if (i <= '\\') {
                if (i == '\\') {
                    this._inputPtr = inPtr2;
                    char c = _decodeEscaped();
                    inPtr = this._inputPtr;
                    inLen = this._inputEnd;
                } else if (i <= '\"') {
                    if (i == '\"') {
                        this._inputPtr = inPtr2;
                        return;
                    } else if (i < ' ') {
                        this._inputPtr = inPtr2;
                        _throwUnquotedSpace(i, "string value");
                    }
                }
            }
            inPtr = inPtr2;
        }
    }

    protected final void _skipCR() throws IOException {
        if ((this._inputPtr < this._inputEnd || loadMore()) && this._inputBuffer[this._inputPtr] == '\n') {
            this._inputPtr++;
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    private final int _skipColon() throws IOException {
        if (this._inputPtr + 4 >= this._inputEnd) {
            return _skipColon2(false);
        }
        char c = this._inputBuffer[this._inputPtr];
        char[] cArr;
        int i;
        int i2;
        if (c == ':') {
            cArr = this._inputBuffer;
            i = this._inputPtr + 1;
            this._inputPtr = i;
            i2 = cArr[i];
            if (i2 <= 32) {
                if (i2 == 32 || i2 == 9) {
                    cArr = this._inputBuffer;
                    i = this._inputPtr + 1;
                    this._inputPtr = i;
                    i2 = cArr[i];
                    if (i2 > 32) {
                        if (i2 == 47 || i2 == 35) {
                            return _skipColon2(true);
                        }
                        this._inputPtr++;
                        return i2;
                    }
                }
                return _skipColon2(true);
            } else if (i2 == 47 || i2 == 35) {
                return _skipColon2(true);
            } else {
                this._inputPtr++;
                return i2;
            }
        }
        if (c == ' ' || c == '\t') {
            cArr = this._inputBuffer;
            i = this._inputPtr + 1;
            this._inputPtr = i;
            c = cArr[i];
        }
        if (c != ':') {
            return _skipColon2(false);
        }
        cArr = this._inputBuffer;
        i = this._inputPtr + 1;
        this._inputPtr = i;
        i2 = cArr[i];
        if (i2 <= 32) {
            if (i2 == 32 || i2 == 9) {
                cArr = this._inputBuffer;
                i = this._inputPtr + 1;
                this._inputPtr = i;
                i2 = cArr[i];
                if (i2 > 32) {
                    if (i2 == 47 || i2 == 35) {
                        return _skipColon2(true);
                    }
                    this._inputPtr++;
                    return i2;
                }
            }
            return _skipColon2(true);
        } else if (i2 == 47 || i2 == 35) {
            return _skipColon2(true);
        } else {
            this._inputPtr++;
            return i2;
        }
    }

    private final int _skipColon2(boolean gotColon) throws IOException {
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = cArr[i];
            if (i2 > 32) {
                if (i2 == 47) {
                    _skipComment();
                } else if (i2 != 35 || !_skipYAMLComment()) {
                    if (gotColon) {
                        return i2;
                    }
                    if (i2 != 58) {
                        if (i2 < 32) {
                            _throwInvalidSpace(i2);
                        }
                        _reportUnexpectedChar(i2, "was expecting a colon to separate field name and value");
                    }
                    gotColon = true;
                }
            } else if (i2 < 32) {
                if (i2 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
                }
            }
        }
    }

    private final int _skipComma(int i) throws IOException {
        if (i != 44) {
            _reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.getTypeDesc() + " entries");
        }
        while (this._inputPtr < this._inputEnd) {
            char[] cArr = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            i = cArr[i2];
            if (i > 32) {
                if (i != 47 && i != 35) {
                    return i;
                }
                this._inputPtr--;
                return _skipAfterComma2();
            } else if (i < 32) {
                if (i == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i == 13) {
                    _skipCR();
                } else if (i != 9) {
                    _throwInvalidSpace(i);
                }
            }
        }
        return _skipAfterComma2();
    }

    private final int _skipAfterComma2() throws IOException {
        int i;
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                char[] cArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                i = cArr[i2];
                if (i > 32) {
                    if (i == 47) {
                        _skipComment();
                    } else if (i != 35 || !_skipYAMLComment()) {
                        return i;
                    }
                } else if (i < 32) {
                    if (i == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (i == 13) {
                        _skipCR();
                    } else if (i != 9) {
                        _throwInvalidSpace(i);
                    }
                }
            } else {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.getTypeDesc() + " entries");
            }
        }
        return i;
    }

    private final int _skipWSOrEnd() throws IOException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            return _eofAsNextChar();
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = cArr[i];
        if (i2 <= 32) {
            if (i2 != 32) {
                if (i2 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
                }
            }
            while (this._inputPtr < this._inputEnd) {
                cArr = this._inputBuffer;
                i = this._inputPtr;
                this._inputPtr = i + 1;
                i2 = cArr[i];
                if (i2 > 32) {
                    if (i2 != 47 && i2 != 35) {
                        return i2;
                    }
                    this._inputPtr--;
                    return _skipWSOrEnd2();
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                    }
                }
            }
            return _skipWSOrEnd2();
        } else if (i2 != 47 && i2 != 35) {
            return i2;
        } else {
            this._inputPtr--;
            return _skipWSOrEnd2();
        }
    }

    private int _skipWSOrEnd2() throws IOException {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                return _eofAsNextChar();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = cArr[i];
            if (i2 > 32) {
                if (i2 == 47) {
                    _skipComment();
                } else if (i2 != 35 || !_skipYAMLComment()) {
                    return i2;
                }
            } else if (i2 != 32) {
                if (i2 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
                }
            }
        }
    }

    private void _skipComment() throws IOException {
        if (!isEnabled(Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in a comment");
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        char c = cArr[i];
        if (c == '/') {
            _skipLine();
        } else if (c == '*') {
            _skipCComment();
        } else {
            _reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
        }
    }

    private void _skipCComment() throws IOException {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = cArr[i];
            if (i2 <= 42) {
                if (i2 == 42) {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        break;
                    } else if (this._inputBuffer[this._inputPtr] == '/') {
                        this._inputPtr++;
                        return;
                    }
                } else if (i2 < 32) {
                    if (i2 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                    }
                }
            }
        }
        _reportInvalidEOF(" in a comment");
    }

    private boolean _skipYAMLComment() throws IOException {
        if (!isEnabled(Feature.ALLOW_YAML_COMMENTS)) {
            return false;
        }
        _skipLine();
        return true;
    }

    private void _skipLine() throws IOException {
        while (true) {
            if (this._inputPtr < this._inputEnd || loadMore()) {
                char[] cArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = cArr[i];
                if (i2 < 32) {
                    if (i2 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                        return;
                    } else if (i2 == 13) {
                        _skipCR();
                        return;
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                    }
                }
            } else {
                return;
            }
        }
    }

    protected char _decodeEscaped() throws IOException {
        if (this._inputPtr >= this._inputEnd && !loadMore()) {
            _reportInvalidEOF(" in character escape sequence");
        }
        char[] cArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        char c = cArr[i];
        switch (c) {
            case '\"':
            case '/':
            case '\\':
                return c;
            case 'b':
                return '\b';
            case 'f':
                return '\f';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 't':
                return '\t';
            case 'u':
                int value = 0;
                for (int i2 = 0; i2 < 4; i2++) {
                    if (this._inputPtr >= this._inputEnd && !loadMore()) {
                        _reportInvalidEOF(" in character escape sequence");
                    }
                    cArr = this._inputBuffer;
                    i = this._inputPtr;
                    this._inputPtr = i + 1;
                    int ch = cArr[i];
                    int digit = CharTypes.charToHex(ch);
                    if (digit < 0) {
                        _reportUnexpectedChar(ch, "expected a hex-digit for character escape sequence");
                    }
                    value = (value << 4) | digit;
                }
                return (char) value;
            default:
                return _handleUnrecognizedCharacterEscape(c);
        }
    }

    private final void _matchTrue() throws IOException {
        int ptr = this._inputPtr;
        if (ptr + 3 < this._inputEnd) {
            char[] b = this._inputBuffer;
            if (b[ptr] == 'r') {
                ptr++;
                if (b[ptr] == 'u') {
                    ptr++;
                    if (b[ptr] == 'e') {
                        ptr++;
                        char c = b[ptr];
                        if (c < NumericKeypadConnector.ZERO || c == ']' || c == '}') {
                            this._inputPtr = ptr;
                            return;
                        }
                    }
                }
            }
        }
        _matchToken("true", 1);
    }

    private final void _matchFalse() throws IOException {
        int ptr = this._inputPtr;
        if (ptr + 4 < this._inputEnd) {
            char[] b = this._inputBuffer;
            if (b[ptr] == 'a') {
                ptr++;
                if (b[ptr] == 'l') {
                    ptr++;
                    if (b[ptr] == 's') {
                        ptr++;
                        if (b[ptr] == 'e') {
                            ptr++;
                            char c = b[ptr];
                            if (c < NumericKeypadConnector.ZERO || c == ']' || c == '}') {
                                this._inputPtr = ptr;
                                return;
                            }
                        }
                    }
                }
            }
        }
        _matchToken("false", 1);
    }

    private final void _matchNull() throws IOException {
        int ptr = this._inputPtr;
        if (ptr + 3 < this._inputEnd) {
            char[] b = this._inputBuffer;
            if (b[ptr] == 'u') {
                ptr++;
                if (b[ptr] == 'l') {
                    ptr++;
                    if (b[ptr] == 'l') {
                        ptr++;
                        char c = b[ptr];
                        if (c < NumericKeypadConnector.ZERO || c == ']' || c == '}') {
                            this._inputPtr = ptr;
                            return;
                        }
                    }
                }
            }
        }
        _matchToken("null", 1);
    }

    protected final void _matchToken(String matchStr, int i) throws IOException {
        int len = matchStr.length();
        do {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                _reportInvalidToken(matchStr.substring(0, i));
            }
            if (this._inputBuffer[this._inputPtr] != matchStr.charAt(i)) {
                _reportInvalidToken(matchStr.substring(0, i));
            }
            this._inputPtr++;
            i++;
        } while (i < len);
        if (this._inputPtr < this._inputEnd || loadMore()) {
            char c = this._inputBuffer[this._inputPtr];
            if (c >= NumericKeypadConnector.ZERO && c != ']' && c != '}' && Character.isJavaIdentifierPart(c)) {
                _reportInvalidToken(matchStr.substring(0, i));
            }
        }
    }

    protected byte[] _decodeBase64(Base64Variant b64variant) throws IOException {
        ByteArrayBuilder builder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                loadMoreGuaranteed();
            }
            char[] cArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char ch = cArr[i];
            if (ch > ' ') {
                int bits = b64variant.decodeBase64Char(ch);
                if (bits < 0) {
                    if (ch == '\"') {
                        return builder.toByteArray();
                    }
                    bits = _decodeBase64Escape(b64variant, ch, 0);
                    if (bits < 0) {
                        continue;
                    }
                }
                int decodedData = bits;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                cArr = this._inputBuffer;
                i = this._inputPtr;
                this._inputPtr = i + 1;
                ch = cArr[i];
                bits = b64variant.decodeBase64Char(ch);
                if (bits < 0) {
                    bits = _decodeBase64Escape(b64variant, ch, 1);
                }
                decodedData = (decodedData << 6) | bits;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                cArr = this._inputBuffer;
                i = this._inputPtr;
                this._inputPtr = i + 1;
                ch = cArr[i];
                bits = b64variant.decodeBase64Char(ch);
                if (bits < 0) {
                    if (bits != -2) {
                        if (ch != '\"' || b64variant.usesPadding()) {
                            bits = _decodeBase64Escape(b64variant, ch, 2);
                        } else {
                            builder.append(decodedData >> 4);
                            return builder.toByteArray();
                        }
                    }
                    if (bits == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            loadMoreGuaranteed();
                        }
                        cArr = this._inputBuffer;
                        i = this._inputPtr;
                        this._inputPtr = i + 1;
                        ch = cArr[i];
                        if (b64variant.usesPaddingChar(ch)) {
                            builder.append(decodedData >> 4);
                        } else {
                            throw reportInvalidBase64Char(b64variant, ch, 3, "expected padding character '" + b64variant.getPaddingChar() + "'");
                        }
                    }
                }
                decodedData = (decodedData << 6) | bits;
                if (this._inputPtr >= this._inputEnd) {
                    loadMoreGuaranteed();
                }
                cArr = this._inputBuffer;
                i = this._inputPtr;
                this._inputPtr = i + 1;
                ch = cArr[i];
                bits = b64variant.decodeBase64Char(ch);
                if (bits < 0) {
                    if (bits != -2) {
                        if (ch != '\"' || b64variant.usesPadding()) {
                            bits = _decodeBase64Escape(b64variant, ch, 3);
                        } else {
                            builder.appendTwoBytes(decodedData >> 2);
                            return builder.toByteArray();
                        }
                    }
                    if (bits == -2) {
                        builder.appendTwoBytes(decodedData >> 2);
                    }
                }
                builder.appendThreeBytes((decodedData << 6) | bits);
            }
        }
    }

    protected void _reportInvalidToken(String matchedPart) throws IOException {
        _reportInvalidToken(matchedPart, "'null', 'true', 'false' or NaN");
    }

    protected void _reportInvalidToken(String matchedPart, String msg) throws IOException {
        StringBuilder sb = new StringBuilder(matchedPart);
        while (true) {
            if (this._inputPtr >= this._inputEnd && !loadMore()) {
                break;
            }
            char c = this._inputBuffer[this._inputPtr];
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            this._inputPtr++;
            sb.append(c);
        }
        _reportError("Unrecognized token '" + sb.toString() + "': was expecting " + msg);
    }
}
