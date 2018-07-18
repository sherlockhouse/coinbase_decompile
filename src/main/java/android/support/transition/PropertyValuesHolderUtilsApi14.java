package android.support.transition;

import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Property;

class PropertyValuesHolderUtilsApi14 implements PropertyValuesHolderUtilsImpl {
    PropertyValuesHolderUtilsApi14() {
    }

    public PropertyValuesHolder ofPointF(Property<?, PointF> property, Path path) {
        return PropertyValuesHolder.ofFloat(new PathProperty(property, path), new float[]{0.0f, 1.0f});
    }
}
