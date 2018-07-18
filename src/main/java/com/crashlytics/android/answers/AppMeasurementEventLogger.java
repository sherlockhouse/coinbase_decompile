package com.crashlytics.android.answers;

import android.content.Context;
import android.os.Bundle;
import java.lang.reflect.Method;

public class AppMeasurementEventLogger implements EventLogger {
    private final Object logEventInstance;
    private final Method logEventMethod;

    public static EventLogger getEventLogger(Context context) {
        Class instanceClass = getClass(context);
        if (instanceClass == null) {
            return null;
        }
        Object instance = getInstance(context, instanceClass);
        if (instance == null) {
            return null;
        }
        Method logEventMethod = getLogEventMethod(context, instanceClass);
        if (logEventMethod != null) {
            return new AppMeasurementEventLogger(instance, logEventMethod);
        }
        return null;
    }

    private static Class getClass(Context context) {
        try {
            return context.getClassLoader().loadClass("com.google.android.gms.measurement.AppMeasurement");
        } catch (Exception e) {
            return null;
        }
    }

    private static Object getInstance(Context context, Class instanceClass) {
        try {
            return instanceClass.getDeclaredMethod("getInstance", new Class[]{Context.class}).invoke(instanceClass, new Object[]{context});
        } catch (Exception e) {
            return null;
        }
    }

    private static Method getLogEventMethod(Context context, Class instanceClass) {
        try {
            return instanceClass.getDeclaredMethod("logEventInternal", new Class[]{String.class, String.class, Bundle.class});
        } catch (Exception e) {
            return null;
        }
    }

    public AppMeasurementEventLogger(Object logEventInstance, Method logEventMethod) {
        this.logEventInstance = logEventInstance;
        this.logEventMethod = logEventMethod;
    }

    public void logEvent(String eventName, Bundle metadata) {
        logEvent("fab", eventName, metadata);
    }

    public void logEvent(String origin, String eventName, Bundle metadata) {
        try {
            this.logEventMethod.invoke(this.logEventInstance, new Object[]{origin, eventName, metadata});
        } catch (Exception e) {
        }
    }
}
