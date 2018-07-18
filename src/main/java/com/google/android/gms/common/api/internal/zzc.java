package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zzc<A extends zzm<? extends Result, zzb>> extends zza {
    private A zzfic;

    public zzc(int i, A a) {
        super(i);
        this.zzfic = a;
    }

    public final void zza(zzah com_google_android_gms_common_api_internal_zzah, boolean z) {
        com_google_android_gms_common_api_internal_zzah.zza(this.zzfic, z);
    }

    public final void zza(zzbr<?> com_google_android_gms_common_api_internal_zzbr_) throws DeadObjectException {
        this.zzfic.zzb(com_google_android_gms_common_api_internal_zzbr_.zzagn());
    }

    public final void zzr(Status status) {
        this.zzfic.zzt(status);
    }
}
