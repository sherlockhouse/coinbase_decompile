package com.google.android.gms.internal;

final class zzcdp implements Runnable {
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ zzcdb zziuk;
    private /* synthetic */ zzcft zziup;

    zzcdp(zzcdb com_google_android_gms_internal_zzcdb, zzcft com_google_android_gms_internal_zzcft, zzcas com_google_android_gms_internal_zzcas) {
        this.zziuk = com_google_android_gms_internal_zzcdb;
        this.zziup = com_google_android_gms_internal_zzcft;
        this.zziuj = com_google_android_gms_internal_zzcas;
    }

    public final void run() {
        this.zziuk.zziki.zzazk();
        this.zziuk.zziki.zzb(this.zziup, this.zziuj);
    }
}
