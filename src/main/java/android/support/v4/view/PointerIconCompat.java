package android.support.v4.view;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.PointerIcon;

public final class PointerIconCompat {
    private Object mPointerIcon;

    private PointerIconCompat(Object pointerIcon) {
        this.mPointerIcon = pointerIcon;
    }

    public Object getPointerIcon() {
        return this.mPointerIcon;
    }

    public static PointerIconCompat getSystemIcon(Context context, int style) {
        if (VERSION.SDK_INT >= 24) {
            return new PointerIconCompat(PointerIcon.getSystemIcon(context, style));
        }
        return new PointerIconCompat(null);
    }
}
