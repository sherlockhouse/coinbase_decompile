package com.google.android.gms.internal;

final class zzced implements Runnable {
    private /* synthetic */ zzcdw zziuy;
    private /* synthetic */ long zzivc;

    zzced(zzcdw com_google_android_gms_internal_zzcdw, long j) {
        this.zziuy = com_google_android_gms_internal_zzcdw;
        this.zzivc = j;
    }

    public final void run() {
        this.zziuy.zzaum().zzird.set(this.zzivc);
        this.zziuy.zzaul().zzayi().zzj("Session timeout duration set", Long.valueOf(this.zzivc));
    }
}
