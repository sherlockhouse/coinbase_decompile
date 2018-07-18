package com.fasterxml.jackson.core.io;

import com.coinbase.android.ui.NumericKeypadConnector;
import java.util.Arrays;

public final class CharTypes {
    private static final byte[] HB;
    private static final char[] HC = "0123456789ABCDEF".toCharArray();
    static final int[] sHexValues = new int[128];
    static final int[] sInputCodes;
    static final int[] sInputCodesComment;
    static final int[] sInputCodesJsNames;
    static final int[] sInputCodesUTF8;
    static final int[] sInputCodesUtf8JsNames;
    static final int[] sInputCodesWS;
    static final int[] sOutputEscapes128;

    static {
        int i;
        int len = HC.length;
        HB = new byte[len];
        for (i = 0; i < len; i++) {
            HB[i] = (byte) HC[i];
        }
        int[] table = new int[256];
        for (i = 0; i < 32; i++) {
            table[i] = -1;
        }
        table[34] = 1;
        table[92] = 1;
        sInputCodes = table;
        table = new int[sInputCodes.length];
        System.arraycopy(sInputCodes, 0, table, 0, table.length);
        for (int c = 128; c < 256; c++) {
            int code;
            if ((c & 224) == 192) {
                code = 2;
            } else if ((c & 240) == 224) {
                code = 3;
            } else if ((c & 248) == 240) {
                code = 4;
            } else {
                code = -1;
            }
            table[c] = code;
        }
        sInputCodesUTF8 = table;
        table = new int[256];
        Arrays.fill(table, -1);
        for (i = 33; i < 256; i++) {
            if (Character.isJavaIdentifierPart((char) i)) {
                table[i] = 0;
            }
        }
        table[64] = 0;
        table[35] = 0;
        table[42] = 0;
        table[45] = 0;
        table[43] = 0;
        sInputCodesJsNames = table;
        table = new int[256];
        System.arraycopy(sInputCodesJsNames, 0, table, 0, table.length);
        Arrays.fill(table, 128, 128, 0);
        sInputCodesUtf8JsNames = table;
        int[] buf = new int[256];
        System.arraycopy(sInputCodesUTF8, 128, buf, 128, 128);
        Arrays.fill(buf, 0, 32, -1);
        buf[9] = 0;
        buf[10] = 10;
        buf[13] = 13;
        buf[42] = 42;
        sInputCodesComment = buf;
        buf = new int[256];
        System.arraycopy(sInputCodesUTF8, 128, buf, 128, 128);
        Arrays.fill(buf, 0, 32, -1);
        buf[32] = 1;
        buf[9] = 1;
        buf[10] = 10;
        buf[13] = 13;
        buf[47] = 47;
        buf[35] = 35;
        sInputCodesWS = buf;
        table = new int[128];
        for (i = 0; i < 32; i++) {
            table[i] = -1;
        }
        table[34] = 34;
        table[92] = 92;
        table[8] = 98;
        table[9] = 116;
        table[12] = 102;
        table[10] = 110;
        table[13] = 114;
        sOutputEscapes128 = table;
        Arrays.fill(sHexValues, -1);
        for (i = 0; i < 10; i++) {
            sHexValues[i + 48] = i;
        }
        for (i = 0; i < 6; i++) {
            sHexValues[i + 97] = i + 10;
            sHexValues[i + 65] = i + 10;
        }
    }

    public static int[] getInputCodeLatin1() {
        return sInputCodes;
    }

    public static int[] getInputCodeLatin1JsNames() {
        return sInputCodesJsNames;
    }

    public static int[] get7BitOutputEscapes() {
        return sOutputEscapes128;
    }

    public static int charToHex(int ch) {
        return ch > 127 ? -1 : sHexValues[ch];
    }

    public static void appendQuoted(StringBuilder sb, String content) {
        int[] escCodes = sOutputEscapes128;
        char escLen = escCodes.length;
        int len = content.length();
        for (int i = 0; i < len; i++) {
            char c = content.charAt(i);
            if (c >= escLen || escCodes[c] == 0) {
                sb.append(c);
            } else {
                sb.append('\\');
                int escCode = escCodes[c];
                if (escCode < 0) {
                    sb.append('u');
                    sb.append(NumericKeypadConnector.ZERO);
                    sb.append(NumericKeypadConnector.ZERO);
                    char value = c;
                    sb.append(HC[value >> 4]);
                    sb.append(HC[value & 15]);
                } else {
                    sb.append((char) escCode);
                }
            }
        }
    }

    public static char[] copyHexChars() {
        return (char[]) HC.clone();
    }

    public static byte[] copyHexBytes() {
        return (byte[]) HB.clone();
    }
}
