package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.ClassUtil;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Member;

public abstract class AnnotatedMember extends Annotated implements Serializable {
    protected final transient AnnotationMap _annotations;

    public abstract Class<?> getDeclaringClass();

    public abstract Member getMember();

    public abstract Object getValue(Object obj) throws UnsupportedOperationException, IllegalArgumentException;

    public abstract void setValue(Object obj, Object obj2) throws UnsupportedOperationException, IllegalArgumentException;

    protected AnnotatedMember(AnnotationMap annotations) {
        this._annotations = annotations;
    }

    protected AnnotationMap getAllAnnotations() {
        return this._annotations;
    }

    public final void addOrOverride(Annotation a) {
        this._annotations.add(a);
    }

    public final void addIfNotPresent(Annotation a) {
        this._annotations.addIfNotPresent(a);
    }

    public final void fixAccess() {
        ClassUtil.checkAndFixAccess(getMember());
    }
}
