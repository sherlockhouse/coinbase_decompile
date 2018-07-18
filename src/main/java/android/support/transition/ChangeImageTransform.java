package android.support.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import java.util.Map;

public class ChangeImageTransform extends Transition {
    private static final Property<ImageView, Matrix> ANIMATED_TRANSFORM_PROPERTY = new Property<ImageView, Matrix>(Matrix.class, "animatedTransform") {
        public void set(ImageView view, Matrix matrix) {
            ImageViewUtils.animateTransform(view, matrix);
        }

        public Matrix get(ImageView object) {
            return null;
        }
    };
    private static final TypeEvaluator<Matrix> NULL_MATRIX_EVALUATOR = new TypeEvaluator<Matrix>() {
        public Matrix evaluate(float fraction, Matrix startValue, Matrix endValue) {
            return null;
        }
    };
    private static final String[] sTransitionProperties = new String[]{"android:changeImageTransform:matrix", "android:changeImageTransform:bounds"};

    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$android$widget$ImageView$ScaleType = new int[ScaleType.values().length];

        static {
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ScaleType.FIT_XY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$android$widget$ImageView$ScaleType[ScaleType.CENTER_CROP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public ChangeImageTransform(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void captureValues(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if ((view instanceof ImageView) && view.getVisibility() == 0) {
            ImageView imageView = (ImageView) view;
            if (imageView.getDrawable() != null) {
                Map<String, Object> values = transitionValues.values;
                values.put("android:changeImageTransform:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
                values.put("android:changeImageTransform:matrix", copyImageMatrix(imageView));
            }
        }
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        Animator animator = null;
        if (!(startValues == null || endValues == null)) {
            Rect startBounds = (Rect) startValues.values.get("android:changeImageTransform:bounds");
            Rect endBounds = (Rect) endValues.values.get("android:changeImageTransform:bounds");
            if (!(startBounds == null || endBounds == null)) {
                Matrix startMatrix = (Matrix) startValues.values.get("android:changeImageTransform:matrix");
                Matrix endMatrix = (Matrix) endValues.values.get("android:changeImageTransform:matrix");
                boolean matricesEqual = (startMatrix == null && endMatrix == null) || (startMatrix != null && startMatrix.equals(endMatrix));
                if (!(startBounds.equals(endBounds) && matricesEqual)) {
                    ImageView imageView = endValues.view;
                    Drawable drawable = imageView.getDrawable();
                    int drawableWidth = drawable.getIntrinsicWidth();
                    int drawableHeight = drawable.getIntrinsicHeight();
                    ImageViewUtils.startAnimateTransform(imageView);
                    if (drawableWidth == 0 || drawableHeight == 0) {
                        animator = createNullAnimator(imageView);
                    } else {
                        if (startMatrix == null) {
                            startMatrix = MatrixUtils.IDENTITY_MATRIX;
                        }
                        if (endMatrix == null) {
                            endMatrix = MatrixUtils.IDENTITY_MATRIX;
                        }
                        ANIMATED_TRANSFORM_PROPERTY.set(imageView, startMatrix);
                        animator = createMatrixAnimator(imageView, startMatrix, endMatrix);
                    }
                    ImageViewUtils.reserveEndAnimateTransform(imageView, animator);
                }
            }
        }
        return animator;
    }

    private ObjectAnimator createNullAnimator(ImageView imageView) {
        return ObjectAnimator.ofObject(imageView, ANIMATED_TRANSFORM_PROPERTY, NULL_MATRIX_EVALUATOR, new Matrix[]{null, null});
    }

    private ObjectAnimator createMatrixAnimator(ImageView imageView, Matrix startMatrix, Matrix endMatrix) {
        return ObjectAnimator.ofObject(imageView, ANIMATED_TRANSFORM_PROPERTY, new MatrixEvaluator(), new Matrix[]{startMatrix, endMatrix});
    }

    private static Matrix copyImageMatrix(ImageView view) {
        switch (AnonymousClass3.$SwitchMap$android$widget$ImageView$ScaleType[view.getScaleType().ordinal()]) {
            case 1:
                return fitXYMatrix(view);
            case 2:
                return centerCropMatrix(view);
            default:
                return new Matrix(view.getImageMatrix());
        }
    }

    private static Matrix fitXYMatrix(ImageView view) {
        Drawable image = view.getDrawable();
        Matrix matrix = new Matrix();
        matrix.postScale(((float) view.getWidth()) / ((float) image.getIntrinsicWidth()), ((float) view.getHeight()) / ((float) image.getIntrinsicHeight()));
        return matrix;
    }

    private static Matrix centerCropMatrix(ImageView view) {
        Drawable image = view.getDrawable();
        int imageWidth = image.getIntrinsicWidth();
        int imageViewWidth = view.getWidth();
        float scaleX = ((float) imageViewWidth) / ((float) imageWidth);
        int imageHeight = image.getIntrinsicHeight();
        int imageViewHeight = view.getHeight();
        float maxScale = Math.max(scaleX, ((float) imageViewHeight) / ((float) imageHeight));
        float height = ((float) imageHeight) * maxScale;
        int tx = Math.round((((float) imageViewWidth) - (((float) imageWidth) * maxScale)) / 2.0f);
        int ty = Math.round((((float) imageViewHeight) - height) / 2.0f);
        Matrix matrix = new Matrix();
        matrix.postScale(maxScale, maxScale);
        matrix.postTranslate((float) tx, (float) ty);
        return matrix;
    }
}
