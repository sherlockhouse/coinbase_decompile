package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public final class AnnotatedMethod extends AnnotatedWithParams implements Serializable {
    protected final transient Method _method;
    protected Class<?>[] _paramClasses;

    public AnnotatedMethod(Method method, AnnotationMap classAnn, AnnotationMap[] paramAnnotations) {
        super(classAnn, paramAnnotations);
        if (method == null) {
            throw new IllegalArgumentException("Can not construct AnnotatedMethod with null Method");
        }
        this._method = method;
    }

    public AnnotatedMethod withMethod(Method m) {
        return new AnnotatedMethod(m, this._annotations, this._paramAnnotations);
    }

    public AnnotatedMethod withAnnotations(AnnotationMap ann) {
        return new AnnotatedMethod(this._method, ann, this._paramAnnotations);
    }

    public Method getAnnotated() {
        return this._method;
    }

    public String getName() {
        return this._method.getName();
    }

    public Type getGenericType() {
        return this._method.getGenericReturnType();
    }

    public Class<?> getRawType() {
        return this._method.getReturnType();
    }

    public JavaType getType(TypeBindings bindings) {
        return getType(bindings, this._method.getTypeParameters());
    }

    public final Object call() throws Exception {
        return this._method.invoke(null, new Object[0]);
    }

    public final Object call(Object[] args) throws Exception {
        return this._method.invoke(null, args);
    }

    public final Object call1(Object arg) throws Exception {
        return this._method.invoke(null, new Object[]{arg});
    }

    public Class<?> getDeclaringClass() {
        return this._method.getDeclaringClass();
    }

    public Method getMember() {
        return this._method;
    }

    public void setValue(Object pojo, Object value) throws IllegalArgumentException {
        try {
            this._method.invoke(pojo, new Object[]{value});
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to setValue() with method " + getFullName() + ": " + e.getMessage(), e);
        } catch (InvocationTargetException e2) {
            throw new IllegalArgumentException("Failed to setValue() with method " + getFullName() + ": " + e2.getMessage(), e2);
        }
    }

    public Object getValue(Object pojo) throws IllegalArgumentException {
        try {
            return this._method.invoke(pojo, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException("Failed to getValue() with method " + getFullName() + ": " + e.getMessage(), e);
        } catch (InvocationTargetException e2) {
            throw new IllegalArgumentException("Failed to getValue() with method " + getFullName() + ": " + e2.getMessage(), e2);
        }
    }

    public int getParameterCount() {
        return getRawParameterTypes().length;
    }

    public String getFullName() {
        return getDeclaringClass().getName() + "#" + getName() + "(" + getParameterCount() + " params)";
    }

    public Class<?>[] getRawParameterTypes() {
        if (this._paramClasses == null) {
            this._paramClasses = this._method.getParameterTypes();
        }
        return this._paramClasses;
    }

    public Class<?> getRawParameterType(int index) {
        Class<?>[] types = getRawParameterTypes();
        return index >= types.length ? null : types[index];
    }

    public Type getGenericParameterType(int index) {
        Type[] types = this._method.getGenericParameterTypes();
        return index >= types.length ? null : types[index];
    }

    public Class<?> getRawReturnType() {
        return this._method.getReturnType();
    }

    public boolean hasReturnType() {
        Class<?> rt = getRawReturnType();
        return (rt == Void.TYPE || rt == Void.class) ? false : true;
    }

    public String toString() {
        return "[method " + getFullName() + "]";
    }
}
