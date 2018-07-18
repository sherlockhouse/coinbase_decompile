package com.google.android.gms.internal;

final class zzcec implements Runnable {
    private /* synthetic */ zzcdw zziuy;
    private /* synthetic */ long zzivc;

    zzcec(zzcdw com_google_android_gms_internal_zzcdw, long j) {
        this.zziuy = com_google_android_gms_internal_zzcdw;
        this.zzivc = j;
    }

    public final void run() {
        this.zziuy.zzaum().zzirc.set(this.zzivc);
        this.zziuy.zzaul().zzayi().zzj("Minimum session duration set", Long.valueOf(this.zzivc));
    }
}
