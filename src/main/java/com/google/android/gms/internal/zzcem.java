package com.google.android.gms.internal;

final class zzcem implements Runnable {
    private /* synthetic */ zzcek zzivv;
    private /* synthetic */ zzcen zzivw;

    zzcem(zzcek com_google_android_gms_internal_zzcek, zzcen com_google_android_gms_internal_zzcen) {
        this.zzivv = com_google_android_gms_internal_zzcek;
        this.zzivw = com_google_android_gms_internal_zzcen;
    }

    public final void run() {
        this.zzivv.zza(this.zzivw);
        this.zzivv.zzivj = null;
        this.zzivv.zzauc().zza(null);
    }
}
