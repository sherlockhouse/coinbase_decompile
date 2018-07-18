package com.google.android.gms.internal;

final class zzcbx implements Runnable {
    private /* synthetic */ String zziqb;
    private /* synthetic */ zzcbw zziqc;

    zzcbx(zzcbw com_google_android_gms_internal_zzcbw, String str) {
        this.zziqc = com_google_android_gms_internal_zzcbw;
        this.zziqb = str;
    }

    public final void run() {
        zzcdu zzaum = this.zziqc.zziki.zzaum();
        if (zzaum.isInitialized()) {
            zzaum.zziqo.zzf(this.zziqb, 1);
        } else {
            this.zziqc.zzk(6, "Persisted config not initialized. Not logging error/warn");
        }
    }
}
