package com.google.android.gms.internal;

final class zzcdm implements Runnable {
    private /* synthetic */ String zziah;
    private /* synthetic */ zzcdb zziuk;
    private /* synthetic */ zzcbk zziuo;

    zzcdm(zzcdb com_google_android_gms_internal_zzcdb, zzcbk com_google_android_gms_internal_zzcbk, String str) {
        this.zziuk = com_google_android_gms_internal_zzcdb;
        this.zziuo = com_google_android_gms_internal_zzcbk;
        this.zziah = str;
    }

    public final void run() {
        this.zziuk.zziki.zzazk();
        this.zziuk.zziki.zzb(this.zziuo, this.zziah);
    }
}
