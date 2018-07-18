package org.joda.time.format;

import com.coinbase.android.ui.NumericKeypadConnector;

public class FormatUtils {
    private static final double LOG_10 = Math.log(10.0d);

    public static void appendPaddedInteger(StringBuffer stringBuffer, int i, int i2) {
        if (i < 0) {
            stringBuffer.append('-');
            if (i != Integer.MIN_VALUE) {
                i = -i;
            } else {
                while (i2 > 10) {
                    stringBuffer.append(NumericKeypadConnector.ZERO);
                    i2--;
                }
                stringBuffer.append("2147483648");
                return;
            }
        }
        if (i < 10) {
            while (i2 > 1) {
                stringBuffer.append(NumericKeypadConnector.ZERO);
                i2--;
            }
            stringBuffer.append((char) (i + 48));
        } else if (i < 100) {
            while (i2 > 2) {
                stringBuffer.append(NumericKeypadConnector.ZERO);
                i2--;
            }
            r0 = ((i + 1) * 13421772) >> 27;
            stringBuffer.append((char) (r0 + 48));
            stringBuffer.append((char) (((i - (r0 << 3)) - (r0 << 1)) + 48));
        } else {
            if (i < 1000) {
                r0 = 3;
            } else if (i < 10000) {
                r0 = 4;
            } else {
                r0 = ((int) (Math.log((double) i) / LOG_10)) + 1;
            }
            while (i2 > r0) {
                stringBuffer.append(NumericKeypadConnector.ZERO);
                i2--;
            }
            stringBuffer.append(Integer.toString(i));
        }
    }

    public static void appendUnpaddedInteger(StringBuffer stringBuffer, int i) {
        if (i < 0) {
            stringBuffer.append('-');
            if (i != Integer.MIN_VALUE) {
                i = -i;
            } else {
                stringBuffer.append("2147483648");
                return;
            }
        }
        if (i < 10) {
            stringBuffer.append((char) (i + 48));
        } else if (i < 100) {
            int i2 = ((i + 1) * 13421772) >> 27;
            stringBuffer.append((char) (i2 + 48));
            stringBuffer.append((char) (((i - (i2 << 3)) - (i2 << 1)) + 48));
        } else {
            stringBuffer.append(Integer.toString(i));
        }
    }

    public static void appendUnpaddedInteger(StringBuffer stringBuffer, long j) {
        int i = (int) j;
        if (((long) i) == j) {
            appendUnpaddedInteger(stringBuffer, i);
        } else {
            stringBuffer.append(Long.toString(j));
        }
    }

    public static int calculateDigitCount(long j) {
        if (j < 0) {
            if (j != Long.MIN_VALUE) {
                return calculateDigitCount(-j) + 1;
            }
            return 20;
        } else if (j < 10) {
            return 1;
        } else {
            if (j < 100) {
                return 2;
            }
            if (j < 1000) {
                return 3;
            }
            return j < 10000 ? 4 : ((int) (Math.log((double) j) / LOG_10)) + 1;
        }
    }

    static int parseTwoDigits(String str, int i) {
        int charAt = str.charAt(i) - 48;
        return (((charAt << 1) + (charAt << 3)) + str.charAt(i + 1)) - 48;
    }

    static String createErrorMessage(String str, int i) {
        String str2;
        int i2 = i + 32;
        if (str.length() <= i2 + 3) {
            str2 = str;
        } else {
            str2 = str.substring(0, i2).concat("...");
        }
        if (i <= 0) {
            return "Invalid format: \"" + str2 + '\"';
        }
        if (i >= str.length()) {
            return "Invalid format: \"" + str2 + "\" is too short";
        }
        return "Invalid format: \"" + str2 + "\" is malformed at \"" + str2.substring(i) + '\"';
    }
}
