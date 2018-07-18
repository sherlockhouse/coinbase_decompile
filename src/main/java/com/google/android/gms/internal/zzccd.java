package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbp;
import java.util.List;
import java.util.Map;

final class zzccd implements Runnable {
    private final String mPackageName;
    private final int zzbyz;
    private final Throwable zzdfp;
    private final zzccc zziqf;
    private final byte[] zziqg;
    private final Map<String, List<String>> zziqh;

    private zzccd(String str, zzccc com_google_android_gms_internal_zzccc, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        zzbp.zzu(com_google_android_gms_internal_zzccc);
        this.zziqf = com_google_android_gms_internal_zzccc;
        this.zzbyz = i;
        this.zzdfp = th;
        this.zziqg = bArr;
        this.mPackageName = str;
        this.zziqh = map;
    }

    public final void run() {
        this.zziqf.zza(this.mPackageName, this.zzbyz, this.zzdfp, this.zziqg, this.zziqh);
    }
}
