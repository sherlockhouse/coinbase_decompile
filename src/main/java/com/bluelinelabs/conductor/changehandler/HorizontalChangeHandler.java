package com.bluelinelabs.conductor.changehandler;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.ControllerChangeHandler;

public class HorizontalChangeHandler extends AnimatorChangeHandler {
    public HorizontalChangeHandler(long duration) {
        super(duration);
    }

    public HorizontalChangeHandler(long duration, boolean removesFromViewOnPush) {
        super(duration, removesFromViewOnPush);
    }

    protected Animator getAnimator(ViewGroup container, View from, View to, boolean isPush, boolean toAddedToContainer) {
        AnimatorSet animatorSet = new AnimatorSet();
        if (isPush) {
            if (from != null) {
                animatorSet.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_X, new float[]{(float) (-from.getWidth())}));
            }
            if (to != null) {
                animatorSet.play(ObjectAnimator.ofFloat(to, View.TRANSLATION_X, new float[]{(float) to.getWidth(), 0.0f}));
            }
        } else {
            if (from != null) {
                animatorSet.play(ObjectAnimator.ofFloat(from, View.TRANSLATION_X, new float[]{(float) from.getWidth()}));
            }
            if (to != null) {
                float fromLeft;
                if (from != null) {
                    fromLeft = from.getTranslationX();
                } else {
                    fromLeft = 0.0f;
                }
                animatorSet.play(ObjectAnimator.ofFloat(to, View.TRANSLATION_X, new float[]{fromLeft - ((float) to.getWidth()), 0.0f}));
            }
        }
        return animatorSet;
    }

    protected void resetFromView(View from) {
        from.setTranslationX(0.0f);
    }

    public ControllerChangeHandler copy() {
        return new HorizontalChangeHandler(getAnimationDuration(), removesFromViewOnPush());
    }
}
