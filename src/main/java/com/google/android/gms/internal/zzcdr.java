package com.google.android.gms.internal;

final class zzcdr implements Runnable {
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ zzcdb zziuk;

    zzcdr(zzcdb com_google_android_gms_internal_zzcdb, zzcas com_google_android_gms_internal_zzcas) {
        this.zziuk = com_google_android_gms_internal_zzcdb;
        this.zziuj = com_google_android_gms_internal_zzcas;
    }

    public final void run() {
        this.zziuk.zziki.zzazk();
        this.zziuk.zziki.zze(this.zziuj);
    }
}
