package android.support.transition;

import android.os.Build.VERSION;
import android.view.ViewGroup;

class ViewGroupUtils {
    private static final ViewGroupUtilsImpl IMPL;

    static {
        if (VERSION.SDK_INT >= 18) {
            IMPL = new ViewGroupUtilsApi18();
        } else {
            IMPL = new ViewGroupUtilsApi14();
        }
    }

    static ViewGroupOverlayImpl getOverlay(ViewGroup group) {
        return IMPL.getOverlay(group);
    }

    static void suppressLayout(ViewGroup group, boolean suppress) {
        IMPL.suppressLayout(group, suppress);
    }
}
