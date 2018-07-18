package com.google.android.gms.internal;

import android.os.Handler;
import com.google.android.gms.common.internal.zzbp;

abstract class zzcbc {
    private static volatile Handler zzdqs;
    private volatile long zzdqt;
    private final zzccw zziki;
    private boolean zzine = true;
    private final Runnable zzv = new zzcbd(this);

    zzcbc(zzccw com_google_android_gms_internal_zzccw) {
        zzbp.zzu(com_google_android_gms_internal_zzccw);
        this.zziki = com_google_android_gms_internal_zzccw;
    }

    private final Handler getHandler() {
        if (zzdqs != null) {
            return zzdqs;
        }
        Handler handler;
        synchronized (zzcbc.class) {
            if (zzdqs == null) {
                zzdqs = new Handler(this.zziki.getContext().getMainLooper());
            }
            handler = zzdqs;
        }
        return handler;
    }

    public final void cancel() {
        this.zzdqt = 0;
        getHandler().removeCallbacks(this.zzv);
    }

    public abstract void run();

    public final boolean zzdp() {
        return this.zzdqt != 0;
    }

    public final void zzs(long j) {
        cancel();
        if (j >= 0) {
            this.zzdqt = this.zziki.zzvx().currentTimeMillis();
            if (!getHandler().postDelayed(this.zzv, j)) {
                this.zziki.zzaul().zzayd().zzj("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }
}
