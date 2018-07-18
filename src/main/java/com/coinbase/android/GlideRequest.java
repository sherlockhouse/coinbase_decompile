package com.coinbase.android;

import android.content.res.Resources.Theme;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.Option;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import java.io.File;
import java.net.URL;

public class GlideRequest<TranscodeType> extends RequestBuilder<TranscodeType> {
    GlideRequest(Class<TranscodeType> transcodeClass, RequestBuilder<?> other) {
        super(transcodeClass, other);
    }

    GlideRequest(Glide glide, RequestManager requestManager, Class<TranscodeType> transcodeClass) {
        super(glide, requestManager, transcodeClass);
    }

    protected GlideRequest<File> getDownloadOnlyRequest() {
        return new GlideRequest(File.class, this).apply(DOWNLOAD_ONLY_OPTIONS);
    }

    public GlideRequest<TranscodeType> sizeMultiplier(float sizeMultiplier) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).sizeMultiplier(sizeMultiplier);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).sizeMultiplier(sizeMultiplier);
        }
        return this;
    }

    public GlideRequest<TranscodeType> useUnlimitedSourceGeneratorsPool(boolean flag) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).useUnlimitedSourceGeneratorsPool(flag);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).useUnlimitedSourceGeneratorsPool(flag);
        }
        return this;
    }

    public GlideRequest<TranscodeType> onlyRetrieveFromCache(boolean flag) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).onlyRetrieveFromCache(flag);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).onlyRetrieveFromCache(flag);
        }
        return this;
    }

    public GlideRequest<TranscodeType> diskCacheStrategy(DiskCacheStrategy arg0) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).diskCacheStrategy(arg0);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).diskCacheStrategy(arg0);
        }
        return this;
    }

    public GlideRequest<TranscodeType> priority(Priority arg0) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).priority(arg0);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).priority(arg0);
        }
        return this;
    }

    public GlideRequest<TranscodeType> placeholder(Drawable arg0) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).placeholder(arg0);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).placeholder(arg0);
        }
        return this;
    }

    public GlideRequest<TranscodeType> placeholder(int resourceId) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).placeholder(resourceId);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).placeholder(resourceId);
        }
        return this;
    }

    public GlideRequest<TranscodeType> fallback(Drawable drawable) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).fallback(drawable);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).fallback(drawable);
        }
        return this;
    }

    public GlideRequest<TranscodeType> fallback(int resourceId) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).fallback(resourceId);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).fallback(resourceId);
        }
        return this;
    }

    public GlideRequest<TranscodeType> error(Drawable arg0) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).error(arg0);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).error(arg0);
        }
        return this;
    }

    public GlideRequest<TranscodeType> error(int resourceId) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).error(resourceId);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).error(resourceId);
        }
        return this;
    }

    public GlideRequest<TranscodeType> theme(Theme theme) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).theme(theme);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).theme(theme);
        }
        return this;
    }

    public GlideRequest<TranscodeType> skipMemoryCache(boolean skip) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).skipMemoryCache(skip);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).skipMemoryCache(skip);
        }
        return this;
    }

    public GlideRequest<TranscodeType> override(int width, int height) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).override(width, height);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).override(width, height);
        }
        return this;
    }

    public GlideRequest<TranscodeType> override(int size) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).override(size);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).override(size);
        }
        return this;
    }

    public GlideRequest<TranscodeType> signature(Key arg0) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).signature(arg0);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).signature(arg0);
        }
        return this;
    }

    public <T> GlideRequest<TranscodeType> set(Option<T> arg0, T arg1) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).set((Option) arg0, (Object) arg1);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).set((Option) arg0, (Object) arg1);
        }
        return this;
    }

    public GlideRequest<TranscodeType> decode(Class<?> arg0) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).decode((Class) arg0);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).decode((Class) arg0);
        }
        return this;
    }

    public GlideRequest<TranscodeType> encodeFormat(CompressFormat arg0) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).encodeFormat(arg0);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).encodeFormat(arg0);
        }
        return this;
    }

    public GlideRequest<TranscodeType> encodeQuality(int quality) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).encodeQuality(quality);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).encodeQuality(quality);
        }
        return this;
    }

    public GlideRequest<TranscodeType> format(DecodeFormat arg0) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).format(arg0);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).format(arg0);
        }
        return this;
    }

    public GlideRequest<TranscodeType> frame(long frameTimeMicros) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).frame(frameTimeMicros);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).frame(frameTimeMicros);
        }
        return this;
    }

    public GlideRequest<TranscodeType> downsample(DownsampleStrategy arg0) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).downsample(arg0);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).downsample(arg0);
        }
        return this;
    }

    public GlideRequest<TranscodeType> optionalCenterCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).optionalCenterCrop();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalCenterCrop();
        }
        return this;
    }

    public GlideRequest<TranscodeType> centerCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).centerCrop();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).centerCrop();
        }
        return this;
    }

    public GlideRequest<TranscodeType> optionalFitCenter() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).optionalFitCenter();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalFitCenter();
        }
        return this;
    }

    public GlideRequest<TranscodeType> fitCenter() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).fitCenter();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).fitCenter();
        }
        return this;
    }

    public GlideRequest<TranscodeType> optionalCenterInside() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).optionalCenterInside();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalCenterInside();
        }
        return this;
    }

    public GlideRequest<TranscodeType> centerInside() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).centerInside();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).centerInside();
        }
        return this;
    }

    public GlideRequest<TranscodeType> optionalCircleCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).optionalCircleCrop();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalCircleCrop();
        }
        return this;
    }

    public GlideRequest<TranscodeType> circleCrop() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).circleCrop();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).circleCrop();
        }
        return this;
    }

    public GlideRequest<TranscodeType> transform(Transformation<Bitmap> arg0) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).transform((Transformation) arg0);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).transform((Transformation) arg0);
        }
        return this;
    }

    public GlideRequest<TranscodeType> optionalTransform(Transformation<Bitmap> transformation) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).optionalTransform((Transformation) transformation);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalTransform((Transformation) transformation);
        }
        return this;
    }

    public <T> GlideRequest<TranscodeType> optionalTransform(Class<T> resourceClass, Transformation<T> transformation) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).optionalTransform((Class) resourceClass, (Transformation) transformation);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).optionalTransform((Class) resourceClass, (Transformation) transformation);
        }
        return this;
    }

    public <T> GlideRequest<TranscodeType> transform(Class<T> resourceClass, Transformation<T> transformation) {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).transform((Class) resourceClass, (Transformation) transformation);
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).transform((Class) resourceClass, (Transformation) transformation);
        }
        return this;
    }

    public GlideRequest<TranscodeType> dontTransform() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).dontTransform();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).dontTransform();
        }
        return this;
    }

    public GlideRequest<TranscodeType> dontAnimate() {
        if (getMutableOptions() instanceof GlideOptions) {
            this.requestOptions = ((GlideOptions) getMutableOptions()).dontAnimate();
        } else {
            this.requestOptions = new GlideOptions().apply(this.requestOptions).dontAnimate();
        }
        return this;
    }

    public GlideRequest<TranscodeType> apply(RequestOptions arg0) {
        return (GlideRequest) super.apply(arg0);
    }

    public GlideRequest<TranscodeType> transition(TransitionOptions<?, ? super TranscodeType> arg0) {
        return (GlideRequest) super.transition(arg0);
    }

    public GlideRequest<TranscodeType> listener(RequestListener<TranscodeType> arg0) {
        return (GlideRequest) super.listener(arg0);
    }

    public GlideRequest<TranscodeType> thumbnail(RequestBuilder<TranscodeType> arg0) {
        return (GlideRequest) super.thumbnail((RequestBuilder) arg0);
    }

    public GlideRequest<TranscodeType> thumbnail(float sizeMultiplier) {
        return (GlideRequest) super.thumbnail(sizeMultiplier);
    }

    public GlideRequest<TranscodeType> load(Object arg0) {
        return (GlideRequest) super.load(arg0);
    }

    public GlideRequest<TranscodeType> load(String arg0) {
        return (GlideRequest) super.load(arg0);
    }

    public GlideRequest<TranscodeType> load(Uri arg0) {
        return (GlideRequest) super.load(arg0);
    }

    public GlideRequest<TranscodeType> load(File arg0) {
        return (GlideRequest) super.load(arg0);
    }

    public GlideRequest<TranscodeType> load(Integer arg0) {
        return (GlideRequest) super.load(arg0);
    }

    public GlideRequest<TranscodeType> load(URL arg0) {
        return (GlideRequest) super.load(arg0);
    }

    public GlideRequest<TranscodeType> load(byte[] arg0) {
        return (GlideRequest) super.load(arg0);
    }

    public GlideRequest<TranscodeType> clone() {
        return (GlideRequest) super.clone();
    }
}
