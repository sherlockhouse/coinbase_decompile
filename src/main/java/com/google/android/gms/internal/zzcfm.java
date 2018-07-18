package com.google.android.gms.internal;

import android.os.Bundle;

final class zzcfm extends zzcbc {
    private /* synthetic */ zzcfl zziww;

    zzcfm(zzcfl com_google_android_gms_internal_zzcfl, zzccw com_google_android_gms_internal_zzccw) {
        this.zziww = com_google_android_gms_internal_zzcfl;
        super(com_google_android_gms_internal_zzccw);
    }

    public final void run() {
        zzcdt com_google_android_gms_internal_zzcdt = this.zziww;
        com_google_android_gms_internal_zzcdt.zzuj();
        com_google_android_gms_internal_zzcdt.zzaul().zzayj().zzj("Session started, time", Long.valueOf(com_google_android_gms_internal_zzcdt.zzvx().elapsedRealtime()));
        com_google_android_gms_internal_zzcdt.zzaum().zzire.set(false);
        com_google_android_gms_internal_zzcdt.zzatz().zzc("auto", "_s", new Bundle());
        com_google_android_gms_internal_zzcdt.zzaum().zzirf.set(com_google_android_gms_internal_zzcdt.zzvx().currentTimeMillis());
    }
}
