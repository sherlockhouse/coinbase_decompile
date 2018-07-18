package rx.internal.util;

public final class PlatformDependent {
    private static final int ANDROID_API_VERSION = resolveAndroidApiVersion();
    private static final boolean IS_ANDROID = (ANDROID_API_VERSION != 0);

    public static boolean isAndroid() {
        return IS_ANDROID;
    }

    public static int getAndroidApiVersion() {
        return ANDROID_API_VERSION;
    }

    private static int resolveAndroidApiVersion() {
        try {
            return ((Integer) Class.forName("android.os.Build$VERSION").getField("SDK_INT").get(null)).intValue();
        } catch (Exception e) {
            return 0;
        }
    }
}
