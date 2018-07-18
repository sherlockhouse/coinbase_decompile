package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzcdn implements Callable<byte[]> {
    private /* synthetic */ String zziah;
    private /* synthetic */ zzcdb zziuk;
    private /* synthetic */ zzcbk zziuo;

    zzcdn(zzcdb com_google_android_gms_internal_zzcdb, zzcbk com_google_android_gms_internal_zzcbk, String str) {
        this.zziuk = com_google_android_gms_internal_zzcdb;
        this.zziuo = com_google_android_gms_internal_zzcbk;
        this.zziah = str;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zziuk.zziki.zzazk();
        return this.zziuk.zziki.zza(this.zziuo, this.zziah);
    }
}
