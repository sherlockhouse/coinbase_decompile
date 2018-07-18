package com.google.android.gms.common.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

final class zzac implements zzg {
    private /* synthetic */ OnConnectionFailedListener zzful;

    zzac(OnConnectionFailedListener onConnectionFailedListener) {
        this.zzful = onConnectionFailedListener;
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        this.zzful.onConnectionFailed(connectionResult);
    }
}
