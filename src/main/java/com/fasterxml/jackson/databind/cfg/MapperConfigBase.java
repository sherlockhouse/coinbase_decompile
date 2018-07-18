package com.fasterxml.jackson.databind.cfg;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.Map;

public abstract class MapperConfigBase<CFG extends ConfigFeature, T extends MapperConfigBase<CFG, T>> extends MapperConfig<T> implements Serializable {
    private static final int DEFAULT_MAPPER_FEATURES = MapperConfig.collectFeatureDefaults(MapperFeature.class);
    protected final ContextAttributes _attributes;
    protected final Map<ClassKey, Class<?>> _mixInAnnotations;
    protected final String _rootName;
    protected final SubtypeResolver _subtypeResolver;
    protected final Class<?> _view;

    protected MapperConfigBase(BaseSettings base, SubtypeResolver str, Map<ClassKey, Class<?>> mixins) {
        super(base, DEFAULT_MAPPER_FEATURES);
        this._mixInAnnotations = mixins;
        this._subtypeResolver = str;
        this._rootName = null;
        this._view = null;
        this._attributes = ContextAttributes.getEmpty();
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> src) {
        super(src);
        this._mixInAnnotations = src._mixInAnnotations;
        this._subtypeResolver = src._subtypeResolver;
        this._rootName = src._rootName;
        this._view = src._view;
        this._attributes = src._attributes;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> src, BaseSettings base) {
        super(base, src._mapperFeatures);
        this._mixInAnnotations = src._mixInAnnotations;
        this._subtypeResolver = src._subtypeResolver;
        this._rootName = src._rootName;
        this._view = src._view;
        this._attributes = src._attributes;
    }

    protected MapperConfigBase(MapperConfigBase<CFG, T> src, int mapperFeatures) {
        super(src._base, mapperFeatures);
        this._mixInAnnotations = src._mixInAnnotations;
        this._subtypeResolver = src._subtypeResolver;
        this._rootName = src._rootName;
        this._view = src._view;
        this._attributes = src._attributes;
    }

    public final SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    public final String getRootName() {
        return this._rootName;
    }

    public final Class<?> getActiveView() {
        return this._view;
    }

    public final ContextAttributes getAttributes() {
        return this._attributes;
    }

    public final Class<?> findMixInClassFor(Class<?> cls) {
        return this._mixInAnnotations == null ? null : (Class) this._mixInAnnotations.get(new ClassKey(cls));
    }
}
