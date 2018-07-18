package com.coinbase.android.crypto;

import java.util.Arrays;

public class ByteArrayUtils {
    public static void xor(byte[] bytes, int offset, byte[] bytesToMix, int mixOffset, int len) {
        int bytesLength = offset + len;
        int mixOffset2 = mixOffset;
        while (offset < bytesLength) {
            mixOffset = mixOffset2 + 1;
            bytes[offset] = (byte) (bytes[offset] ^ bytesToMix[mixOffset2]);
            offset++;
            mixOffset2 = mixOffset;
        }
    }

    public static void xor(byte[] dest, byte[] bytesToMix) {
        xor(dest, 0, bytesToMix, 0, dest.length);
    }

    public static byte[] copyOfRange(byte[] bytes, int offset, int len) {
        byte[] result = new byte[len];
        System.arraycopy(bytes, offset, result, 0, len);
        return result;
    }

    public static byte[] toBytes(int integer) {
        byte[] result = new byte[4];
        toBytes(integer, result, 0);
        return result;
    }

    public static void toBytes(int integer, byte[] output, int offset) {
        int i = offset;
        int i2 = i + 1;
        output[i] = (byte) (integer >>> 24);
        i = i2 + 1;
        output[i2] = (byte) (integer >>> 16);
        i2 = i + 1;
        output[i] = (byte) (integer >>> 8);
        output[i2] = (byte) (integer & 255);
    }

    public static byte[] toBytes(int... integer) {
        byte[] result = new byte[(integer.length * 4)];
        int offset = 0;
        for (int anInteger : integer) {
            toBytes(anInteger, result, offset);
            offset += 4;
        }
        return result;
    }

    public static byte[] toBytes(String s) {
        return toBytes(s.toCharArray());
    }

    public static byte[] toBytes(char[] chars) {
        byte[] result = new byte[chars.length];
        int length = chars.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            result[i2] = (byte) chars[i];
            i++;
            i2 = i3;
        }
        return result;
    }

    public static byte[] concatenate(byte[]... bytes) {
        int length = 0;
        for (byte[] array : bytes) {
            length += array.length;
        }
        byte[] result = new byte[length];
        int offset = 0;
        for (byte[] array2 : bytes) {
            System.arraycopy(array2, 0, result, offset, array2.length);
            offset += array2.length;
        }
        return result;
    }

    public static byte[] toBytes(long longValue) {
        return new byte[]{(byte) ((int) (longValue >>> 56)), (byte) ((int) (longValue >>> 48)), (byte) ((int) (longValue >>> 40)), (byte) ((int) (longValue >>> 32)), (byte) ((int) (longValue >>> 24)), (byte) ((int) (longValue >>> 16)), (byte) ((int) (longValue >>> 8)), (byte) ((int) (255 & longValue))};
    }

    public static byte[] hexToBytes(String hex) {
        hex = removeSpaces(hex);
        int resultLen = hex.length() / 2;
        byte[] result = new byte[resultLen];
        int j = 0;
        for (int i = 0; i < resultLen; i++) {
            int j2 = j + 1;
            j = j2 + 1;
            result[i] = (byte) ((Byte.parseByte(hex.substring(j, j2), 16) << 4) | Byte.parseByte(hex.substring(j2, j), 16));
        }
        return result;
    }

    private static String removeSpaces(String string) {
        return string.replaceAll("\\s+", "");
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(Integer.toHexString((b >> 4) & 15));
            builder.append(Integer.toHexString(b & 15));
        }
        return builder.toString();
    }

    public static String toHexString(byte[] bytes, int offset, int length) {
        return toHexString(copyOfRange(bytes, offset, length));
    }

    public static int getBit(byte[] bytes, int bitNr) {
        return (bytes[(bytes.length - (bitNr / 8)) - 1] >>> (bitNr % 8)) & 1;
    }

    public static void setBit(byte[] bytes, int bitNr, int bit) {
        int byteNr = (bytes.length - (bitNr / 8)) - 1;
        int bitNrInByte = bitNr % 8;
        if (bit != 0) {
            bytes[byteNr] = (byte) (bytes[byteNr] | (1 << bitNrInByte));
        } else {
            bytes[byteNr] = (byte) (bytes[byteNr] & ((1 << bitNrInByte) ^ -1));
        }
    }

    public static String toAsciiString(byte[] output) {
        char[] chars = new char[output.length];
        for (int i = 0; i < output.length; i++) {
            chars[i] = (char) output[i];
        }
        return new String(chars);
    }

    public static int toInteger(byte[] input) {
        return toInteger(input, 0);
    }

    private static int toInteger(byte[] input, int offset) {
        int offset2 = offset + 1;
        offset = offset2 + 1;
        return ((((input[offset] & 255) << 24) | ((input[offset2] & 255) << 16)) | ((input[offset] & 255) << 8)) | (input[offset + 1] & 255);
    }

    public static int[] toIntegerArray(byte[] input, int offset, int len) {
        int[] result = new int[(len / 4)];
        toIntegerArray(input, offset, len, result, 0);
        return result;
    }

    public static void toIntegerArray(byte[] input, int offset, int len, int[] output, int outputOffset) {
        int outputLen = len / 4;
        for (int i = outputOffset; i < outputOffset + outputLen; i++) {
            output[i] = toInteger(input, offset);
            offset += 4;
        }
    }

    public static byte[] repeat(byte b, int nrRepeats) {
        byte[] result = new byte[nrRepeats];
        Arrays.fill(result, b);
        return result;
    }

    public static byte[] copyOf(byte[] bytes) {
        return copyOfRange(bytes, 0, bytes.length);
    }
}
