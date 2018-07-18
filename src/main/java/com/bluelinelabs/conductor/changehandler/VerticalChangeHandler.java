package com.bluelinelabs.conductor.changehandler;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import java.util.ArrayList;
import java.util.List;

public class VerticalChangeHandler extends AnimatorChangeHandler {
    public VerticalChangeHandler(long duration) {
        super(duration);
    }

    public VerticalChangeHandler(long duration, boolean removesFromViewOnPush) {
        super(duration, removesFromViewOnPush);
    }

    protected Animator getAnimator(ViewGroup container, View from, View to, boolean isPush, boolean toAddedToContainer) {
        AnimatorSet animator = new AnimatorSet();
        List<Animator> viewAnimators = new ArrayList();
        if (isPush && to != null) {
            viewAnimators.add(ObjectAnimator.ofFloat(to, View.TRANSLATION_Y, new float[]{(float) to.getHeight(), 0.0f}));
        } else if (!(isPush || from == null)) {
            viewAnimators.add(ObjectAnimator.ofFloat(from, View.TRANSLATION_Y, new float[]{(float) from.getHeight()}));
        }
        animator.playTogether(viewAnimators);
        return animator;
    }

    protected void resetFromView(View from) {
    }

    public ControllerChangeHandler copy() {
        return new VerticalChangeHandler(getAnimationDuration(), removesFromViewOnPush());
    }
}
