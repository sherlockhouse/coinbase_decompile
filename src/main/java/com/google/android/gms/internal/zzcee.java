package com.google.android.gms.internal;

import android.os.Bundle;

final class zzcee implements Runnable {
    private /* synthetic */ String val$name;
    private /* synthetic */ String zziah;
    private /* synthetic */ String zzium;
    private /* synthetic */ zzcdw zziuy;
    private /* synthetic */ long zzivd;
    private /* synthetic */ Bundle zzive;
    private /* synthetic */ boolean zzivf;
    private /* synthetic */ boolean zzivg;
    private /* synthetic */ boolean zzivh;

    zzcee(zzcdw com_google_android_gms_internal_zzcdw, String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        this.zziuy = com_google_android_gms_internal_zzcdw;
        this.zzium = str;
        this.val$name = str2;
        this.zzivd = j;
        this.zzive = bundle;
        this.zzivf = z;
        this.zzivg = z2;
        this.zzivh = z3;
        this.zziah = str3;
    }

    public final void run() {
        this.zziuy.zzb(this.zzium, this.val$name, this.zzivd, this.zzive, this.zzivf, this.zzivg, this.zzivh, this.zziah);
    }
}
