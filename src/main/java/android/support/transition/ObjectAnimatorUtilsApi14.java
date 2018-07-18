package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;

class ObjectAnimatorUtilsApi14 implements ObjectAnimatorUtilsImpl {
    ObjectAnimatorUtilsApi14() {
    }

    public <T> ObjectAnimator ofPointF(T target, Property<T, PointF> property, Path path) {
        return ObjectAnimator.ofFloat(target, new PathProperty(property, path), new float[]{0.0f, 1.0f});
    }
}
