package com.mobsandgeeks.saripaar.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Select {
    int defaultSelection() default 0;

    String message() default "Select an item.";

    int messageResId() default 0;

    int order();
}
