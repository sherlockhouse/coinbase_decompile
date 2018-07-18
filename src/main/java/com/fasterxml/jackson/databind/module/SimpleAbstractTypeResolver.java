package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.HashMap;

public class SimpleAbstractTypeResolver extends AbstractTypeResolver implements Serializable {
    protected final HashMap<ClassKey, Class<?>> _mappings = new HashMap();

    public JavaType findTypeMapping(DeserializationConfig config, JavaType type) {
        Class<?> dst = (Class) this._mappings.get(new ClassKey(type.getRawClass()));
        if (dst == null) {
            return null;
        }
        return type.narrowBy(dst);
    }

    public JavaType resolveAbstractType(DeserializationConfig config, JavaType type) {
        return null;
    }
}
