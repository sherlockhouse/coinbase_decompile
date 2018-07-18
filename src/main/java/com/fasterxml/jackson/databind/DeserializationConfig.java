package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.util.LinkedNode;
import java.io.Serializable;
import java.util.Map;

public final class DeserializationConfig extends MapperConfigBase<DeserializationFeature, DeserializationConfig> implements Serializable {
    protected final int _deserFeatures;
    protected final JsonNodeFactory _nodeFactory;
    protected final LinkedNode<DeserializationProblemHandler> _problemHandlers;

    public DeserializationConfig(BaseSettings base, SubtypeResolver str, Map<ClassKey, Class<?>> mixins) {
        super(base, str, mixins);
        this._deserFeatures = MapperConfig.collectFeatureDefaults(DeserializationFeature.class);
        this._nodeFactory = JsonNodeFactory.instance;
        this._problemHandlers = null;
    }

    private DeserializationConfig(DeserializationConfig src, int mapperFeatures, int deserFeatures) {
        super((MapperConfigBase) src, mapperFeatures);
        this._deserFeatures = deserFeatures;
        this._nodeFactory = src._nodeFactory;
        this._problemHandlers = src._problemHandlers;
    }

    private DeserializationConfig(DeserializationConfig src, BaseSettings base) {
        super((MapperConfigBase) src, base);
        this._deserFeatures = src._deserFeatures;
        this._nodeFactory = src._nodeFactory;
        this._problemHandlers = src._problemHandlers;
    }

    public DeserializationConfig with(MapperFeature... features) {
        int newMapperFlags = this._mapperFeatures;
        for (MapperFeature f : features) {
            newMapperFlags |= f.getMask();
        }
        return newMapperFlags == this._mapperFeatures ? this : new DeserializationConfig(this, newMapperFlags, this._deserFeatures);
    }

    public DeserializationConfig without(MapperFeature... features) {
        int newMapperFlags = this._mapperFeatures;
        for (MapperFeature f : features) {
            newMapperFlags &= f.getMask() ^ -1;
        }
        return newMapperFlags == this._mapperFeatures ? this : new DeserializationConfig(this, newMapperFlags, this._deserFeatures);
    }

    public DeserializationConfig with(PropertyNamingStrategy pns) {
        return _withBase(this._base.withPropertyNamingStrategy(pns));
    }

    private final DeserializationConfig _withBase(BaseSettings newBase) {
        return this._base == newBase ? this : new DeserializationConfig(this, newBase);
    }

    public DeserializationConfig with(DeserializationFeature feature) {
        int newDeserFeatures = this._deserFeatures | feature.getMask();
        return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures);
    }

    public DeserializationConfig without(DeserializationFeature feature) {
        int newDeserFeatures = this._deserFeatures & (feature.getMask() ^ -1);
        return newDeserFeatures == this._deserFeatures ? this : new DeserializationConfig(this, this._mapperFeatures, newDeserFeatures);
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        if (isEnabled(MapperFeature.USE_ANNOTATIONS)) {
            return super.getAnnotationIntrospector();
        }
        return NopAnnotationIntrospector.instance;
    }

    public boolean useRootWrapping() {
        if (this._rootName != null) {
            return this._rootName.length() > 0;
        } else {
            return isEnabled(DeserializationFeature.UNWRAP_ROOT_VALUE);
        }
    }

    public BeanDescription introspectClassAnnotations(JavaType type) {
        return getClassIntrospector().forClassAnnotations(this, type, this);
    }

    public VisibilityChecker<?> getDefaultVisibilityChecker() {
        VisibilityChecker<?> vchecker = super.getDefaultVisibilityChecker();
        if (!isEnabled(MapperFeature.AUTO_DETECT_SETTERS)) {
            vchecker = vchecker.withSetterVisibility(Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_CREATORS)) {
            vchecker = vchecker.withCreatorVisibility(Visibility.NONE);
        }
        if (isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
            return vchecker;
        }
        return vchecker.withFieldVisibility(Visibility.NONE);
    }

    public final boolean isEnabled(DeserializationFeature f) {
        return (this._deserFeatures & f.getMask()) != 0;
    }

    public final int getDeserializationFeatures() {
        return this._deserFeatures;
    }

    public LinkedNode<DeserializationProblemHandler> getProblemHandlers() {
        return this._problemHandlers;
    }

    public final JsonNodeFactory getNodeFactory() {
        return this._nodeFactory;
    }

    public <T extends BeanDescription> T introspect(JavaType type) {
        return getClassIntrospector().forDeserialization(this, type, this);
    }

    public <T extends BeanDescription> T introspectForCreation(JavaType type) {
        return getClassIntrospector().forCreation(this, type, this);
    }

    public <T extends BeanDescription> T introspectForBuilder(JavaType type) {
        return getClassIntrospector().forDeserializationWithBuilder(this, type, this);
    }
}
