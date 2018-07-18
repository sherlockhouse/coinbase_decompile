package com.squareup.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class LruCache implements Cache {
    private int evictionCount;
    private int hitCount;
    final LinkedHashMap<String, Bitmap> map;
    private final int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(Context context) {
        this(Utils.calculateMemoryCacheSize(context));
    }

    public LruCache(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Max size must be positive.");
        }
        this.maxSize = maxSize;
        this.map = new LinkedHashMap(0, 0.75f, true);
    }

    public Bitmap get(String key) {
        if (key == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            Bitmap mapValue = (Bitmap) this.map.get(key);
            if (mapValue != null) {
                this.hitCount++;
                return mapValue;
            }
            this.missCount++;
            return null;
        }
    }

    public void set(String key, Bitmap bitmap) {
        if (key == null || bitmap == null) {
            throw new NullPointerException("key == null || bitmap == null");
        }
        synchronized (this) {
            this.putCount++;
            this.size += Utils.getBitmapBytes(bitmap);
            Bitmap previous = (Bitmap) this.map.put(key, bitmap);
            if (previous != null) {
                this.size -= Utils.getBitmapBytes(previous);
            }
        }
        trimToSize(this.maxSize);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void trimToSize(int maxSize) {
        while (true) {
            synchronized (this) {
                if (this.size >= 0 && (!this.map.isEmpty() || this.size == 0)) {
                    if (this.size <= maxSize || this.map.isEmpty()) {
                    } else {
                        Entry<String, Bitmap> toEvict = (Entry) this.map.entrySet().iterator().next();
                        Bitmap value = (Bitmap) toEvict.getValue();
                        this.map.remove((String) toEvict.getKey());
                        this.size -= Utils.getBitmapBytes(value);
                        this.evictionCount++;
                    }
                }
            }
        }
    }

    public final synchronized int size() {
        return this.size;
    }

    public final synchronized int maxSize() {
        return this.maxSize;
    }
}
