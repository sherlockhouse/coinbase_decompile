package com.google.android.gms.internal;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

final class zzceh implements Callable<String> {
    private /* synthetic */ zzcdw zziuy;

    zzceh(zzcdw com_google_android_gms_internal_zzcdw) {
        this.zziuy = com_google_android_gms_internal_zzcdw;
    }

    public final /* synthetic */ Object call() throws Exception {
        Object zzayn = this.zziuy.zzaum().zzayn();
        if (zzayn == null) {
            zzcdt zzatz = this.zziuy.zzatz();
            if (zzatz.zzauk().zzays()) {
                zzatz.zzaul().zzayd().log("Cannot retrieve app instance id from analytics worker thread");
                zzayn = null;
            } else {
                zzatz.zzauk();
                if (zzccr.zzaq()) {
                    zzatz.zzaul().zzayd().log("Cannot retrieve app instance id from main thread");
                    zzayn = null;
                } else {
                    long elapsedRealtime = zzatz.zzvx().elapsedRealtime();
                    zzayn = zzatz.zzbc(120000);
                    elapsedRealtime = zzatz.zzvx().elapsedRealtime() - elapsedRealtime;
                    if (zzayn == null && elapsedRealtime < 120000) {
                        zzayn = zzatz.zzbc(120000 - elapsedRealtime);
                    }
                }
            }
            if (zzayn == null) {
                throw new TimeoutException();
            }
            this.zziuy.zzaum().zzjk(zzayn);
        }
        return zzayn;
    }
}
