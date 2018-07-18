package com.bluelinelabs.conductor.changehandler;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.ControllerChangeHandler;
import com.bluelinelabs.conductor.ControllerChangeHandler.ControllerChangeCompletedListener;

public abstract class AnimatorChangeHandler extends ControllerChangeHandler {
    private long animationDuration;
    private Animator animator;
    private boolean canceled;
    private boolean completed;
    private boolean needsImmediateCompletion;
    private OnAnimationReadyOrAbortedListener onAnimationReadyOrAbortedListener;
    private boolean removesFromViewOnPush;

    private class OnAnimationReadyOrAbortedListener implements OnPreDrawListener {
        final boolean addingToView;
        final ControllerChangeCompletedListener changeListener;
        final ViewGroup container;
        final View from;
        private boolean hasRun;
        final boolean isPush;
        final View to;

        OnAnimationReadyOrAbortedListener(ViewGroup container, View from, View to, boolean isPush, boolean addingToView, ControllerChangeCompletedListener changeListener) {
            this.container = container;
            this.from = from;
            this.to = to;
            this.isPush = isPush;
            this.addingToView = addingToView;
            this.changeListener = changeListener;
        }

        public boolean onPreDraw() {
            onReadyOrAborted();
            return true;
        }

        void onReadyOrAborted() {
            if (!this.hasRun) {
                this.hasRun = true;
                if (this.to != null) {
                    ViewTreeObserver observer = this.to.getViewTreeObserver();
                    if (observer.isAlive()) {
                        observer.removeOnPreDrawListener(this);
                    }
                }
                AnimatorChangeHandler.this.performAnimation(this.container, this.from, this.to, this.isPush, this.addingToView, this.changeListener);
            }
        }
    }

    protected abstract Animator getAnimator(ViewGroup viewGroup, View view, View view2, boolean z, boolean z2);

    protected abstract void resetFromView(View view);

    public AnimatorChangeHandler() {
        this(-1, true);
    }

    public AnimatorChangeHandler(boolean removesFromViewOnPush) {
        this(-1, removesFromViewOnPush);
    }

    public AnimatorChangeHandler(long duration) {
        this(duration, true);
    }

    public AnimatorChangeHandler(long duration, boolean removesFromViewOnPush) {
        this.animationDuration = duration;
        this.removesFromViewOnPush = removesFromViewOnPush;
    }

    public void saveToBundle(Bundle bundle) {
        super.saveToBundle(bundle);
        bundle.putLong("AnimatorChangeHandler.duration", this.animationDuration);
        bundle.putBoolean("AnimatorChangeHandler.removesFromViewOnPush", this.removesFromViewOnPush);
    }

    public void restoreFromBundle(Bundle bundle) {
        super.restoreFromBundle(bundle);
        this.animationDuration = bundle.getLong("AnimatorChangeHandler.duration");
        this.removesFromViewOnPush = bundle.getBoolean("AnimatorChangeHandler.removesFromViewOnPush");
    }

    public void onAbortPush(ControllerChangeHandler newHandler, Controller newTop) {
        super.onAbortPush(newHandler, newTop);
        this.canceled = true;
        if (this.animator != null) {
            this.animator.cancel();
        } else if (this.onAnimationReadyOrAbortedListener != null) {
            this.onAnimationReadyOrAbortedListener.onReadyOrAborted();
        }
    }

    public void completeImmediately() {
        super.completeImmediately();
        this.needsImmediateCompletion = true;
        if (this.animator != null) {
            this.animator.end();
        } else if (this.onAnimationReadyOrAbortedListener != null) {
            this.onAnimationReadyOrAbortedListener.onReadyOrAborted();
        }
    }

    public long getAnimationDuration() {
        return this.animationDuration;
    }

    public boolean removesFromViewOnPush() {
        return this.removesFromViewOnPush;
    }

    public final void performChange(ViewGroup container, View from, View to, boolean isPush, ControllerChangeCompletedListener changeListener) {
        boolean readyToAnimate = true;
        boolean addingToView = to != null && to.getParent() == null;
        if (addingToView) {
            if (isPush || from == null) {
                container.addView(to);
            } else if (to.getParent() == null) {
                container.addView(to, container.indexOfChild(from));
            }
            if (to.getWidth() <= 0 && to.getHeight() <= 0) {
                readyToAnimate = false;
                this.onAnimationReadyOrAbortedListener = new OnAnimationReadyOrAbortedListener(container, from, to, isPush, true, changeListener);
                to.getViewTreeObserver().addOnPreDrawListener(this.onAnimationReadyOrAbortedListener);
            }
        }
        if (readyToAnimate) {
            performAnimation(container, from, to, isPush, addingToView, changeListener);
        }
    }

    private void complete(ControllerChangeCompletedListener changeListener, AnimatorListener animatorListener) {
        if (!this.completed) {
            this.completed = true;
            changeListener.onChangeCompleted();
        }
        if (this.animator != null) {
            if (animatorListener != null) {
                this.animator.removeListener(animatorListener);
            }
            this.animator.cancel();
            this.animator = null;
        }
        this.onAnimationReadyOrAbortedListener = null;
    }

    private void performAnimation(ViewGroup container, View from, View to, boolean isPush, boolean toAddedToContainer, ControllerChangeCompletedListener changeListener) {
        if (this.canceled) {
            complete(changeListener, null);
        } else if (this.needsImmediateCompletion) {
            if (from != null && (!isPush || this.removesFromViewOnPush)) {
                container.removeView(from);
            }
            complete(changeListener, null);
            if (isPush && from != null) {
                resetFromView(from);
            }
        } else {
            this.animator = getAnimator(container, from, to, isPush, toAddedToContainer);
            if (this.animationDuration > 0) {
                this.animator.setDuration(this.animationDuration);
            }
            final View view = from;
            final boolean z = isPush;
            final ViewGroup viewGroup = container;
            final ControllerChangeCompletedListener controllerChangeCompletedListener = changeListener;
            this.animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationCancel(Animator animation) {
                    if (view != null && ((!z || AnimatorChangeHandler.this.removesFromViewOnPush) && AnimatorChangeHandler.this.needsImmediateCompletion)) {
                        viewGroup.removeView(view);
                    }
                    AnimatorChangeHandler.this.complete(controllerChangeCompletedListener, this);
                }

                public void onAnimationEnd(Animator animation) {
                    if (!AnimatorChangeHandler.this.canceled && AnimatorChangeHandler.this.animator != null) {
                        if (view != null && (!z || AnimatorChangeHandler.this.removesFromViewOnPush)) {
                            viewGroup.removeView(view);
                        }
                        AnimatorChangeHandler.this.complete(controllerChangeCompletedListener, this);
                        if (z && view != null) {
                            AnimatorChangeHandler.this.resetFromView(view);
                        }
                    }
                }
            });
            this.animator.start();
        }
    }
}
