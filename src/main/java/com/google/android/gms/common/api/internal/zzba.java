package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

final class zzba implements ConnectionCallbacks, OnConnectionFailedListener {
    private /* synthetic */ zzar zzflx;

    private zzba(zzar com_google_android_gms_common_api_internal_zzar) {
        this.zzflx = com_google_android_gms_common_api_internal_zzar;
    }

    public final void onConnected(Bundle bundle) {
        this.zzflx.zzflp.zza(new zzay(this.zzflx));
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzflx.zzfke.lock();
        try {
            if (this.zzflx.zzd(connectionResult)) {
                this.zzflx.zzahd();
                this.zzflx.zzahb();
            } else {
                this.zzflx.zze(connectionResult);
            }
            this.zzflx.zzfke.unlock();
        } catch (Throwable th) {
            this.zzflx.zzfke.unlock();
        }
    }

    public final void onConnectionSuspended(int i) {
    }
}
