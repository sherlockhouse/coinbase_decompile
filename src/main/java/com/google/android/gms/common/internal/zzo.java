package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;

public final class zzo extends zze {
    private /* synthetic */ zzd zzftl;

    public zzo(zzd com_google_android_gms_common_internal_zzd, int i, Bundle bundle) {
        this.zzftl = com_google_android_gms_common_internal_zzd;
        super(com_google_android_gms_common_internal_zzd, i, null);
    }

    protected final boolean zzajn() {
        this.zzftl.zzfsx.zzf(ConnectionResult.zzfff);
        return true;
    }

    protected final void zzj(ConnectionResult connectionResult) {
        this.zzftl.zzfsx.zzf(connectionResult);
        this.zzftl.onConnectionFailed(connectionResult);
    }
}
