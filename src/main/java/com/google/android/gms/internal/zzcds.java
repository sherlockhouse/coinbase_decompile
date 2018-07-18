package com.google.android.gms.internal;

import com.google.android.gms.measurement.AppMeasurement.zzb;

final class zzcds implements Runnable {
    private /* synthetic */ String zziah;
    private /* synthetic */ zzcdb zziuk;
    private /* synthetic */ String zziuq;
    private /* synthetic */ String zziur;
    private /* synthetic */ long zzius;

    zzcds(zzcdb com_google_android_gms_internal_zzcdb, String str, String str2, String str3, long j) {
        this.zziuk = com_google_android_gms_internal_zzcdb;
        this.zziuq = str;
        this.zziah = str2;
        this.zziur = str3;
        this.zzius = j;
    }

    public final void run() {
        if (this.zziuq == null) {
            this.zziuk.zziki.zzaud().zza(this.zziah, null);
            return;
        }
        zzb com_google_android_gms_measurement_AppMeasurement_zzb = new zzb();
        com_google_android_gms_measurement_AppMeasurement_zzb.zzikn = this.zziur;
        com_google_android_gms_measurement_AppMeasurement_zzb.zziko = this.zziuq;
        com_google_android_gms_measurement_AppMeasurement_zzb.zzikp = this.zzius;
        this.zziuk.zziki.zzaud().zza(this.zziah, com_google_android_gms_measurement_AppMeasurement_zzb);
    }
}
