package com.bumptech.glide.request.target;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.transition.Transition;

public final class PreloadTarget<Z> extends SimpleTarget<Z> {
    private final RequestManager requestManager;

    public static <Z> PreloadTarget<Z> obtain(RequestManager requestManager, int width, int height) {
        return new PreloadTarget(requestManager, width, height);
    }

    private PreloadTarget(RequestManager requestManager, int width, int height) {
        super(width, height);
        this.requestManager = requestManager;
    }

    public void onResourceReady(Z z, Transition<? super Z> transition) {
        this.requestManager.clear((Target) this);
    }
}
