package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;

class PropertyValuesHolderUtilsApi21 implements PropertyValuesHolderUtilsImpl {
    PropertyValuesHolderUtilsApi21() {
    }

    public PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        return PropertyValuesHolder.ofObject(property, null, path);
    }
}
