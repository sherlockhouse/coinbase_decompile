package com.google.android.gms.measurement;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.google.android.gms.internal.zzccn;
import com.google.android.gms.internal.zzccp;

public final class AppMeasurementReceiver extends WakefulBroadcastReceiver implements zzccp {
    private zzccn zziks;

    public final void doStartService(Context context, Intent intent) {
        WakefulBroadcastReceiver.startWakefulService(context, intent);
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.zziks == null) {
            this.zziks = new zzccn(this);
        }
        this.zziks.onReceive(context, intent);
    }
}
