package com.bumptech.glide;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestCoordinator;
import com.bumptech.glide.request.RequestFutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.SingleRequest;
import com.bumptech.glide.request.ThumbnailRequestCoordinator;
import com.bumptech.glide.request.target.PreloadTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.ApplicationVersionSignature;
import com.bumptech.glide.signature.ObjectKey;
import com.bumptech.glide.util.Preconditions;
import com.bumptech.glide.util.Util;
import java.io.File;
import java.net.URL;
import java.util.UUID;

public class RequestBuilder<TranscodeType> implements Cloneable {
    private static final TransitionOptions<?, ?> DEFAULT_ANIMATION_OPTIONS = new GenericTransitionOptions();
    protected static final RequestOptions DOWNLOAD_ONLY_OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.DATA).priority(Priority.LOW).skipMemoryCache(true);
    private final GlideContext context;
    private final RequestOptions defaultRequestOptions;
    private final Glide glide;
    private boolean isModelSet;
    private boolean isThumbnailBuilt;
    private Object model;
    private RequestListener<TranscodeType> requestListener;
    private final RequestManager requestManager;
    protected RequestOptions requestOptions;
    private Float thumbSizeMultiplier;
    private RequestBuilder<TranscodeType> thumbnailBuilder;
    private final Class<TranscodeType> transcodeClass;
    private TransitionOptions<?, ? super TranscodeType> transitionOptions;

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ScaleType.values().length];

        static {
            $SwitchMap$com$bumptech$glide$Priority = new int[Priority.values().length];
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.LOW.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.NORMAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.HIGH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$bumptech$glide$Priority[Priority.IMMEDIATE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ScaleType.CENTER_CROP.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ScaleType.CENTER_INSIDE.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ScaleType.FIT_CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ScaleType.FIT_START.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ScaleType.FIT_END.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ScaleType.FIT_XY.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ScaleType.CENTER.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ScaleType.MATRIX.ordinal()] = 8;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    protected RequestBuilder(Glide glide, RequestManager requestManager, Class<TranscodeType> transcodeClass) {
        this.transitionOptions = DEFAULT_ANIMATION_OPTIONS;
        this.glide = glide;
        this.requestManager = requestManager;
        this.context = glide.getGlideContext();
        this.transcodeClass = transcodeClass;
        this.defaultRequestOptions = requestManager.getDefaultRequestOptions();
        this.requestOptions = this.defaultRequestOptions;
    }

    protected RequestBuilder(Class<TranscodeType> transcodeClass, RequestBuilder<?> other) {
        this(other.glide, other.requestManager, transcodeClass);
        this.model = other.model;
        this.isModelSet = other.isModelSet;
        this.requestOptions = other.requestOptions;
    }

    public RequestBuilder<TranscodeType> apply(RequestOptions requestOptions) {
        Preconditions.checkNotNull(requestOptions);
        this.requestOptions = getMutableOptions().apply(requestOptions);
        return this;
    }

    protected RequestOptions getMutableOptions() {
        return this.defaultRequestOptions == this.requestOptions ? this.requestOptions.clone() : this.requestOptions;
    }

    public RequestBuilder<TranscodeType> transition(TransitionOptions<?, ? super TranscodeType> transitionOptions) {
        this.transitionOptions = (TransitionOptions) Preconditions.checkNotNull(transitionOptions);
        return this;
    }

    public RequestBuilder<TranscodeType> listener(RequestListener<TranscodeType> requestListener) {
        this.requestListener = requestListener;
        return this;
    }

    public RequestBuilder<TranscodeType> thumbnail(RequestBuilder<TranscodeType> thumbnailRequest) {
        this.thumbnailBuilder = thumbnailRequest;
        return this;
    }

    public RequestBuilder<TranscodeType> thumbnail(float sizeMultiplier) {
        if (sizeMultiplier < 0.0f || sizeMultiplier > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.thumbSizeMultiplier = Float.valueOf(sizeMultiplier);
        return this;
    }

    public RequestBuilder<TranscodeType> load(Object model) {
        return loadGeneric(model);
    }

    private RequestBuilder<TranscodeType> loadGeneric(Object model) {
        this.model = model;
        this.isModelSet = true;
        return this;
    }

    public RequestBuilder<TranscodeType> load(String string) {
        return loadGeneric(string);
    }

    public RequestBuilder<TranscodeType> load(Uri uri) {
        return loadGeneric(uri);
    }

    public RequestBuilder<TranscodeType> load(File file) {
        return loadGeneric(file);
    }

    public RequestBuilder<TranscodeType> load(Integer resourceId) {
        return loadGeneric(resourceId).apply(RequestOptions.signatureOf(ApplicationVersionSignature.obtain(this.context)));
    }

    @Deprecated
    public RequestBuilder<TranscodeType> load(URL url) {
        return loadGeneric(url);
    }

    public RequestBuilder<TranscodeType> load(byte[] model) {
        return loadGeneric(model).apply(RequestOptions.signatureOf(new ObjectKey(UUID.randomUUID().toString())).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true));
    }

    public RequestBuilder<TranscodeType> clone() {
        try {
            RequestBuilder<TranscodeType> result = (RequestBuilder) super.clone();
            result.requestOptions = result.requestOptions.clone();
            result.transitionOptions = result.transitionOptions.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public <Y extends Target<TranscodeType>> Y into(Y target) {
        Util.assertMainThread();
        Preconditions.checkNotNull(target);
        if (this.isModelSet) {
            if (target.getRequest() != null) {
                this.requestManager.clear((Target) target);
            }
            this.requestOptions.lock();
            Request request = buildRequest(target);
            target.setRequest(request);
            this.requestManager.track(target, request);
            return target;
        }
        throw new IllegalArgumentException("You must call #load() before calling #into()");
    }

    public Target<TranscodeType> into(ImageView view) {
        Util.assertMainThread();
        Preconditions.checkNotNull(view);
        if (!(this.requestOptions.isTransformationSet() || !this.requestOptions.isTransformationAllowed() || view.getScaleType() == null)) {
            if (this.requestOptions.isLocked()) {
                this.requestOptions = this.requestOptions.clone();
            }
            switch (AnonymousClass2.$SwitchMap$android$widget$ImageView$ScaleType[view.getScaleType().ordinal()]) {
                case 1:
                    this.requestOptions.optionalCenterCrop();
                    break;
                case 2:
                    this.requestOptions.optionalCenterInside();
                    break;
                case 3:
                case 4:
                case 5:
                    this.requestOptions.optionalFitCenter();
                    break;
                case 6:
                    this.requestOptions.optionalCenterInside();
                    break;
            }
        }
        return into(this.context.buildImageViewTarget(view, this.transcodeClass));
    }

    @Deprecated
    public FutureTarget<TranscodeType> into(int width, int height) {
        return submit(width, height);
    }

    public FutureTarget<TranscodeType> submit() {
        return submit(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public FutureTarget<TranscodeType> submit(int width, int height) {
        final Target target = new RequestFutureTarget(this.context.getMainHandler(), width, height);
        if (Util.isOnBackgroundThread()) {
            this.context.getMainHandler().post(new Runnable() {
                public void run() {
                    if (!target.isCancelled()) {
                        RequestBuilder.this.into(target);
                    }
                }
            });
        } else {
            into(target);
        }
        return target;
    }

    public Target<TranscodeType> preload(int width, int height) {
        return into(PreloadTarget.obtain(this.requestManager, width, height));
    }

    public Target<TranscodeType> preload() {
        return preload(Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    @Deprecated
    public <Y extends Target<File>> Y downloadOnly(Y target) {
        return getDownloadOnlyRequest().into((Target) target);
    }

    @Deprecated
    public FutureTarget<File> downloadOnly(int width, int height) {
        return getDownloadOnlyRequest().submit(width, height);
    }

    protected RequestBuilder<File> getDownloadOnlyRequest() {
        return new RequestBuilder(File.class, this).apply(DOWNLOAD_ONLY_OPTIONS);
    }

    private Priority getThumbnailPriority(Priority current) {
        switch (current) {
            case LOW:
                return Priority.NORMAL;
            case NORMAL:
                return Priority.HIGH;
            case HIGH:
            case IMMEDIATE:
                return Priority.IMMEDIATE;
            default:
                throw new IllegalArgumentException("unknown priority: " + this.requestOptions.getPriority());
        }
    }

    private Request buildRequest(Target<TranscodeType> target) {
        return buildRequestRecursive(target, null, this.transitionOptions, this.requestOptions.getPriority(), this.requestOptions.getOverrideWidth(), this.requestOptions.getOverrideHeight());
    }

    private Request buildRequestRecursive(Target<TranscodeType> target, ThumbnailRequestCoordinator parentCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int overrideWidth, int overrideHeight) {
        ThumbnailRequestCoordinator coordinator;
        if (this.thumbnailBuilder != null) {
            if (this.isThumbnailBuilt) {
                throw new IllegalStateException("You cannot use a request as both the main request and a thumbnail, consider using clone() on the request(s) passed to thumbnail()");
            }
            Priority thumbPriority;
            TransitionOptions<?, ? super TranscodeType> thumbTransitionOptions = this.thumbnailBuilder.transitionOptions;
            if (DEFAULT_ANIMATION_OPTIONS.equals(thumbTransitionOptions)) {
                thumbTransitionOptions = transitionOptions;
            }
            if (this.thumbnailBuilder.requestOptions.isPrioritySet()) {
                thumbPriority = this.thumbnailBuilder.requestOptions.getPriority();
            } else {
                thumbPriority = getThumbnailPriority(priority);
            }
            int thumbOverrideWidth = this.thumbnailBuilder.requestOptions.getOverrideWidth();
            int thumbOverrideHeight = this.thumbnailBuilder.requestOptions.getOverrideHeight();
            if (Util.isValidDimensions(overrideWidth, overrideHeight) && !this.thumbnailBuilder.requestOptions.isValidOverride()) {
                thumbOverrideWidth = this.requestOptions.getOverrideWidth();
                thumbOverrideHeight = this.requestOptions.getOverrideHeight();
            }
            coordinator = new ThumbnailRequestCoordinator(parentCoordinator);
            Request fullRequest = obtainRequest(target, this.requestOptions, coordinator, transitionOptions, priority, overrideWidth, overrideHeight);
            this.isThumbnailBuilt = true;
            Request thumbRequest = this.thumbnailBuilder.buildRequestRecursive(target, coordinator, thumbTransitionOptions, thumbPriority, thumbOverrideWidth, thumbOverrideHeight);
            this.isThumbnailBuilt = false;
            coordinator.setRequests(fullRequest, thumbRequest);
            return coordinator;
        } else if (this.thumbSizeMultiplier != null) {
            coordinator = new ThumbnailRequestCoordinator(parentCoordinator);
            coordinator.setRequests(obtainRequest(target, this.requestOptions, coordinator, transitionOptions, priority, overrideWidth, overrideHeight), obtainRequest(target, this.requestOptions.clone().sizeMultiplier(this.thumbSizeMultiplier.floatValue()), coordinator, transitionOptions, getThumbnailPriority(priority), overrideWidth, overrideHeight));
            return coordinator;
        } else {
            return obtainRequest(target, this.requestOptions, parentCoordinator, transitionOptions, priority, overrideWidth, overrideHeight);
        }
    }

    private Request obtainRequest(Target<TranscodeType> target, RequestOptions requestOptions, RequestCoordinator requestCoordinator, TransitionOptions<?, ? super TranscodeType> transitionOptions, Priority priority, int overrideWidth, int overrideHeight) {
        requestOptions.lock();
        return SingleRequest.obtain(this.context, this.model, this.transcodeClass, requestOptions, overrideWidth, overrideHeight, priority, target, this.requestListener, requestCoordinator, this.context.getEngine(), transitionOptions.getTransitionFactory());
    }
}
