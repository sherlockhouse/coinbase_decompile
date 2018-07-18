package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;

final class zzab implements zzf {
    private /* synthetic */ ConnectionCallbacks zzfuk;

    zzab(ConnectionCallbacks connectionCallbacks) {
        this.zzfuk = connectionCallbacks;
    }

    public final void onConnected(Bundle bundle) {
        this.zzfuk.onConnected(bundle);
    }

    public final void onConnectionSuspended(int i) {
        this.zzfuk.onConnectionSuspended(i);
    }
}
