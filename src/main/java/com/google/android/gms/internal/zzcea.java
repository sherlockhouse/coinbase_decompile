package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzcea implements Runnable {
    private /* synthetic */ String zziah;
    private /* synthetic */ String zzium;
    private /* synthetic */ String zziun;
    private /* synthetic */ zzcdw zziuy;
    private /* synthetic */ AtomicReference zziva;

    zzcea(zzcdw com_google_android_gms_internal_zzcdw, AtomicReference atomicReference, String str, String str2, String str3) {
        this.zziuy = com_google_android_gms_internal_zzcdw;
        this.zziva = atomicReference;
        this.zziah = str;
        this.zzium = str2;
        this.zziun = str3;
    }

    public final void run() {
        this.zziuy.zziki.zzauc().zza(this.zziva, this.zziah, this.zzium, this.zziun);
    }
}
