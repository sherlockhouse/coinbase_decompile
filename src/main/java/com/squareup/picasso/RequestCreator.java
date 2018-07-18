package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Request.Builder;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestCreator {
    private static final AtomicInteger nextId = new AtomicInteger();
    private final Builder data;
    private boolean deferred;
    private Drawable errorDrawable;
    private int errorResId;
    private int memoryPolicy;
    private int networkPolicy;
    private boolean noFade;
    private final Picasso picasso;
    private Drawable placeholderDrawable;
    private int placeholderResId;
    private boolean setPlaceholder;
    private Object tag;

    RequestCreator(Picasso picasso, Uri uri, int resourceId) {
        this.setPlaceholder = true;
        if (picasso.shutdown) {
            throw new IllegalStateException("Picasso instance already shut down. Cannot submit new requests.");
        }
        this.picasso = picasso;
        this.data = new Builder(uri, resourceId, picasso.defaultBitmapConfig);
    }

    RequestCreator() {
        this.setPlaceholder = true;
        this.picasso = null;
        this.data = new Builder(null, 0, null);
    }

    public RequestCreator placeholder(int placeholderResId) {
        if (!this.setPlaceholder) {
            throw new IllegalStateException("Already explicitly declared as no placeholder.");
        } else if (placeholderResId == 0) {
            throw new IllegalArgumentException("Placeholder image resource invalid.");
        } else if (this.placeholderDrawable != null) {
            throw new IllegalStateException("Placeholder image already set.");
        } else {
            this.placeholderResId = placeholderResId;
            return this;
        }
    }

    public RequestCreator placeholder(Drawable placeholderDrawable) {
        if (!this.setPlaceholder) {
            throw new IllegalStateException("Already explicitly declared as no placeholder.");
        } else if (this.placeholderResId != 0) {
            throw new IllegalStateException("Placeholder image already set.");
        } else {
            this.placeholderDrawable = placeholderDrawable;
            return this;
        }
    }

    public RequestCreator error(int errorResId) {
        if (errorResId == 0) {
            throw new IllegalArgumentException("Error image resource invalid.");
        } else if (this.errorDrawable != null) {
            throw new IllegalStateException("Error image already set.");
        } else {
            this.errorResId = errorResId;
            return this;
        }
    }

    RequestCreator unfit() {
        this.deferred = false;
        return this;
    }

    public RequestCreator resize(int targetWidth, int targetHeight) {
        this.data.resize(targetWidth, targetHeight);
        return this;
    }

    public RequestCreator transform(Transformation transformation) {
        this.data.transform(transformation);
        return this;
    }

    public void into(ImageView target) {
        into(target, null);
    }

    public void into(ImageView target, Callback callback) {
        long started = System.nanoTime();
        Utils.checkMain();
        if (target == null) {
            throw new IllegalArgumentException("Target must not be null.");
        } else if (this.data.hasImage()) {
            if (this.deferred) {
                if (this.data.hasSize()) {
                    throw new IllegalStateException("Fit cannot be used with resize.");
                }
                int width = target.getWidth();
                int height = target.getHeight();
                if (width == 0 || height == 0) {
                    if (this.setPlaceholder) {
                        PicassoDrawable.setPlaceholder(target, getPlaceholderDrawable());
                    }
                    this.picasso.defer(target, new DeferredRequestCreator(this, target, callback));
                    return;
                }
                this.data.resize(width, height);
            }
            Request request = createRequest(started);
            String requestKey = Utils.createKey(request);
            if (MemoryPolicy.shouldReadFromMemoryCache(this.memoryPolicy)) {
                Bitmap bitmap = this.picasso.quickMemoryCacheCheck(requestKey);
                if (bitmap != null) {
                    this.picasso.cancelRequest(target);
                    PicassoDrawable.setBitmap(target, this.picasso.context, bitmap, LoadedFrom.MEMORY, this.noFade, this.picasso.indicatorsEnabled);
                    if (this.picasso.loggingEnabled) {
                        Utils.log("Main", "completed", request.plainId(), "from " + LoadedFrom.MEMORY);
                    }
                    if (callback != null) {
                        callback.onSuccess();
                        return;
                    }
                    return;
                }
            }
            if (this.setPlaceholder) {
                PicassoDrawable.setPlaceholder(target, getPlaceholderDrawable());
            }
            this.picasso.enqueueAndSubmit(new ImageViewAction(this.picasso, target, request, this.memoryPolicy, this.networkPolicy, this.errorResId, this.errorDrawable, requestKey, this.tag, callback, this.noFade));
        } else {
            this.picasso.cancelRequest(target);
            if (this.setPlaceholder) {
                PicassoDrawable.setPlaceholder(target, getPlaceholderDrawable());
            }
        }
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderResId != 0) {
            return this.picasso.context.getResources().getDrawable(this.placeholderResId);
        }
        return this.placeholderDrawable;
    }

    private Request createRequest(long started) {
        int id = nextId.getAndIncrement();
        Request request = this.data.build();
        request.id = id;
        request.started = started;
        boolean loggingEnabled = this.picasso.loggingEnabled;
        if (loggingEnabled) {
            Utils.log("Main", "created", request.plainId(), request.toString());
        }
        Request transformed = this.picasso.transformRequest(request);
        if (transformed != request) {
            transformed.id = id;
            transformed.started = started;
            if (loggingEnabled) {
                Utils.log("Main", "changed", transformed.logId(), "into " + transformed);
            }
        }
        return transformed;
    }
}
