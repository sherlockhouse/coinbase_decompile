package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import java.io.Serializable;
import java.util.HashMap;

public class SimpleSerializers extends Base implements Serializable {
    protected HashMap<ClassKey, JsonSerializer<?>> _classMappings = null;
    protected boolean _hasEnumSerializer = false;
    protected HashMap<ClassKey, JsonSerializer<?>> _interfaceMappings = null;

    public <T> void addSerializer(Class<? extends T> type, JsonSerializer<T> ser) {
        _addSerializer(type, ser);
    }

    public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) {
        JsonSerializer<?> ser;
        Class<?> cls = type.getRawClass();
        ClassKey key = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings != null) {
                ser = (JsonSerializer) this._interfaceMappings.get(key);
                if (ser != null) {
                    return ser;
                }
            }
        } else if (this._classMappings != null) {
            ser = (JsonSerializer) this._classMappings.get(key);
            if (ser != null) {
                return ser;
            }
            if (this._hasEnumSerializer && type.isEnumType()) {
                key.reset(Enum.class);
                ser = (JsonSerializer) this._classMappings.get(key);
                if (ser != null) {
                    return ser;
                }
            }
            for (Class<?> curr = cls; curr != null; curr = curr.getSuperclass()) {
                key.reset(curr);
                ser = (JsonSerializer) this._classMappings.get(key);
                if (ser != null) {
                    return ser;
                }
            }
        }
        if (this._interfaceMappings != null) {
            ser = _findInterfaceMapping(cls, key);
            if (ser != null) {
                return ser;
            }
            if (!cls.isInterface()) {
                do {
                    cls = cls.getSuperclass();
                    if (cls != null) {
                        ser = _findInterfaceMapping(cls, key);
                    }
                } while (ser == null);
                return ser;
            }
        }
        return null;
    }

    public JsonSerializer<?> findArraySerializer(SerializationConfig config, ArrayType type, BeanDescription beanDesc, TypeSerializer elementTypeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(config, type, beanDesc);
    }

    public JsonSerializer<?> findCollectionSerializer(SerializationConfig config, CollectionType type, BeanDescription beanDesc, TypeSerializer elementTypeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(config, type, beanDesc);
    }

    public JsonSerializer<?> findCollectionLikeSerializer(SerializationConfig config, CollectionLikeType type, BeanDescription beanDesc, TypeSerializer elementTypeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(config, type, beanDesc);
    }

    public JsonSerializer<?> findMapSerializer(SerializationConfig config, MapType type, BeanDescription beanDesc, JsonSerializer<Object> jsonSerializer, TypeSerializer elementTypeSerializer, JsonSerializer<Object> jsonSerializer2) {
        return findSerializer(config, type, beanDesc);
    }

    public JsonSerializer<?> findMapLikeSerializer(SerializationConfig config, MapLikeType type, BeanDescription beanDesc, JsonSerializer<Object> jsonSerializer, TypeSerializer elementTypeSerializer, JsonSerializer<Object> jsonSerializer2) {
        return findSerializer(config, type, beanDesc);
    }

    protected JsonSerializer<?> _findInterfaceMapping(Class<?> cls, ClassKey key) {
        for (Class<?> iface : cls.getInterfaces()) {
            key.reset(iface);
            JsonSerializer<?> ser = (JsonSerializer) this._interfaceMappings.get(key);
            if (ser != null) {
                return ser;
            }
            ser = _findInterfaceMapping(iface, key);
            if (ser != null) {
                return ser;
            }
        }
        return null;
    }

    protected void _addSerializer(Class<?> cls, JsonSerializer<?> ser) {
        ClassKey key = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings == null) {
                this._interfaceMappings = new HashMap();
            }
            this._interfaceMappings.put(key, ser);
            return;
        }
        if (this._classMappings == null) {
            this._classMappings = new HashMap();
        }
        this._classMappings.put(key, ser);
        if (cls == Enum.class) {
            this._hasEnumSerializer = true;
        }
    }
}
