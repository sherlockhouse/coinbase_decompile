package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import com.coinbase.api.internal.ApiConstants;
import com.google.android.gms.common.util.zzd;

public final class zzcfr extends zzcdu {
    private final AlarmManager zzdqx = ((AlarmManager) getContext().getSystemService("alarm"));
    private Integer zzdqy;
    private final zzcbc zziwx;

    protected zzcfr(zzccw com_google_android_gms_internal_zzccw) {
        super(com_google_android_gms_internal_zzccw);
        this.zziwx = new zzcfs(this, com_google_android_gms_internal_zzccw);
    }

    private final int getJobId() {
        if (this.zzdqy == null) {
            String str = "measurement";
            String valueOf = String.valueOf(getContext().getPackageName());
            this.zzdqy = Integer.valueOf((valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).hashCode());
        }
        return this.zzdqy.intValue();
    }

    @TargetApi(24)
    private final void zzazv() {
        JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
        zzaul().zzayj().zzj("Cancelling job. JobID", Integer.valueOf(getJobId()));
        jobScheduler.cancel(getJobId());
    }

    private final void zzazw() {
        Intent intent = new Intent();
        Context context = getContext();
        zzcax.zzawk();
        intent = intent.setClassName(context, "com.google.android.gms.measurement.AppMeasurementReceiver");
        intent.setAction("com.google.android.gms.measurement.UPLOAD");
        getContext().sendBroadcast(intent);
    }

    private final PendingIntent zzyk() {
        Intent intent = new Intent();
        Context context = getContext();
        zzcax.zzawk();
        intent = intent.setClassName(context, "com.google.android.gms.measurement.AppMeasurementReceiver");
        intent.setAction("com.google.android.gms.measurement.UPLOAD");
        return PendingIntent.getBroadcast(getContext(), 0, intent, 0);
    }

    public final void cancel() {
        zzwk();
        this.zzdqx.cancel(zzyk());
        this.zziwx.cancel();
        if (VERSION.SDK_INT >= 24) {
            zzazv();
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ void zzatu() {
        super.zzatu();
    }

    public final /* bridge */ /* synthetic */ void zzatv() {
        super.zzatv();
    }

    public final /* bridge */ /* synthetic */ void zzatw() {
        super.zzatw();
    }

    public final /* bridge */ /* synthetic */ zzcan zzatx() {
        return super.zzatx();
    }

    public final /* bridge */ /* synthetic */ zzcau zzaty() {
        return super.zzaty();
    }

    public final /* bridge */ /* synthetic */ zzcdw zzatz() {
        return super.zzatz();
    }

    public final /* bridge */ /* synthetic */ zzcbr zzaua() {
        return super.zzaua();
    }

    public final /* bridge */ /* synthetic */ zzcbe zzaub() {
        return super.zzaub();
    }

    public final /* bridge */ /* synthetic */ zzceo zzauc() {
        return super.zzauc();
    }

    public final /* bridge */ /* synthetic */ zzcek zzaud() {
        return super.zzaud();
    }

    public final /* bridge */ /* synthetic */ zzcbs zzaue() {
        return super.zzaue();
    }

    public final /* bridge */ /* synthetic */ zzcay zzauf() {
        return super.zzauf();
    }

    public final /* bridge */ /* synthetic */ zzcbu zzaug() {
        return super.zzaug();
    }

    public final /* bridge */ /* synthetic */ zzcfw zzauh() {
        return super.zzauh();
    }

    public final /* bridge */ /* synthetic */ zzccq zzaui() {
        return super.zzaui();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzauj() {
        return super.zzauj();
    }

    public final /* bridge */ /* synthetic */ zzccr zzauk() {
        return super.zzauk();
    }

    public final /* bridge */ /* synthetic */ zzcbw zzaul() {
        return super.zzaul();
    }

    public final /* bridge */ /* synthetic */ zzcch zzaum() {
        return super.zzaum();
    }

    public final /* bridge */ /* synthetic */ zzcax zzaun() {
        return super.zzaun();
    }

    public final void zzs(long j) {
        zzwk();
        zzcax.zzawk();
        if (!zzccn.zzj(getContext(), false)) {
            zzaul().zzayi().log("Receiver not registered/enabled");
        }
        zzcax.zzawk();
        if (!zzcfh.zzk(getContext(), false)) {
            zzaul().zzayi().log("Service not registered/enabled");
        }
        cancel();
        long elapsedRealtime = zzvx().elapsedRealtime() + j;
        if (j < zzcax.zzaxb() && !this.zziwx.zzdp()) {
            zzaul().zzayj().log("Scheduling upload with DelayedRunnable");
            this.zziwx.zzs(j);
        }
        zzcax.zzawk();
        if (VERSION.SDK_INT >= 24) {
            zzaul().zzayj().log("Scheduling upload with JobScheduler");
            JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
            Builder builder = new Builder(getJobId(), new ComponentName(getContext(), "com.google.android.gms.measurement.AppMeasurementJobService"));
            builder.setMinimumLatency(j);
            builder.setOverrideDeadline(j << 1);
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString(ApiConstants.ACTION, "com.google.android.gms.measurement.UPLOAD");
            builder.setExtras(persistableBundle);
            JobInfo build = builder.build();
            zzaul().zzayj().zzj("Scheduling job. JobID", Integer.valueOf(getJobId()));
            jobScheduler.schedule(build);
            return;
        }
        zzaul().zzayj().log("Scheduling upload with AlarmManager");
        this.zzdqx.setInexactRepeating(2, elapsedRealtime, Math.max(zzcax.zzaxc(), j), zzyk());
    }

    public final /* bridge */ /* synthetic */ void zzuj() {
        super.zzuj();
    }

    protected final void zzuk() {
        this.zzdqx.cancel(zzyk());
        if (VERSION.SDK_INT >= 24) {
            zzazv();
        }
    }

    public final /* bridge */ /* synthetic */ zzd zzvx() {
        return super.zzvx();
    }
}
