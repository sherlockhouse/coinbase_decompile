package com.google.zxing.oned;

import com.coinbase.android.ui.NumericKeypadConnector;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public final class Code128Writer extends OneDimensionalCodeWriter {
    public BitMatrix encode(String contents, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) throws WriterException {
        if (format == BarcodeFormat.CODE_128) {
            return super.encode(contents, format, width, height, hints);
        }
        throw new IllegalArgumentException("Can only encode CODE_128, but got " + format);
    }

    public boolean[] encode(String contents) {
        int length = contents.length();
        if (length < 1 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got " + length);
        }
        for (int i = 0; i < length; i++) {
            char c = contents.charAt(i);
            if (c < ' ' || c > '~') {
                switch (c) {
                    case 'ñ':
                    case 'ò':
                    case 'ó':
                    case 'ô':
                        break;
                    default:
                        throw new IllegalArgumentException("Bad character in input: " + c);
                }
            }
        }
        Collection<int[]> patterns = new ArrayList();
        int checkSum = 0;
        int checkWeight = 1;
        int codeSet = 0;
        int position = 0;
        while (position < length) {
            int newCodeSet;
            int patternIndex;
            if (isDigits(contents, position, codeSet == 99 ? 2 : 4)) {
                newCodeSet = 99;
            } else {
                newCodeSet = 100;
            }
            if (newCodeSet == codeSet) {
                if (codeSet != 100) {
                    switch (contents.charAt(position)) {
                        case 'ñ':
                            patternIndex = 102;
                            position++;
                            break;
                        case 'ò':
                            patternIndex = 97;
                            position++;
                            break;
                        case 'ó':
                            patternIndex = 96;
                            position++;
                            break;
                        case 'ô':
                            patternIndex = 100;
                            position++;
                            break;
                        default:
                            patternIndex = Integer.parseInt(contents.substring(position, position + 2));
                            position += 2;
                            break;
                    }
                }
                patternIndex = contents.charAt(position) - 32;
                position++;
            } else {
                if (codeSet != 0) {
                    patternIndex = newCodeSet;
                } else if (newCodeSet == 100) {
                    patternIndex = 104;
                } else {
                    patternIndex = 105;
                }
                codeSet = newCodeSet;
            }
            patterns.add(Code128Reader.CODE_PATTERNS[patternIndex]);
            checkSum += patternIndex * checkWeight;
            if (position != 0) {
                checkWeight++;
            }
        }
        patterns.add(Code128Reader.CODE_PATTERNS[checkSum % 103]);
        patterns.add(Code128Reader.CODE_PATTERNS[106]);
        int codeWidth = 0;
        for (int[] arr$ : patterns) {
            for (int width : (int[]) r10.next()) {
                codeWidth += width;
            }
        }
        boolean[] result = new boolean[codeWidth];
        int pos = 0;
        for (int[] pattern : patterns) {
            pos += OneDimensionalCodeWriter.appendPattern(result, pos, pattern, true);
        }
        return result;
    }

    private static boolean isDigits(CharSequence value, int start, int length) {
        int end = start + length;
        int last = value.length();
        int i = start;
        while (i < end && i < last) {
            char c = value.charAt(i);
            if (c < NumericKeypadConnector.ZERO || c > '9') {
                if (c != 'ñ') {
                    return false;
                }
                end++;
            }
            i++;
        }
        if (end <= last) {
            return true;
        }
        return false;
    }
}
