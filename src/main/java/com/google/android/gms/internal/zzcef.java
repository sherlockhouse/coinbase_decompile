package com.google.android.gms.internal;

final class zzcef implements Runnable {
    private /* synthetic */ String val$name;
    private /* synthetic */ String zzium;
    private /* synthetic */ zzcdw zziuy;
    private /* synthetic */ long zzivd;
    private /* synthetic */ Object zzivi;

    zzcef(zzcdw com_google_android_gms_internal_zzcdw, String str, String str2, Object obj, long j) {
        this.zziuy = com_google_android_gms_internal_zzcdw;
        this.zzium = str;
        this.val$name = str2;
        this.zzivi = obj;
        this.zzivd = j;
    }

    public final void run() {
        this.zziuy.zza(this.zzium, this.val$name, this.zzivi, this.zzivd);
    }
}
