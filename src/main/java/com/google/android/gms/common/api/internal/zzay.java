package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.zzcpx;
import com.google.android.gms.internal.zzcqf;
import java.lang.ref.WeakReference;

final class zzay extends zzcpx {
    private final WeakReference<zzar> zzfly;

    zzay(zzar com_google_android_gms_common_api_internal_zzar) {
        this.zzfly = new WeakReference(com_google_android_gms_common_api_internal_zzar);
    }

    public final void zzb(zzcqf com_google_android_gms_internal_zzcqf) {
        zzar com_google_android_gms_common_api_internal_zzar = (zzar) this.zzfly.get();
        if (com_google_android_gms_common_api_internal_zzar != null) {
            com_google_android_gms_common_api_internal_zzar.zzflh.zza(new zzaz(this, com_google_android_gms_common_api_internal_zzar, com_google_android_gms_common_api_internal_zzar, com_google_android_gms_internal_zzcqf));
        }
    }
}
