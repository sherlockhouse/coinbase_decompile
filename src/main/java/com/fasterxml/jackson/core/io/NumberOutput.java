package com.fasterxml.jackson.core.io;

public final class NumberOutput {
    private static int BILLION = 1000000000;
    static final char[] FULL_3 = new char[4000];
    static final byte[] FULL_TRIPLETS_B = new byte[4000];
    static final char[] LEAD_3 = new char[4000];
    private static long MAX_INT_AS_LONG = 2147483647L;
    private static int MILLION = 1000000;
    private static long MIN_INT_AS_LONG = -2147483648L;
    static final String SMALLEST_LONG = String.valueOf(Long.MIN_VALUE);
    private static long TEN_BILLION_L = 10000000000L;
    private static long THOUSAND_L = 1000;
    static final String[] sSmallIntStrs = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    static final String[] sSmallIntStrs2 = new String[]{"-1", "-2", "-3", "-4", "-5", "-6", "-7", "-8", "-9", "-10"};

    static {
        int ix = 0;
        for (int i1 = 0; i1 < 10; i1++) {
            char l1;
            char f1 = (char) (i1 + 48);
            if (i1 == 0) {
                l1 = '\u0000';
            } else {
                l1 = f1;
            }
            int i2 = 0;
            while (i2 < 10) {
                char l2;
                char f2 = (char) (i2 + 48);
                if (i1 == 0 && i2 == 0) {
                    l2 = '\u0000';
                } else {
                    l2 = f2;
                }
                for (int i3 = 0; i3 < 10; i3++) {
                    char f3 = (char) (i3 + 48);
                    LEAD_3[ix] = l1;
                    LEAD_3[ix + 1] = l2;
                    LEAD_3[ix + 2] = f3;
                    FULL_3[ix] = f1;
                    FULL_3[ix + 1] = f2;
                    FULL_3[ix + 2] = f3;
                    ix += 4;
                }
                i2++;
            }
        }
        for (int i = 0; i < 4000; i++) {
            FULL_TRIPLETS_B[i] = (byte) FULL_3[i];
        }
    }

    public static int outputInt(int v, char[] b, int off) {
        if (v < 0) {
            if (v == Integer.MIN_VALUE) {
                return outputLong((long) v, b, off);
            }
            int off2 = off + 1;
            b[off] = '-';
            v = -v;
            off = off2;
        }
        if (v < MILLION) {
            if (v >= 1000) {
                int thousands = v / 1000;
                off = full3(v - (thousands * 1000), b, leading3(thousands, b, off));
            } else if (v < 10) {
                off2 = off + 1;
                b[off] = (char) (v + 48);
                off = off2;
            } else {
                off = leading3(v, b, off);
            }
            return off;
        }
        boolean hasBillions = v >= BILLION;
        if (hasBillions) {
            v -= BILLION;
            if (v >= BILLION) {
                v -= BILLION;
                off2 = off + 1;
                b[off] = '2';
                off = off2;
            } else {
                off2 = off + 1;
                b[off] = '1';
                off = off2;
            }
        }
        int newValue = v / 1000;
        int ones = v - (newValue * 1000);
        v = newValue;
        newValue /= 1000;
        thousands = v - (newValue * 1000);
        if (hasBillions) {
            off = full3(newValue, b, off);
        } else {
            off = leading3(newValue, b, off);
        }
        return full3(ones, b, full3(thousands, b, off));
    }

    public static int outputLong(long v, char[] b, int off) {
        if (v < 0) {
            if (v > MIN_INT_AS_LONG) {
                return outputInt((int) v, b, off);
            }
            if (v == Long.MIN_VALUE) {
                int len = SMALLEST_LONG.length();
                SMALLEST_LONG.getChars(0, len, b, off);
                return off + len;
            }
            int off2 = off + 1;
            b[off] = '-';
            v = -v;
            off = off2;
        } else if (v <= MAX_INT_AS_LONG) {
            return outputInt((int) v, b, off);
        }
        int origOffset = off;
        off += calcLongStrLength(v);
        int ptr = off;
        while (v > MAX_INT_AS_LONG) {
            ptr -= 3;
            long newValue = v / THOUSAND_L;
            full3((int) (v - (THOUSAND_L * newValue)), b, ptr);
            v = newValue;
        }
        int ivalue = (int) v;
        while (ivalue >= 1000) {
            ptr -= 3;
            int newValue2 = ivalue / 1000;
            full3(ivalue - (newValue2 * 1000), b, ptr);
            ivalue = newValue2;
        }
        leading3(ivalue, b, origOffset);
        return off;
    }

    public static String toString(int v) {
        if (v < sSmallIntStrs.length) {
            if (v >= 0) {
                return sSmallIntStrs[v];
            }
            int v2 = (-v) - 1;
            if (v2 < sSmallIntStrs2.length) {
                return sSmallIntStrs2[v2];
            }
        }
        return Integer.toString(v);
    }

    public static String toString(long v) {
        if (v > 2147483647L || v < -2147483648L) {
            return Long.toString(v);
        }
        return toString((int) v);
    }

    public static String toString(double v) {
        return Double.toString(v);
    }

    private static int leading3(int t, char[] b, int off) {
        int off2;
        int digitOffset = t << 2;
        int digitOffset2 = digitOffset + 1;
        char c = LEAD_3[digitOffset];
        if (c != '\u0000') {
            off2 = off + 1;
            b[off] = c;
            off = off2;
        }
        digitOffset = digitOffset2 + 1;
        c = LEAD_3[digitOffset2];
        if (c != '\u0000') {
            off2 = off + 1;
            b[off] = c;
            off = off2;
        }
        off2 = off + 1;
        b[off] = LEAD_3[digitOffset];
        return off2;
    }

    private static int full3(int t, char[] b, int off) {
        int digitOffset = t << 2;
        int i = off + 1;
        int digitOffset2 = digitOffset + 1;
        b[off] = FULL_3[digitOffset];
        off = i + 1;
        digitOffset = digitOffset2 + 1;
        b[i] = FULL_3[digitOffset2];
        i = off + 1;
        b[off] = FULL_3[digitOffset];
        return i;
    }

    private static int calcLongStrLength(long v) {
        int len = 10;
        for (long cmp = TEN_BILLION_L; v >= cmp && len != 19; cmp = (cmp << 3) + (cmp << 1)) {
            len++;
        }
        return len;
    }
}
