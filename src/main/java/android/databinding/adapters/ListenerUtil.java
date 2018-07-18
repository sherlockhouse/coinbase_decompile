package android.databinding.adapters;

import android.os.Build.VERSION;
import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class ListenerUtil {
    private static SparseArray<WeakHashMap<View, WeakReference<?>>> sListeners = new SparseArray();

    public static <T> T trackListener(View view, T listener, int listenerResourceId) {
        if (VERSION.SDK_INT >= 14) {
            T oldValue = view.getTag(listenerResourceId);
            view.setTag(listenerResourceId, listener);
            return oldValue;
        }
        synchronized (sListeners) {
            WeakReference<T> oldValue2;
            WeakHashMap<View, WeakReference<?>> listeners = (WeakHashMap) sListeners.get(listenerResourceId);
            if (listeners == null) {
                listeners = new WeakHashMap();
                sListeners.put(listenerResourceId, listeners);
            }
            if (listener == null) {
                oldValue2 = (WeakReference) listeners.remove(view);
            } else {
                oldValue2 = (WeakReference) listeners.put(view, new WeakReference(listener));
            }
            if (oldValue2 == null) {
                return null;
            }
            oldValue = oldValue2.get();
            return oldValue;
        }
    }
}
