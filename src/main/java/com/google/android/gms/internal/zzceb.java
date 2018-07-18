package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzceb implements Runnable {
    private /* synthetic */ String zziah;
    private /* synthetic */ String zzium;
    private /* synthetic */ String zziun;
    private /* synthetic */ zzcdw zziuy;
    private /* synthetic */ AtomicReference zziva;
    private /* synthetic */ boolean zzivb;

    zzceb(zzcdw com_google_android_gms_internal_zzcdw, AtomicReference atomicReference, String str, String str2, String str3, boolean z) {
        this.zziuy = com_google_android_gms_internal_zzcdw;
        this.zziva = atomicReference;
        this.zziah = str;
        this.zzium = str2;
        this.zziun = str3;
        this.zzivb = z;
    }

    public final void run() {
        this.zziuy.zziki.zzauc().zza(this.zziva, this.zziah, this.zzium, this.zziun, this.zzivb);
    }
}
