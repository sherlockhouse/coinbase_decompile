package org.spongycastle.util;

public final class Arrays {
    public static boolean areEqual(byte[] a, byte[] b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i != a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static boolean constantTimeAreEqual(byte[] a, byte[] b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        int nonEqual = 0;
        for (int i = 0; i != a.length; i++) {
            nonEqual |= a[i] ^ b[i];
        }
        if (nonEqual != 0) {
            return false;
        }
        return true;
    }

    public static boolean areEqual(int[] a, int[] b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i != a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void fill(byte[] array, byte value) {
        for (int i = 0; i < array.length; i++) {
            array[i] = value;
        }
    }

    public static byte[] clone(byte[] data) {
        if (data == null) {
            return null;
        }
        byte[] copy = new byte[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);
        return copy;
    }

    public static int[] clone(int[] data) {
        if (data == null) {
            return null;
        }
        int[] copy = new int[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);
        return copy;
    }
}
