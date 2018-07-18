package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Status;

final class zzai implements zza {
    private /* synthetic */ zzs zzflc;
    private /* synthetic */ zzah zzfld;

    zzai(zzah com_google_android_gms_common_api_internal_zzah, zzs com_google_android_gms_common_api_internal_zzs) {
        this.zzfld = com_google_android_gms_common_api_internal_zzah;
        this.zzflc = com_google_android_gms_common_api_internal_zzs;
    }

    public final void zzq(Status status) {
        this.zzfld.zzfla.remove(this.zzflc);
    }
}
