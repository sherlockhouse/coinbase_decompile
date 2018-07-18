package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.internal.zzccn;
import com.google.android.gms.internal.zzccp;

public final class AppMeasurementInstallReferrerReceiver extends BroadcastReceiver implements zzccp {
    private zzccn zziks;

    public final void doStartService(Context context, Intent intent) {
    }

    public final void onReceive(Context context, Intent intent) {
        if (this.zziks == null) {
            this.zziks = new zzccn(this);
        }
        this.zziks.onReceive(context, intent);
    }
}
