package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.internal.zzd;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzq;
import java.util.Collections;
import java.util.List;

final class zzc extends zza<zzd, GoogleSignInOptions> {
    zzc() {
    }

    public final /* synthetic */ zze zza(Context context, Looper looper, zzq com_google_android_gms_common_internal_zzq, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        return new zzd(context, looper, com_google_android_gms_common_internal_zzq, (GoogleSignInOptions) obj, connectionCallbacks, onConnectionFailedListener);
    }

    public final /* synthetic */ List zzn(Object obj) {
        GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
        return googleSignInOptions == null ? Collections.emptyList() : googleSignInOptions.zzaag();
    }
}
