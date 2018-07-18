package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import com.google.android.gms.internal.zzcfh;
import com.google.android.gms.internal.zzcfk;

@TargetApi(24)
public final class AppMeasurementJobService extends JobService implements zzcfk {
    private zzcfh zzikt;

    private final zzcfh zzatt() {
        if (this.zzikt == null) {
            this.zzikt = new zzcfh(this);
        }
        return this.zzikt;
    }

    public final boolean callServiceStopSelfResult(int i) {
        throw new UnsupportedOperationException();
    }

    public final void onCreate() {
        super.onCreate();
        zzatt().onCreate();
    }

    public final void onDestroy() {
        zzatt().onDestroy();
        super.onDestroy();
    }

    public final void onRebind(Intent intent) {
        zzatt().onRebind(intent);
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        return zzatt().onStartJob(jobParameters);
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public final boolean onUnbind(Intent intent) {
        return zzatt().onUnbind(intent);
    }

    @TargetApi(24)
    public final void zza(JobParameters jobParameters, boolean z) {
        jobFinished(jobParameters, false);
    }
}
