package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzcdj implements Callable<List<zzcav>> {
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ zzcdb zziuk;
    private /* synthetic */ String zzium;
    private /* synthetic */ String zziun;

    zzcdj(zzcdb com_google_android_gms_internal_zzcdb, zzcas com_google_android_gms_internal_zzcas, String str, String str2) {
        this.zziuk = com_google_android_gms_internal_zzcdb;
        this.zziuj = com_google_android_gms_internal_zzcas;
        this.zzium = str;
        this.zziun = str2;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zziuk.zziki.zzazk();
        return this.zziuk.zziki.zzauf().zzh(this.zziuj.packageName, this.zzium, this.zziun);
    }
}
