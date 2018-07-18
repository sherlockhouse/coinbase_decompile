package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.ser.Serializers;
import java.io.Serializable;

public class OptionalHandlerFactory implements Serializable {
    public static final OptionalHandlerFactory instance = new OptionalHandlerFactory();

    protected OptionalHandlerFactory() {
    }

    public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) {
        Class<?> rawType = type.getRawClass();
        if (rawType.getName().startsWith("javax.xml.") || hasSupertypeStartingWith(rawType, "javax.xml.")) {
            Object ob = instantiate("com.fasterxml.jackson.databind.ext.CoreXMLSerializers");
            if (ob == null) {
                return null;
            }
            return ((Serializers) ob).findSerializer(config, type, beanDesc);
        } else if (doesImplement(rawType, "org.w3c.dom.Node")) {
            return (JsonSerializer) instantiate("com.fasterxml.jackson.databind.ext.DOMSerializer");
        } else {
            return null;
        }
    }

    public JsonDeserializer<?> findDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        Class<?> rawType = type.getRawClass();
        if (rawType.getName().startsWith("javax.xml.") || hasSupertypeStartingWith(rawType, "javax.xml.")) {
            Object ob = instantiate("com.fasterxml.jackson.databind.ext.CoreXMLDeserializers");
            if (ob == null) {
                return null;
            }
            return ((Deserializers) ob).findBeanDeserializer(type, config, beanDesc);
        } else if (doesImplement(rawType, "org.w3c.dom.Node")) {
            return (JsonDeserializer) instantiate("com.fasterxml.jackson.databind.ext.DOMDeserializer$DocumentDeserializer");
        } else {
            if (doesImplement(rawType, "org.w3c.dom.Node")) {
                return (JsonDeserializer) instantiate("com.fasterxml.jackson.databind.ext.DOMDeserializer$NodeDeserializer");
            }
            return null;
        }
    }

    private Object instantiate(String className) {
        try {
            return Class.forName(className).newInstance();
        } catch (LinkageError e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    private boolean doesImplement(Class<?> actualType, String classNameToImplement) {
        Class<?> type = actualType;
        while (type != null) {
            if (type.getName().equals(classNameToImplement) || hasInterface(type, classNameToImplement)) {
                return true;
            }
            type = type.getSuperclass();
        }
        return false;
    }

    private boolean hasInterface(Class<?> type, String interfaceToImplement) {
        Class<?>[] interfaces = type.getInterfaces();
        for (Class<?> iface : interfaces) {
            if (iface.getName().equals(interfaceToImplement)) {
                return true;
            }
        }
        for (Class<?> iface2 : interfaces) {
            if (hasInterface(iface2, interfaceToImplement)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSupertypeStartingWith(Class<?> rawType, String prefix) {
        for (Class<?> supertype = rawType.getSuperclass(); supertype != null; supertype = supertype.getSuperclass()) {
            if (supertype.getName().startsWith(prefix)) {
                return true;
            }
        }
        for (Class<?> cls = rawType; cls != null; cls = cls.getSuperclass()) {
            if (hasInterfaceStartingWith(cls, prefix)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasInterfaceStartingWith(Class<?> type, String prefix) {
        Class<?>[] interfaces = type.getInterfaces();
        for (Class<?> iface : interfaces) {
            if (iface.getName().startsWith(prefix)) {
                return true;
            }
        }
        for (Class<?> iface2 : interfaces) {
            if (hasInterfaceStartingWith(iface2, prefix)) {
                return true;
            }
        }
        return false;
    }
}
