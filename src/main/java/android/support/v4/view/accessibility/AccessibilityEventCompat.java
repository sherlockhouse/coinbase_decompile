package android.support.v4.view.accessibility;

import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityEvent;

public final class AccessibilityEventCompat {
    private static final AccessibilityEventCompatBaseImpl IMPL;

    static class AccessibilityEventCompatBaseImpl {
        AccessibilityEventCompatBaseImpl() {
        }

        public void setContentChangeTypes(AccessibilityEvent event, int types) {
        }

        public int getContentChangeTypes(AccessibilityEvent event) {
            return 0;
        }
    }

    static class AccessibilityEventCompatApi16Impl extends AccessibilityEventCompatBaseImpl {
        AccessibilityEventCompatApi16Impl() {
        }
    }

    static class AccessibilityEventCompatApi19Impl extends AccessibilityEventCompatApi16Impl {
        AccessibilityEventCompatApi19Impl() {
        }

        public void setContentChangeTypes(AccessibilityEvent event, int types) {
            event.setContentChangeTypes(types);
        }

        public int getContentChangeTypes(AccessibilityEvent event) {
            return event.getContentChangeTypes();
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            IMPL = new AccessibilityEventCompatApi19Impl();
        } else if (VERSION.SDK_INT >= 16) {
            IMPL = new AccessibilityEventCompatApi16Impl();
        } else {
            IMPL = new AccessibilityEventCompatBaseImpl();
        }
    }

    public static void setContentChangeTypes(AccessibilityEvent event, int changeTypes) {
        IMPL.setContentChangeTypes(event, changeTypes);
    }

    public static int getContentChangeTypes(AccessibilityEvent event) {
        return IMPL.getContentChangeTypes(event);
    }
}
