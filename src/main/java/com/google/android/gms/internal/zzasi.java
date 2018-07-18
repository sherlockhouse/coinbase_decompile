package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;
import com.google.android.gms.auth.api.credentials.CredentialRequestResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

final class zzasi extends zzaso<CredentialRequestResult> {
    private /* synthetic */ CredentialRequest zzebh;

    zzasi(zzash com_google_android_gms_internal_zzash, GoogleApiClient googleApiClient, CredentialRequest credentialRequest) {
        this.zzebh = credentialRequest;
        super(googleApiClient);
    }

    protected final void zza(Context context, zzasu com_google_android_gms_internal_zzasu) throws RemoteException {
        com_google_android_gms_internal_zzasu.zza(new zzasj(this), this.zzebh);
    }

    protected final /* synthetic */ Result zzb(Status status) {
        return zzasg.zzf(status);
    }
}
