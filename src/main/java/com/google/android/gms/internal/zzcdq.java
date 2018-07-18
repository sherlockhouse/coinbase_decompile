package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzcdq implements Callable<List<zzcfv>> {
    private /* synthetic */ zzcas zziuj;
    private /* synthetic */ zzcdb zziuk;

    zzcdq(zzcdb com_google_android_gms_internal_zzcdb, zzcas com_google_android_gms_internal_zzcas) {
        this.zziuk = com_google_android_gms_internal_zzcdb;
        this.zziuj = com_google_android_gms_internal_zzcas;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zziuk.zziki.zzazk();
        return this.zziuk.zziki.zzauf().zziv(this.zziuj.packageName);
    }
}
