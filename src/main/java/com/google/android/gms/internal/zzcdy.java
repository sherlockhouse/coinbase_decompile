package com.google.android.gms.internal;

import com.google.android.gms.measurement.AppMeasurement.ConditionalUserProperty;

final class zzcdy implements Runnable {
    private /* synthetic */ zzcdw zziuy;
    private /* synthetic */ ConditionalUserProperty zziuz;

    zzcdy(zzcdw com_google_android_gms_internal_zzcdw, ConditionalUserProperty conditionalUserProperty) {
        this.zziuy = com_google_android_gms_internal_zzcdw;
        this.zziuz = conditionalUserProperty;
    }

    public final void run() {
        this.zziuy.zzb(this.zziuz);
    }
}
