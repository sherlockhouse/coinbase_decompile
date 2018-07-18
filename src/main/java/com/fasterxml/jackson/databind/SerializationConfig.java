package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfigBase;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.Map;

public final class SerializationConfig extends MapperConfigBase<SerializationFeature, SerializationConfig> implements Serializable {
    protected final FilterProvider _filterProvider;
    protected final int _serFeatures;
    protected Include _serializationInclusion;

    public SerializationConfig(BaseSettings base, SubtypeResolver str, Map<ClassKey, Class<?>> mixins) {
        super(base, str, mixins);
        this._serializationInclusion = null;
        this._serFeatures = MapperConfig.collectFeatureDefaults(SerializationFeature.class);
        this._filterProvider = null;
    }

    private SerializationConfig(SerializationConfig src, int mapperFeatures, int serFeatures) {
        super((MapperConfigBase) src, mapperFeatures);
        this._serializationInclusion = null;
        this._serFeatures = serFeatures;
        this._serializationInclusion = src._serializationInclusion;
        this._filterProvider = src._filterProvider;
    }

    private SerializationConfig(SerializationConfig src, BaseSettings base) {
        super((MapperConfigBase) src, base);
        this._serializationInclusion = null;
        this._serFeatures = src._serFeatures;
        this._serializationInclusion = src._serializationInclusion;
        this._filterProvider = src._filterProvider;
    }

    private SerializationConfig(SerializationConfig src, Include incl) {
        super(src);
        this._serializationInclusion = null;
        this._serFeatures = src._serFeatures;
        this._serializationInclusion = incl;
        this._filterProvider = src._filterProvider;
    }

    public SerializationConfig with(MapperFeature... features) {
        int newMapperFlags = this._mapperFeatures;
        for (MapperFeature f : features) {
            newMapperFlags |= f.getMask();
        }
        return newMapperFlags == this._mapperFeatures ? this : new SerializationConfig(this, newMapperFlags, this._serFeatures);
    }

    public SerializationConfig without(MapperFeature... features) {
        int newMapperFlags = this._mapperFeatures;
        for (MapperFeature f : features) {
            newMapperFlags &= f.getMask() ^ -1;
        }
        return newMapperFlags == this._mapperFeatures ? this : new SerializationConfig(this, newMapperFlags, this._serFeatures);
    }

    public SerializationConfig with(PropertyNamingStrategy pns) {
        return _withBase(this._base.withPropertyNamingStrategy(pns));
    }

    private final SerializationConfig _withBase(BaseSettings newBase) {
        return this._base == newBase ? this : new SerializationConfig(this, newBase);
    }

    public SerializationConfig with(SerializationFeature feature) {
        int newSerFeatures = this._serFeatures | feature.getMask();
        return newSerFeatures == this._serFeatures ? this : new SerializationConfig(this, this._mapperFeatures, newSerFeatures);
    }

    public SerializationConfig without(SerializationFeature feature) {
        int newSerFeatures = this._serFeatures & (feature.getMask() ^ -1);
        return newSerFeatures == this._serFeatures ? this : new SerializationConfig(this, this._mapperFeatures, newSerFeatures);
    }

    public SerializationConfig withSerializationInclusion(Include incl) {
        return this._serializationInclusion == incl ? this : new SerializationConfig(this, incl);
    }

    public AnnotationIntrospector getAnnotationIntrospector() {
        if (isEnabled(MapperFeature.USE_ANNOTATIONS)) {
            return super.getAnnotationIntrospector();
        }
        return AnnotationIntrospector.nopInstance();
    }

    public BeanDescription introspectClassAnnotations(JavaType type) {
        return getClassIntrospector().forClassAnnotations(this, type, this);
    }

    public VisibilityChecker<?> getDefaultVisibilityChecker() {
        VisibilityChecker<?> vchecker = super.getDefaultVisibilityChecker();
        if (!isEnabled(MapperFeature.AUTO_DETECT_GETTERS)) {
            vchecker = vchecker.withGetterVisibility(Visibility.NONE);
        }
        if (!isEnabled(MapperFeature.AUTO_DETECT_IS_GETTERS)) {
            vchecker = vchecker.withIsGetterVisibility(Visibility.NONE);
        }
        if (isEnabled(MapperFeature.AUTO_DETECT_FIELDS)) {
            return vchecker;
        }
        return vchecker.withFieldVisibility(Visibility.NONE);
    }

    public final boolean isEnabled(SerializationFeature f) {
        return (this._serFeatures & f.getMask()) != 0;
    }

    public Include getSerializationInclusion() {
        if (this._serializationInclusion != null) {
            return this._serializationInclusion;
        }
        return Include.ALWAYS;
    }

    public FilterProvider getFilterProvider() {
        return this._filterProvider;
    }

    public <T extends BeanDescription> T introspect(JavaType type) {
        return getClassIntrospector().forSerialization(this, type, this);
    }

    public String toString() {
        return "[SerializationConfig: flags=0x" + Integer.toHexString(this._serFeatures) + "]";
    }
}
