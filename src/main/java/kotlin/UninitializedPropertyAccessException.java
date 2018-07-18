package kotlin;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: UninitializedPropertyAccessException.kt */
public final class UninitializedPropertyAccessException extends RuntimeException {
    public UninitializedPropertyAccessException(String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        super(message);
    }
}
