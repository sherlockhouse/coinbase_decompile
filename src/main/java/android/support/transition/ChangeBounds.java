package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

public class ChangeBounds extends Transition {
    private static final Property<View, PointF> BOTTOM_RIGHT_ONLY_PROPERTY = new Property<View, PointF>(PointF.class, "bottomRight") {
        public void set(View view, PointF bottomRight) {
            ViewUtils.setLeftTopRightBottom(view, view.getLeft(), view.getTop(), Math.round(bottomRight.x), Math.round(bottomRight.y));
        }

        public PointF get(View view) {
            return null;
        }
    };
    private static final Property<ViewBounds, PointF> BOTTOM_RIGHT_PROPERTY = new Property<ViewBounds, PointF>(PointF.class, "bottomRight") {
        public void set(ViewBounds viewBounds, PointF bottomRight) {
            viewBounds.setBottomRight(bottomRight);
        }

        public PointF get(ViewBounds viewBounds) {
            return null;
        }
    };
    private static final Property<Drawable, PointF> DRAWABLE_ORIGIN_PROPERTY = new Property<Drawable, PointF>(PointF.class, "boundsOrigin") {
        private Rect mBounds = new Rect();

        public void set(Drawable object, PointF value) {
            object.copyBounds(this.mBounds);
            this.mBounds.offsetTo(Math.round(value.x), Math.round(value.y));
            object.setBounds(this.mBounds);
        }

        public PointF get(Drawable object) {
            object.copyBounds(this.mBounds);
            return new PointF((float) this.mBounds.left, (float) this.mBounds.top);
        }
    };
    private static final Property<View, PointF> POSITION_PROPERTY = new Property<View, PointF>(PointF.class, "position") {
        public void set(View view, PointF topLeft) {
            int left = Math.round(topLeft.x);
            int top = Math.round(topLeft.y);
            ViewUtils.setLeftTopRightBottom(view, left, top, left + view.getWidth(), top + view.getHeight());
        }

        public PointF get(View view) {
            return null;
        }
    };
    private static final Property<View, PointF> TOP_LEFT_ONLY_PROPERTY = new Property<View, PointF>(PointF.class, "topLeft") {
        public void set(View view, PointF topLeft) {
            ViewUtils.setLeftTopRightBottom(view, Math.round(topLeft.x), Math.round(topLeft.y), view.getRight(), view.getBottom());
        }

        public PointF get(View view) {
            return null;
        }
    };
    private static final Property<ViewBounds, PointF> TOP_LEFT_PROPERTY = new Property<ViewBounds, PointF>(PointF.class, "topLeft") {
        public void set(ViewBounds viewBounds, PointF topLeft) {
            viewBounds.setTopLeft(topLeft);
        }

        public PointF get(ViewBounds viewBounds) {
            return null;
        }
    };
    private static RectEvaluator sRectEvaluator = new RectEvaluator();
    private static final String[] sTransitionProperties = new String[]{"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};
    private boolean mReparent;
    private boolean mResizeClip;
    private int[] mTempLocation;

    private static class ViewBounds {
        private int mBottom;
        private int mBottomRightCalls;
        private int mLeft;
        private int mRight;
        private int mTop;
        private int mTopLeftCalls;
        private View mView;

        ViewBounds(View view) {
            this.mView = view;
        }

        void setTopLeft(PointF topLeft) {
            this.mLeft = Math.round(topLeft.x);
            this.mTop = Math.round(topLeft.y);
            this.mTopLeftCalls++;
            if (this.mTopLeftCalls == this.mBottomRightCalls) {
                setLeftTopRightBottom();
            }
        }

        void setBottomRight(PointF bottomRight) {
            this.mRight = Math.round(bottomRight.x);
            this.mBottom = Math.round(bottomRight.y);
            this.mBottomRightCalls++;
            if (this.mTopLeftCalls == this.mBottomRightCalls) {
                setLeftTopRightBottom();
            }
        }

        private void setLeftTopRightBottom() {
            ViewUtils.setLeftTopRightBottom(this.mView, this.mLeft, this.mTop, this.mRight, this.mBottom);
            this.mTopLeftCalls = 0;
            this.mBottomRightCalls = 0;
        }
    }

    public ChangeBounds() {
        this.mTempLocation = new int[2];
        this.mResizeClip = false;
        this.mReparent = false;
    }

    public ChangeBounds(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mTempLocation = new int[2];
        this.mResizeClip = false;
        this.mReparent = false;
        TypedArray a = context.obtainStyledAttributes(attrs, Styleable.CHANGE_BOUNDS);
        boolean resizeClip = TypedArrayUtils.getNamedBoolean(a, (XmlResourceParser) attrs, "resizeClip", 0, false);
        a.recycle();
        setResizeClip(resizeClip);
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public void setResizeClip(boolean resizeClip) {
        this.mResizeClip = resizeClip;
    }

    private void captureValues(TransitionValues values) {
        View view = values.view;
        if (ViewCompat.isLaidOut(view) || view.getWidth() != 0 || view.getHeight() != 0) {
            values.values.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            values.values.put("android:changeBounds:parent", values.view.getParent());
            if (this.mReparent) {
                values.view.getLocationInWindow(this.mTempLocation);
                values.values.put("android:changeBounds:windowX", Integer.valueOf(this.mTempLocation[0]));
                values.values.put("android:changeBounds:windowY", Integer.valueOf(this.mTempLocation[1]));
            }
            if (this.mResizeClip) {
                values.values.put("android:changeBounds:clip", ViewCompat.getClipBounds(view));
            }
        }
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private boolean parentMatches(View startParent, View endParent) {
        if (!this.mReparent) {
            return true;
        }
        TransitionValues endValues = getMatchedTransitionValues(startParent, true);
        if (endValues != null) {
            return endParent == endValues.view;
        } else if (startParent == endParent) {
            return true;
        } else {
            return false;
        }
    }

    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null) {
            return null;
        }
        Map<String, Object> startParentVals = startValues.values;
        View startParent = (ViewGroup) startParentVals.get("android:changeBounds:parent");
        View endParent = (ViewGroup) endValues.values.get("android:changeBounds:parent");
        if (startParent == null || endParent == null) {
            return null;
        }
        final View view = endValues.view;
        Animator mergeAnimators;
        if (parentMatches(startParent, endParent)) {
            Rect startBounds = (Rect) startValues.values.get("android:changeBounds:bounds");
            Rect endBounds = (Rect) endValues.values.get("android:changeBounds:bounds");
            int startLeft = startBounds.left;
            final int endLeft = endBounds.left;
            int startTop = startBounds.top;
            final int endTop = endBounds.top;
            int startRight = startBounds.right;
            final int endRight = endBounds.right;
            int startBottom = startBounds.bottom;
            final int endBottom = endBounds.bottom;
            int startWidth = startRight - startLeft;
            int startHeight = startBottom - startTop;
            int endWidth = endRight - endLeft;
            int endHeight = endBottom - endTop;
            Rect startClip = (Rect) startValues.values.get("android:changeBounds:clip");
            Rect endClip = (Rect) endValues.values.get("android:changeBounds:clip");
            int numChanges = 0;
            if (!((startWidth == 0 || startHeight == 0) && (endWidth == 0 || endHeight == 0))) {
                if (!(startLeft == endLeft && startTop == endTop)) {
                    numChanges = 0 + 1;
                }
                if (!(startRight == endRight && startBottom == endBottom)) {
                    numChanges++;
                }
            }
            if (!(startClip == null || startClip.equals(endClip)) || (startClip == null && endClip != null)) {
                numChanges++;
            }
            if (numChanges > 0) {
                if (this.mResizeClip) {
                    Rect rect;
                    int i = startLeft;
                    int i2 = startTop;
                    ViewUtils.setLeftTopRightBottom(view, i, i2, startLeft + Math.max(startWidth, endWidth), startTop + Math.max(startHeight, endHeight));
                    Animator positionAnimator = null;
                    if (!(startLeft == endLeft && startTop == endTop)) {
                        positionAnimator = ObjectAnimatorUtils.ofPointF(view, POSITION_PROPERTY, getPathMotion().getPath((float) startLeft, (float) startTop, (float) endLeft, (float) endTop));
                    }
                    final Rect finalClip = endClip;
                    if (startClip == null) {
                        rect = new Rect(0, 0, startWidth, startHeight);
                    }
                    if (endClip == null) {
                        rect = new Rect(0, 0, endWidth, endHeight);
                    }
                    Animator clipAnimator = null;
                    if (!startClip.equals(endClip)) {
                        ViewCompat.setClipBounds(view, startClip);
                        clipAnimator = ObjectAnimator.ofObject(view, "clipBounds", sRectEvaluator, new Object[]{startClip, endClip});
                        clipAnimator.addListener(new AnimatorListenerAdapter() {
                            private boolean mIsCanceled;

                            public void onAnimationCancel(Animator animation) {
                                this.mIsCanceled = true;
                            }

                            public void onAnimationEnd(Animator animation) {
                                if (!this.mIsCanceled) {
                                    ViewCompat.setClipBounds(view, finalClip);
                                    ViewUtils.setLeftTopRightBottom(view, endLeft, endTop, endRight, endBottom);
                                }
                            }
                        });
                    }
                    mergeAnimators = TransitionUtils.mergeAnimators(positionAnimator, clipAnimator);
                } else {
                    ViewUtils.setLeftTopRightBottom(view, startLeft, startTop, startRight, startBottom);
                    if (numChanges == 2) {
                        if (startWidth == endWidth && startHeight == endHeight) {
                            mergeAnimators = ObjectAnimatorUtils.ofPointF(view, POSITION_PROPERTY, getPathMotion().getPath((float) startLeft, (float) startTop, (float) endLeft, (float) endTop));
                        } else {
                            ViewBounds viewBounds = new ViewBounds(view);
                            viewBounds = viewBounds;
                            ObjectAnimator topLeftAnimator = ObjectAnimatorUtils.ofPointF(viewBounds, TOP_LEFT_PROPERTY, getPathMotion().getPath((float) startLeft, (float) startTop, (float) endLeft, (float) endTop));
                            viewBounds = viewBounds;
                            ObjectAnimator bottomRightAnimator = ObjectAnimatorUtils.ofPointF(viewBounds, BOTTOM_RIGHT_PROPERTY, getPathMotion().getPath((float) startRight, (float) startBottom, (float) endRight, (float) endBottom));
                            Animator set = new AnimatorSet();
                            set.playTogether(new Animator[]{topLeftAnimator, bottomRightAnimator});
                            mergeAnimators = set;
                            final ViewBounds viewBounds2 = viewBounds;
                            set.addListener(new AnimatorListenerAdapter() {
                                private ViewBounds mViewBounds = viewBounds2;
                            });
                        }
                    } else if (startLeft == endLeft && startTop == endTop) {
                        mergeAnimators = ObjectAnimatorUtils.ofPointF(view, BOTTOM_RIGHT_ONLY_PROPERTY, getPathMotion().getPath((float) startRight, (float) startBottom, (float) endRight, (float) endBottom));
                    } else {
                        mergeAnimators = ObjectAnimatorUtils.ofPointF(view, TOP_LEFT_ONLY_PROPERTY, getPathMotion().getPath((float) startLeft, (float) startTop, (float) endLeft, (float) endTop));
                    }
                }
                if (!(view.getParent() instanceof ViewGroup)) {
                    return mergeAnimators;
                }
                ViewGroup parent = (ViewGroup) view.getParent();
                ViewGroupUtils.suppressLayout(parent, true);
                final ViewGroup viewGroup = parent;
                addListener(new TransitionListenerAdapter() {
                    boolean mCanceled = false;

                    public void onTransitionEnd(Transition transition) {
                        if (!this.mCanceled) {
                            ViewGroupUtils.suppressLayout(viewGroup, false);
                        }
                        transition.removeListener(this);
                    }

                    public void onTransitionPause(Transition transition) {
                        ViewGroupUtils.suppressLayout(viewGroup, false);
                    }

                    public void onTransitionResume(Transition transition) {
                        ViewGroupUtils.suppressLayout(viewGroup, true);
                    }
                });
                return mergeAnimators;
            }
        }
        int startX = ((Integer) startValues.values.get("android:changeBounds:windowX")).intValue();
        int startY = ((Integer) startValues.values.get("android:changeBounds:windowY")).intValue();
        int endX = ((Integer) endValues.values.get("android:changeBounds:windowX")).intValue();
        int endY = ((Integer) endValues.values.get("android:changeBounds:windowY")).intValue();
        if (!(startX == endX && startY == endY)) {
            sceneRoot.getLocationInWindow(this.mTempLocation);
            Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.ARGB_8888);
            view.draw(new Canvas(bitmap));
            final BitmapDrawable drawable = new BitmapDrawable(bitmap);
            final float transitionAlpha = ViewUtils.getTransitionAlpha(view);
            ViewUtils.setTransitionAlpha(view, 0.0f);
            ViewUtils.getOverlay(sceneRoot).add(drawable);
            PropertyValuesHolder origin = PropertyValuesHolderUtils.ofPointF(DRAWABLE_ORIGIN_PROPERTY, getPathMotion().getPath((float) (startX - this.mTempLocation[0]), (float) (startY - this.mTempLocation[1]), (float) (endX - this.mTempLocation[0]), (float) (endY - this.mTempLocation[1])));
            mergeAnimators = ObjectAnimator.ofPropertyValuesHolder(drawable, new PropertyValuesHolder[]{origin});
            final ViewGroup viewGroup2 = sceneRoot;
            final View view2 = view;
            mergeAnimators.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animation) {
                    ViewUtils.getOverlay(viewGroup2).remove(drawable);
                    ViewUtils.setTransitionAlpha(view2, transitionAlpha);
                }
            });
            return mergeAnimators;
        }
        return null;
    }
}
