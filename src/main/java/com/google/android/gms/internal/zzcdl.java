package com.google.android.gms.internal;

final class zzcdl implements Runnable {
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ zzcdb zziuk;
    private /* synthetic */ zzcbk zziuo;

    zzcdl(zzcdb com_google_android_gms_internal_zzcdb, zzcbk com_google_android_gms_internal_zzcbk, zzcas com_google_android_gms_internal_zzcas) {
        this.zziuk = com_google_android_gms_internal_zzcdb;
        this.zziuo = com_google_android_gms_internal_zzcbk;
        this.zziuj = com_google_android_gms_internal_zzcas;
    }

    public final void run() {
        this.zziuk.zziki.zzazk();
        this.zziuk.zziki.zzb(this.zziuo, this.zziuj);
    }
}
