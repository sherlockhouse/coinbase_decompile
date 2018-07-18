package com.bluelinelabs.conductor.internal;

import android.text.TextUtils;

public class ClassUtils {
    public static <T> Class<? extends T> classForName(String className, boolean allowEmptyName) {
        if (allowEmptyName && TextUtils.isEmpty(className)) {
            return null;
        }
        try {
            return Class.forName(className);
        } catch (Exception e) {
            throw new RuntimeException("An exception occurred while finding class for name " + className + ". " + e.getMessage());
        }
    }

    public static <T> T newInstance(String className) {
        try {
            Class<? extends T> cls = classForName(className, true);
            return cls != null ? cls.newInstance() : null;
        } catch (Exception e) {
            throw new RuntimeException("An exception occurred while creating a new instance of " + className + ". " + e.getMessage());
        }
    }
}
