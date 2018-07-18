package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

public class CenterInside extends BitmapTransformation {
    private static final byte[] ID_BYTES = "com.bumptech.glide.load.resource.bitmap.CenterInside".getBytes(CHARSET);

    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return TransformationUtils.centerInside(pool, toTransform, outWidth, outHeight);
    }

    public boolean equals(Object o) {
        return o instanceof CenterInside;
    }

    public int hashCode() {
        return "com.bumptech.glide.load.resource.bitmap.CenterInside".hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }
}
