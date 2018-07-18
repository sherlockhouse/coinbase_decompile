package kotlin.jvm.internal;

import java.util.Arrays;
import java.util.List;
import kotlin.KotlinNullPointerException;
import kotlin.UninitializedPropertyAccessException;

public class Intrinsics {
    private Intrinsics() {
    }

    public static void throwNpe() {
        throw ((KotlinNullPointerException) sanitizeStackTrace(new KotlinNullPointerException()));
    }

    public static void throwUninitializedProperty(String message) {
        throw ((UninitializedPropertyAccessException) sanitizeStackTrace(new UninitializedPropertyAccessException(message)));
    }

    public static void throwUninitializedPropertyAccessException(String propertyName) {
        throwUninitializedProperty("lateinit property " + propertyName + " has not been initialized");
    }

    public static void checkExpressionValueIsNotNull(Object value, String expression) {
        if (value == null) {
            throw ((IllegalStateException) sanitizeStackTrace(new IllegalStateException(expression + " must not be null")));
        }
    }

    public static void checkParameterIsNotNull(Object value, String paramName) {
        if (value == null) {
            throwParameterIsNullException(paramName);
        }
    }

    private static void throwParameterIsNullException(String paramName) {
        StackTraceElement caller = Thread.currentThread().getStackTrace()[3];
        String className = caller.getClassName();
        throw ((IllegalArgumentException) sanitizeStackTrace(new IllegalArgumentException("Parameter specified as non-null is null: method " + className + "." + caller.getMethodName() + ", parameter " + paramName)));
    }

    private static <T extends Throwable> T sanitizeStackTrace(T throwable) {
        return sanitizeStackTrace(throwable, Intrinsics.class.getName());
    }

    static <T extends Throwable> T sanitizeStackTrace(T throwable, String classNameToDrop) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        int size = stackTrace.length;
        int lastIntrinsic = -1;
        for (int i = 0; i < size; i++) {
            if (classNameToDrop.equals(stackTrace[i].getClassName())) {
                lastIntrinsic = i;
            }
        }
        List<StackTraceElement> list = Arrays.asList(stackTrace).subList(lastIntrinsic + 1, size);
        throwable.setStackTrace((StackTraceElement[]) list.toArray(new StackTraceElement[list.size()]));
        return throwable;
    }
}
