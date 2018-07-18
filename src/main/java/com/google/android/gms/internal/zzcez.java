package com.google.android.gms.internal;

final class zzcez implements Runnable {
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ zzcft zziup;
    private /* synthetic */ zzceo zziwf;
    private /* synthetic */ boolean zziwj;

    zzcez(zzceo com_google_android_gms_internal_zzceo, boolean z, zzcft com_google_android_gms_internal_zzcft, zzcas com_google_android_gms_internal_zzcas) {
        this.zziwf = com_google_android_gms_internal_zzceo;
        this.zziwj = z;
        this.zziup = com_google_android_gms_internal_zzcft;
        this.zziuj = com_google_android_gms_internal_zzcas;
    }

    public final void run() {
        zzcbo zzd = this.zziwf.zzivz;
        if (zzd == null) {
            this.zziwf.zzaul().zzayd().log("Discarding data. Failed to set user attribute");
            return;
        }
        this.zziwf.zza(zzd, this.zziwj ? null : this.zziup, this.zziuj);
        this.zziwf.zzww();
    }
}
