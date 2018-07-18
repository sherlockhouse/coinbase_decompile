package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Build.VERSION;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import org.xmlpull.v1.XmlPullParser;

public class ChangeTransform extends Transition {
    private static final Property<PathAnimatorMatrix, float[]> NON_TRANSLATIONS_PROPERTY = new Property<PathAnimatorMatrix, float[]>(float[].class, "nonTranslations") {
        public float[] get(PathAnimatorMatrix object) {
            return null;
        }

        public void set(PathAnimatorMatrix object, float[] value) {
            object.setValues(value);
        }
    };
    private static final boolean SUPPORTS_VIEW_REMOVAL_SUPPRESSION;
    private static final Property<PathAnimatorMatrix, PointF> TRANSLATIONS_PROPERTY = new Property<PathAnimatorMatrix, PointF>(PointF.class, "translations") {
        public PointF get(PathAnimatorMatrix object) {
            return null;
        }

        public void set(PathAnimatorMatrix object, PointF value) {
            object.setTranslation(value);
        }
    };
    private static final String[] sTransitionProperties = new String[]{"android:changeTransform:matrix", "android:changeTransform:transforms", "android:changeTransform:parentMatrix"};
    private boolean mReparent = true;
    private Matrix mTempMatrix = new Matrix();
    private boolean mUseOverlay = true;

    private static class GhostListener extends TransitionListenerAdapter {
        private GhostViewImpl mGhostView;
        private View mView;

        GhostListener(View view, GhostViewImpl ghostView) {
            this.mView = view;
            this.mGhostView = ghostView;
        }

        public void onTransitionEnd(Transition transition) {
            transition.removeListener(this);
            GhostViewUtils.removeGhost(this.mView);
            this.mView.setTag(R.id.transition_transform, null);
            this.mView.setTag(R.id.parent_matrix, null);
        }

        public void onTransitionPause(Transition transition) {
            this.mGhostView.setVisibility(4);
        }

        public void onTransitionResume(Transition transition) {
            this.mGhostView.setVisibility(0);
        }
    }

    private static class PathAnimatorMatrix {
        private final Matrix mMatrix = new Matrix();
        private float mTranslationX;
        private float mTranslationY;
        private final float[] mValues;
        private final View mView;

        PathAnimatorMatrix(View view, float[] values) {
            this.mView = view;
            this.mValues = (float[]) values.clone();
            this.mTranslationX = this.mValues[2];
            this.mTranslationY = this.mValues[5];
            setAnimationMatrix();
        }

        void setValues(float[] values) {
            System.arraycopy(values, 0, this.mValues, 0, values.length);
            setAnimationMatrix();
        }

        void setTranslation(PointF translation) {
            this.mTranslationX = translation.x;
            this.mTranslationY = translation.y;
            setAnimationMatrix();
        }

        private void setAnimationMatrix() {
            this.mValues[2] = this.mTranslationX;
            this.mValues[5] = this.mTranslationY;
            this.mMatrix.setValues(this.mValues);
            ViewUtils.setAnimationMatrix(this.mView, this.mMatrix);
        }

        Matrix getMatrix() {
            return this.mMatrix;
        }
    }

    private static class Transforms {
        final float mRotationX;
        final float mRotationY;
        final float mRotationZ;
        final float mScaleX;
        final float mScaleY;
        final float mTranslationX;
        final float mTranslationY;
        final float mTranslationZ;

        Transforms(View view) {
            this.mTranslationX = view.getTranslationX();
            this.mTranslationY = view.getTranslationY();
            this.mTranslationZ = ViewCompat.getTranslationZ(view);
            this.mScaleX = view.getScaleX();
            this.mScaleY = view.getScaleY();
            this.mRotationX = view.getRotationX();
            this.mRotationY = view.getRotationY();
            this.mRotationZ = view.getRotation();
        }

        public void restore(View view) {
            ChangeTransform.setTransforms(view, this.mTranslationX, this.mTranslationY, this.mTranslationZ, this.mScaleX, this.mScaleY, this.mRotationX, this.mRotationY, this.mRotationZ);
        }

        public boolean equals(Object that) {
            if (!(that instanceof Transforms)) {
                return false;
            }
            Transforms thatTransform = (Transforms) that;
            if (thatTransform.mTranslationX == this.mTranslationX && thatTransform.mTranslationY == this.mTranslationY && thatTransform.mTranslationZ == this.mTranslationZ && thatTransform.mScaleX == this.mScaleX && thatTransform.mScaleY == this.mScaleY && thatTransform.mRotationX == this.mRotationX && thatTransform.mRotationY == this.mRotationY && thatTransform.mRotationZ == this.mRotationZ) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int code;
            int floatToIntBits;
            int i = 0;
            if (this.mTranslationX != 0.0f) {
                code = Float.floatToIntBits(this.mTranslationX);
            } else {
                code = 0;
            }
            int i2 = code * 31;
            if (this.mTranslationY != 0.0f) {
                floatToIntBits = Float.floatToIntBits(this.mTranslationY);
            } else {
                floatToIntBits = 0;
            }
            i2 = (i2 + floatToIntBits) * 31;
            if (this.mTranslationZ != 0.0f) {
                floatToIntBits = Float.floatToIntBits(this.mTranslationZ);
            } else {
                floatToIntBits = 0;
            }
            i2 = (i2 + floatToIntBits) * 31;
            if (this.mScaleX != 0.0f) {
                floatToIntBits = Float.floatToIntBits(this.mScaleX);
            } else {
                floatToIntBits = 0;
            }
            i2 = (i2 + floatToIntBits) * 31;
            if (this.mScaleY != 0.0f) {
                floatToIntBits = Float.floatToIntBits(this.mScaleY);
            } else {
                floatToIntBits = 0;
            }
            i2 = (i2 + floatToIntBits) * 31;
            if (this.mRotationX != 0.0f) {
                floatToIntBits = Float.floatToIntBits(this.mRotationX);
            } else {
                floatToIntBits = 0;
            }
            i2 = (i2 + floatToIntBits) * 31;
            if (this.mRotationY != 0.0f) {
                floatToIntBits = Float.floatToIntBits(this.mRotationY);
            } else {
                floatToIntBits = 0;
            }
            floatToIntBits = (i2 + floatToIntBits) * 31;
            if (this.mRotationZ != 0.0f) {
                i = Float.floatToIntBits(this.mRotationZ);
            }
            return floatToIntBits + i;
        }
    }

    static {
        boolean z = true;
        if (VERSION.SDK_INT < 21) {
            z = false;
        }
        SUPPORTS_VIEW_REMOVAL_SUPPRESSION = z;
    }

    public ChangeTransform(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, Styleable.CHANGE_TRANSFORM);
        this.mUseOverlay = TypedArrayUtils.getNamedBoolean(a, (XmlPullParser) attrs, "reparentWithOverlay", 1, true);
        this.mReparent = TypedArrayUtils.getNamedBoolean(a, (XmlPullParser) attrs, "reparent", 0, true);
        a.recycle();
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (view.getVisibility() != 8) {
            transitionValues.values.put("android:changeTransform:parent", view.getParent());
            transitionValues.values.put("android:changeTransform:transforms", new Transforms(view));
            Matrix matrix = view.getMatrix();
            if (matrix == null || matrix.isIdentity()) {
                matrix = null;
            } else {
                matrix = new Matrix(matrix);
            }
            transitionValues.values.put("android:changeTransform:matrix", matrix);
            if (this.mReparent) {
                Matrix parentMatrix = new Matrix();
                ViewGroup parent = (ViewGroup) view.getParent();
                ViewUtils.transformMatrixToGlobal(parent, parentMatrix);
                parentMatrix.preTranslate((float) (-parent.getScrollX()), (float) (-parent.getScrollY()));
                transitionValues.values.put("android:changeTransform:parentMatrix", parentMatrix);
                transitionValues.values.put("android:changeTransform:intermediateMatrix", view.getTag(R.id.transition_transform));
                transitionValues.values.put("android:changeTransform:intermediateParentMatrix", view.getTag(R.id.parent_matrix));
            }
        }
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
        if (!SUPPORTS_VIEW_REMOVAL_SUPPRESSION) {
            ((ViewGroup) transitionValues.view.getParent()).startViewTransition(transitionValues.view);
        }
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null || endValues == null || !startValues.values.containsKey("android:changeTransform:parent") || !endValues.values.containsKey("android:changeTransform:parent")) {
            return null;
        }
        ViewGroup startParent = (ViewGroup) startValues.values.get("android:changeTransform:parent");
        boolean handleParentChange = this.mReparent && !parentsMatch(startParent, (ViewGroup) endValues.values.get("android:changeTransform:parent"));
        Matrix startMatrix = (Matrix) startValues.values.get("android:changeTransform:intermediateMatrix");
        if (startMatrix != null) {
            startValues.values.put("android:changeTransform:matrix", startMatrix);
        }
        Matrix startParentMatrix = (Matrix) startValues.values.get("android:changeTransform:intermediateParentMatrix");
        if (startParentMatrix != null) {
            startValues.values.put("android:changeTransform:parentMatrix", startParentMatrix);
        }
        if (handleParentChange) {
            setMatricesForParent(startValues, endValues);
        }
        Animator transformAnimator = createTransformAnimator(startValues, endValues, handleParentChange);
        if (handleParentChange && transformAnimator != null && this.mUseOverlay) {
            createGhostView(sceneRoot, startValues, endValues);
            return transformAnimator;
        } else if (SUPPORTS_VIEW_REMOVAL_SUPPRESSION) {
            return transformAnimator;
        } else {
            startParent.endViewTransition(startValues.view);
            return transformAnimator;
        }
    }

    private ObjectAnimator createTransformAnimator(TransitionValues startValues, TransitionValues endValues, boolean handleParentChange) {
        Matrix startMatrix = (Matrix) startValues.values.get("android:changeTransform:matrix");
        Matrix endMatrix = (Matrix) endValues.values.get("android:changeTransform:matrix");
        if (startMatrix == null) {
            startMatrix = MatrixUtils.IDENTITY_MATRIX;
        }
        if (endMatrix == null) {
            endMatrix = MatrixUtils.IDENTITY_MATRIX;
        }
        if (startMatrix.equals(endMatrix)) {
            return null;
        }
        final Transforms transforms = (Transforms) endValues.values.get("android:changeTransform:transforms");
        final View view = endValues.view;
        setIdentityTransforms(view);
        startMatrixValues = new float[9];
        startMatrix.getValues(startMatrixValues);
        float[] endMatrixValues = new float[9];
        endMatrix.getValues(endMatrixValues);
        final PathAnimatorMatrix pathAnimatorMatrix = new PathAnimatorMatrix(view, startMatrixValues);
        PropertyValuesHolder valuesProperty = PropertyValuesHolder.ofObject(NON_TRANSLATIONS_PROPERTY, new FloatArrayEvaluator(new float[9]), new float[][]{startMatrixValues, endMatrixValues});
        PropertyValuesHolder translationProperty = PropertyValuesHolderUtils.ofPointF(TRANSLATIONS_PROPERTY, getPathMotion().getPath(startMatrixValues[2], startMatrixValues[5], endMatrixValues[2], endMatrixValues[5]));
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(pathAnimatorMatrix, new PropertyValuesHolder[]{valuesProperty, translationProperty});
        final Matrix finalEndMatrix = endMatrix;
        final boolean z = handleParentChange;
        AnimatorListenerAdapter listener = new AnimatorListenerAdapter() {
            private boolean mIsCanceled;
            private Matrix mTempMatrix = new Matrix();

            public void onAnimationCancel(Animator animation) {
                this.mIsCanceled = true;
            }

            public void onAnimationEnd(Animator animation) {
                if (!this.mIsCanceled) {
                    if (z && ChangeTransform.this.mUseOverlay) {
                        setCurrentMatrix(finalEndMatrix);
                    } else {
                        view.setTag(R.id.transition_transform, null);
                        view.setTag(R.id.parent_matrix, null);
                    }
                }
                ViewUtils.setAnimationMatrix(view, null);
                transforms.restore(view);
            }

            public void onAnimationPause(Animator animation) {
                setCurrentMatrix(pathAnimatorMatrix.getMatrix());
            }

            public void onAnimationResume(Animator animation) {
                ChangeTransform.setIdentityTransforms(view);
            }

            private void setCurrentMatrix(Matrix currentMatrix) {
                this.mTempMatrix.set(currentMatrix);
                view.setTag(R.id.transition_transform, this.mTempMatrix);
                transforms.restore(view);
            }
        };
        animator.addListener(listener);
        AnimatorUtils.addPauseListener(animator, listener);
        return animator;
    }

    private boolean parentsMatch(ViewGroup startParent, ViewGroup endParent) {
        if (!isValidTarget(startParent) || !isValidTarget(endParent)) {
            return startParent == endParent;
        } else {
            TransitionValues endValues = getMatchedTransitionValues(startParent, true);
            if (endValues == null) {
                return false;
            }
            return endParent == endValues.view;
        }
    }

    private void createGhostView(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        View view = endValues.view;
        Matrix localEndMatrix = new Matrix((Matrix) endValues.values.get("android:changeTransform:parentMatrix"));
        ViewUtils.transformMatrixToLocal(sceneRoot, localEndMatrix);
        GhostViewImpl ghostView = GhostViewUtils.addGhost(view, sceneRoot, localEndMatrix);
        if (ghostView != null) {
            ghostView.reserveEndViewTransition((ViewGroup) startValues.values.get("android:changeTransform:parent"), startValues.view);
            Transition outerTransition = this;
            while (outerTransition.mParent != null) {
                outerTransition = outerTransition.mParent;
            }
            outerTransition.addListener(new GhostListener(view, ghostView));
            if (SUPPORTS_VIEW_REMOVAL_SUPPRESSION) {
                if (startValues.view != endValues.view) {
                    ViewUtils.setTransitionAlpha(startValues.view, 0.0f);
                }
                ViewUtils.setTransitionAlpha(view, 1.0f);
            }
        }
    }

    private void setMatricesForParent(TransitionValues startValues, TransitionValues endValues) {
        Matrix endParentMatrix = (Matrix) endValues.values.get("android:changeTransform:parentMatrix");
        endValues.view.setTag(R.id.parent_matrix, endParentMatrix);
        Matrix toLocal = this.mTempMatrix;
        toLocal.reset();
        endParentMatrix.invert(toLocal);
        Matrix startLocal = (Matrix) startValues.values.get("android:changeTransform:matrix");
        if (startLocal == null) {
            startLocal = new Matrix();
            startValues.values.put("android:changeTransform:matrix", startLocal);
        }
        startLocal.postConcat((Matrix) startValues.values.get("android:changeTransform:parentMatrix"));
        startLocal.postConcat(toLocal);
    }

    private static void setIdentityTransforms(View view) {
        setTransforms(view, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f);
    }

    private static void setTransforms(View view, float translationX, float translationY, float translationZ, float scaleX, float scaleY, float rotationX, float rotationY, float rotationZ) {
        view.setTranslationX(translationX);
        view.setTranslationY(translationY);
        ViewCompat.setTranslationZ(view, translationZ);
        view.setScaleX(scaleX);
        view.setScaleY(scaleY);
        view.setRotationX(rotationX);
        view.setRotationY(rotationY);
        view.setRotation(rotationZ);
    }
}
