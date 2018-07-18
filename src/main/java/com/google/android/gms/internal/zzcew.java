package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;

final class zzcew implements Runnable {
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ zzceo zziwf;
    private /* synthetic */ boolean zziwi = true;
    private /* synthetic */ boolean zziwj;
    private /* synthetic */ zzcav zziwk;
    private /* synthetic */ zzcav zziwl;

    zzcew(zzceo com_google_android_gms_internal_zzceo, boolean z, boolean z2, zzcav com_google_android_gms_internal_zzcav, zzcas com_google_android_gms_internal_zzcas, zzcav com_google_android_gms_internal_zzcav2) {
        this.zziwf = com_google_android_gms_internal_zzceo;
        this.zziwj = z2;
        this.zziwk = com_google_android_gms_internal_zzcav;
        this.zziuj = com_google_android_gms_internal_zzcas;
        this.zziwl = com_google_android_gms_internal_zzcav2;
    }

    public final void run() {
        zzcbo zzd = this.zziwf.zzivz;
        if (zzd == null) {
            this.zziwf.zzaul().zzayd().log("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.zziwi) {
            this.zziwf.zza(zzd, this.zziwj ? null : this.zziwk, this.zziuj);
        } else {
            try {
                if (TextUtils.isEmpty(this.zziwl.packageName)) {
                    zzd.zza(this.zziwk, this.zziuj);
                } else {
                    zzd.zzb(this.zziwk);
                }
            } catch (RemoteException e) {
                this.zziwf.zzaul().zzayd().zzj("Failed to send conditional user property to the service", e);
            }
        }
        this.zziwf.zzww();
    }
}
