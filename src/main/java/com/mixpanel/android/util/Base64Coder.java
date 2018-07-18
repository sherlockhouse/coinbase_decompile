package com.mixpanel.android.util;

import com.coinbase.android.ui.NumericKeypadConnector;

public class Base64Coder {
    private static char[] map1 = new char[64];
    private static byte[] map2 = new byte[128];

    static {
        int i;
        char c = 'A';
        int i2 = 0;
        while (c <= 'Z') {
            i = i2 + 1;
            map1[i2] = c;
            c = (char) (c + 1);
            i2 = i;
        }
        c = 'a';
        while (c <= 'z') {
            i = i2 + 1;
            map1[i2] = c;
            c = (char) (c + 1);
            i2 = i;
        }
        c = NumericKeypadConnector.ZERO;
        while (c <= '9') {
            i = i2 + 1;
            map1[i2] = c;
            c = (char) (c + 1);
            i2 = i;
        }
        i = i2 + 1;
        map1[i2] = '+';
        i2 = i + 1;
        map1[i] = '/';
        for (i = 0; i < map2.length; i++) {
            map2[i] = (byte) -1;
        }
        for (i = 0; i < 64; i++) {
            map2[map1[i]] = (byte) i;
        }
    }

    public static String encodeString(String s) {
        return new String(encode(s.getBytes()));
    }

    public static char[] encode(byte[] in) {
        return encode(in, in.length);
    }

    public static char[] encode(byte[] in, int iLen) {
        int oDataLen = ((iLen * 4) + 2) / 3;
        char[] out = new char[(((iLen + 2) / 3) * 4)];
        int op = 0;
        int ip = 0;
        while (ip < iLen) {
            int i1;
            int i2;
            int ip2 = ip + 1;
            int i0 = in[ip] & 255;
            if (ip2 < iLen) {
                ip = ip2 + 1;
                i1 = in[ip2] & 255;
            } else {
                i1 = 0;
                ip = ip2;
            }
            if (ip < iLen) {
                ip2 = ip + 1;
                i2 = in[ip] & 255;
            } else {
                i2 = 0;
                ip2 = ip;
            }
            int o1 = ((i0 & 3) << 4) | (i1 >>> 4);
            int o2 = ((i1 & 15) << 2) | (i2 >>> 6);
            int o3 = i2 & 63;
            int i = op + 1;
            out[op] = map1[i0 >>> 2];
            op = i + 1;
            out[i] = map1[o1];
            out[op] = op < oDataLen ? map1[o2] : '=';
            i = op + 1;
            out[i] = i < oDataLen ? map1[o3] : '=';
            op = i + 1;
            ip = ip2;
        }
        return out;
    }
}
