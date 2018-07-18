package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.zzcqf;

final class zzcx implements Runnable {
    private /* synthetic */ zzcqf zzfmf;
    private /* synthetic */ zzcw zzfpf;

    zzcx(zzcw com_google_android_gms_common_api_internal_zzcw, zzcqf com_google_android_gms_internal_zzcqf) {
        this.zzfpf = com_google_android_gms_common_api_internal_zzcw;
        this.zzfmf = com_google_android_gms_internal_zzcqf;
    }

    public final void run() {
        this.zzfpf.zzc(this.zzfmf);
    }
}
