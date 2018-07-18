package android.support.design.internal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.transition.Transition;
import android.support.transition.TransitionValues;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Map;

public class TextScale extends Transition {
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof TextView) {
            transitionValues.values.put("android:textscale:scale", Float.valueOf(transitionValues.view.getScaleX()));
        }
    }

    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null || !(startValues.view instanceof TextView) || !(endValues.view instanceof TextView)) {
            return null;
        }
        float startSize;
        float endSize;
        final TextView view = endValues.view;
        Map<String, Object> startVals = startValues.values;
        Map<String, Object> endVals = endValues.values;
        if (startVals.get("android:textscale:scale") != null) {
            startSize = ((Float) startVals.get("android:textscale:scale")).floatValue();
        } else {
            startSize = 1.0f;
        }
        if (endVals.get("android:textscale:scale") != null) {
            endSize = ((Float) endVals.get("android:textscale:scale")).floatValue();
        } else {
            endSize = 1.0f;
        }
        if (startSize == endSize) {
            return null;
        }
        Animator animator = ValueAnimator.ofFloat(new float[]{startSize, endSize});
        animator.addUpdateListener(new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                view.setScaleX(animatedValue);
                view.setScaleY(animatedValue);
            }
        });
        return animator;
    }
}
