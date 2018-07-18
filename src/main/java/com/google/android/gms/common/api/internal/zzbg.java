package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;

final class zzbg implements OnConnectionFailedListener {
    private /* synthetic */ zzda zzfmx;

    zzbg(zzbd com_google_android_gms_common_api_internal_zzbd, zzda com_google_android_gms_common_api_internal_zzda) {
        this.zzfmx = com_google_android_gms_common_api_internal_zzda;
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzfmx.setResult(new Status(8));
    }
}
