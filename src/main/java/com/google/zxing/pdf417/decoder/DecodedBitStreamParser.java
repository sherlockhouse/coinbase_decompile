package com.google.zxing.pdf417.decoder;

import com.coinbase.android.ui.NumericKeypadConnector;
import com.google.zxing.FormatException;
import com.google.zxing.common.DecoderResult;
import java.math.BigInteger;

final class DecodedBitStreamParser {
    private static final BigInteger[] EXP900 = new BigInteger[16];
    private static final char[] MIXED_CHARS = new char[]{NumericKeypadConnector.ZERO, '1', '2', '3', '4', '5', '6', '7', '8', '9', '&', '\r', '\t', ',', ':', '#', '-', NumericKeypadConnector.DOT, '$', '/', '+', '%', '*', '=', '^'};
    private static final char[] PUNCT_CHARS = new char[]{';', '<', '>', '@', '[', '\\', '}', '_', '`', '~', '!', '\r', '\t', ',', ':', '\n', '-', NumericKeypadConnector.DOT, '$', '/', '\"', '|', '*', '(', ')', '?', '{', '}', '\''};

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        EXP900[0] = BigInteger.ONE;
        BigInteger nineHundred = BigInteger.valueOf(900);
        EXP900[1] = nineHundred;
        for (int i = 2; i < EXP900.length; i++) {
            EXP900[i] = EXP900[i - 1].multiply(nineHundred);
        }
    }

    static DecoderResult decode(int[] codewords) throws FormatException {
        StringBuilder result = new StringBuilder(100);
        int codeIndex = 1 + 1;
        int code = codewords[1];
        int codeIndex2 = codeIndex;
        while (codeIndex2 < codewords[0]) {
            switch (code) {
                case 900:
                    codeIndex2 = textCompaction(codewords, codeIndex2, result);
                    break;
                case 901:
                    codeIndex2 = byteCompaction(code, codewords, codeIndex2, result);
                    break;
                case 902:
                    codeIndex2 = numericCompaction(codewords, codeIndex2, result);
                    break;
                case 913:
                    codeIndex2 = byteCompaction(code, codewords, codeIndex2, result);
                    break;
                case 924:
                    codeIndex2 = byteCompaction(code, codewords, codeIndex2, result);
                    break;
                default:
                    codeIndex2 = textCompaction(codewords, codeIndex2 - 1, result);
                    break;
            }
            if (codeIndex2 < codewords.length) {
                codeIndex = codeIndex2 + 1;
                code = codewords[codeIndex2];
                codeIndex2 = codeIndex;
            } else {
                throw FormatException.getFormatInstance();
            }
        }
        if (result.length() != 0) {
            return new DecoderResult(null, result.toString(), null, null);
        }
        throw FormatException.getFormatInstance();
    }

    private static int textCompaction(int[] codewords, int codeIndex, StringBuilder result) {
        int[] textCompactionData = new int[(codewords[0] << 1)];
        int[] byteCompactionData = new int[(codewords[0] << 1)];
        int index = 0;
        boolean end = false;
        while (codeIndex < codewords[0] && !end) {
            int codeIndex2 = codeIndex + 1;
            int code = codewords[codeIndex];
            if (code >= 900) {
                switch (code) {
                    case 900:
                        int index2 = index + 1;
                        textCompactionData[index] = 900;
                        index = index2;
                        codeIndex = codeIndex2;
                        break;
                    case 901:
                        codeIndex = codeIndex2 - 1;
                        end = true;
                        break;
                    case 902:
                        codeIndex = codeIndex2 - 1;
                        end = true;
                        break;
                    case 913:
                        textCompactionData[index] = 913;
                        codeIndex = codeIndex2 + 1;
                        byteCompactionData[index] = codewords[codeIndex2];
                        index++;
                        break;
                    case 924:
                        codeIndex = codeIndex2 - 1;
                        end = true;
                        break;
                    default:
                        codeIndex = codeIndex2;
                        break;
                }
            }
            textCompactionData[index] = code / 30;
            textCompactionData[index + 1] = code % 30;
            index += 2;
            codeIndex = codeIndex2;
        }
        decodeTextCompaction(textCompactionData, byteCompactionData, index, result);
        return codeIndex;
    }

    private static void decodeTextCompaction(int[] textCompactionData, int[] byteCompactionData, int length, StringBuilder result) {
        Mode subMode = Mode.ALPHA;
        Mode priorToShiftMode = Mode.ALPHA;
        for (int i = 0; i < length; i++) {
            int subModeCh = textCompactionData[i];
            char ch = '\u0000';
            switch (subMode) {
                case ALPHA:
                    if (subModeCh >= 26) {
                        if (subModeCh != 26) {
                            if (subModeCh != 27) {
                                if (subModeCh != 28) {
                                    if (subModeCh != 29) {
                                        if (subModeCh != 913) {
                                            if (subModeCh == 900) {
                                                subMode = Mode.ALPHA;
                                                break;
                                            }
                                        }
                                        result.append((char) byteCompactionData[i]);
                                        break;
                                    }
                                    priorToShiftMode = subMode;
                                    subMode = Mode.PUNCT_SHIFT;
                                    break;
                                }
                                subMode = Mode.MIXED;
                                break;
                            }
                            subMode = Mode.LOWER;
                            break;
                        }
                        ch = ' ';
                        break;
                    }
                    ch = (char) (subModeCh + 65);
                    break;
                    break;
                case LOWER:
                    if (subModeCh >= 26) {
                        if (subModeCh != 26) {
                            if (subModeCh != 27) {
                                if (subModeCh != 28) {
                                    if (subModeCh != 29) {
                                        if (subModeCh != 913) {
                                            if (subModeCh == 900) {
                                                subMode = Mode.ALPHA;
                                                break;
                                            }
                                        }
                                        result.append((char) byteCompactionData[i]);
                                        break;
                                    }
                                    priorToShiftMode = subMode;
                                    subMode = Mode.PUNCT_SHIFT;
                                    break;
                                }
                                subMode = Mode.MIXED;
                                break;
                            }
                            priorToShiftMode = subMode;
                            subMode = Mode.ALPHA_SHIFT;
                            break;
                        }
                        ch = ' ';
                        break;
                    }
                    ch = (char) (subModeCh + 97);
                    break;
                    break;
                case MIXED:
                    if (subModeCh >= 25) {
                        if (subModeCh != 25) {
                            if (subModeCh != 26) {
                                if (subModeCh != 27) {
                                    if (subModeCh != 28) {
                                        if (subModeCh != 29) {
                                            if (subModeCh != 913) {
                                                if (subModeCh == 900) {
                                                    subMode = Mode.ALPHA;
                                                    break;
                                                }
                                            }
                                            result.append((char) byteCompactionData[i]);
                                            break;
                                        }
                                        priorToShiftMode = subMode;
                                        subMode = Mode.PUNCT_SHIFT;
                                        break;
                                    }
                                    subMode = Mode.ALPHA;
                                    break;
                                }
                                subMode = Mode.LOWER;
                                break;
                            }
                            ch = ' ';
                            break;
                        }
                        subMode = Mode.PUNCT;
                        break;
                    }
                    ch = MIXED_CHARS[subModeCh];
                    break;
                    break;
                case PUNCT:
                    if (subModeCh >= 29) {
                        if (subModeCh != 29) {
                            if (subModeCh != 913) {
                                if (subModeCh == 900) {
                                    subMode = Mode.ALPHA;
                                    break;
                                }
                            }
                            result.append((char) byteCompactionData[i]);
                            break;
                        }
                        subMode = Mode.ALPHA;
                        break;
                    }
                    ch = PUNCT_CHARS[subModeCh];
                    break;
                    break;
                case ALPHA_SHIFT:
                    subMode = priorToShiftMode;
                    if (subModeCh >= 26) {
                        if (subModeCh != 26) {
                            if (subModeCh == 900) {
                                subMode = Mode.ALPHA;
                                break;
                            }
                        }
                        ch = ' ';
                        break;
                    }
                    ch = (char) (subModeCh + 65);
                    break;
                    break;
                case PUNCT_SHIFT:
                    subMode = priorToShiftMode;
                    if (subModeCh >= 29) {
                        if (subModeCh != 29) {
                            if (subModeCh != 913) {
                                if (subModeCh == 900) {
                                    subMode = Mode.ALPHA;
                                    break;
                                }
                            }
                            result.append((char) byteCompactionData[i]);
                            break;
                        }
                        subMode = Mode.ALPHA;
                        break;
                    }
                    ch = PUNCT_CHARS[subModeCh];
                    break;
                    break;
            }
            if (ch != '\u0000') {
                result.append(ch);
            }
        }
    }

    private static int byteCompaction(int mode, int[] codewords, int codeIndex, StringBuilder result) {
        int count;
        long value;
        char[] decodedData;
        boolean end;
        int codeIndex2;
        int j;
        if (mode == 901) {
            int count2;
            count = 0;
            value = 0;
            decodedData = new char[6];
            int[] byteCompactedCodewords = new int[6];
            end = false;
            codeIndex2 = codeIndex + 1;
            int nextCode = codewords[codeIndex];
            codeIndex = codeIndex2;
            while (codeIndex < codewords[0] && !end) {
                count2 = count + 1;
                byteCompactedCodewords[count] = nextCode;
                value = (900 * value) + ((long) nextCode);
                codeIndex2 = codeIndex + 1;
                nextCode = codewords[codeIndex];
                if (nextCode == 900 || nextCode == 901 || nextCode == 902 || nextCode == 924 || nextCode == 928 || nextCode == 923 || nextCode == 922) {
                    codeIndex = codeIndex2 - 1;
                    end = true;
                    count = count2;
                } else if (count2 % 5 != 0 || count2 <= 0) {
                    count = count2;
                    codeIndex = codeIndex2;
                } else {
                    for (j = 0; j < 6; j++) {
                        decodedData[5 - j] = (char) ((int) (value % 256));
                        value >>= 8;
                    }
                    result.append(decodedData);
                    count = 0;
                    codeIndex = codeIndex2;
                }
            }
            if (codeIndex == codewords[0] && nextCode < 900) {
                count2 = count + 1;
                byteCompactedCodewords[count] = nextCode;
                count = count2;
            }
            for (int i = 0; i < count; i++) {
                result.append((char) byteCompactedCodewords[i]);
            }
        } else if (mode == 924) {
            count = 0;
            value = 0;
            end = false;
            while (codeIndex < codewords[0] && !end) {
                codeIndex2 = codeIndex + 1;
                int code = codewords[codeIndex];
                if (code < 900) {
                    count++;
                    value = (900 * value) + ((long) code);
                    codeIndex = codeIndex2;
                } else if (code == 900 || code == 901 || code == 902 || code == 924 || code == 928 || code == 923 || code == 922) {
                    codeIndex = codeIndex2 - 1;
                    end = true;
                } else {
                    codeIndex = codeIndex2;
                }
                if (count % 5 == 0 && count > 0) {
                    decodedData = new char[6];
                    for (j = 0; j < 6; j++) {
                        decodedData[5 - j] = (char) ((int) (255 & value));
                        value >>= 8;
                    }
                    result.append(decodedData);
                    count = 0;
                }
            }
        }
        return codeIndex;
    }

    private static int numericCompaction(int[] codewords, int codeIndex, StringBuilder result) throws FormatException {
        int count = 0;
        boolean end = false;
        int[] numericCodewords = new int[15];
        while (codeIndex < codewords[0] && !end) {
            int codeIndex2 = codeIndex + 1;
            int code = codewords[codeIndex];
            if (codeIndex2 == codewords[0]) {
                end = true;
            }
            if (code < 900) {
                numericCodewords[count] = code;
                count++;
                codeIndex = codeIndex2;
            } else if (code == 900 || code == 901 || code == 924 || code == 928 || code == 923 || code == 922) {
                codeIndex = codeIndex2 - 1;
                end = true;
            } else {
                codeIndex = codeIndex2;
            }
            if (count % 15 == 0 || code == 902 || end) {
                result.append(decodeBase900toBase10(numericCodewords, count));
                count = 0;
            }
        }
        return codeIndex;
    }

    private static String decodeBase900toBase10(int[] codewords, int count) throws FormatException {
        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < count; i++) {
            result = result.add(EXP900[(count - i) - 1].multiply(BigInteger.valueOf((long) codewords[i])));
        }
        String resultString = result.toString();
        if (resultString.charAt(0) == '1') {
            return resultString.substring(1);
        }
        throw FormatException.getFormatInstance();
    }
}
