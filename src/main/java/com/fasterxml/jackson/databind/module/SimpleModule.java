package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.Module.SetupContext;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

public class SimpleModule extends Module implements Serializable {
    protected SimpleAbstractTypeResolver _abstractTypes;
    protected BeanDeserializerModifier _deserializerModifier;
    protected SimpleDeserializers _deserializers;
    protected SimpleKeyDeserializers _keyDeserializers;
    protected SimpleSerializers _keySerializers;
    protected HashMap<Class<?>, Class<?>> _mixins;
    protected final String _name;
    protected PropertyNamingStrategy _namingStrategy;
    protected BeanSerializerModifier _serializerModifier;
    protected SimpleSerializers _serializers;
    protected LinkedHashSet<NamedType> _subtypes;
    protected SimpleValueInstantiators _valueInstantiators;
    protected final Version _version;

    public SimpleModule() {
        this._serializers = null;
        this._deserializers = null;
        this._keySerializers = null;
        this._keyDeserializers = null;
        this._abstractTypes = null;
        this._valueInstantiators = null;
        this._deserializerModifier = null;
        this._serializerModifier = null;
        this._mixins = null;
        this._subtypes = null;
        this._namingStrategy = null;
        this._name = "SimpleModule-" + System.identityHashCode(this);
        this._version = Version.unknownVersion();
    }

    public SimpleModule(Version version) {
        this._serializers = null;
        this._deserializers = null;
        this._keySerializers = null;
        this._keyDeserializers = null;
        this._abstractTypes = null;
        this._valueInstantiators = null;
        this._deserializerModifier = null;
        this._serializerModifier = null;
        this._mixins = null;
        this._subtypes = null;
        this._namingStrategy = null;
        this._name = version.getArtifactId();
        this._version = version;
    }

    public <T> SimpleModule addSerializer(Class<? extends T> type, JsonSerializer<T> ser) {
        if (this._serializers == null) {
            this._serializers = new SimpleSerializers();
        }
        this._serializers.addSerializer(type, ser);
        return this;
    }

    public <T> SimpleModule addDeserializer(Class<T> type, JsonDeserializer<? extends T> deser) {
        if (this._deserializers == null) {
            this._deserializers = new SimpleDeserializers();
        }
        this._deserializers.addDeserializer(type, deser);
        return this;
    }

    public SimpleModule addKeyDeserializer(Class<?> type, KeyDeserializer deser) {
        if (this._keyDeserializers == null) {
            this._keyDeserializers = new SimpleKeyDeserializers();
        }
        this._keyDeserializers.addDeserializer(type, deser);
        return this;
    }

    public String getModuleName() {
        return this._name;
    }

    public void setupModule(SetupContext context) {
        if (this._serializers != null) {
            context.addSerializers(this._serializers);
        }
        if (this._deserializers != null) {
            context.addDeserializers(this._deserializers);
        }
        if (this._keySerializers != null) {
            context.addKeySerializers(this._keySerializers);
        }
        if (this._keyDeserializers != null) {
            context.addKeyDeserializers(this._keyDeserializers);
        }
        if (this._abstractTypes != null) {
            context.addAbstractTypeResolver(this._abstractTypes);
        }
        if (this._valueInstantiators != null) {
            context.addValueInstantiators(this._valueInstantiators);
        }
        if (this._deserializerModifier != null) {
            context.addBeanDeserializerModifier(this._deserializerModifier);
        }
        if (this._serializerModifier != null) {
            context.addBeanSerializerModifier(this._serializerModifier);
        }
        if (this._subtypes != null && this._subtypes.size() > 0) {
            context.registerSubtypes((NamedType[]) this._subtypes.toArray(new NamedType[this._subtypes.size()]));
        }
        if (this._namingStrategy != null) {
            context.setNamingStrategy(this._namingStrategy);
        }
        if (this._mixins != null) {
            for (Entry<Class<?>, Class<?>> entry : this._mixins.entrySet()) {
                context.setMixInAnnotations((Class) entry.getKey(), (Class) entry.getValue());
            }
        }
    }

    public Version version() {
        return this._version;
    }
}
