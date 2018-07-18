package com.bumptech.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.bitmap.BitmapResource;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;

public class GifDrawableTransformation implements Transformation<GifDrawable> {
    private final Transformation<Bitmap> wrapped;

    public GifDrawableTransformation(Transformation<Bitmap> wrapped) {
        this.wrapped = (Transformation) Preconditions.checkNotNull(wrapped);
    }

    public Resource<GifDrawable> transform(Context context, Resource<GifDrawable> resource, int outWidth, int outHeight) {
        GifDrawable drawable = (GifDrawable) resource.get();
        Resource<Bitmap> bitmapResource = new BitmapResource(drawable.getFirstFrame(), Glide.get(context).getBitmapPool());
        Resource<Bitmap> transformed = this.wrapped.transform(context, bitmapResource, outWidth, outHeight);
        if (!bitmapResource.equals(transformed)) {
            bitmapResource.recycle();
        }
        drawable.setFrameTransformation(this.wrapped, (Bitmap) transformed.get());
        return resource;
    }

    public boolean equals(Object o) {
        if (!(o instanceof GifDrawableTransformation)) {
            return false;
        }
        return this.wrapped.equals(((GifDrawableTransformation) o).wrapped);
    }

    public int hashCode() {
        return this.wrapped.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        this.wrapped.updateDiskCacheKey(messageDigest);
    }
}
