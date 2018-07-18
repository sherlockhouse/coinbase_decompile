package com.google.android.gms.measurement;

import android.app.Service;
import android.app.job.JobParameters;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.internal.zzcfh;
import com.google.android.gms.internal.zzcfk;

public final class AppMeasurementService extends Service implements zzcfk {
    private zzcfh zzikt;

    private final zzcfh zzatt() {
        if (this.zzikt == null) {
            this.zzikt = new zzcfh(this);
        }
        return this.zzikt;
    }

    public final boolean callServiceStopSelfResult(int i) {
        return stopSelfResult(i);
    }

    public final IBinder onBind(Intent intent) {
        return zzatt().onBind(intent);
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

    public final int onStartCommand(Intent intent, int i, int i2) {
        zzatt().onStartCommand(intent, i, i2);
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
        return 2;
    }

    public final boolean onUnbind(Intent intent) {
        return zzatt().onUnbind(intent);
    }

    public final void zza(JobParameters jobParameters, boolean z) {
        throw new UnsupportedOperationException();
    }
}
