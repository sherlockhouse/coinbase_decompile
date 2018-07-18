package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbp;

public final class zzw implements ConnectionCallbacks, OnConnectionFailedListener {
    public final Api<?> zzfdg;
    private final boolean zzfjs;
    private zzx zzfjt;

    public zzw(Api<?> api, boolean z) {
        this.zzfdg = api;
        this.zzfjs = z;
    }

    private final void zzagh() {
        zzbp.zzb(this.zzfjt, (Object) "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }

    public final void onConnected(Bundle bundle) {
        zzagh();
        this.zzfjt.onConnected(bundle);
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        zzagh();
        this.zzfjt.zza(connectionResult, this.zzfdg, this.zzfjs);
    }

    public final void onConnectionSuspended(int i) {
        zzagh();
        this.zzfjt.onConnectionSuspended(i);
    }

    public final void zza(zzx com_google_android_gms_common_api_internal_zzx) {
        this.zzfjt = com_google_android_gms_common_api_internal_zzx;
    }
}
