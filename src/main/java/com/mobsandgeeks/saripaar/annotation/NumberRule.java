package com.mobsandgeeks.saripaar.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NumberRule {

    public enum NumberType {
        INTEGER,
        LONG,
        FLOAT,
        DOUBLE
    }

    double eq() default Double.MAX_VALUE;

    double gt() default Double.MAX_VALUE;

    double lt() default Double.MIN_VALUE;

    String message() default "";

    int messageResId() default 0;

    int order();

    NumberType type();
}
