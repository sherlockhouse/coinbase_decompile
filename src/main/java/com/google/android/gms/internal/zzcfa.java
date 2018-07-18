package com.google.android.gms.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class zzcfa implements Runnable {
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ boolean zzivb;
    private /* synthetic */ zzceo zziwf;
    private /* synthetic */ AtomicReference zziwg;

    zzcfa(zzceo com_google_android_gms_internal_zzceo, AtomicReference atomicReference, zzcas com_google_android_gms_internal_zzcas, boolean z) {
        this.zziwf = com_google_android_gms_internal_zzceo;
        this.zziwg = atomicReference;
        this.zziuj = com_google_android_gms_internal_zzcas;
        this.zzivb = z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        synchronized (this.zziwg) {
            try {
                zzcbo zzd = this.zziwf.zzivz;
                if (zzd == null) {
                    this.zziwf.zzaul().zzayd().log("Failed to get user properties");
                } else {
                    this.zziwg.set(zzd.zza(this.zziuj, this.zzivb));
                    this.zziwf.zzww();
                    this.zziwg.notify();
                }
            } catch (RemoteException e) {
                this.zziwf.zzaul().zzayd().zzj("Failed to get user properties", e);
            } finally {
                this.zziwg.notify();
            }
        }
    }
}
