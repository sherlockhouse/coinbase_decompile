package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;

public final class zzm implements zzj {
    private /* synthetic */ zzd zzftl;

    public zzm(zzd com_google_android_gms_common_internal_zzd) {
        this.zzftl = com_google_android_gms_common_internal_zzd;
    }

    public final void zzf(ConnectionResult connectionResult) {
        if (connectionResult.isSuccess()) {
            this.zzftl.zza(null, this.zzftl.zzajl());
        } else if (this.zzftl.zzftd != null) {
            this.zzftl.zzftd.onConnectionFailed(connectionResult);
        }
    }
}
