package com.google.android.gms.internal;

final class zzcap implements Runnable {
    private /* synthetic */ String zzany;
    private /* synthetic */ long zzikx;
    private /* synthetic */ zzcan zziky;

    zzcap(zzcan com_google_android_gms_internal_zzcan, String str, long j) {
        this.zziky = com_google_android_gms_internal_zzcan;
        this.zzany = str;
        this.zzikx = j;
    }

    public final void run() {
        this.zziky.zze(this.zzany, this.zzikx);
    }
}
