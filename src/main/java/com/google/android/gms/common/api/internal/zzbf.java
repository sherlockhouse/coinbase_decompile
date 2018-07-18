package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import java.util.concurrent.atomic.AtomicReference;

final class zzbf implements ConnectionCallbacks {
    private /* synthetic */ zzbd zzfmv;
    private /* synthetic */ AtomicReference zzfmw;
    private /* synthetic */ zzda zzfmx;

    zzbf(zzbd com_google_android_gms_common_api_internal_zzbd, AtomicReference atomicReference, zzda com_google_android_gms_common_api_internal_zzda) {
        this.zzfmv = com_google_android_gms_common_api_internal_zzbd;
        this.zzfmw = atomicReference;
        this.zzfmx = com_google_android_gms_common_api_internal_zzda;
    }

    public final void onConnected(Bundle bundle) {
        this.zzfmv.zza((GoogleApiClient) this.zzfmw.get(), this.zzfmx, true);
    }

    public final void onConnectionSuspended(int i) {
    }
}
