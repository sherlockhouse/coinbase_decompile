package com.bumptech.glide.request.target;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.request.transition.Transition.ViewAdapter;

public abstract class ImageViewTarget<Z> extends ViewTarget<ImageView, Z> implements ViewAdapter {
    private Animatable animatable;

    protected abstract void setResource(Z z);

    public ImageViewTarget(ImageView view) {
        super(view);
    }

    public void setDrawable(Drawable drawable) {
        ((ImageView) this.view).setImageDrawable(drawable);
    }

    public void onLoadStarted(Drawable placeholder) {
        super.onLoadStarted(placeholder);
        setResourceInternal(null);
        setDrawable(placeholder);
    }

    public void onLoadFailed(Drawable errorDrawable) {
        super.onLoadFailed(errorDrawable);
        setResourceInternal(null);
        setDrawable(errorDrawable);
    }

    public void onLoadCleared(Drawable placeholder) {
        super.onLoadCleared(placeholder);
        setResourceInternal(null);
        setDrawable(placeholder);
    }

    public void onResourceReady(Z resource, Transition<? super Z> transition) {
        if (transition == null || !transition.transition(resource, this)) {
            setResourceInternal(resource);
        } else {
            maybeUpdateAnimatable(resource);
        }
    }

    public void onStart() {
        if (this.animatable != null) {
            this.animatable.start();
        }
    }

    public void onStop() {
        if (this.animatable != null) {
            this.animatable.stop();
        }
    }

    private void setResourceInternal(Z resource) {
        maybeUpdateAnimatable(resource);
        setResource(resource);
    }

    private void maybeUpdateAnimatable(Z resource) {
        if (resource instanceof Animatable) {
            this.animatable = (Animatable) resource;
            this.animatable.start();
            return;
        }
        this.animatable = null;
    }
}
