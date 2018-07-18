package com.google.android.gms.internal;

final class zzcfe implements Runnable {
    private /* synthetic */ zzcfb zziwp;
    private /* synthetic */ zzcbo zziwq;

    zzcfe(zzcfb com_google_android_gms_internal_zzcfb, zzcbo com_google_android_gms_internal_zzcbo) {
        this.zziwp = com_google_android_gms_internal_zzcfb;
        this.zziwq = com_google_android_gms_internal_zzcbo;
    }

    public final void run() {
        synchronized (this.zziwp) {
            this.zziwp.zziwm = false;
            if (!this.zziwp.zziwf.isConnected()) {
                this.zziwp.zziwf.zzaul().zzayi().log("Connected to remote service");
                this.zziwp.zziwf.zza(this.zziwq);
            }
        }
    }
}
