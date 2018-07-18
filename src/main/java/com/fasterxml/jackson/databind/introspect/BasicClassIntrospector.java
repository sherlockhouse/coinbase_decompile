package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector.MixInResolver;
import com.fasterxml.jackson.databind.type.SimpleType;
import java.io.Serializable;

public class BasicClassIntrospector extends ClassIntrospector implements Serializable {
    protected static final BasicBeanDescription BOOLEAN_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Boolean.TYPE), AnnotatedClass.constructWithoutSuperTypes(Boolean.TYPE, null, null));
    protected static final BasicBeanDescription INT_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Integer.TYPE), AnnotatedClass.constructWithoutSuperTypes(Integer.TYPE, null, null));
    protected static final BasicBeanDescription LONG_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Long.TYPE), AnnotatedClass.constructWithoutSuperTypes(Long.TYPE, null, null));
    protected static final BasicBeanDescription STRING_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(String.class), AnnotatedClass.constructWithoutSuperTypes(String.class, null, null));
    public static final BasicClassIntrospector instance = new BasicClassIntrospector();

    public BasicBeanDescription forSerialization(SerializationConfig cfg, JavaType type, MixInResolver r) {
        BasicBeanDescription desc = _findCachedDesc(type);
        if (desc != null) {
            return desc;
        }
        return BasicBeanDescription.forSerialization(collectProperties(cfg, type, r, true, "set"));
    }

    public BasicBeanDescription forDeserialization(DeserializationConfig cfg, JavaType type, MixInResolver r) {
        BasicBeanDescription desc = _findCachedDesc(type);
        if (desc != null) {
            return desc;
        }
        return BasicBeanDescription.forDeserialization(collectProperties(cfg, type, r, false, "set"));
    }

    public BasicBeanDescription forDeserializationWithBuilder(DeserializationConfig cfg, JavaType type, MixInResolver r) {
        return BasicBeanDescription.forDeserialization(collectPropertiesWithBuilder(cfg, type, r, false));
    }

    public BasicBeanDescription forCreation(DeserializationConfig cfg, JavaType type, MixInResolver r) {
        BasicBeanDescription desc = _findCachedDesc(type);
        if (desc != null) {
            return desc;
        }
        return BasicBeanDescription.forDeserialization(collectProperties(cfg, type, r, false, "set"));
    }

    public BasicBeanDescription forClassAnnotations(MapperConfig<?> cfg, JavaType type, MixInResolver r) {
        return BasicBeanDescription.forOtherUse(cfg, type, AnnotatedClass.construct(type.getRawClass(), cfg.isAnnotationProcessingEnabled() ? cfg.getAnnotationIntrospector() : null, r));
    }

    protected POJOPropertiesCollector collectProperties(MapperConfig<?> config, JavaType type, MixInResolver r, boolean forSerialization, String mutatorPrefix) {
        return constructPropertyCollector(config, AnnotatedClass.construct(type.getRawClass(), config.isAnnotationProcessingEnabled() ? config.getAnnotationIntrospector() : null, r), type, forSerialization, mutatorPrefix).collect();
    }

    protected POJOPropertiesCollector collectPropertiesWithBuilder(MapperConfig<?> config, JavaType type, MixInResolver r, boolean forSerialization) {
        AnnotationIntrospector ai;
        if (config.isAnnotationProcessingEnabled()) {
            ai = config.getAnnotationIntrospector();
        } else {
            ai = null;
        }
        AnnotatedClass ac = AnnotatedClass.construct(type.getRawClass(), ai, r);
        Value builderConfig = ai == null ? null : ai.findPOJOBuilderConfig(ac);
        return constructPropertyCollector(config, ac, type, forSerialization, builderConfig == null ? "with" : builderConfig.withPrefix).collect();
    }

    protected POJOPropertiesCollector constructPropertyCollector(MapperConfig<?> config, AnnotatedClass ac, JavaType type, boolean forSerialization, String mutatorPrefix) {
        return new POJOPropertiesCollector(config, forSerialization, type, ac, mutatorPrefix);
    }

    protected BasicBeanDescription _findCachedDesc(JavaType type) {
        Class<?> cls = type.getRawClass();
        if (cls == String.class) {
            return STRING_DESC;
        }
        if (cls == Boolean.TYPE) {
            return BOOLEAN_DESC;
        }
        if (cls == Integer.TYPE) {
            return INT_DESC;
        }
        if (cls == Long.TYPE) {
            return LONG_DESC;
        }
        return null;
    }
}
