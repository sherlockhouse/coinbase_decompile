package com.google.android.gms.internal;

import android.content.ComponentName;
import android.content.Context;

final class zzcff implements Runnable {
    private /* synthetic */ zzcfb zziwp;

    zzcff(zzcfb com_google_android_gms_internal_zzcfb) {
        this.zziwp = com_google_android_gms_internal_zzcfb;
    }

    public final void run() {
        zzceo com_google_android_gms_internal_zzceo = this.zziwp.zziwf;
        Context context = this.zziwp.zziwf.getContext();
        zzcax.zzawk();
        com_google_android_gms_internal_zzceo.onServiceDisconnected(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
