package com.google.android.gms.internal;

import android.os.RemoteException;

final class zzceu implements Runnable {
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ zzceo zziwf;

    zzceu(zzceo com_google_android_gms_internal_zzceo, zzcas com_google_android_gms_internal_zzcas) {
        this.zziwf = com_google_android_gms_internal_zzceo;
        this.zziuj = com_google_android_gms_internal_zzcas;
    }

    public final void run() {
        zzcbo zzd = this.zziwf.zzivz;
        if (zzd == null) {
            this.zziwf.zzaul().zzayd().log("Failed to send measurementEnabled to service");
            return;
        }
        try {
            zzd.zzb(this.zziuj);
            this.zziwf.zzww();
        } catch (RemoteException e) {
            this.zziwf.zzaul().zzayd().zzj("Failed to send measurementEnabled to the service", e);
        }
    }
}
