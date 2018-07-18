package org.spongycastle.crypto.modes.gcm;

import org.spongycastle.util.Pack;

public abstract class GCMUtil {
    private static final int[] LOOKUP = generateLookup();

    private static int[] generateLookup() {
        int[] lookup = new int[256];
        for (int c = 0; c < 256; c++) {
            int v = 0;
            for (int i = 7; i >= 0; i--) {
                if (((1 << i) & c) != 0) {
                    v ^= -520093696 >>> (7 - i);
                }
            }
            lookup[c] = v;
        }
        return lookup;
    }

    public static int[] oneAsInts() {
        int[] tmp = new int[4];
        tmp[0] = Integer.MIN_VALUE;
        return tmp;
    }

    public static void asBytes(int[] x, byte[] z) {
        Pack.intToBigEndian(x, z, 0);
    }

    public static int[] asInts(byte[] x) {
        int[] z = new int[4];
        Pack.bigEndianToInt(x, 0, z);
        return z;
    }

    public static void asInts(byte[] x, int[] z) {
        Pack.bigEndianToInt(x, 0, z);
    }

    public static void multiply(byte[] x, byte[] y) {
        int[] t1 = asInts(x);
        multiply(t1, asInts(y));
        asBytes(t1, x);
    }

    public static void multiply(int[] x, int[] y) {
        int r00 = x[0];
        int r01 = x[1];
        int r02 = x[2];
        int r03 = x[3];
        int r10 = 0;
        int r11 = 0;
        int r12 = 0;
        int r13 = 0;
        for (int i = 0; i < 4; i++) {
            int bits = y[i];
            for (int j = 0; j < 32; j++) {
                int m1 = bits >> 31;
                bits <<= 1;
                r10 ^= r00 & m1;
                r11 ^= r01 & m1;
                r12 ^= r02 & m1;
                r13 ^= r03 & m1;
                int m2 = (r03 << 31) >> 8;
                r03 = (r03 >>> 1) | (r02 << 63);
                r02 = (r02 >>> 1) | (r01 << 63);
                r01 = (r01 >>> 1) | (r00 << 63);
                r00 = (r00 >>> 1) ^ (-520093696 & m2);
            }
        }
        x[0] = r10;
        x[1] = r11;
        x[2] = r12;
        x[3] = r13;
    }

    public static void multiplyP(int[] x, int[] z) {
        z[0] = z[0] ^ (-520093696 & (shiftRight(x, z) >> 8));
    }

    public static void multiplyP8(int[] x, int[] y) {
        y[0] = y[0] ^ LOOKUP[shiftRightN(x, 8, y) >>> 24];
    }

    static int shiftRight(int[] x, int[] z) {
        int b = x[0];
        z[0] = b >>> 1;
        int c = b << 31;
        b = x[1];
        z[1] = (b >>> 1) | c;
        c = b << 31;
        b = x[2];
        z[2] = (b >>> 1) | c;
        c = b << 31;
        b = x[3];
        z[3] = (b >>> 1) | c;
        return b << 31;
    }

    static int shiftRightN(int[] x, int n, int[] z) {
        int b = x[0];
        int nInv = 32 - n;
        z[0] = b >>> n;
        int c = b << nInv;
        b = x[1];
        z[1] = (b >>> n) | c;
        c = b << nInv;
        b = x[2];
        z[2] = (b >>> n) | c;
        c = b << nInv;
        b = x[3];
        z[3] = (b >>> n) | c;
        return b << nInv;
    }

    public static void xor(byte[] x, byte[] y) {
        int i = 0;
        do {
            x[i] = (byte) (x[i] ^ y[i]);
            i++;
            x[i] = (byte) (x[i] ^ y[i]);
            i++;
            x[i] = (byte) (x[i] ^ y[i]);
            i++;
            x[i] = (byte) (x[i] ^ y[i]);
            i++;
        } while (i < 16);
    }

    public static void xor(byte[] x, byte[] y, int yOff, int yLen) {
        while (true) {
            yLen--;
            if (yLen >= 0) {
                x[yLen] = (byte) (x[yLen] ^ y[yOff + yLen]);
            } else {
                return;
            }
        }
    }

    public static void xor(int[] x, int[] y, int[] z) {
        z[0] = x[0] ^ y[0];
        z[1] = x[1] ^ y[1];
        z[2] = x[2] ^ y[2];
        z[3] = x[3] ^ y[3];
    }
}
