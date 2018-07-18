package com.google.android.gms.internal;

import android.content.Context;
import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;

abstract class zzaso<R extends Result> extends zzm<R, zzasp> {
    zzaso(GoogleApiClient googleApiClient) {
        super(Auth.CREDENTIALS_API, googleApiClient);
    }

    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }

    protected abstract void zza(Context context, zzasu com_google_android_gms_internal_zzasu) throws DeadObjectException, RemoteException;

    protected final /* synthetic */ void zza(zzb com_google_android_gms_common_api_Api_zzb) throws RemoteException {
        zzasp com_google_android_gms_internal_zzasp = (zzasp) com_google_android_gms_common_api_Api_zzb;
        zza(com_google_android_gms_internal_zzasp.getContext(), (zzasu) com_google_android_gms_internal_zzasp.zzajj());
    }
}
