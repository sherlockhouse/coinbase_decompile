package com.google.android.gms.internal;

final class zzcdx implements Runnable {
    private /* synthetic */ boolean val$enabled;
    private /* synthetic */ zzcdw zziuy;

    zzcdx(zzcdw com_google_android_gms_internal_zzcdw, boolean z) {
        this.zziuy = com_google_android_gms_internal_zzcdw;
        this.val$enabled = z;
    }

    public final void run() {
        this.zziuy.zzbp(this.val$enabled);
    }
}
