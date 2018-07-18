package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeBindings;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;

public final class AnnotatedConstructor extends AnnotatedWithParams {
    protected final Constructor<?> _constructor;

    public AnnotatedConstructor(Constructor<?> constructor, AnnotationMap classAnn, AnnotationMap[] paramAnn) {
        super(classAnn, paramAnn);
        if (constructor == null) {
            throw new IllegalArgumentException("Null constructor not allowed");
        }
        this._constructor = constructor;
    }

    public Constructor<?> getAnnotated() {
        return this._constructor;
    }

    public String getName() {
        return this._constructor.getName();
    }

    public Type getGenericType() {
        return getRawType();
    }

    public Class<?> getRawType() {
        return this._constructor.getDeclaringClass();
    }

    public JavaType getType(TypeBindings bindings) {
        return getType(bindings, this._constructor.getTypeParameters());
    }

    public int getParameterCount() {
        return this._constructor.getParameterTypes().length;
    }

    public Class<?> getRawParameterType(int index) {
        Class<?>[] types = this._constructor.getParameterTypes();
        return index >= types.length ? null : types[index];
    }

    public Type getGenericParameterType(int index) {
        Type[] types = this._constructor.getGenericParameterTypes();
        return index >= types.length ? null : types[index];
    }

    public final Object call() throws Exception {
        return this._constructor.newInstance(new Object[0]);
    }

    public final Object call(Object[] args) throws Exception {
        return this._constructor.newInstance(args);
    }

    public final Object call1(Object arg) throws Exception {
        return this._constructor.newInstance(new Object[]{arg});
    }

    public Class<?> getDeclaringClass() {
        return this._constructor.getDeclaringClass();
    }

    public Member getMember() {
        return this._constructor;
    }

    public void setValue(Object pojo, Object value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot call setValue() on constructor of " + getDeclaringClass().getName());
    }

    public Object getValue(Object pojo) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Cannot call getValue() on constructor of " + getDeclaringClass().getName());
    }

    public String toString() {
        return "[constructor for " + getName() + ", annotations: " + this._annotations + "]";
    }
}
