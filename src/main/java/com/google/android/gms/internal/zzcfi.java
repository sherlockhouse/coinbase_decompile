package com.google.android.gms.internal;

import android.app.job.JobParameters;

final class zzcfi implements Runnable {
    final /* synthetic */ Integer zzdtz;
    final /* synthetic */ JobParameters zzduc;
    private /* synthetic */ zzccw zzirr;
    final /* synthetic */ zzcbw zziru;
    final /* synthetic */ zzcfh zziwr;

    zzcfi(zzcfh com_google_android_gms_internal_zzcfh, zzccw com_google_android_gms_internal_zzccw, Integer num, zzcbw com_google_android_gms_internal_zzcbw, JobParameters jobParameters) {
        this.zziwr = com_google_android_gms_internal_zzcfh;
        this.zzirr = com_google_android_gms_internal_zzccw;
        this.zzdtz = num;
        this.zziru = com_google_android_gms_internal_zzcbw;
        this.zzduc = jobParameters;
    }

    public final void run() {
        this.zzirr.zzazk();
        this.zzirr.zzi(new zzcfj(this));
        this.zzirr.zzazg();
    }
}
