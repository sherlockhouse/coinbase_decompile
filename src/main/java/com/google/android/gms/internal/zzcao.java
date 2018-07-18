package com.google.android.gms.internal;

final class zzcao implements Runnable {
    private /* synthetic */ String zzany;
    private /* synthetic */ long zzikx;
    private /* synthetic */ zzcan zziky;

    zzcao(zzcan com_google_android_gms_internal_zzcan, String str, long j) {
        this.zziky = com_google_android_gms_internal_zzcan;
        this.zzany = str;
        this.zzikx = j;
    }

    public final void run() {
        this.zziky.zzd(this.zzany, this.zzikx);
    }
}
