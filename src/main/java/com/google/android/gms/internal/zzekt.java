package com.google.android.gms.internal;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import java.util.concurrent.atomic.AtomicReference;

public final class zzekt {
    private static final AtomicReference<zzekt> zzliq = new AtomicReference();

    private zzekt(Context context) {
    }

    public static zzekt zzep(Context context) {
        zzliq.compareAndSet(null, new zzekt(context));
        return (zzekt) zzliq.get();
    }

    public static void zzf(FirebaseApp firebaseApp) {
    }
}
