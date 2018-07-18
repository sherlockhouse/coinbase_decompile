package com.google.android.gms.internal;

final class zzcfs extends zzcbc {
    private /* synthetic */ zzcfr zziwy;

    zzcfs(zzcfr com_google_android_gms_internal_zzcfr, zzccw com_google_android_gms_internal_zzccw) {
        this.zziwy = com_google_android_gms_internal_zzcfr;
        super(com_google_android_gms_internal_zzccw);
    }

    public final void run() {
        this.zziwy.zzaul().zzayj().log("Sending upload intent from DelayedRunnable");
        this.zziwy.zzazw();
    }
}
