package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.measurement.AppMeasurement.zzb;

final class zzces implements Runnable {
    private /* synthetic */ zzceo zziwf;
    private /* synthetic */ zzb zziwh;

    zzces(zzceo com_google_android_gms_internal_zzceo, zzb com_google_android_gms_measurement_AppMeasurement_zzb) {
        this.zziwf = com_google_android_gms_internal_zzceo;
        this.zziwh = com_google_android_gms_measurement_AppMeasurement_zzb;
    }

    public final void run() {
        zzcbo zzd = this.zziwf.zzivz;
        if (zzd == null) {
            this.zziwf.zzaul().zzayd().log("Failed to send current screen to service");
            return;
        }
        try {
            if (this.zziwh == null) {
                zzd.zza(0, null, null, this.zziwf.getContext().getPackageName());
            } else {
                zzd.zza(this.zziwh.zzikp, this.zziwh.zzikn, this.zziwh.zziko, this.zziwf.getContext().getPackageName());
            }
            this.zziwf.zzww();
        } catch (RemoteException e) {
            this.zziwf.zzaul().zzayd().zzj("Failed to send current screen to the service", e);
        }
    }
}
