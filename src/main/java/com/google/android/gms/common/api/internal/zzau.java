package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.internal.zzj;
import java.util.Map;

final class zzau extends zzbb {
    final /* synthetic */ zzar zzflx;
    private final Map<zze, zzat> zzflz;

    public zzau(zzar com_google_android_gms_common_api_internal_zzar, Map<zze, zzat> map) {
        this.zzflx = com_google_android_gms_common_api_internal_zzar;
        super(com_google_android_gms_common_api_internal_zzar);
        this.zzflz = map;
    }

    public final void zzagz() {
        int i;
        int i2 = 1;
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        for (zze com_google_android_gms_common_api_Api_zze : this.zzflz.keySet()) {
            if (!com_google_android_gms_common_api_Api_zze.zzaff()) {
                i = 0;
                i4 = i5;
            } else if (!((zzat) this.zzflz.get(com_google_android_gms_common_api_Api_zze)).zzfjs) {
                i = 1;
                break;
            } else {
                i = i4;
                i4 = 1;
            }
            i5 = i4;
            i4 = i;
        }
        i2 = i5;
        i = 0;
        if (i2 != 0) {
            i3 = this.zzflx.zzfko.isGooglePlayServicesAvailable(this.zzflx.mContext);
        }
        if (i3 == 0 || (r0 == 0 && i4 == 0)) {
            if (this.zzflx.zzflr) {
                this.zzflx.zzflp.connect();
            }
            for (zze com_google_android_gms_common_api_Api_zze2 : this.zzflz.keySet()) {
                zzj com_google_android_gms_common_internal_zzj = (zzj) this.zzflz.get(com_google_android_gms_common_api_Api_zze2);
                if (!com_google_android_gms_common_api_Api_zze2.zzaff() || i3 == 0) {
                    com_google_android_gms_common_api_Api_zze2.zza(com_google_android_gms_common_internal_zzj);
                } else {
                    this.zzflx.zzflh.zza(new zzaw(this, this.zzflx, com_google_android_gms_common_internal_zzj));
                }
            }
            return;
        }
        this.zzflx.zzflh.zza(new zzav(this, this.zzflx, new ConnectionResult(i3, null)));
    }
}
