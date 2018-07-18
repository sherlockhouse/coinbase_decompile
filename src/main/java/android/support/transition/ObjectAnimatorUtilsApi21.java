package android.support.transition;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;

class ObjectAnimatorUtilsApi21 implements ObjectAnimatorUtilsImpl {
    ObjectAnimatorUtilsApi21() {
    }

    public <T> ObjectAnimator ofPointF(T target, Property<T, PointF> property, Path path) {
        return ObjectAnimator.ofObject(target, property, null, path);
    }
}
