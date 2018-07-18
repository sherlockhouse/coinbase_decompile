package com.google.android.gms.common.api.internal;

import java.lang.ref.WeakReference;

final class zzbj extends zzbz {
    private WeakReference<zzbd> zzfmz;

    zzbj(zzbd com_google_android_gms_common_api_internal_zzbd) {
        this.zzfmz = new WeakReference(com_google_android_gms_common_api_internal_zzbd);
    }

    public final void zzage() {
        zzbd com_google_android_gms_common_api_internal_zzbd = (zzbd) this.zzfmz.get();
        if (com_google_android_gms_common_api_internal_zzbd != null) {
            com_google_android_gms_common_api_internal_zzbd.resume();
        }
    }
}
