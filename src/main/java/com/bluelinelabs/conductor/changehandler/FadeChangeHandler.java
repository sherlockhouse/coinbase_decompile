package com.bluelinelabs.conductor.changehandler;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.ControllerChangeHandler;

public class FadeChangeHandler extends AnimatorChangeHandler {
    public FadeChangeHandler(boolean removesFromViewOnPush) {
        super(removesFromViewOnPush);
    }

    public FadeChangeHandler(long duration, boolean removesFromViewOnPush) {
        super(duration, removesFromViewOnPush);
    }

    protected Animator getAnimator(ViewGroup container, View from, View to, boolean isPush, boolean toAddedToContainer) {
        AnimatorSet animator = new AnimatorSet();
        if (to != null) {
            float start = toAddedToContainer ? 0.0f : to.getAlpha();
            animator.play(ObjectAnimator.ofFloat(to, View.ALPHA, new float[]{start, 1.0f}));
        }
        if (from != null && (!isPush || removesFromViewOnPush())) {
            animator.play(ObjectAnimator.ofFloat(from, View.ALPHA, new float[]{0.0f}));
        }
        return animator;
    }

    protected void resetFromView(View from) {
        from.setAlpha(1.0f);
    }

    public ControllerChangeHandler copy() {
        return new FadeChangeHandler(getAnimationDuration(), removesFromViewOnPush());
    }
}
