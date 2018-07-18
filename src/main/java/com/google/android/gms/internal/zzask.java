package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzask extends zzaso<Status> {
    private /* synthetic */ Credential zzebj;

    zzask(zzash com_google_android_gms_internal_zzash, GoogleApiClient googleApiClient, Credential credential) {
        this.zzebj = credential;
        super(googleApiClient);
    }

    protected final void zza(Context context, zzasu com_google_android_gms_internal_zzasu) throws RemoteException {
        com_google_android_gms_internal_zzasu.zza(new zzasn(this), new zzasw(this.zzebj));
    }

    protected final /* synthetic */ Result zzb(Status status) {
        return status;
    }
}
