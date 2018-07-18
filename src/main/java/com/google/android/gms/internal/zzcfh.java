package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import com.coinbase.api.internal.ApiConstants;
import com.google.android.gms.common.internal.zzbp;

public final class zzcfh<T extends Context & zzcfk> {
    private final T zzdtx;

    public zzcfh(T t) {
        zzbp.zzu(t);
        this.zzdtx = t;
    }

    private final void zza(Integer num, JobParameters jobParameters) {
        zzccw zzdn = zzccw.zzdn(this.zzdtx);
        zzdn.zzauk().zzg(new zzcfi(this, zzdn, num, zzdn.zzaul(), jobParameters));
    }

    private final zzcbw zzaul() {
        return zzccw.zzdn(this.zzdtx).zzaul();
    }

    public static boolean zzk(Context context, boolean z) {
        zzbp.zzu(context);
        return VERSION.SDK_INT >= 24 ? zzcfw.zzv(context, "com.google.android.gms.measurement.AppMeasurementJobService") : zzcfw.zzv(context, "com.google.android.gms.measurement.AppMeasurementService");
    }

    public final IBinder onBind(Intent intent) {
        if (intent == null) {
            zzaul().zzayd().log("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzcdb(zzccw.zzdn(this.zzdtx));
        }
        zzaul().zzayf().zzj("onBind received unknown action", action);
        return null;
    }

    public final void onCreate() {
        zzcbw zzaul = zzccw.zzdn(this.zzdtx).zzaul();
        zzcax.zzawk();
        zzaul.zzayj().log("Local AppMeasurementService is starting up");
    }

    public final void onDestroy() {
        zzcbw zzaul = zzccw.zzdn(this.zzdtx).zzaul();
        zzcax.zzawk();
        zzaul.zzayj().log("Local AppMeasurementService is shutting down");
    }

    public final void onRebind(Intent intent) {
        if (intent == null) {
            zzaul().zzayd().log("onRebind called with null intent");
            return;
        }
        zzaul().zzayj().zzj("onRebind called. action", intent.getAction());
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        zzcbw zzaul = zzccw.zzdn(this.zzdtx).zzaul();
        if (intent == null) {
            zzaul.zzayf().log("AppMeasurementService started with null intent");
        } else {
            String action = intent.getAction();
            zzcax.zzawk();
            zzaul.zzayj().zze("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
            if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
                zza(Integer.valueOf(i2), null);
            }
        }
        return 2;
    }

    @TargetApi(24)
    public final boolean onStartJob(JobParameters jobParameters) {
        zzcbw zzaul = zzccw.zzdn(this.zzdtx).zzaul();
        String string = jobParameters.getExtras().getString(ApiConstants.ACTION);
        zzcax.zzawk();
        zzaul.zzayj().zzj("Local AppMeasurementJobService called. action", string);
        if ("com.google.android.gms.measurement.UPLOAD".equals(string)) {
            zza(null, jobParameters);
        }
        return true;
    }

    public final boolean onUnbind(Intent intent) {
        if (intent == null) {
            zzaul().zzayd().log("onUnbind called with null intent");
        } else {
            zzaul().zzayj().zzj("onUnbind called for intent. action", intent.getAction());
        }
        return true;
    }
}
