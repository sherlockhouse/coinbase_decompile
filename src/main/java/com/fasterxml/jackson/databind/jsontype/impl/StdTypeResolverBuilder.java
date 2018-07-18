package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import java.util.Collection;

public class StdTypeResolverBuilder implements TypeResolverBuilder<StdTypeResolverBuilder> {
    protected TypeIdResolver _customIdResolver;
    protected Class<?> _defaultImpl;
    protected Id _idType;
    protected As _includeAs;
    protected boolean _typeIdVisible = false;
    protected String _typeProperty;

    public static StdTypeResolverBuilder noTypeInfoBuilder() {
        return new StdTypeResolverBuilder().init(Id.NONE, null);
    }

    public StdTypeResolverBuilder init(Id idType, TypeIdResolver idRes) {
        if (idType == null) {
            throw new IllegalArgumentException("idType can not be null");
        }
        this._idType = idType;
        this._customIdResolver = idRes;
        this._typeProperty = idType.getDefaultPropertyName();
        return this;
    }

    public TypeSerializer buildTypeSerializer(SerializationConfig config, JavaType baseType, Collection<NamedType> subtypes) {
        if (this._idType == Id.NONE) {
            return null;
        }
        TypeIdResolver idRes = idResolver(config, baseType, subtypes, true, false);
        switch (this._includeAs) {
            case WRAPPER_ARRAY:
                return new AsArrayTypeSerializer(idRes, null);
            case PROPERTY:
                return new AsPropertyTypeSerializer(idRes, null, this._typeProperty);
            case WRAPPER_OBJECT:
                return new AsWrapperTypeSerializer(idRes, null);
            case EXTERNAL_PROPERTY:
                return new AsExternalTypeSerializer(idRes, null, this._typeProperty);
            default:
                throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this._includeAs);
        }
    }

    public TypeDeserializer buildTypeDeserializer(DeserializationConfig config, JavaType baseType, Collection<NamedType> subtypes) {
        if (this._idType == Id.NONE) {
            return null;
        }
        TypeIdResolver idRes = idResolver(config, baseType, subtypes, false, true);
        switch (this._includeAs) {
            case WRAPPER_ARRAY:
                return new AsArrayTypeDeserializer(baseType, idRes, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case PROPERTY:
                return new AsPropertyTypeDeserializer(baseType, idRes, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case WRAPPER_OBJECT:
                return new AsWrapperTypeDeserializer(baseType, idRes, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            case EXTERNAL_PROPERTY:
                return new AsExternalTypeDeserializer(baseType, idRes, this._typeProperty, this._typeIdVisible, this._defaultImpl);
            default:
                throw new IllegalStateException("Do not know how to construct standard type serializer for inclusion type: " + this._includeAs);
        }
    }

    public StdTypeResolverBuilder inclusion(As includeAs) {
        if (includeAs == null) {
            throw new IllegalArgumentException("includeAs can not be null");
        }
        this._includeAs = includeAs;
        return this;
    }

    public StdTypeResolverBuilder typeProperty(String typeIdPropName) {
        if (typeIdPropName == null || typeIdPropName.length() == 0) {
            typeIdPropName = this._idType.getDefaultPropertyName();
        }
        this._typeProperty = typeIdPropName;
        return this;
    }

    public StdTypeResolverBuilder defaultImpl(Class<?> defaultImpl) {
        this._defaultImpl = defaultImpl;
        return this;
    }

    public StdTypeResolverBuilder typeIdVisibility(boolean isVisible) {
        this._typeIdVisible = isVisible;
        return this;
    }

    public Class<?> getDefaultImpl() {
        return this._defaultImpl;
    }

    protected TypeIdResolver idResolver(MapperConfig<?> config, JavaType baseType, Collection<NamedType> subtypes, boolean forSer, boolean forDeser) {
        if (this._customIdResolver != null) {
            return this._customIdResolver;
        }
        if (this._idType == null) {
            throw new IllegalStateException("Can not build, 'init()' not yet called");
        }
        switch (this._idType) {
            case CLASS:
                return new ClassNameIdResolver(baseType, config.getTypeFactory());
            case MINIMAL_CLASS:
                return new MinimalClassNameIdResolver(baseType, config.getTypeFactory());
            case NAME:
                return TypeNameIdResolver.construct(config, baseType, subtypes, forSer, forDeser);
            case NONE:
                return null;
            default:
                throw new IllegalStateException("Do not know how to construct standard type id resolver for idType: " + this._idType);
        }
    }
}
