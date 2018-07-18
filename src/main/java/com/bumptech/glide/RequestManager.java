package com.bumptech.glide;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.manager.ConnectivityMonitor;
import com.bumptech.glide.manager.ConnectivityMonitor.ConnectivityListener;
import com.bumptech.glide.manager.ConnectivityMonitorFactory;
import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.LifecycleListener;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.bumptech.glide.manager.RequestTracker;
import com.bumptech.glide.manager.TargetTracker;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
import java.io.File;

public class RequestManager implements LifecycleListener {
    private static final RequestOptions DECODE_TYPE_BITMAP = RequestOptions.decodeTypeOf(Bitmap.class).lock();
    private static final RequestOptions DECODE_TYPE_GIF = RequestOptions.decodeTypeOf(GifDrawable.class).lock();
    private static final RequestOptions DOWNLOAD_ONLY_OPTIONS = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.DATA).priority(Priority.LOW).skipMemoryCache(true);
    private final Runnable addSelfToLifecycle;
    private final ConnectivityMonitor connectivityMonitor;
    protected final Glide glide;
    final Lifecycle lifecycle;
    private final Handler mainHandler;
    private RequestOptions requestOptions;
    private final RequestTracker requestTracker;
    private final TargetTracker targetTracker;
    private final RequestManagerTreeNode treeNode;

    private static class ClearTarget extends ViewTarget<View, Object> {
        public ClearTarget(View view) {
            super(view);
        }

        public void onResourceReady(Object resource, Transition<? super Object> transition) {
        }
    }

    private static class RequestManagerConnectivityListener implements ConnectivityListener {
        private final RequestTracker requestTracker;

        public RequestManagerConnectivityListener(RequestTracker requestTracker) {
            this.requestTracker = requestTracker;
        }

        public void onConnectivityChanged(boolean isConnected) {
            if (isConnected) {
                this.requestTracker.restartRequests();
            }
        }
    }

    public RequestManager(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode treeNode) {
        this(glide, lifecycle, treeNode, new RequestTracker(), glide.getConnectivityMonitorFactory());
    }

    RequestManager(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode treeNode, RequestTracker requestTracker, ConnectivityMonitorFactory factory) {
        this.targetTracker = new TargetTracker();
        this.addSelfToLifecycle = new Runnable() {
            public void run() {
                RequestManager.this.lifecycle.addListener(RequestManager.this);
            }
        };
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.glide = glide;
        this.lifecycle = lifecycle;
        this.treeNode = treeNode;
        this.requestTracker = requestTracker;
        this.connectivityMonitor = factory.build(glide.getGlideContext().getBaseContext(), new RequestManagerConnectivityListener(requestTracker));
        if (Util.isOnBackgroundThread()) {
            this.mainHandler.post(this.addSelfToLifecycle);
        } else {
            lifecycle.addListener(this);
        }
        lifecycle.addListener(this.connectivityMonitor);
        setRequestOptions(glide.getGlideContext().getDefaultRequestOptions());
        glide.registerRequestManager(this);
    }

    protected void setRequestOptions(RequestOptions toSet) {
        this.requestOptions = toSet.clone().autoClone();
    }

    private void updateRequestOptions(RequestOptions toUpdate) {
        this.requestOptions.apply(toUpdate);
    }

    public RequestManager applyDefaultRequestOptions(RequestOptions requestOptions) {
        updateRequestOptions(requestOptions);
        return this;
    }

    public RequestManager setDefaultRequestOptions(RequestOptions requestOptions) {
        setRequestOptions(requestOptions);
        return this;
    }

    public void onTrimMemory(int level) {
        this.glide.getGlideContext().onTrimMemory(level);
    }

    public void onLowMemory() {
        this.glide.getGlideContext().onLowMemory();
    }

    public boolean isPaused() {
        Util.assertMainThread();
        return this.requestTracker.isPaused();
    }

    public void pauseRequests() {
        Util.assertMainThread();
        this.requestTracker.pauseRequests();
    }

    public void pauseRequestsRecursive() {
        Util.assertMainThread();
        pauseRequests();
        for (RequestManager requestManager : this.treeNode.getDescendants()) {
            requestManager.pauseRequests();
        }
    }

    public void resumeRequests() {
        Util.assertMainThread();
        this.requestTracker.resumeRequests();
    }

    public void resumeRequestsRecursive() {
        Util.assertMainThread();
        resumeRequests();
        for (RequestManager requestManager : this.treeNode.getDescendants()) {
            requestManager.resumeRequests();
        }
    }

    public void onStart() {
        resumeRequests();
        this.targetTracker.onStart();
    }

    public void onStop() {
        pauseRequests();
        this.targetTracker.onStop();
    }

    public void onDestroy() {
        this.targetTracker.onDestroy();
        for (Target target : this.targetTracker.getAll()) {
            clear(target);
        }
        this.targetTracker.clear();
        this.requestTracker.clearRequests();
        this.lifecycle.removeListener(this);
        this.lifecycle.removeListener(this.connectivityMonitor);
        this.mainHandler.removeCallbacks(this.addSelfToLifecycle);
        this.glide.unregisterRequestManager(this);
    }

    public RequestBuilder<Bitmap> asBitmap() {
        return as(Bitmap.class).transition(new GenericTransitionOptions()).apply(DECODE_TYPE_BITMAP);
    }

    public RequestBuilder<GifDrawable> asGif() {
        return as(GifDrawable.class).transition(new DrawableTransitionOptions()).apply(DECODE_TYPE_GIF);
    }

    public RequestBuilder<Drawable> asDrawable() {
        return as(Drawable.class).transition(new DrawableTransitionOptions());
    }

    public RequestBuilder<Drawable> load(Object model) {
        return asDrawable().load(model);
    }

    public RequestBuilder<File> downloadOnly() {
        return as(File.class).apply(DOWNLOAD_ONLY_OPTIONS);
    }

    public RequestBuilder<File> download(Object model) {
        return downloadOnly().load(model);
    }

    public RequestBuilder<File> asFile() {
        return as(File.class).apply(RequestOptions.skipMemoryCacheOf(true));
    }

    public <ResourceType> RequestBuilder<ResourceType> as(Class<ResourceType> resourceClass) {
        return new RequestBuilder(this.glide, this, resourceClass);
    }

    public void clear(View view) {
        clear(new ClearTarget(view));
    }

    public void clear(final Target<?> target) {
        if (target != null) {
            if (Util.isOnMainThread()) {
                untrackOrDelegate(target);
            } else {
                this.mainHandler.post(new Runnable() {
                    public void run() {
                        RequestManager.this.clear(target);
                    }
                });
            }
        }
    }

    private void untrackOrDelegate(Target<?> target) {
        if (!untrack(target)) {
            this.glide.removeFromManagers(target);
        }
    }

    boolean untrack(Target<?> target) {
        Request request = target.getRequest();
        if (request == null) {
            return true;
        }
        if (!this.requestTracker.clearRemoveAndRecycle(request)) {
            return false;
        }
        this.targetTracker.untrack(target);
        target.setRequest(null);
        return true;
    }

    void track(Target<?> target, Request request) {
        this.targetTracker.track(target);
        this.requestTracker.runRequest(request);
    }

    RequestOptions getDefaultRequestOptions() {
        return this.requestOptions;
    }

    public String toString() {
        return super.toString() + "{tracker=" + this.requestTracker + ", treeNode=" + this.treeNode + "}";
    }
}
