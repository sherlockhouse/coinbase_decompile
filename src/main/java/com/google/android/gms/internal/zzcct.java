package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbp;
import java.lang.Thread.UncaughtExceptionHandler;

final class zzcct implements UncaughtExceptionHandler {
    private final String zzisl;
    private /* synthetic */ zzccr zzism;

    public zzcct(zzccr com_google_android_gms_internal_zzccr, String str) {
        this.zzism = com_google_android_gms_internal_zzccr;
        zzbp.zzu(str);
        this.zzisl = str;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzism.zzaul().zzayd().zzj(this.zzisl, th);
    }
}
