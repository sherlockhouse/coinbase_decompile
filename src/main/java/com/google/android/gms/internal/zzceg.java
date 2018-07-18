package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzceg implements Runnable {
    private /* synthetic */ zzcdw zziuy;
    private /* synthetic */ AtomicReference zziva;
    private /* synthetic */ boolean zzivb;

    zzceg(zzcdw com_google_android_gms_internal_zzcdw, AtomicReference atomicReference, boolean z) {
        this.zziuy = com_google_android_gms_internal_zzcdw;
        this.zziva = atomicReference;
        this.zzivb = z;
    }

    public final void run() {
        this.zziuy.zzauc().zza(this.zziva, this.zzivb);
    }
}
