package com.fasterxml.jackson.databind.util;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;

public class RootNameLookup implements Serializable {
    protected transient LRUMap<ClassKey, PropertyName> _rootNames = new LRUMap(20, Callback.DEFAULT_DRAG_ANIMATION_DURATION);

    public PropertyName findRootName(JavaType rootType, MapperConfig<?> config) {
        return findRootName(rootType.getRawClass(), (MapperConfig) config);
    }

    public PropertyName findRootName(Class<?> rootType, MapperConfig<?> config) {
        ClassKey key = new ClassKey(rootType);
        PropertyName name = (PropertyName) this._rootNames.get(key);
        if (name != null) {
            return name;
        }
        name = config.getAnnotationIntrospector().findRootName(config.introspectClassAnnotations((Class) rootType).getClassInfo());
        if (name == null || !name.hasSimpleName()) {
            name = new PropertyName(rootType.getSimpleName());
        }
        this._rootNames.put(key, name);
        return name;
    }
}
