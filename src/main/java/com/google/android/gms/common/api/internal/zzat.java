package com.google.android.gms.common.api.internal;

import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.internal.zzj;
import java.lang.ref.WeakReference;

final class zzat implements zzj {
    private final Api<?> zzfdg;
    private final boolean zzfjs;
    private final WeakReference<zzar> zzfly;

    public zzat(zzar com_google_android_gms_common_api_internal_zzar, Api<?> api, boolean z) {
        this.zzfly = new WeakReference(com_google_android_gms_common_api_internal_zzar);
        this.zzfdg = api;
        this.zzfjs = z;
    }

    public final void zzf(ConnectionResult connectionResult) {
        boolean z = false;
        zzar com_google_android_gms_common_api_internal_zzar = (zzar) this.zzfly.get();
        if (com_google_android_gms_common_api_internal_zzar != null) {
            if (Looper.myLooper() == com_google_android_gms_common_api_internal_zzar.zzflh.zzfju.getLooper()) {
                z = true;
            }
            zzbp.zza(z, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
            com_google_android_gms_common_api_internal_zzar.zzfke.lock();
            try {
                if (com_google_android_gms_common_api_internal_zzar.zzbr(0)) {
                    if (!connectionResult.isSuccess()) {
                        com_google_android_gms_common_api_internal_zzar.zzb(connectionResult, this.zzfdg, this.zzfjs);
                    }
                    if (com_google_android_gms_common_api_internal_zzar.zzaha()) {
                        com_google_android_gms_common_api_internal_zzar.zzahb();
                    }
                    com_google_android_gms_common_api_internal_zzar.zzfke.unlock();
                }
            } finally {
                com_google_android_gms_common_api_internal_zzar.zzfke.unlock();
            }
        }
    }
}
