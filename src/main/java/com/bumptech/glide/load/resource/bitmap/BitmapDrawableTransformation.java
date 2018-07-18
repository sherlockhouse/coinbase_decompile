package com.bumptech.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.util.Preconditions;
import java.security.MessageDigest;

public class BitmapDrawableTransformation implements Transformation<BitmapDrawable> {
    private final Transformation<Bitmap> wrapped;

    public BitmapDrawableTransformation(Transformation<Bitmap> wrapped) {
        this.wrapped = (Transformation) Preconditions.checkNotNull(wrapped);
    }

    public Resource<BitmapDrawable> transform(Context context, Resource<BitmapDrawable> drawableResourceToTransform, int outWidth, int outHeight) {
        BitmapResource bitmapResourceToTransform = BitmapResource.obtain(((BitmapDrawable) drawableResourceToTransform.get()).getBitmap(), Glide.get(context).getBitmapPool());
        Resource<Bitmap> transformedBitmapResource = this.wrapped.transform(context, bitmapResourceToTransform, outWidth, outHeight);
        return transformedBitmapResource.equals(bitmapResourceToTransform) ? drawableResourceToTransform : LazyBitmapDrawableResource.obtain(context, (Bitmap) transformedBitmapResource.get());
    }

    public boolean equals(Object o) {
        if (!(o instanceof BitmapDrawableTransformation)) {
            return false;
        }
        return this.wrapped.equals(((BitmapDrawableTransformation) o).wrapped);
    }

    public int hashCode() {
        return this.wrapped.hashCode();
    }

    public void updateDiskCacheKey(MessageDigest messageDigest) {
        this.wrapped.updateDiskCacheKey(messageDigest);
    }
}
