package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.transition.Transition.TransitionListener;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public abstract class Visibility extends Transition {
    private static final String[] sTransitionProperties = new String[]{"android:visibility:visibility", "android:visibility:parent"};
    private int mMode = 3;

    private static class DisappearListener extends AnimatorListenerAdapter implements AnimatorPauseListenerCompat, TransitionListener {
        boolean mCanceled = false;
        private final int mFinalVisibility;
        private boolean mLayoutSuppressed;
        private final ViewGroup mParent;
        private final boolean mSuppressLayout;
        private final View mView;

        DisappearListener(View view, int finalVisibility, boolean suppressLayout) {
            this.mView = view;
            this.mFinalVisibility = finalVisibility;
            this.mParent = (ViewGroup) view.getParent();
            this.mSuppressLayout = suppressLayout;
            suppressLayout(true);
        }

        public void onAnimationPause(Animator animation) {
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
            }
        }

        public void onAnimationResume(Animator animation) {
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, 0);
            }
        }

        public void onAnimationCancel(Animator animation) {
            this.mCanceled = true;
        }

        public void onAnimationRepeat(Animator animation) {
        }

        public void onAnimationStart(Animator animation) {
        }

        public void onAnimationEnd(Animator animation) {
            hideViewWhenNotCanceled();
        }

        public void onTransitionStart(Transition transition) {
        }

        public void onTransitionEnd(Transition transition) {
            hideViewWhenNotCanceled();
            transition.removeListener(this);
        }

        public void onTransitionPause(Transition transition) {
            suppressLayout(false);
        }

        public void onTransitionResume(Transition transition) {
            suppressLayout(true);
        }

        private void hideViewWhenNotCanceled() {
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
                if (this.mParent != null) {
                    this.mParent.invalidate();
                }
            }
            suppressLayout(false);
        }

        private void suppressLayout(boolean suppress) {
            if (this.mSuppressLayout && this.mLayoutSuppressed != suppress && this.mParent != null) {
                this.mLayoutSuppressed = suppress;
                ViewGroupUtils.suppressLayout(this.mParent, suppress);
            }
        }
    }

    private static class VisibilityInfo {
        ViewGroup mEndParent;
        int mEndVisibility;
        boolean mFadeIn;
        ViewGroup mStartParent;
        int mStartVisibility;
        boolean mVisibilityChange;

        private VisibilityInfo() {
        }
    }

    public Visibility(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, Styleable.VISIBILITY_TRANSITION);
        int mode = TypedArrayUtils.getNamedInt(a, (XmlResourceParser) attrs, "transitionVisibilityMode", 0, 0);
        a.recycle();
        if (mode != 0) {
            setMode(mode);
        }
    }

    public void setMode(int mode) {
        if ((mode & -4) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.mMode = mode;
    }

    public int getMode() {
        return this.mMode;
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    private void captureValues(TransitionValues transitionValues) {
        transitionValues.values.put("android:visibility:visibility", Integer.valueOf(transitionValues.view.getVisibility()));
        transitionValues.values.put("android:visibility:parent", transitionValues.view.getParent());
        int[] loc = new int[2];
        transitionValues.view.getLocationOnScreen(loc);
        transitionValues.values.put("android:visibility:screenLocation", loc);
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private VisibilityInfo getVisibilityChangeInfo(TransitionValues startValues, TransitionValues endValues) {
        VisibilityInfo visInfo = new VisibilityInfo();
        visInfo.mVisibilityChange = false;
        visInfo.mFadeIn = false;
        if (startValues == null || !startValues.values.containsKey("android:visibility:visibility")) {
            visInfo.mStartVisibility = -1;
            visInfo.mStartParent = null;
        } else {
            visInfo.mStartVisibility = ((Integer) startValues.values.get("android:visibility:visibility")).intValue();
            visInfo.mStartParent = (ViewGroup) startValues.values.get("android:visibility:parent");
        }
        if (endValues == null || !endValues.values.containsKey("android:visibility:visibility")) {
            visInfo.mEndVisibility = -1;
            visInfo.mEndParent = null;
        } else {
            visInfo.mEndVisibility = ((Integer) endValues.values.get("android:visibility:visibility")).intValue();
            visInfo.mEndParent = (ViewGroup) endValues.values.get("android:visibility:parent");
        }
        if (startValues == null || endValues == null) {
            if (startValues == null && visInfo.mEndVisibility == 0) {
                visInfo.mFadeIn = true;
                visInfo.mVisibilityChange = true;
            } else if (endValues == null && visInfo.mStartVisibility == 0) {
                visInfo.mFadeIn = false;
                visInfo.mVisibilityChange = true;
            }
        } else if (!(visInfo.mStartVisibility == visInfo.mEndVisibility && visInfo.mStartParent == visInfo.mEndParent)) {
            if (visInfo.mStartVisibility != visInfo.mEndVisibility) {
                if (visInfo.mStartVisibility == 0) {
                    visInfo.mFadeIn = false;
                    visInfo.mVisibilityChange = true;
                } else if (visInfo.mEndVisibility == 0) {
                    visInfo.mFadeIn = true;
                    visInfo.mVisibilityChange = true;
                }
            } else if (visInfo.mEndParent == null) {
                visInfo.mFadeIn = false;
                visInfo.mVisibilityChange = true;
            } else if (visInfo.mStartParent == null) {
                visInfo.mFadeIn = true;
                visInfo.mVisibilityChange = true;
            }
        }
        return visInfo;
    }

    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        VisibilityInfo visInfo = getVisibilityChangeInfo(startValues, endValues);
        if (!visInfo.mVisibilityChange || (visInfo.mStartParent == null && visInfo.mEndParent == null)) {
            return null;
        }
        if (visInfo.mFadeIn) {
            return onAppear(sceneRoot, startValues, visInfo.mStartVisibility, endValues, visInfo.mEndVisibility);
        }
        return onDisappear(sceneRoot, startValues, visInfo.mStartVisibility, endValues, visInfo.mEndVisibility);
    }

    public Animator onAppear(ViewGroup sceneRoot, TransitionValues startValues, int startVisibility, TransitionValues endValues, int endVisibility) {
        if ((this.mMode & 1) != 1 || endValues == null) {
            return null;
        }
        if (startValues == null) {
            View endParent = (View) endValues.view.getParent();
            if (getVisibilityChangeInfo(getMatchedTransitionValues(endParent, false), getTransitionValues(endParent, false)).mVisibilityChange) {
                return null;
            }
        }
        return onAppear(sceneRoot, endValues.view, startValues, endValues);
    }

    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return null;
    }

    public Animator onDisappear(ViewGroup sceneRoot, TransitionValues startValues, int startVisibility, TransitionValues endValues, int endVisibility) {
        if ((this.mMode & 2) != 2) {
            return null;
        }
        View startView = startValues != null ? startValues.view : null;
        View endView = endValues != null ? endValues.view : null;
        View overlayView = null;
        View viewToKeep = null;
        if (endView == null || endView.getParent() == null) {
            if (endView != null) {
                overlayView = endView;
            } else if (startView != null) {
                if (startView.getParent() == null) {
                    overlayView = startView;
                } else if (startView.getParent() instanceof View) {
                    View startParent = (View) startView.getParent();
                    if (!getVisibilityChangeInfo(getTransitionValues(startParent, true), getMatchedTransitionValues(startParent, true)).mVisibilityChange) {
                        overlayView = TransitionUtils.copyViewImage(sceneRoot, startView, startParent);
                    } else if (startParent.getParent() == null) {
                        int id = startParent.getId();
                        if (!(id == -1 || sceneRoot.findViewById(id) == null || !this.mCanRemoveViews)) {
                            overlayView = startView;
                        }
                    }
                }
            }
        } else if (endVisibility == 4) {
            viewToKeep = endView;
        } else if (startView == endView) {
            viewToKeep = endView;
        } else {
            overlayView = startView;
        }
        int finalVisibility = endVisibility;
        Animator animator;
        if (overlayView != null && startValues != null) {
            int[] screenLoc = (int[]) startValues.values.get("android:visibility:screenLocation");
            int screenX = screenLoc[0];
            int screenY = screenLoc[1];
            int[] loc = new int[2];
            sceneRoot.getLocationOnScreen(loc);
            overlayView.offsetLeftAndRight((screenX - loc[0]) - overlayView.getLeft());
            overlayView.offsetTopAndBottom((screenY - loc[1]) - overlayView.getTop());
            final ViewGroupOverlayImpl overlay = ViewGroupUtils.getOverlay(sceneRoot);
            overlay.add(overlayView);
            animator = onDisappear(sceneRoot, overlayView, startValues, endValues);
            if (animator == null) {
                overlay.remove(overlayView);
                return animator;
            }
            final View finalOverlayView = overlayView;
            animator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    overlay.remove(finalOverlayView);
                }
            });
            return animator;
        } else if (viewToKeep == null) {
            return null;
        } else {
            int originalVisibility = viewToKeep.getVisibility();
            ViewUtils.setTransitionVisibility(viewToKeep, 0);
            animator = onDisappear(sceneRoot, viewToKeep, startValues, endValues);
            if (animator != null) {
                DisappearListener disappearListener = new DisappearListener(viewToKeep, finalVisibility, true);
                animator.addListener(disappearListener);
                AnimatorUtils.addPauseListener(animator, disappearListener);
                addListener(disappearListener);
                return animator;
            }
            ViewUtils.setTransitionVisibility(viewToKeep, originalVisibility);
            return animator;
        }
    }

    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return null;
    }

    public boolean isTransitionRequired(TransitionValues startValues, TransitionValues newValues) {
        if (startValues == null && newValues == null) {
            return false;
        }
        if (startValues != null && newValues != null && newValues.values.containsKey("android:visibility:visibility") != startValues.values.containsKey("android:visibility:visibility")) {
            return false;
        }
        VisibilityInfo changeInfo = getVisibilityChangeInfo(startValues, newValues);
        if (!changeInfo.mVisibilityChange) {
            return false;
        }
        if (changeInfo.mStartVisibility == 0 || changeInfo.mEndVisibility == 0) {
            return true;
        }
        return false;
    }
}
