package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;

public final class zzc implements GoogleSignInApi {
    public final PendingResult<Status> revokeAccess(GoogleApiClient googleApiClient) {
        return zze.zzb(googleApiClient, googleApiClient.getContext());
    }
}
