package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;

final class zzasn extends zzasf {
    private zzn<Status> zzebk;

    zzasn(zzn<Status> com_google_android_gms_common_api_internal_zzn_com_google_android_gms_common_api_Status) {
        this.zzebk = com_google_android_gms_common_api_internal_zzn_com_google_android_gms_common_api_Status;
    }

    public final void zze(Status status) {
        this.zzebk.setResult(status);
    }
}
