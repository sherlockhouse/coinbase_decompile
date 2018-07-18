package com.fasterxml.jackson.core.io;

import com.coinbase.android.ui.NumericKeypadConnector;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.lang.ref.SoftReference;

public final class JsonStringEncoder {
    private static final byte[] HB = CharTypes.copyHexBytes();
    private static final char[] HC = CharTypes.copyHexChars();
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal();
    protected final char[] _qbuf = new char[6];
    protected TextBuffer _text;

    public JsonStringEncoder() {
        this._qbuf[0] = '\\';
        this._qbuf[2] = NumericKeypadConnector.ZERO;
        this._qbuf[3] = NumericKeypadConnector.ZERO;
    }

    public static JsonStringEncoder getInstance() {
        SoftReference<JsonStringEncoder> ref = (SoftReference) _threadEncoder.get();
        JsonStringEncoder enc = ref == null ? null : (JsonStringEncoder) ref.get();
        if (enc != null) {
            return enc;
        }
        enc = new JsonStringEncoder();
        _threadEncoder.set(new SoftReference(enc));
        return enc;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char[] quoteAsString(String input) {
        TextBuffer textBuffer = this._text;
        if (textBuffer == null) {
            textBuffer = new TextBuffer(null);
            this._text = textBuffer;
        }
        char[] outputBuffer = textBuffer.emptyAndGetCurrentSegment();
        int[] escCodes = CharTypes.get7BitOutputEscapes();
        char escCodeCount = escCodes.length;
        int inPtr = 0;
        int inputLen = input.length();
        int outPtr = 0;
        loop0:
        while (inPtr < inputLen) {
            int outPtr2;
            while (true) {
                char c = input.charAt(inPtr);
                if (c < escCodeCount && escCodes[c] != 0) {
                    break;
                }
                if (outPtr >= outputBuffer.length) {
                    outputBuffer = textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outPtr2 = outPtr + 1;
                outputBuffer[outPtr] = c;
                inPtr++;
                if (inPtr >= inputLen) {
                    break loop0;
                }
                outPtr = outPtr2;
            }
            outPtr = outPtr2;
            break loop0;
        }
        textBuffer.setCurrentLength(outPtr);
        return textBuffer.contentsAsArray();
    }

    private int _appendNumeric(int value, char[] qbuf) {
        qbuf[1] = 'u';
        qbuf[4] = HC[value >> 4];
        qbuf[5] = HC[value & 15];
        return 6;
    }

    private int _appendNamed(int esc, char[] qbuf) {
        qbuf[1] = (char) esc;
        return 2;
    }
}
