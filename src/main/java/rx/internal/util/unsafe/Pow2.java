package rx.internal.util.unsafe;

public final class Pow2 {
    public static int roundToPowerOfTwo(int value) {
        return 1 << (32 - Integer.numberOfLeadingZeros(value - 1));
    }

    public static boolean isPowerOfTwo(int value) {
        return ((value + -1) & value) == 0;
    }
}
