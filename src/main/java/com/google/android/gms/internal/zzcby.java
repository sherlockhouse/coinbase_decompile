package com.google.android.gms.internal;

public final class zzcby {
    private final int mPriority;
    private /* synthetic */ zzcbw zziqc;
    private final boolean zziqd;
    private final boolean zziqe;

    zzcby(zzcbw com_google_android_gms_internal_zzcbw, int i, boolean z, boolean z2) {
        this.zziqc = com_google_android_gms_internal_zzcbw;
        this.mPriority = i;
        this.zziqd = z;
        this.zziqe = z2;
    }

    public final void log(String str) {
        this.zziqc.zza(this.mPriority, this.zziqd, this.zziqe, str, null, null, null);
    }

    public final void zzd(String str, Object obj, Object obj2, Object obj3) {
        this.zziqc.zza(this.mPriority, this.zziqd, this.zziqe, str, obj, obj2, obj3);
    }

    public final void zze(String str, Object obj, Object obj2) {
        this.zziqc.zza(this.mPriority, this.zziqd, this.zziqe, str, obj, obj2, null);
    }

    public final void zzj(String str, Object obj) {
        this.zziqc.zza(this.mPriority, this.zziqd, this.zziqe, str, obj, null, null);
    }
}
