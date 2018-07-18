package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import java.util.Collections;

public final class zzbc implements zzbk {
    private final zzbl zzflh;

    public zzbc(zzbl com_google_android_gms_common_api_internal_zzbl) {
        this.zzflh = com_google_android_gms_common_api_internal_zzbl;
    }

    public final void begin() {
        for (zze disconnect : this.zzflh.zzfmn.values()) {
            disconnect.disconnect();
        }
        this.zzflh.zzfju.zzfmo = Collections.emptySet();
    }

    public final void connect() {
        this.zzflh.zzahl();
    }

    public final boolean disconnect() {
        return true;
    }

    public final void onConnected(Bundle bundle) {
    }

    public final void onConnectionSuspended(int i) {
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        this.zzflh.zzfju.zzfks.add(t);
        return t;
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
    }
}
