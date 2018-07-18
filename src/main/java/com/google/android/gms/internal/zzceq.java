package com.google.android.gms.internal;

import android.os.RemoteException;
import java.util.concurrent.atomic.AtomicReference;

final class zzceq implements Runnable {
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ zzceo zziwf;
    private /* synthetic */ AtomicReference zziwg;

    zzceq(zzceo com_google_android_gms_internal_zzceo, AtomicReference atomicReference, zzcas com_google_android_gms_internal_zzcas) {
        this.zziwf = com_google_android_gms_internal_zzceo;
        this.zziwg = atomicReference;
        this.zziuj = com_google_android_gms_internal_zzcas;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        synchronized (this.zziwg) {
            try {
                zzcbo zzd = this.zziwf.zzivz;
                if (zzd == null) {
                    this.zziwf.zzaul().zzayd().log("Failed to get app instance id");
                } else {
                    this.zziwg.set(zzd.zzc(this.zziuj));
                    String str = (String) this.zziwg.get();
                    if (str != null) {
                        this.zziwf.zzatz().zzjk(str);
                        this.zziwf.zzaum().zziqv.zzjl(str);
                    }
                    this.zziwf.zzww();
                    this.zziwg.notify();
                }
            } catch (RemoteException e) {
                this.zziwf.zzaul().zzayd().zzj("Failed to get app instance id", e);
            } finally {
                this.zziwg.notify();
            }
        }
    }
}
