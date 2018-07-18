package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class ChangeClipBounds extends Transition {
    private static final String[] sTransitionProperties = new String[]{"android:clipBounds:clip"};

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public ChangeClipBounds(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void captureValues(TransitionValues values) {
        View view = values.view;
        if (view.getVisibility() != 8) {
            Rect clip = ViewCompat.getClipBounds(view);
            values.values.put("android:clipBounds:clip", clip);
            if (clip == null) {
                values.values.put("android:clipBounds:bounds", new Rect(0, 0, view.getWidth(), view.getHeight()));
            }
        }
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        Animator animator = null;
        if (startValues != null && endValues != null && startValues.values.containsKey("android:clipBounds:clip") && endValues.values.containsKey("android:clipBounds:clip")) {
            boolean endIsNull;
            Rect start = (Rect) startValues.values.get("android:clipBounds:clip");
            Rect end = (Rect) endValues.values.get("android:clipBounds:clip");
            if (end == null) {
                endIsNull = true;
            } else {
                endIsNull = false;
            }
            if (!(start == null && end == null)) {
                if (start == null) {
                    start = (Rect) startValues.values.get("android:clipBounds:bounds");
                } else if (end == null) {
                    end = (Rect) endValues.values.get("android:clipBounds:bounds");
                }
                if (!start.equals(end)) {
                    ViewCompat.setClipBounds(endValues.view, start);
                    RectEvaluator evaluator = new RectEvaluator(new Rect());
                    animator = ObjectAnimator.ofObject(endValues.view, ViewUtils.CLIP_BOUNDS, evaluator, new Rect[]{start, end});
                    if (endIsNull) {
                        final View endView = endValues.view;
                        animator.addListener(new AnimatorListenerAdapter() {
                            public void onAnimationEnd(Animator animation) {
                                ViewCompat.setClipBounds(endView, null);
                            }
                        });
                    }
                }
            }
        }
        return animator;
    }
}
