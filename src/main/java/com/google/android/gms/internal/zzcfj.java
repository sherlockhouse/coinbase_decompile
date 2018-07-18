package com.google.android.gms.internal;

import android.os.Build.VERSION;

final class zzcfj implements Runnable {
    private /* synthetic */ zzcfi zziws;

    zzcfj(zzcfi com_google_android_gms_internal_zzcfi) {
        this.zziws = com_google_android_gms_internal_zzcfi;
    }

    public final void run() {
        if (this.zziws.zzdtz == null) {
            zzcax.zzawk();
            if (VERSION.SDK_INT >= 24) {
                this.zziws.zziru.zzayj().log("AppMeasurementJobService processed last upload request.");
                ((zzcfk) this.zziws.zziwr.zzdtx).zza(this.zziws.zzduc, false);
            }
        } else if (((zzcfk) this.zziws.zziwr.zzdtx).callServiceStopSelfResult(this.zziws.zzdtz.intValue())) {
            zzcax.zzawk();
            this.zziws.zziru.zzayj().zzj("Local AppMeasurementService processed last upload request. StartId", this.zziws.zzdtz);
        }
    }
}
