package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;

final class zzcfq {
    private long mStartTime;
    private final zzd zzasc;

    public zzcfq(zzd com_google_android_gms_common_util_zzd) {
        zzbp.zzu(com_google_android_gms_common_util_zzd);
        this.zzasc = com_google_android_gms_common_util_zzd;
    }

    public final void clear() {
        this.mStartTime = 0;
    }

    public final void start() {
        this.mStartTime = this.zzasc.elapsedRealtime();
    }

    public final boolean zzu(long j) {
        return this.mStartTime == 0 || this.zzasc.elapsedRealtime() - this.mStartTime >= j;
    }
}
