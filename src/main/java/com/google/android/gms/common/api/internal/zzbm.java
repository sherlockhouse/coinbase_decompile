package com.google.android.gms.common.api.internal;

abstract class zzbm {
    private final zzbk zzfnh;

    protected zzbm(zzbk com_google_android_gms_common_api_internal_zzbk) {
        this.zzfnh = com_google_android_gms_common_api_internal_zzbk;
    }

    protected abstract void zzagz();

    public final void zzc(zzbl com_google_android_gms_common_api_internal_zzbl) {
        com_google_android_gms_common_api_internal_zzbl.zzfke.lock();
        try {
            if (com_google_android_gms_common_api_internal_zzbl.zzfnd == this.zzfnh) {
                zzagz();
                com_google_android_gms_common_api_internal_zzbl.zzfke.unlock();
            }
        } finally {
            com_google_android_gms_common_api_internal_zzbl.zzfke.unlock();
        }
    }
}
