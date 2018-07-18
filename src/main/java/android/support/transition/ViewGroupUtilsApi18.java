package android.support.transition;

import android.util.Log;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ViewGroupUtilsApi18 extends ViewGroupUtilsApi14 {
    private static Method sSuppressLayoutMethod;
    private static boolean sSuppressLayoutMethodFetched;

    ViewGroupUtilsApi18() {
    }

    public ViewGroupOverlayImpl getOverlay(ViewGroup group) {
        return new ViewGroupOverlayApi18(group);
    }

    public void suppressLayout(ViewGroup group, boolean suppress) {
        fetchSuppressLayoutMethod();
        if (sSuppressLayoutMethod != null) {
            try {
                sSuppressLayoutMethod.invoke(group, new Object[]{Boolean.valueOf(suppress)});
            } catch (IllegalAccessException e) {
                Log.i("ViewUtilsApi18", "Failed to invoke suppressLayout method", e);
            } catch (InvocationTargetException e2) {
                Log.i("ViewUtilsApi18", "Error invoking suppressLayout method", e2);
            }
        }
    }

    private void fetchSuppressLayoutMethod() {
        if (!sSuppressLayoutMethodFetched) {
            try {
                sSuppressLayoutMethod = ViewGroup.class.getDeclaredMethod("suppressLayout", new Class[]{Boolean.TYPE});
                sSuppressLayoutMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.i("ViewUtilsApi18", "Failed to retrieve suppressLayout method", e);
            }
            sSuppressLayoutMethodFetched = true;
        }
    }
}
