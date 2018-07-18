package org.apache.commons.codec.binary;

import com.coinbase.android.ui.NumericKeypadConnector;
import java.nio.charset.Charset;
import org.apache.commons.codec.Charsets;

public class Hex {
    public static final Charset DEFAULT_CHARSET = Charsets.UTF_8;
    private static final char[] DIGITS_LOWER = new char[]{NumericKeypadConnector.ZERO, '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = new char[]{NumericKeypadConnector.ZERO, '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final Charset charset = DEFAULT_CHARSET;

    public static char[] encodeHex(byte[] data) {
        return encodeHex(data, true);
    }

    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        int l = data.length;
        char[] out = new char[(l << 1)];
        int j = 0;
        for (int i = 0; i < l; i++) {
            int i2 = j + 1;
            out[j] = toDigits[(data[i] & 240) >>> 4];
            j = i2 + 1;
            out[i2] = toDigits[data[i] & 15];
        }
        return out;
    }

    public String toString() {
        return super.toString() + "[charsetName=" + this.charset + "]";
    }
}
