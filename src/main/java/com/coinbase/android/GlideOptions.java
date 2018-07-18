package com.coinbase.android;

import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestOptions;

public final class GlideOptions extends RequestOptions {
    private static GlideOptions centerCropTransform2;
    private static GlideOptions centerInsideTransform1;
    private static GlideOptions circleCropTransform3;
    private static GlideOptions fitCenterTransform0;
    private static GlideOptions noAnimation5;
    private static GlideOptions noTransformation4;

    public static GlideOptions sizeMultiplierOf(float sizeMultiplier) {
        return new GlideOptions().sizeMultiplier(sizeMultiplier);
    }

    public static GlideOptions diskCacheStrategyOf(DiskCacheStrategy arg0) {
        return new GlideOptions().diskCacheStrategy(arg0);
    }

    public static GlideOptions priorityOf(Priority arg0) {
        return new GlideOptions().priority(arg0);
    }

    public static GlideOptions placeholderOf(Drawable arg0) {
        return new GlideOptions().placeholder(arg0);
    }

    public static GlideOptions placeholderOf(int placeholderId) {
        return new GlideOptions().placeholder(placeholderId);
    }

    public static GlideOptions errorOf(Drawable arg0) {
        return new GlideOptions().error(arg0);
    }

    public static GlideOptions errorOf(int errorId) {
        return new GlideOptions().error(errorId);
    }

    public static GlideOptions skipMemoryCacheOf(boolean skipMemoryCache) {
        return new GlideOptions().skipMemoryCache(skipMemoryCache);
    }

    public static GlideOptions overrideOf(int width, int height) {
        return new GlideOptions().override(width, height);
    }

    public static GlideOptions overrideOf(int size) {
        return new GlideOptions().override(size);
    }

    public static GlideOptions signatureOf(Key arg0) {
        return new GlideOptions().signature(arg0);
    }

    public static GlideOptions fitCenterTransform() {
        if (fitCenterTransform0 == null) {
            fitCenterTransform0 = new GlideOptions().fitCenter().autoClone();
        }
        return fitCenterTransform0;
    }

    public static GlideOptions centerInsideTransform() {
        if (centerInsideTransform1 == null) {
            centerInsideTransform1 = new GlideOptions().centerInside().autoClone();
        }
        return centerInsideTransform1;
    }

    public static GlideOptions centerCropTransform() {
        if (centerCropTransform2 == null) {
            centerCropTransform2 = new GlideOptions().centerCrop().autoClone();
        }
        return centerCropTransform2;
    }

    public static GlideOptions circleCropTransform() {
        if (circleCropTransform3 == null) {
            circleCropTransform3 = new GlideOptions().circleCrop().autoClone();
        }
        return circleCropTransform3;
    }

    public static GlideOptions bitmapTransform(Transformation<Bitmap> arg0) {
        return new GlideOptions().transform((Transformation) arg0);
    }

    public static GlideOptions noTransformation() {
        if (noTransformation4 == null) {
            noTransformation4 = new GlideOptions().dontTransform().autoClone();
        }
        return noTransformation4;
    }

    public static <T> GlideOptions option(Option<T> arg0, T arg1) {
        return new GlideOptions().set((Option) arg0, (Object) arg1);
    }

    public static GlideOptions decodeTypeOf(Class<?> arg0) {
        return new GlideOptions().decode((Class) arg0);
    }

    public static GlideOptions formatOf(DecodeFormat arg0) {
        return new GlideOptions().format(arg0);
    }

    public static GlideOptions frameOf(long frameTimeMicros) {
        return new GlideOptions().frame(frameTimeMicros);
    }

    public static GlideOptions downsampleOf(DownsampleStrategy arg0) {
        return new GlideOptions().downsample(arg0);
    }

    public static GlideOptions encodeQualityOf(int quality) {
        return new GlideOptions().encodeQuality(quality);
    }

    public static GlideOptions encodeFormatOf(CompressFormat arg0) {
        return new GlideOptions().encodeFormat(arg0);
    }

    public static GlideOptions noAnimation() {
        if (noAnimation5 == null) {
            noAnimation5 = new GlideOptions().dontAnimate().autoClone();
        }
        return noAnimation5;
    }

    public GlideOptions sizeMultiplier(float sizeMultiplier) {
        return (GlideOptions) super.sizeMultiplier(sizeMultiplier);
    }

    public GlideOptions useUnlimitedSourceGeneratorsPool(boolean flag) {
        return (GlideOptions) super.useUnlimitedSourceGeneratorsPool(flag);
    }

    public GlideOptions onlyRetrieveFromCache(boolean flag) {
        return (GlideOptions) super.onlyRetrieveFromCache(flag);
    }

    public GlideOptions diskCacheStrategy(DiskCacheStrategy arg0) {
        return (GlideOptions) super.diskCacheStrategy(arg0);
    }

    public GlideOptions priority(Priority arg0) {
        return (GlideOptions) super.priority(arg0);
    }

    public GlideOptions placeholder(Drawable arg0) {
        return (GlideOptions) super.placeholder(arg0);
    }

    public GlideOptions placeholder(int resourceId) {
        return (GlideOptions) super.placeholder(resourceId);
    }

    public GlideOptions fallback(Drawable drawable) {
        return (GlideOptions) super.fallback(drawable);
    }

    public GlideOptions fallback(int resourceId) {
        return (GlideOptions) super.fallback(resourceId);
    }

    public GlideOptions error(Drawable arg0) {
        return (GlideOptions) super.error(arg0);
    }

    public GlideOptions error(int resourceId) {
        return (GlideOptions) super.error(resourceId);
    }

    public GlideOptions theme(Theme theme) {
        return (GlideOptions) super.theme(theme);
    }

    public GlideOptions skipMemoryCache(boolean skip) {
        return (GlideOptions) super.skipMemoryCache(skip);
    }

    public GlideOptions override(int width, int height) {
        return (GlideOptions) super.override(width, height);
    }

    public GlideOptions override(int size) {
        return (GlideOptions) super.override(size);
    }

    public GlideOptions signature(Key arg0) {
        return (GlideOptions) super.signature(arg0);
    }

    public GlideOptions clone() {
        return (GlideOptions) super.clone();
    }

    public <T> GlideOptions set(Option<T> arg0, T arg1) {
        return (GlideOptions) super.set(arg0, arg1);
    }

    public GlideOptions decode(Class<?> arg0) {
        return (GlideOptions) super.decode(arg0);
    }

    public GlideOptions encodeFormat(CompressFormat arg0) {
        return (GlideOptions) super.encodeFormat(arg0);
    }

    public GlideOptions encodeQuality(int quality) {
        return (GlideOptions) super.encodeQuality(quality);
    }

    public GlideOptions format(DecodeFormat arg0) {
        return (GlideOptions) super.format(arg0);
    }

    public GlideOptions frame(long frameTimeMicros) {
        return (GlideOptions) super.frame(frameTimeMicros);
    }

    public GlideOptions downsample(DownsampleStrategy arg0) {
        return (GlideOptions) super.downsample(arg0);
    }

    public GlideOptions optionalCenterCrop() {
        return (GlideOptions) super.optionalCenterCrop();
    }

    public GlideOptions centerCrop() {
        return (GlideOptions) super.centerCrop();
    }

    public GlideOptions optionalFitCenter() {
        return (GlideOptions) super.optionalFitCenter();
    }

    public GlideOptions fitCenter() {
        return (GlideOptions) super.fitCenter();
    }

    public GlideOptions optionalCenterInside() {
        return (GlideOptions) super.optionalCenterInside();
    }

    public GlideOptions centerInside() {
        return (GlideOptions) super.centerInside();
    }

    public GlideOptions optionalCircleCrop() {
        return (GlideOptions) super.optionalCircleCrop();
    }

    public GlideOptions circleCrop() {
        return (GlideOptions) super.circleCrop();
    }

    public GlideOptions transform(Transformation<Bitmap> arg0) {
        return (GlideOptions) super.transform(arg0);
    }

    public GlideOptions optionalTransform(Transformation<Bitmap> transformation) {
        return (GlideOptions) super.optionalTransform(transformation);
    }

    public <T> GlideOptions optionalTransform(Class<T> resourceClass, Transformation<T> transformation) {
        return (GlideOptions) super.optionalTransform((Class) resourceClass, (Transformation) transformation);
    }

    public <T> GlideOptions transform(Class<T> resourceClass, Transformation<T> transformation) {
        return (GlideOptions) super.transform((Class) resourceClass, (Transformation) transformation);
    }

    public GlideOptions dontTransform() {
        return (GlideOptions) super.dontTransform();
    }

    public GlideOptions dontAnimate() {
        return (GlideOptions) super.dontAnimate();
    }

    public GlideOptions apply(RequestOptions other) {
        return (GlideOptions) super.apply(other);
    }

    public GlideOptions lock() {
        return (GlideOptions) super.lock();
    }

    public GlideOptions autoClone() {
        return (GlideOptions) super.autoClone();
    }
}
