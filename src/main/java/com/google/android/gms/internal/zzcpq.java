package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;

final class zzcpq extends zza<zzcqc, zzcpt> {
    zzcpq() {
    }

    public final /* synthetic */ zze zza(Context context, Looper looper, zzq com_google_android_gms_common_internal_zzq, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        zzcpt com_google_android_gms_internal_zzcpt = (zzcpt) obj;
        return new zzcqc(context, looper, true, com_google_android_gms_common_internal_zzq, com_google_android_gms_internal_zzcpt == null ? zzcpt.zzjno : com_google_android_gms_internal_zzcpt, connectionCallbacks, onConnectionFailedListener);
    }
}
