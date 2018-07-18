package com.google.android.gms.internal;

final class zzcfp implements Runnable {
    private /* synthetic */ long zzikx;
    private /* synthetic */ zzcfl zziww;

    zzcfp(zzcfl com_google_android_gms_internal_zzcfl, long j) {
        this.zziww = com_google_android_gms_internal_zzcfl;
        this.zzikx = j;
    }

    public final void run() {
        this.zziww.zzbe(this.zzikx);
    }
}
