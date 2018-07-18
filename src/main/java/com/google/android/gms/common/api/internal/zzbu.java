package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;

final class zzbu implements Runnable {
    private /* synthetic */ zzbr zzfod;
    private /* synthetic */ ConnectionResult zzfoe;

    zzbu(zzbr com_google_android_gms_common_api_internal_zzbr, ConnectionResult connectionResult) {
        this.zzfod = com_google_android_gms_common_api_internal_zzbr;
        this.zzfoe = connectionResult;
    }

    public final void run() {
        this.zzfod.onConnectionFailed(this.zzfoe);
    }
}
