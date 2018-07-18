package com.google.android.gms.internal;

final class zzcaq implements Runnable {
    private /* synthetic */ long zzikx;
    private /* synthetic */ zzcan zziky;

    zzcaq(zzcan com_google_android_gms_internal_zzcan, long j) {
        this.zziky = com_google_android_gms_internal_zzcan;
        this.zzikx = j;
    }

    public final void run() {
        this.zziky.zzak(this.zzikx);
    }
}
