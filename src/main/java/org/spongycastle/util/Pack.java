package org.spongycastle.util;

public abstract class Pack {
    public static int bigEndianToInt(byte[] bs, int off) {
        off++;
        off++;
        return (((bs[off] << 24) | ((bs[off] & 255) << 16)) | ((bs[off] & 255) << 8)) | (bs[off + 1] & 255);
    }

    public static void bigEndianToInt(byte[] bs, int off, int[] ns) {
        for (int i = 0; i < ns.length; i++) {
            ns[i] = bigEndianToInt(bs, off);
            off += 4;
        }
    }

    public static void intToBigEndian(int n, byte[] bs, int off) {
        bs[off] = (byte) (n >>> 24);
        off++;
        bs[off] = (byte) (n >>> 16);
        off++;
        bs[off] = (byte) (n >>> 8);
        bs[off + 1] = (byte) n;
    }

    public static void intToBigEndian(int[] ns, byte[] bs, int off) {
        for (int intToBigEndian : ns) {
            intToBigEndian(intToBigEndian, bs, off);
            off += 4;
        }
    }

    public static void longToBigEndian(long n, byte[] bs, int off) {
        intToBigEndian((int) (n >>> 32), bs, off);
        intToBigEndian((int) (4294967295L & n), bs, off + 4);
    }

    public static int littleEndianToInt(byte[] bs, int off) {
        off++;
        off++;
        return (((bs[off] & 255) | ((bs[off] & 255) << 8)) | ((bs[off] & 255) << 16)) | (bs[off + 1] << 24);
    }

    public static void intToLittleEndian(int n, byte[] bs, int off) {
        bs[off] = (byte) n;
        off++;
        bs[off] = (byte) (n >>> 8);
        off++;
        bs[off] = (byte) (n >>> 16);
        bs[off + 1] = (byte) (n >>> 24);
    }
}
