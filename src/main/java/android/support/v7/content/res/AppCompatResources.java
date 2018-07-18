package android.support.v7.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import java.util.WeakHashMap;

public final class AppCompatResources {
    private static final ThreadLocal<TypedValue> TL_TYPED_VALUE = new ThreadLocal();
    private static final Object sColorStateCacheLock = new Object();
    private static final WeakHashMap<Context, SparseArray<ColorStateListCacheEntry>> sColorStateCaches = new WeakHashMap(0);

    private static class ColorStateListCacheEntry {
        final Configuration configuration;
        final ColorStateList value;

        ColorStateListCacheEntry(ColorStateList value, Configuration configuration) {
            this.value = value;
            this.configuration = configuration;
        }
    }

    public static ColorStateList getColorStateList(Context context, int resId) {
        if (VERSION.SDK_INT >= 23) {
            return context.getColorStateList(resId);
        }
        ColorStateList csl = getCachedColorStateList(context, resId);
        if (csl != null) {
            return csl;
        }
        csl = inflateColorStateList(context, resId);
        if (csl == null) {
            return ContextCompat.getColorStateList(context, resId);
        }
        addColorStateListToCache(context, resId, csl);
        return csl;
    }

    public static Drawable getDrawable(Context context, int resId) {
        return AppCompatDrawableManager.get().getDrawable(context, resId);
    }

    private static ColorStateList inflateColorStateList(Context context, int resId) {
        ColorStateList colorStateList = null;
        if (!isColorInt(context, resId)) {
            Resources r = context.getResources();
            try {
                colorStateList = AppCompatColorStateListInflater.createFromXml(r, r.getXml(resId), context.getTheme());
            } catch (Exception e) {
                Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", e);
            }
        }
        return colorStateList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static ColorStateList getCachedColorStateList(Context context, int resId) {
        synchronized (sColorStateCacheLock) {
            SparseArray<ColorStateListCacheEntry> entries = (SparseArray) sColorStateCaches.get(context);
            if (entries != null && entries.size() > 0) {
                ColorStateListCacheEntry entry = (ColorStateListCacheEntry) entries.get(resId);
                if (entry != null) {
                    if (entry.configuration.equals(context.getResources().getConfiguration())) {
                        ColorStateList colorStateList = entry.value;
                        return colorStateList;
                    }
                    entries.remove(resId);
                }
            }
        }
    }

    private static void addColorStateListToCache(Context context, int resId, ColorStateList value) {
        synchronized (sColorStateCacheLock) {
            SparseArray<ColorStateListCacheEntry> entries = (SparseArray) sColorStateCaches.get(context);
            if (entries == null) {
                entries = new SparseArray();
                sColorStateCaches.put(context, entries);
            }
            entries.append(resId, new ColorStateListCacheEntry(value, context.getResources().getConfiguration()));
        }
    }

    private static boolean isColorInt(Context context, int resId) {
        Resources r = context.getResources();
        TypedValue value = getTypedValue();
        r.getValue(resId, value, true);
        if (value.type < 28 || value.type > 31) {
            return false;
        }
        return true;
    }

    private static TypedValue getTypedValue() {
        TypedValue tv = (TypedValue) TL_TYPED_VALUE.get();
        if (tv != null) {
            return tv;
        }
        tv = new TypedValue();
        TL_TYPED_VALUE.set(tv);
        return tv;
    }
}
