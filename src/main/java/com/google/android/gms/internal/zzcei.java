package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzcei implements Runnable {
    private /* synthetic */ zzcdw zziuy;
    private /* synthetic */ AtomicReference zziva;

    zzcei(zzcdw com_google_android_gms_internal_zzcdw, AtomicReference atomicReference) {
        this.zziuy = com_google_android_gms_internal_zzcdw;
        this.zziva = atomicReference;
    }

    public final void run() {
        this.zziuy.zzauc().zza(this.zziva);
    }
}
