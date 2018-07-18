package com.google.android.gms.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement.zzb;

final class zzcel implements Runnable {
    private /* synthetic */ boolean zzivs;
    private /* synthetic */ zzb zzivt;
    private /* synthetic */ zzcen zzivu;
    private /* synthetic */ zzcek zzivv;

    zzcel(zzcek com_google_android_gms_internal_zzcek, boolean z, zzb com_google_android_gms_measurement_AppMeasurement_zzb, zzcen com_google_android_gms_internal_zzcen) {
        this.zzivv = com_google_android_gms_internal_zzcek;
        this.zzivs = z;
        this.zzivt = com_google_android_gms_measurement_AppMeasurement_zzb;
        this.zzivu = com_google_android_gms_internal_zzcen;
    }

    public final void run() {
        if (this.zzivs && this.zzivv.zzivj != null) {
            this.zzivv.zza(this.zzivv.zzivj);
        }
        Object obj = (this.zzivt != null && this.zzivt.zzikp == this.zzivu.zzikp && zzcfw.zzas(this.zzivt.zziko, this.zzivu.zziko) && zzcfw.zzas(this.zzivt.zzikn, this.zzivu.zzikn)) ? null : 1;
        if (obj != null) {
            Bundle bundle = new Bundle();
            zzcek.zza(this.zzivu, bundle);
            if (this.zzivt != null) {
                if (this.zzivt.zzikn != null) {
                    bundle.putString("_pn", this.zzivt.zzikn);
                }
                bundle.putString("_pc", this.zzivt.zziko);
                bundle.putLong("_pi", this.zzivt.zzikp);
            }
            this.zzivv.zzatz().zzc("auto", "_vs", bundle);
        }
        this.zzivv.zzivj = this.zzivu;
        this.zzivv.zzauc().zza(this.zzivu);
    }
}
