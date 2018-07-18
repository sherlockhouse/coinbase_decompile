package com.google.android.gms.internal;

final class zzcdd implements Runnable {
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ zzcdb zziuk;
    private /* synthetic */ zzcav zziul;

    zzcdd(zzcdb com_google_android_gms_internal_zzcdb, zzcav com_google_android_gms_internal_zzcav, zzcas com_google_android_gms_internal_zzcas) {
        this.zziuk = com_google_android_gms_internal_zzcdb;
        this.zziul = com_google_android_gms_internal_zzcav;
        this.zziuj = com_google_android_gms_internal_zzcas;
    }

    public final void run() {
        this.zziuk.zziki.zzazk();
        this.zziuk.zziki.zzc(this.zziul, this.zziuj);
    }
}
