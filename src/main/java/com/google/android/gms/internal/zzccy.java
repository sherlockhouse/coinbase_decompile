package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzccy implements Callable<String> {
    private /* synthetic */ String zziah;
    private /* synthetic */ zzccw zziud;

    zzccy(zzccw com_google_android_gms_internal_zzccw, String str) {
        this.zziud = com_google_android_gms_internal_zzccw;
        this.zziah = str;
    }

    public final /* synthetic */ Object call() throws Exception {
        zzcar zziw = this.zziud.zzauf().zziw(this.zziah);
        if (zziw != null) {
            return zziw.getAppInstanceId();
        }
        this.zziud.zzaul().zzayf().log("App info was null when attempting to get app instance id");
        return null;
    }
}
