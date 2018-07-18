package com.google.android.gms.internal;

final class zzcfc implements Runnable {
    private /* synthetic */ zzcbo zziwo;
    private /* synthetic */ zzcfb zziwp;

    zzcfc(zzcfb com_google_android_gms_internal_zzcfb, zzcbo com_google_android_gms_internal_zzcbo) {
        this.zziwp = com_google_android_gms_internal_zzcfb;
        this.zziwo = com_google_android_gms_internal_zzcbo;
    }

    public final void run() {
        synchronized (this.zziwp) {
            this.zziwp.zziwm = false;
            if (!this.zziwp.zziwf.isConnected()) {
                this.zziwp.zziwf.zzaul().zzayj().log("Connected to service");
                this.zziwp.zziwf.zza(this.zziwo);
            }
        }
    }
}
