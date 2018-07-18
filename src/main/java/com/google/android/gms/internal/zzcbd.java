package com.google.android.gms.internal;

import android.os.Looper;

final class zzcbd implements Runnable {
    private /* synthetic */ zzcbc zzinf;

    zzcbd(zzcbc com_google_android_gms_internal_zzcbc) {
        this.zzinf = com_google_android_gms_internal_zzcbc;
    }

    public final void run() {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.zzinf.zziki.zzauk().zzg(this);
            return;
        }
        boolean zzdp = this.zzinf.zzdp();
        this.zzinf.zzdqt = 0;
        if (zzdp && this.zzinf.zzine) {
            this.zzinf.run();
        }
    }
}
