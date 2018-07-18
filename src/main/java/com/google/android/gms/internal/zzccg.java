package com.google.android.gms.internal;

final class zzccg implements Runnable {
    private /* synthetic */ boolean zziql;
    private /* synthetic */ zzccf zziqm;

    zzccg(zzccf com_google_android_gms_internal_zzccf, boolean z) {
        this.zziqm = com_google_android_gms_internal_zzccf;
        this.zziql = z;
    }

    public final void run() {
        this.zziqm.zziki.zzbo(this.zziql);
    }
}
