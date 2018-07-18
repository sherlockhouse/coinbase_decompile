package com.google.android.gms.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

final class zzcex implements Runnable {
    private /* synthetic */ String zziah;
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ String zzium;
    private /* synthetic */ String zziun;
    private /* synthetic */ zzceo zziwf;
    private /* synthetic */ AtomicReference zziwg;

    zzcex(zzceo com_google_android_gms_internal_zzceo, AtomicReference atomicReference, String str, String str2, String str3, zzcas com_google_android_gms_internal_zzcas) {
        this.zziwf = com_google_android_gms_internal_zzceo;
        this.zziwg = atomicReference;
        this.zziah = str;
        this.zzium = str2;
        this.zziun = str3;
        this.zziuj = com_google_android_gms_internal_zzcas;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        synchronized (this.zziwg) {
            try {
                zzcbo zzd = this.zziwf.zzivz;
                if (zzd == null) {
                    this.zziwf.zzaul().zzayd().zzd("Failed to get conditional properties", zzcbw.zzjf(this.zziah), this.zzium, this.zziun);
                    this.zziwg.set(Collections.emptyList());
                } else {
                    if (TextUtils.isEmpty(this.zziah)) {
                        this.zziwg.set(zzd.zza(this.zzium, this.zziun, this.zziuj));
                    } else {
                        this.zziwg.set(zzd.zzj(this.zziah, this.zzium, this.zziun));
                    }
                    this.zziwf.zzww();
                    this.zziwg.notify();
                }
            } catch (RemoteException e) {
                this.zziwf.zzaul().zzayd().zzd("Failed to get conditional properties", zzcbw.zzjf(this.zziah), this.zzium, e);
                this.zziwg.set(Collections.emptyList());
            } finally {
                this.zziwg.notify();
            }
        }
    }
}
