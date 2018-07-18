package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;

final class zzcev implements Runnable {
    private /* synthetic */ String zziah;
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ zzcbk zziuo;
    private /* synthetic */ zzceo zziwf;
    private /* synthetic */ boolean zziwi = true;
    private /* synthetic */ boolean zziwj;

    zzcev(zzceo com_google_android_gms_internal_zzceo, boolean z, boolean z2, zzcbk com_google_android_gms_internal_zzcbk, zzcas com_google_android_gms_internal_zzcas, String str) {
        this.zziwf = com_google_android_gms_internal_zzceo;
        this.zziwj = z2;
        this.zziuo = com_google_android_gms_internal_zzcbk;
        this.zziuj = com_google_android_gms_internal_zzcas;
        this.zziah = str;
    }

    public final void run() {
        zzcbo zzd = this.zziwf.zzivz;
        if (zzd == null) {
            this.zziwf.zzaul().zzayd().log("Discarding data. Failed to send event to service");
            return;
        }
        if (this.zziwi) {
            this.zziwf.zza(zzd, this.zziwj ? null : this.zziuo, this.zziuj);
        } else {
            try {
                if (TextUtils.isEmpty(this.zziah)) {
                    zzd.zza(this.zziuo, this.zziuj);
                } else {
                    zzd.zza(this.zziuo, this.zziah, this.zziwf.zzaul().zzayk());
                }
            } catch (RemoteException e) {
                this.zziwf.zzaul().zzayd().zzj("Failed to send event to the service", e);
            }
        }
        this.zziwf.zzww();
    }
}
