package com.bumptech.glide.request;

import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapDrawableTransformation;
import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.load.resource.bitmap.Downsampler;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.VideoBitmapDecoder;
import com.bumptech.glide.load.resource.gif.ByteBufferGifDecoder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawableTransformation;
import com.bumptech.glide.load.resource.gif.StreamGifDecoder;
import com.bumptech.glide.signature.EmptySignature;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.util.HashMap;
import java.util.Map;

public class RequestOptions implements Cloneable {
    private static final int DISK_CACHE_STRATEGY = 4;
    private static final int ERROR_ID = 32;
    private static final int ERROR_PLACEHOLDER = 16;
    private static final int FALLBACK = 8192;
    private static final int FALLBACK_ID = 16384;
    private static final int IS_CACHEABLE = 256;
    private static final int ONLY_RETRIEVE_FROM_CACHE = 524288;
    private static final int OVERRIDE = 512;
    private static final int PLACEHOLDER = 64;
    private static final int PLACEHOLDER_ID = 128;
    private static final int PRIORITY = 8;
    private static final int RESOURCE_CLASS = 4096;
    private static final int SIGNATURE = 1024;
    private static final int SIZE_MULTIPLIER = 2;
    private static final int THEME = 32768;
    private static final int TRANSFORMATION = 2048;
    private static final int TRANSFORMATION_ALLOWED = 65536;
    private static final int TRANSFORMATION_REQUIRED = 131072;
    private static final int UNSET = -1;
    private static final int USE_UNLIMITED_SOURCE_GENERATORS_POOL = 262144;
    private static RequestOptions centerCropOptions;
    private static RequestOptions centerInsideOptions;
    private static RequestOptions circleCropOptions;
    private static RequestOptions fitCenterOptions;
    private static RequestOptions noAnimationOptions;
    private static RequestOptions noTransformOptions;
    private static RequestOptions skipMemoryCacheFalseOptions;
    private static RequestOptions skipMemoryCacheTrueOptions;
    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;
    private int errorId;
    private Drawable errorPlaceholder;
    private Drawable fallbackDrawable;
    private int fallbackId;
    private int fields;
    private boolean isAutoCloneEnabled;
    private boolean isCacheable = true;
    private boolean isLocked;
    private boolean isTransformationAllowed = true;
    private boolean isTransformationRequired;
    private boolean onlyRetrieveFromCache;
    private Options options = new Options();
    private int overrideHeight = -1;
    private int overrideWidth = -1;
    private Drawable placeholderDrawable;
    private int placeholderId;
    private Priority priority = Priority.NORMAL;
    private Class<?> resourceClass = Object.class;
    private Key signature = EmptySignature.obtain();
    private float sizeMultiplier = 1.0f;
    private Theme theme;
    private Map<Class<?>, Transformation<?>> transformations = new HashMap();
    private boolean useUnlimitedSourceGeneratorsPool;

    public static RequestOptions sizeMultiplierOf(float sizeMultiplier) {
        return new RequestOptions().sizeMultiplier(sizeMultiplier);
    }

    public static RequestOptions diskCacheStrategyOf(DiskCacheStrategy diskCacheStrategy) {
        return new RequestOptions().diskCacheStrategy(diskCacheStrategy);
    }

    public static RequestOptions priorityOf(Priority priority) {
        return new RequestOptions().priority(priority);
    }

    public static RequestOptions placeholderOf(Drawable placeholder) {
        return new RequestOptions().placeholder(placeholder);
    }

    public static RequestOptions placeholderOf(int placeholderId) {
        return new RequestOptions().placeholder(placeholderId);
    }

    public static RequestOptions errorOf(Drawable errorDrawable) {
        return new RequestOptions().error(errorDrawable);
    }

    public static RequestOptions errorOf(int errorId) {
        return new RequestOptions().error(errorId);
    }

    public static RequestOptions skipMemoryCacheOf(boolean skipMemoryCache) {
        if (skipMemoryCache) {
            if (skipMemoryCacheTrueOptions == null) {
                skipMemoryCacheTrueOptions = new RequestOptions().skipMemoryCache(true).autoClone();
            }
            return skipMemoryCacheTrueOptions;
        }
        if (skipMemoryCacheFalseOptions == null) {
            skipMemoryCacheFalseOptions = new RequestOptions().skipMemoryCache(false).autoClone();
        }
        return skipMemoryCacheFalseOptions;
    }

    public static RequestOptions overrideOf(int width, int height) {
        return new RequestOptions().override(width, height);
    }

    public static RequestOptions overrideOf(int size) {
        return overrideOf(size, size);
    }

    public static RequestOptions signatureOf(Key signature) {
        return new RequestOptions().signature(signature);
    }

    public static RequestOptions fitCenterTransform() {
        if (fitCenterOptions == null) {
            fitCenterOptions = new RequestOptions().fitCenter().autoClone();
        }
        return fitCenterOptions;
    }

    public static RequestOptions centerInsideTransform() {
        if (centerInsideOptions == null) {
            centerInsideOptions = new RequestOptions().centerInside().autoClone();
        }
        return centerInsideOptions;
    }

    public static RequestOptions centerCropTransform() {
        if (centerCropOptions == null) {
            centerCropOptions = new RequestOptions().centerCrop().autoClone();
        }
        return centerCropOptions;
    }

    public static RequestOptions circleCropTransform() {
        if (circleCropOptions == null) {
            circleCropOptions = new RequestOptions().circleCrop().autoClone();
        }
        return circleCropOptions;
    }

    public static RequestOptions bitmapTransform(Transformation<Bitmap> transformation) {
        return new RequestOptions().transform(transformation);
    }

    public static RequestOptions noTransformation() {
        if (noTransformOptions == null) {
            noTransformOptions = new RequestOptions().dontTransform().autoClone();
        }
        return noTransformOptions;
    }

    public static <T> RequestOptions option(Option<T> option, T value) {
        return new RequestOptions().set(option, value);
    }

    public static RequestOptions decodeTypeOf(Class<?> resourceClass) {
        return new RequestOptions().decode(resourceClass);
    }

    public static RequestOptions formatOf(DecodeFormat format) {
        return new RequestOptions().format(format);
    }

    public static RequestOptions frameOf(long frameTimeMicros) {
        return new RequestOptions().frame(frameTimeMicros);
    }

    public static RequestOptions downsampleOf(DownsampleStrategy strategy) {
        return new RequestOptions().downsample(strategy);
    }

    public static RequestOptions encodeQualityOf(int quality) {
        return new RequestOptions().encodeQuality(quality);
    }

    public static RequestOptions encodeFormatOf(CompressFormat format) {
        return new RequestOptions().encodeFormat(format);
    }

    public static RequestOptions noAnimation() {
        if (noAnimationOptions == null) {
            noAnimationOptions = new RequestOptions().dontAnimate().autoClone();
        }
        return noAnimationOptions;
    }

    private static boolean isSet(int fields, int flag) {
        return (fields & flag) != 0;
    }

    public RequestOptions sizeMultiplier(float sizeMultiplier) {
        if (this.isAutoCloneEnabled) {
            return clone().sizeMultiplier(sizeMultiplier);
        }
        if (sizeMultiplier < 0.0f || sizeMultiplier > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.sizeMultiplier = sizeMultiplier;
        this.fields |= 2;
        return selfOrThrowIfLocked();
    }

    public RequestOptions useUnlimitedSourceGeneratorsPool(boolean flag) {
        if (this.isAutoCloneEnabled) {
            return clone().useUnlimitedSourceGeneratorsPool(flag);
        }
        this.useUnlimitedSourceGeneratorsPool = flag;
        this.fields |= USE_UNLIMITED_SOURCE_GENERATORS_POOL;
        return selfOrThrowIfLocked();
    }

    public RequestOptions onlyRetrieveFromCache(boolean flag) {
        if (this.isAutoCloneEnabled) {
            return clone().onlyRetrieveFromCache(flag);
        }
        this.onlyRetrieveFromCache = flag;
        this.fields |= ONLY_RETRIEVE_FROM_CACHE;
        return selfOrThrowIfLocked();
    }

    public RequestOptions diskCacheStrategy(DiskCacheStrategy strategy) {
        if (this.isAutoCloneEnabled) {
            return clone().diskCacheStrategy(strategy);
        }
        this.diskCacheStrategy = (DiskCacheStrategy) Preconditions.checkNotNull(strategy);
        this.fields |= 4;
        return selfOrThrowIfLocked();
    }

    public RequestOptions priority(Priority priority) {
        if (this.isAutoCloneEnabled) {
            return clone().priority(priority);
        }
        this.priority = (Priority) Preconditions.checkNotNull(priority);
        this.fields |= 8;
        return selfOrThrowIfLocked();
    }

    public RequestOptions placeholder(Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().placeholder(drawable);
        }
        this.placeholderDrawable = drawable;
        this.fields |= 64;
        return selfOrThrowIfLocked();
    }

    public RequestOptions placeholder(int resourceId) {
        if (this.isAutoCloneEnabled) {
            return clone().placeholder(resourceId);
        }
        this.placeholderId = resourceId;
        this.fields |= PLACEHOLDER_ID;
        return selfOrThrowIfLocked();
    }

    public RequestOptions fallback(Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().fallback(drawable);
        }
        this.fallbackDrawable = drawable;
        this.fields |= FALLBACK;
        return selfOrThrowIfLocked();
    }

    public RequestOptions fallback(int resourceId) {
        if (this.isAutoCloneEnabled) {
            return clone().fallback(resourceId);
        }
        this.fallbackId = resourceId;
        this.fields |= FALLBACK_ID;
        return selfOrThrowIfLocked();
    }

    public RequestOptions error(Drawable drawable) {
        if (this.isAutoCloneEnabled) {
            return clone().error(drawable);
        }
        this.errorPlaceholder = drawable;
        this.fields |= 16;
        return selfOrThrowIfLocked();
    }

    public RequestOptions error(int resourceId) {
        if (this.isAutoCloneEnabled) {
            return clone().error(resourceId);
        }
        this.errorId = resourceId;
        this.fields |= 32;
        return selfOrThrowIfLocked();
    }

    public RequestOptions theme(Theme theme) {
        if (this.isAutoCloneEnabled) {
            return clone().theme(theme);
        }
        this.theme = theme;
        this.fields |= THEME;
        return selfOrThrowIfLocked();
    }

    public RequestOptions skipMemoryCache(boolean skip) {
        boolean z = true;
        if (this.isAutoCloneEnabled) {
            return clone().skipMemoryCache(true);
        }
        if (skip) {
            z = false;
        }
        this.isCacheable = z;
        this.fields |= IS_CACHEABLE;
        return selfOrThrowIfLocked();
    }

    public RequestOptions override(int width, int height) {
        if (this.isAutoCloneEnabled) {
            return clone().override(width, height);
        }
        this.overrideWidth = width;
        this.overrideHeight = height;
        this.fields |= OVERRIDE;
        return selfOrThrowIfLocked();
    }

    public RequestOptions override(int size) {
        return override(size, size);
    }

    public RequestOptions signature(Key signature) {
        if (this.isAutoCloneEnabled) {
            return clone().signature(signature);
        }
        this.signature = (Key) Preconditions.checkNotNull(signature);
        this.fields |= SIGNATURE;
        return selfOrThrowIfLocked();
    }

    public RequestOptions clone() {
        try {
            RequestOptions result = (RequestOptions) super.clone();
            result.options = new Options();
            result.options.putAll(this.options);
            result.transformations = new HashMap();
            result.transformations.putAll(this.transformations);
            result.isLocked = false;
            result.isAutoCloneEnabled = false;
            return result;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> RequestOptions set(Option<T> option, T value) {
        if (this.isAutoCloneEnabled) {
            return clone().set(option, value);
        }
        Preconditions.checkNotNull(option);
        Preconditions.checkNotNull(value);
        this.options.set(option, value);
        return selfOrThrowIfLocked();
    }

    public RequestOptions decode(Class<?> resourceClass) {
        if (this.isAutoCloneEnabled) {
            return clone().decode(resourceClass);
        }
        this.resourceClass = (Class) Preconditions.checkNotNull(resourceClass);
        this.fields |= RESOURCE_CLASS;
        return selfOrThrowIfLocked();
    }

    public final boolean isTransformationAllowed() {
        return this.isTransformationAllowed;
    }

    public final boolean isTransformationSet() {
        return isSet(TRANSFORMATION);
    }

    public final boolean isLocked() {
        return this.isLocked;
    }

    public RequestOptions encodeFormat(CompressFormat format) {
        return set(BitmapEncoder.COMPRESSION_FORMAT, Preconditions.checkNotNull(format));
    }

    public RequestOptions encodeQuality(int quality) {
        return set(BitmapEncoder.COMPRESSION_QUALITY, Integer.valueOf(quality));
    }

    public RequestOptions format(DecodeFormat format) {
        return set(Downsampler.DECODE_FORMAT, Preconditions.checkNotNull(format));
    }

    public RequestOptions frame(long frameTimeMicros) {
        return set(VideoBitmapDecoder.TARGET_FRAME, Long.valueOf(frameTimeMicros));
    }

    public RequestOptions downsample(DownsampleStrategy strategy) {
        return set(Downsampler.DOWNSAMPLE_STRATEGY, Preconditions.checkNotNull(strategy));
    }

    public RequestOptions optionalCenterCrop() {
        return optionalTransform(DownsampleStrategy.CENTER_OUTSIDE, new CenterCrop());
    }

    public RequestOptions centerCrop() {
        return transform(DownsampleStrategy.CENTER_OUTSIDE, new CenterCrop());
    }

    public RequestOptions optionalFitCenter() {
        return optionalTransform(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    public RequestOptions fitCenter() {
        return transform(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    public RequestOptions optionalCenterInside() {
        return optionalTransform(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    public RequestOptions centerInside() {
        return transform(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    public RequestOptions optionalCircleCrop() {
        return optionalTransform(DownsampleStrategy.CENTER_OUTSIDE, new CircleCrop());
    }

    public RequestOptions circleCrop() {
        return transform(DownsampleStrategy.CENTER_INSIDE, new CircleCrop());
    }

    final RequestOptions optionalTransform(DownsampleStrategy downsampleStrategy, Transformation<Bitmap> transformation) {
        if (this.isAutoCloneEnabled) {
            return clone().optionalTransform(downsampleStrategy, (Transformation) transformation);
        }
        downsample(downsampleStrategy);
        return optionalTransform(transformation);
    }

    final RequestOptions transform(DownsampleStrategy downsampleStrategy, Transformation<Bitmap> transformation) {
        if (this.isAutoCloneEnabled) {
            return clone().transform(downsampleStrategy, (Transformation) transformation);
        }
        downsample(downsampleStrategy);
        return transform(transformation);
    }

    public RequestOptions transform(Transformation<Bitmap> transformation) {
        if (this.isAutoCloneEnabled) {
            return clone().transform(transformation);
        }
        optionalTransform(transformation);
        this.isTransformationRequired = true;
        this.fields |= TRANSFORMATION_REQUIRED;
        return selfOrThrowIfLocked();
    }

    public RequestOptions optionalTransform(Transformation<Bitmap> transformation) {
        if (this.isAutoCloneEnabled) {
            return clone().optionalTransform(transformation);
        }
        optionalTransform(Bitmap.class, (Transformation) transformation);
        optionalTransform(BitmapDrawable.class, new BitmapDrawableTransformation(transformation));
        optionalTransform(GifDrawable.class, new GifDrawableTransformation(transformation));
        return selfOrThrowIfLocked();
    }

    public <T> RequestOptions optionalTransform(Class<T> resourceClass, Transformation<T> transformation) {
        if (this.isAutoCloneEnabled) {
            return clone().optionalTransform((Class) resourceClass, (Transformation) transformation);
        }
        Preconditions.checkNotNull(resourceClass);
        Preconditions.checkNotNull(transformation);
        this.transformations.put(resourceClass, transformation);
        this.fields |= TRANSFORMATION;
        this.isTransformationAllowed = true;
        this.fields |= TRANSFORMATION_ALLOWED;
        return selfOrThrowIfLocked();
    }

    public <T> RequestOptions transform(Class<T> resourceClass, Transformation<T> transformation) {
        if (this.isAutoCloneEnabled) {
            return clone().transform((Class) resourceClass, (Transformation) transformation);
        }
        optionalTransform((Class) resourceClass, (Transformation) transformation);
        this.isTransformationRequired = true;
        this.fields |= TRANSFORMATION_REQUIRED;
        return selfOrThrowIfLocked();
    }

    public RequestOptions dontTransform() {
        if (this.isAutoCloneEnabled) {
            return clone().dontTransform();
        }
        this.transformations.clear();
        this.fields &= -2049;
        this.isTransformationRequired = false;
        this.fields &= -131073;
        this.isTransformationAllowed = false;
        this.fields |= TRANSFORMATION_ALLOWED;
        return selfOrThrowIfLocked();
    }

    public RequestOptions dontAnimate() {
        if (this.isAutoCloneEnabled) {
            return clone().dontAnimate();
        }
        set(ByteBufferGifDecoder.DISABLE_ANIMATION, Boolean.valueOf(true));
        set(StreamGifDecoder.DISABLE_ANIMATION, Boolean.valueOf(true));
        return selfOrThrowIfLocked();
    }

    public RequestOptions apply(RequestOptions other) {
        if (this.isAutoCloneEnabled) {
            return clone().apply(other);
        }
        if (isSet(other.fields, 2)) {
            this.sizeMultiplier = other.sizeMultiplier;
        }
        if (isSet(other.fields, USE_UNLIMITED_SOURCE_GENERATORS_POOL)) {
            this.useUnlimitedSourceGeneratorsPool = other.useUnlimitedSourceGeneratorsPool;
        }
        if (isSet(other.fields, 4)) {
            this.diskCacheStrategy = other.diskCacheStrategy;
        }
        if (isSet(other.fields, 8)) {
            this.priority = other.priority;
        }
        if (isSet(other.fields, 16)) {
            this.errorPlaceholder = other.errorPlaceholder;
        }
        if (isSet(other.fields, 32)) {
            this.errorId = other.errorId;
        }
        if (isSet(other.fields, 64)) {
            this.placeholderDrawable = other.placeholderDrawable;
        }
        if (isSet(other.fields, PLACEHOLDER_ID)) {
            this.placeholderId = other.placeholderId;
        }
        if (isSet(other.fields, IS_CACHEABLE)) {
            this.isCacheable = other.isCacheable;
        }
        if (isSet(other.fields, OVERRIDE)) {
            this.overrideWidth = other.overrideWidth;
            this.overrideHeight = other.overrideHeight;
        }
        if (isSet(other.fields, SIGNATURE)) {
            this.signature = other.signature;
        }
        if (isSet(other.fields, RESOURCE_CLASS)) {
            this.resourceClass = other.resourceClass;
        }
        if (isSet(other.fields, FALLBACK)) {
            this.fallbackDrawable = other.fallbackDrawable;
        }
        if (isSet(other.fields, FALLBACK_ID)) {
            this.fallbackId = other.fallbackId;
        }
        if (isSet(other.fields, THEME)) {
            this.theme = other.theme;
        }
        if (isSet(other.fields, TRANSFORMATION_ALLOWED)) {
            this.isTransformationAllowed = other.isTransformationAllowed;
        }
        if (isSet(other.fields, TRANSFORMATION_REQUIRED)) {
            this.isTransformationRequired = other.isTransformationRequired;
        }
        if (isSet(other.fields, TRANSFORMATION)) {
            this.transformations.putAll(other.transformations);
        }
        if (isSet(other.fields, ONLY_RETRIEVE_FROM_CACHE)) {
            this.onlyRetrieveFromCache = other.onlyRetrieveFromCache;
        }
        if (!this.isTransformationAllowed) {
            this.transformations.clear();
            this.fields &= -2049;
            this.isTransformationRequired = false;
            this.fields &= -131073;
        }
        this.fields |= other.fields;
        this.options.putAll(other.options);
        return selfOrThrowIfLocked();
    }

    public RequestOptions lock() {
        this.isLocked = true;
        return this;
    }

    public RequestOptions autoClone() {
        if (!this.isLocked || this.isAutoCloneEnabled) {
            this.isAutoCloneEnabled = true;
            return lock();
        }
        throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
    }

    private RequestOptions selfOrThrowIfLocked() {
        if (!this.isLocked) {
            return this;
        }
        throw new IllegalStateException("You cannot modify locked RequestOptions, consider clone()");
    }

    public final Map<Class<?>, Transformation<?>> getTransformations() {
        return this.transformations;
    }

    public final boolean isTransformationRequired() {
        return this.isTransformationRequired;
    }

    public final Options getOptions() {
        return this.options;
    }

    public final Class<?> getResourceClass() {
        return this.resourceClass;
    }

    public final DiskCacheStrategy getDiskCacheStrategy() {
        return this.diskCacheStrategy;
    }

    public final Drawable getErrorPlaceholder() {
        return this.errorPlaceholder;
    }

    public final int getErrorId() {
        return this.errorId;
    }

    public final int getPlaceholderId() {
        return this.placeholderId;
    }

    public final Drawable getPlaceholderDrawable() {
        return this.placeholderDrawable;
    }

    public final int getFallbackId() {
        return this.fallbackId;
    }

    public final Drawable getFallbackDrawable() {
        return this.fallbackDrawable;
    }

    public final Theme getTheme() {
        return this.theme;
    }

    public final boolean isMemoryCacheable() {
        return this.isCacheable;
    }

    public final Key getSignature() {
        return this.signature;
    }

    public final boolean isPrioritySet() {
        return isSet(8);
    }

    public final Priority getPriority() {
        return this.priority;
    }

    public final int getOverrideWidth() {
        return this.overrideWidth;
    }

    public final boolean isValidOverride() {
        return Util.isValidDimensions(this.overrideWidth, this.overrideHeight);
    }

    public final int getOverrideHeight() {
        return this.overrideHeight;
    }

    public final float getSizeMultiplier() {
        return this.sizeMultiplier;
    }

    private boolean isSet(int flag) {
        return isSet(this.fields, flag);
    }

    public final boolean getUseUnlimitedSourceGeneratorsPool() {
        return this.useUnlimitedSourceGeneratorsPool;
    }

    public final boolean getOnlyRetrieveFromCache() {
        return this.onlyRetrieveFromCache;
    }
}
