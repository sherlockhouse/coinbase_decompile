package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;

final class zzcco implements Runnable {
    private /* synthetic */ Context zzaoa;
    private /* synthetic */ zzccw zzirr;
    private /* synthetic */ long zzirs;
    private /* synthetic */ Bundle zzirt;
    private /* synthetic */ zzcbw zziru;

    zzcco(zzccn com_google_android_gms_internal_zzccn, zzccw com_google_android_gms_internal_zzccw, long j, Bundle bundle, Context context, zzcbw com_google_android_gms_internal_zzcbw) {
        this.zzirr = com_google_android_gms_internal_zzccw;
        this.zzirs = j;
        this.zzirt = bundle;
        this.zzaoa = context;
        this.zziru = com_google_android_gms_internal_zzcbw;
    }

    public final void run() {
        zzcfv zzah = this.zzirr.zzauf().zzah(this.zzirr.zzaua().getAppId(), "_fot");
        long longValue = (zzah == null || !(zzah.mValue instanceof Long)) ? 0 : ((Long) zzah.mValue).longValue();
        long j = this.zzirs;
        longValue = (longValue <= 0 || (j < longValue && j > 0)) ? j : longValue - 1;
        if (longValue > 0) {
            this.zzirt.putLong("click_timestamp", longValue);
        }
        AppMeasurement.getInstance(this.zzaoa).logEventInternal("auto", "_cmp", this.zzirt);
        this.zziru.zzayj().log("Install campaign recorded");
    }
}
