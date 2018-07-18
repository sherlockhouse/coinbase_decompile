package rx.internal.util.unsafe;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public final class UnsafeAccess {
    private static final boolean DISABLED_BY_USER;
    public static final Unsafe UNSAFE;

    static {
        boolean z = true;
        if (System.getProperty("rx.unsafe-disable") == null) {
            z = false;
        }
        DISABLED_BY_USER = z;
        Unsafe u = null;
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            u = (Unsafe) field.get(null);
        } catch (Throwable th) {
        }
        UNSAFE = u;
    }

    public static boolean isUnsafeAvailable() {
        return (UNSAFE == null || DISABLED_BY_USER) ? false : true;
    }

    public static long addressOf(Class<?> clazz, String fieldName) {
        try {
            return UNSAFE.objectFieldOffset(clazz.getDeclaredField(fieldName));
        } catch (NoSuchFieldException ex) {
            InternalError ie = new InternalError();
            ie.initCause(ex);
            throw ie;
        }
    }
}
