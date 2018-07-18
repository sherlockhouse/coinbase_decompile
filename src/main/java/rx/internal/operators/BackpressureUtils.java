package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;

public final class BackpressureUtils {
    public static long getAndAddRequest(AtomicLong requested, long n) {
        long current;
        do {
            current = requested.get();
        } while (!requested.compareAndSet(current, addCap(current, n)));
        return current;
    }

    public static long addCap(long a, long b) {
        long u = a + b;
        if (u < 0) {
            return Long.MAX_VALUE;
        }
        return u;
    }

    public static long produced(AtomicLong requested, long n) {
        long next;
        long current;
        do {
            current = requested.get();
            if (current == Long.MAX_VALUE) {
                return Long.MAX_VALUE;
            }
            next = current - n;
            if (next < 0) {
                throw new IllegalStateException("More produced than requested: " + next);
            }
        } while (!requested.compareAndSet(current, next));
        return next;
    }

    public static boolean validate(long n) {
        if (n >= 0) {
            return n != 0;
        } else {
            throw new IllegalArgumentException("n >= 0 required but it was " + n);
        }
    }
}
