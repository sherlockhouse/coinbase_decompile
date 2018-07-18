package com.coinbase.android.ui;

import android.app.Application;
import android.content.Context;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimationUtilsWrapper {
    private final Context mContext;

    public AnimationUtilsWrapper(Application app) {
        this.mContext = app;
    }

    public void startAnimation(ImageView iv, int animation) {
        iv.startAnimation(AnimationUtils.loadAnimation(this.mContext, animation));
    }

    public boolean shouldAnimate() {
        return true;
    }

    public void startMaterialProgressDrawableAnimation(MaterialProgressDrawable drawable) {
        drawable.start();
    }
}
